package cn.mengtianyou.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
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


}