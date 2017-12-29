package cn.mengtianyou.common;

import cn.mengtianyou.common.constants.ApplicationConstant;
import cn.mengtianyou.common.helper.ApplicationContextHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;

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

    /**
     * 密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    /**
     * Sha512加密器
     * @return
     */
    @Bean
    public org.springframework.security.authentication.encoding.PasswordEncoder ShaPasswordEncode() {
        ShaPasswordEncoder encode =  new org.springframework.security.authentication.encoding.ShaPasswordEncoder(512);
        encode.setEncodeHashAsBase64(true);
        return encode;
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer initJackson() {
        Jackson2ObjectMapperBuilderCustomizer c = builder -> {
            //自定义Long类型转换 超过12个数字用String格式返回，由于js的number只能表示15个数字
//                builder.serializerByType(Long.class,new CustomLongConverter());
//                builder.serializerByType(Long.TYPE,new CustomLongConverter());
            //不包含为空的字段
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        };
        return c;
    }


    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:org/springframework/security/messages","classpath:org/springframework/security/messages");
        return messageSource;
    }

}
