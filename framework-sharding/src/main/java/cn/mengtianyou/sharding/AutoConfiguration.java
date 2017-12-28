package cn.mengtianyou.sharding;

import cn.mengtianyou.common.constants.ApplicationConstant;
import cn.mengtianyou.common.helper.ApplicationContextHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;

/**
 * @author liups
 * @create 2017/12/15
 */
@Configuration
@ComponentScan
public class AutoConfiguration {

}
