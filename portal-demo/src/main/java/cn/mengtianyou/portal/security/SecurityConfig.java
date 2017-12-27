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
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.session.SessionManagementFilter;

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
                .sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(portalAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/","/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .addFilterAfter(new SetDatabaseFilter(), SessionManagementFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        auth.authenticationProvider(provider);
        auth.parentAuthenticationManager(authenticationManager);
    }

}
