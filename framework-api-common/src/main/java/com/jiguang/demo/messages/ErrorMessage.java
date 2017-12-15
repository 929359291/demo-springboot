package com.jiguang.demo.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liubin on 15-8-3.
 */
public class ErrorMessage {

    private String code;
    private String sysCode;
    private String message;
    private String requestUri;

    @JsonCreator
    public ErrorMessage(@JsonProperty("code") String code,
                        @JsonProperty("sysCode") String sysCode,
                        @JsonProperty("requestUri") String requestUri,
                        @JsonProperty(value = "message", defaultValue = "") String message) {
        this.code = code;
        this.sysCode = sysCode;
        this.requestUri = requestUri;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getSysCode() {
        return sysCode;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestUri() {
        return requestUri;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", requestUri='" + requestUri + '\'' +
                '}';
    }
}
