package com.jiguang.demo.exceptions;

import com.jiguang.demo.constants.CustomHttpStatus;

/**
 * 数据库中表现为U：User 代表正常提示用户信息
 * hystrix会忽略这个异常, 不会触发熔断
 * @author liups
 * @create 2017/12/14
 */
public class AppUserException extends BaseException {
    public AppUserException(String message) {
        super(message);
    }

    public AppUserException(String message, String sysCode, String code) {
        super(message, sysCode, code, CustomHttpStatus.BAD_REQUEST.getStatus());
    }

    public AppUserException(String message, String sysCode, String code, int httpStatus) {
        super(message, sysCode, code, httpStatus);
    }

}
