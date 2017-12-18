package com.jiguang.demo.handler;

import com.google.common.base.Joiner;
import com.jiguang.demo.ResponseResult;
import com.jiguang.demo.constants.ApplicationConstant;
import com.jiguang.demo.constants.CustomHttpStatus;
import com.jiguang.demo.exceptions.BaseException;
import com.jiguang.demo.messages.ErrorMessage;
import com.jiguang.demo.utils.JsonUtils;
import com.jiguang.demo.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author liups
 * @create 2017/12/14
 */
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);


    @Autowired
    ApplicationConstant applicationConstant;

//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
//                                                         org.springframework.http.HttpStatus status, WebRequest request) {
//        //无效路径
//        CustomHttpStatus badRequest = CustomHttpStatus.BAD_REQUEST;
//        List<ObjectError> allErrors = ex.getAllErrors();
//        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, badRequest.getMessage());
//        return createResponseEntity(badRequest, request.getDescription(false), errorMessage,ex);
//
//    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, org.springframework.http.HttpStatus status,
//                                                                  WebRequest request) {
//        //无效参数
//        CustomHttpStatus errorCode = CustomHttpStatus.BAD_REQUEST;
//        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
//        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, errorCode.getMessage());
//        return createResponseEntity(errorCode, request.getDescription(false), errorMessage,ex);
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, org.springframework.http.HttpStatus status,
                                                             WebRequest request) {
        //spring mvc 异常
        logger.error("spring mvc 异常: " + ex.getMessage(), ex);
        CustomHttpStatus errorCode = CustomHttpStatus.fromHttpStatus(status.value());
        return createResponseEntity(errorCode, request.getDescription(false), errorCode.getMessage(),ex);
    }

//    @ExceptionHandler(value = HystrixTimeoutException.class)
//    public ResponseEntity<Object> handleHystrixTimeoutException(HttpServletRequest request, HystrixTimeoutException e) {
//        //服务超时
//        logger.error(e.getMessage(), e);
//        CustomHttpStatus errorCode = CustomHttpStatus.INTERNAL_ERROR;
//        return createResponseEntity(errorCode, request.getRequestURI(), e.getMessage(),e);
//    }
//
//    @ExceptionHandler(value = HystrixRuntimeException.class)
//    public ResponseEntity<Object> handleHystrixRuntimeException(HttpServletRequest request, HystrixRuntimeException e) {
//        //Hystrix Command 运行报错
//        logger.error("Hystrix Command 运行报错: " + e.getMessage(), e);
//        CustomHttpStatus errorCode = CustomHttpStatus.INTERNAL_ERROR;
//        return createResponseEntity(errorCode, request.getRequestURI(), e.getMessage(),e);
//    }



    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleAppBusinessException(HttpServletRequest request, BaseException e) {

        //业务异常
        return createResponseEntity(e.getCode(), e.getSysCode(),e.getHttpStatus(), request.getRequestURI(), e.getMessage(),e);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) {
        Throwable resultEx = ExceptionUtils.getCause(e);
        if(resultEx instanceof BaseException){
            return handleAppBusinessException(request, (BaseException) resultEx);
        }
//        else if(resultEx instanceof HystrixTimeoutException){
//            return handleHystrixTimeoutException(request, (HystrixTimeoutException) resultEx);
//        }else if(resultEx instanceof HystrixRuntimeException){
//            return handleHystrixRuntimeException(request, (HystrixRuntimeException) resultEx);
//        }
        //服务器未知错误
        logger.error("服务器发生错误: " + resultEx.getMessage(), resultEx);
        CustomHttpStatus errorCode = CustomHttpStatus.INTERNAL_ERROR;
        return createResponseEntity(errorCode, request.getRequestURI(), errorCode.getMessage(),resultEx);

    }

    private ResponseEntity<Object> createResponseEntity(CustomHttpStatus errorCode, String requestUri, String message, Throwable throwable) {
        //TODO 获取基本的code
        String sysCode = "default";
        ErrorMessage error= buildErrorMessage(applicationConstant,requestUri,errorCode.getCode(),sysCode,message,throwable,null);
        String json = JsonUtils.object2Json(ResponseResult.buildError(error));
        return ResponseEntity.status(org.springframework.http.HttpStatus.valueOf(errorCode.getStatus())).body(json);
    }

    private ResponseEntity<Object> createResponseEntity(String code, String sysCode,int httpStatus, String requestUri,String message, BaseException ex) {
        Stack<ErrorMessage.ExceptionDetail> exceptionStack = ex.getExceptionStack();
        ErrorMessage error= buildErrorMessage(applicationConstant,requestUri,code,sysCode,message,ex,exceptionStack);
        String json = JsonUtils.object2Json(ResponseResult.buildError(error));
        return ResponseEntity.status(org.springframework.http.HttpStatus.valueOf(httpStatus)).body(json);
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

    public static ErrorMessage buildErrorMessage(ApplicationConstant applicationConstant,String requestUri,String code,String sysCode,
                                                 String message,Throwable throwable,Stack<ErrorMessage.ExceptionDetail> exceptionStack){
        if(applicationConstant.isOutputExceptionStack()){
            if(exceptionStack == null){
                exceptionStack = new Stack();
            }
            ErrorMessage.ExceptionDetail  exceptionDetail;
            //TODO 获取机器ID？
            String systemId = applicationConstant.getApplicationName();
            String stackTrace = null;
            //只有刚抛出的微服务才记录异常详细堆栈
            if(applicationConstant.isOutputExceptionStackTrace() && exceptionStack.empty()){
                stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(throwable);
            }
            exceptionDetail = new ErrorMessage.ExceptionDetail(systemId,requestUri,stackTrace);
            if(exceptionDetail != null){
                exceptionStack.push(exceptionDetail);
            }
        }
        return new ErrorMessage(code, sysCode, exceptionStack, message);
    }

}