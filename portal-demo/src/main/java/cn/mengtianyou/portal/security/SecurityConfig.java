package cn.mengtianyou.portal.security;

import cn.mengtianyou.portal.filter.SetDatabaseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PortalAuthenticationSuccessHandler portalAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                .sessionManagement()
//                .sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy()) //打开此注释表示登录鉴权成功后不会另外创建session(另外创建session会占用redis内存)
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(portalAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/","/register","/vc/get")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                //默认的登陆只支持post请求，这里改变登出的路径匹配器，使其支持get请求
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .addFilterBefore(new CheckCodeAuthenticationFilter("/login","/login?error=true","232#as.$3af'@"),UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new SetDatabaseFilter(), SessionManagementFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        auth.authenticationProvider(provider);
        auth.parentAuthenticationManager(authenticationManager);
    }

}
