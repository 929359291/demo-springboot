package com.jiguang.demo.exceptions;

/**
 * 平台异常基类，所有平台异常都要继承这个类
 * @author liups
 * @create 2017/12/12
 */
public class BaseException extends RuntimeException {

    private String sysCode;
    private String code;
    private int httpStatus;

    public BaseException(String message, String sysCode, String code, int httpStatus) {
        super(message);
        this.sysCode = sysCode;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getSysCode() {
        return sysCode;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
