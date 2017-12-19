package com.jiguang.demo.exceptions;

import com.jiguang.demo.constants.CustomHttpStatus;
import com.jiguang.demo.messages.AppMessageServiceType;

/**
 * 数据库中表现为E：Error 代表错误异常，一般为程序级别
 * hystrix会忽略这个异常, 不会触发熔断
 * @author liups
 * @create 2017/12/14
 */
public class AppErrorException extends BaseException {

    public AppErrorException(String sysCode, String code, String msgTxt, String message) {
        super(sysCode, code, msgTxt, AppMessageServiceType.E.name(), CustomHttpStatus.INTERNAL_ERROR.getStatus(), message);
    }

    public AppErrorException(String sysCode, String code, String msgTxt, String message, Throwable cause) {
        super(sysCode, code, msgTxt, AppMessageServiceType.E.name(), CustomHttpStatus.INTERNAL_ERROR.getStatus(), message, cause);
    }
}
