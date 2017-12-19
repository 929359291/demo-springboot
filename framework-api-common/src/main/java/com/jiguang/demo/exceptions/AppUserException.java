package com.jiguang.demo.exceptions;

import com.jiguang.demo.constants.CustomHttpStatus;
import com.jiguang.demo.messages.AppMessageServiceType;

/**
 * 数据库中表现为U：User 代表正常提示用户信息
 * hystrix会忽略这个异常, 不会触发熔断
 * @author liups
 * @create 2017/12/14
 */
public class AppUserException extends BaseException {

    public AppUserException(String sysCode, String code, String msgTxt, String message) {
        super(sysCode, code, msgTxt, AppMessageServiceType.U.name(), CustomHttpStatus.BAD_REQUEST.getStatus(), message);
    }

    public AppUserException(String sysCode, String code, String msgTxt, String message, Throwable cause) {
        super(sysCode, code, msgTxt, AppMessageServiceType.U.name(), CustomHttpStatus.BAD_REQUEST.getStatus(), message, cause);
    }
}
