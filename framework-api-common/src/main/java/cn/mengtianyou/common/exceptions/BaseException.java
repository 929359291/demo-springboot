package cn.mengtianyou.common.exceptions;

import cn.mengtianyou.common.messages.ErrorMessage;
import cn.mengtianyou.common.constants.CustomHttpStatus;

import java.util.Stack;

/**
 * 平台异常基类，所有平台异常都要继承这个类
 * @author liups
 * @create 2017/12/12
 */
public class BaseException extends RuntimeException {
    private String code;
    private String sysCode;
    private String msgTxt;
    private String serviceType;
    private int httpStatus;

    /**
     * 异常堆栈（可配置是否传递）
     */
    private Stack<ErrorMessage.ExceptionDetail> exceptionStack;

    public BaseException(String sysCode, String code,String msgTxt,String serviceType,String message) {
        this(sysCode, code, msgTxt, serviceType, CustomHttpStatus.BAD_REQUEST.getStatus(),message);
    }

    protected BaseException( String sysCode, String code, String msgTxt,String serviceType,int httpStatus,String message) {
        super(message);
        this.sysCode = sysCode;
        this.code = code;
        this.msgTxt = msgTxt;
        this.serviceType = serviceType;
        this.httpStatus = httpStatus;
    }

    protected BaseException(String sysCode, String code, String msgTxt,String serviceType,int httpStatus,String message, Throwable cause) {
        super(message,cause);
        this.sysCode = sysCode;
        this.code = code;
        this.msgTxt = msgTxt;
        this.serviceType = serviceType;
        this.httpStatus = httpStatus;
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

    public String getMsgTxt() {
        return msgTxt;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Stack<ErrorMessage.ExceptionDetail> getExceptionStack() {
        return exceptionStack;
    }

    public void setExceptionStack(Stack<ErrorMessage.ExceptionDetail> exceptionStack) {
        this.exceptionStack = exceptionStack;
    }
}
