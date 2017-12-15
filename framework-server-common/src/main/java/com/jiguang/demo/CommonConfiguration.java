package com.jiguang.demo;

import com.jiguang.demo.constants.ApplicationConstant;
import com.jiguang.demo.helper.ApplicationContextHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liups
 * @create 2017/12/15
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public ApplicationConstant applicationConstant() {
        return new ApplicationConstant();
    }

    @Bean
    public ApplicationContextHelper applicationContextHolder() {
        return ApplicationContextHelper.getInstance();
    }
}
