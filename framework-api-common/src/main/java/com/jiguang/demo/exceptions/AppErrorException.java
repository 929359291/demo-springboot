package com.jiguang.demo.exceptions;

import com.jiguang.demo.constants.CommonHttpStatus;

/**
 * 数据库中表现为E：Error 代表错误异常，一般为程序级别
 * hystrix会忽略这个异常, 不会触发熔断
 * @author liups
 * @create 2017/12/14
 */
public class AppErrorException extends BaseException {
    public AppErrorException(String message) {
        super(message);
    }

    public AppErrorException(String message, String sysCode, String code) {
        super(message, sysCode, code, CommonHttpStatus.INTERNAL_ERROR.getStatus());
    }

    public AppErrorException(String message, String sysCode, String code, int httpStatus) {
        super(message, sysCode, code, httpStatus);
    }
}
