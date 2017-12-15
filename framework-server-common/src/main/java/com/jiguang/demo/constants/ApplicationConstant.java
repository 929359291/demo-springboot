package com.jiguang.demo.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author liups
 * @create 2017/12/14
 */
public class ApplicationConstant {

    @Value("${spring.application.name}")
    public String applicationName;

}
