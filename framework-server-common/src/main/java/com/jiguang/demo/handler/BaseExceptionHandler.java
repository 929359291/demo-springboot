package com.jiguang.demo.handler;

import com.google.common.base.Joiner;
import com.jiguang.demo.constants.CommonHttpStatus;
import com.jiguang.demo.messages.ErrorMessage;
import com.jiguang.demo.constants.HttpStatusCode;
import com.jiguang.demo.exceptions.BaseException;
import com.jiguang.demo.helper.AppMessageHelper;
import com.jiguang.demo.utils.JsonUtils;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author liups
 * @create 2017/12/14
 */
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {

        CommonHttpStatus badRequest = CommonHttpStatus.BAD_REQUEST;
        List<ObjectError> allErrors = ex.getAllErrors();
        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, badRequest.getMessage());
        return createResponseEntity(badRequest, request.getDescription(false), errorMessage);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        CommonHttpStatus errorCode = CommonHttpStatus.BAD_REQUEST;
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, errorCode.getMessage());
        return createResponseEntity(errorCode, request.getDescription(false), errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {

        logger.error("spring mvc 异常: " + ex.getMessage(), ex);
        CommonHttpStatus errorCode = CommonHttpStatus.fromHttpStatus(status.value());
        return createResponseEntity(errorCode, request.getDescription(false), errorCode.getMessage());
    }

    @ExceptionHandler(value = HystrixTimeoutException.class)
    public ResponseEntity<Object> handleHystrixTimeoutException(HttpServletRequest request, HystrixTimeoutException e) {

        logger.error(e.getMessage(), e);
        CommonHttpStatus errorCode = CommonHttpStatus.GATEWAY_TIMEOUT;
        return createResponseEntity(errorCode, request.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler(value = HystrixRuntimeException.class)
    public ResponseEntity<Object> handleHystrixRuntimeException(HttpServletRequest request, HystrixRuntimeException e) {

        logger.error("Hystrix Command 运行报错: " + e.getMessage(), e);
        CommonHttpStatus errorCode = CommonHttpStatus.INTERNAL_ERROR;
        return createResponseEntity(errorCode, request.getRequestURI(), e.getMessage());
    }



    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleAppBusinessException(HttpServletRequest request, BaseException e) {

        //业务异常
        return createResponseEntity(e.getCode(), e.getSysCode(),e.getHttpStatus(), request.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) {

        logger.error("服务器发生错误: " + e.getMessage(), e);
        CommonHttpStatus errorCode = CommonHttpStatus.INTERNAL_ERROR;
        return createResponseEntity(errorCode, request.getRequestURI(), errorCode.getMessage());

    }

    private ResponseEntity<Object> createResponseEntity(HttpStatusCode errorCode, String requestUri, String message) {
        //TODO 获取基本的code
        String sysCode = "default";
        return createResponseEntity(errorCode.getCode(), sysCode,errorCode.getStatus(), requestUri, message);
    }

    private ResponseEntity<Object> createResponseEntity(String code, String sysCode,int httpStatus, String requestUri, String message) {
        ErrorMessage error = new ErrorMessage(code, sysCode,requestUri, message);
        String json = JsonUtils.object2Json(error);

        return ResponseEntity.status(HttpStatus.valueOf(httpStatus)).body(json);

    }


    private String extractErrorMessageFromObjectErrors(List<ObjectError> allErrors, String defaultMessage) {
        if(allErrors == null || allErrors.isEmpty()) {
            return defaultMessage;
        } else {
            List<String> errorMessages = allErrors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return Joiner.on(",").skipNulls().join(errorMessages);
        }
    }
}