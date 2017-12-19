package com.jiguang.demo.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author liups
 * @create 2017/12/14
 */
public class ErrorMessage implements Serializable{
    private static final long serialVersionUID = 1L;

    private String code;
    private String sysCode;
    private String msgTxt;
    private String serviceType;
    private String message;
    /**
     * 异常堆栈（可配置是否传递）
     */
    private Stack<ExceptionDetail> exceptionStack;

    @JsonCreator
    public ErrorMessage(@JsonProperty("code") String code,
                        @JsonProperty("sysCode") String sysCode,
                        @JsonProperty("msgTxt") String msgTxt,
                        @JsonProperty("serviceType") String serviceType,
                        @JsonProperty(value = "message", defaultValue = "") String message,
                        @JsonProperty(value = "exceptionStack", defaultValue = "") Stack<ExceptionDetail> exceptionStack){
        this.code = code;
        this.sysCode = sysCode;
        this.msgTxt = msgTxt;
        this.serviceType = serviceType;
        this.message = message;
        this.exceptionStack = exceptionStack;
    }

    public String getCode() {
        return code;
    }

    public String getSysCode() {
        return sysCode;
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getMessage() {
        return message;
    }

    public Stack<ExceptionDetail> getExceptionStack() {
        return exceptionStack;
    }

    public static class ExceptionDetail {
        /**
         * 系统Id
         */
        private String systemId;
        /**
         *  涉及的uri
         */
        private String requestUri;
        /**
         * 详细异常堆栈信息（可配置是否传递）
         */
        private String stackTrace;

        @JsonCreator
        public ExceptionDetail(@JsonProperty("systemId") String systemId,
                               @JsonProperty("requestUri") String requestUri,
                               @JsonProperty(value = "stackTrace", defaultValue = "") String stackTrace) {
            this.systemId = systemId;
            this.requestUri = requestUri;
            this.stackTrace = stackTrace;
        }

        public String getSystemId() {
            return systemId;
        }

        public String getRequestUri() {
            return requestUri;
        }

        public String getStackTrace() {
            return stackTrace;
        }

    }

}
