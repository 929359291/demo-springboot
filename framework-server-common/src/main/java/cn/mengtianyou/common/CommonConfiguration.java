package cn.mengtianyou.common;

import cn.mengtianyou.common.constants.ApplicationConstant;
import cn.mengtianyou.common.helper.ApplicationContextHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liups
 * @create 2017/12/15
 */
@Configuration
@ComponentScan
public class CommonConfiguration {

    @Bean
    public ApplicationConstant applicationConstant() {
        return new ApplicationConstant();
    }

    @Bean
    public ApplicationContextHelper applicationContextHolder() {
        return ApplicationContextHelper.getInstance();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
