package com.jiguang.demo;

import com.jiguang.demo.constants.ApplicationConstant;
import com.jiguang.demo.controller.BaseExceptionController;
import com.jiguang.demo.handler.BaseExceptionHandler;
import com.jiguang.demo.helper.ApplicationContextHelper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author liups
 * @create 2017/12/14
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class CommonConfiguration {

    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolvers;

    public CommonConfiguration(ServerProperties serverProperties,
                                     ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }

    @Bean
    public ApplicationConstant applicationConstant() {
        return new ApplicationConstant();
    }

    @Bean
    public ApplicationContextHelper applicationContextHolder() {
        return ApplicationContextHelper.getInstance();
    }


    //error page
    @Bean
    public BaseExceptionController baseExceptionController(ErrorAttributes errorAttributes) {
        return new BaseExceptionController(errorAttributes, this.serverProperties.getError(),
                this.errorViewResolvers);

    }

    //exception handler
    @Bean
    public BaseExceptionHandler baseExceptionHandler() {
        return new BaseExceptionHandler();
    }

}
