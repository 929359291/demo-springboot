package cn.mengtianyou.auth;

import cn.mengtianyou.auth.security.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author liups
 * @create 2017/12/22
 */
@EnableResourceServer
@SpringCloudApplication
@EnableFeignClients
public class AuthorizationServerApplication  {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }



    @Bean
    @Order(-1)
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MyFilter());
        registration.addUrlPatterns("/**");
        return registration;
    }

}