package cn.mengtianyou.common;

import cn.mengtianyou.common.controller.BaseExceptionController;
import cn.mengtianyou.common.handler.BaseExceptionHandler;
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
 * 只有web项目才会配置,此配置不能用@Import的方式引入，放到spring.factories中
 * @author liups
 * @create 2017/12/14
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore({WebMvcAutoConfiguration.class,ErrorMvcAutoConfiguration.class})
@EnableConfigurationProperties(ResourceProperties.class)
public class MvcConfiguration {

    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolvers;

    public MvcConfiguration(ServerProperties serverProperties,
                            ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
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
