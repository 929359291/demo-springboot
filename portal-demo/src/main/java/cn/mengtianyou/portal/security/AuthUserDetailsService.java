package cn.mengtianyou.portal.security;

import cn.mengtianyou.portal.comsumer.UserFeignConsumer;
import cn.mengtianyou.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tandy on 2016/6/7.
 */
@Service("userDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    UserFeignConsumer userFeignConsumer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 根据登录用户名来查找用户,并返回用户信息给框架较验
        User login = userFeignConsumer.login(username);
        if(login == null){
            throw new UsernameNotFoundException("用户不存在");
        }else{
            return new PortalUserDetails(login.getId(),login.getName(),login.getPassword(),roles("USER"));
        }
    }

    private List<GrantedAuthority> roles(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
                roles.length);
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority( role));
        }
        return authorities;
    }
}
