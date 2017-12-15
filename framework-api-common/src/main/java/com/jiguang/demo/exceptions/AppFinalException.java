package com.jiguang.demo.exceptions;

import com.jiguang.demo.constants.HttpStatus;

/**
 * 数据库中表现为F：Final 代表系统奔溃级别，一般为JVM内存溢出或底层的错误（这种能处理吗？）
 * @author liups
 * @create 2017/12/14
 */
public class AppFinalException extends BaseException {

    public AppFinalException(String message) {
        super(message);
    }

    public AppFinalException(String message, String sysCode, String code) {
        this(message, sysCode, code, HttpStatus.INTERNAL_ERROR.getStatus());
    }

    public AppFinalException(String message, String sysCode, String code, int httpStatus) {
        super(message, sysCode, code, httpStatus);
    }
}
