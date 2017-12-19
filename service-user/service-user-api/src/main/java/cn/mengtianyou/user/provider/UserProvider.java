package cn.mengtianyou.user.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liups
 * @create 2017/12/7
 */
@RequestMapping("/user")
public interface UserProvider {

    @RequestMapping("/insert_user")
    String insertUser(Long id,String name,String password);

    @RequestMapping("/ds_route/find_user")
    String getUserName(HttpServletRequest request, Long userId, @RequestParam(required = false) String ds);

    @RequestMapping("/login")
    String login(HttpServletRequest request, String name);


    @RequestMapping("/ds_route/insert_order")
    String insertOrder(Long userId,String orderName);


    @RequestMapping("/ds_route/find_order")
    String findOrder(Long orderId,Long userId);


}
