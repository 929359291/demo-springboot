package com.jiguang.demo.handler;

import com.jiguang.demo.ResponseResult;
import com.jiguang.demo.exceptions.BaseException;
import com.jiguang.demo.messages.ErrorMessage;
import com.jiguang.demo.utils.JsonUtils;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;

/**
 * @author liups
 * @create 2017/12/18
 */
@Component
public class BasExceptionFeignErrorDecoder implements ErrorDecoder {
    Logger logger = LoggerFactory.getLogger(BasExceptionFeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        try {
            Reader reader = response.body().asReader();
            ResponseResult responseResult = JsonUtils.json2Object(reader, ResponseResult.class);
            if(responseResult == null || responseResult.getError() == null){
                return feign.FeignException.errorStatus(methodKey, response);
            }
            ErrorMessage error = responseResult.getError();
            BaseException exception = new BaseException(error.getSysCode(),error.getCode(),error.getMsgTxt(),error.getServiceType(),error.getMessage());
            exception.setExceptionStack(error.getExceptionStack());
            return new HystrixBadRequestException(error.getMessage(),exception);
        } catch (IOException e) {
            logger.error("Feign 错误转换出错：{}",e.getMessage());
            return feign.FeignException.errorStatus(methodKey, response);
        }

    }
}
