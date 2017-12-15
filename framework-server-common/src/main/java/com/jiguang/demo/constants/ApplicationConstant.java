package com.jiguang.demo.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author liups
 * @create 2017/12/14
 */
@Component
@ConfigurationProperties(prefix = "jiguang.config")
public class ApplicationConstant {

    @Value("${spring.application.name}")
    public String applicationName;

    /**
     * 描述 : 是否输出异常堆栈（微服务级别）
     */
    @NotNull
    private boolean outputExceptionStack = true;

    /**
     * 描述 : 是否输出异常堆栈详情（即异常详情）
     */
    @NotNull
    private boolean outputExceptionStackTrace = false;


    public String getApplicationName() {
        return applicationName;
    }

    public boolean isOutputExceptionStack() {
        return outputExceptionStack;
    }

    public boolean isOutputExceptionStackTrace() {
        return outputExceptionStackTrace;
    }
}
