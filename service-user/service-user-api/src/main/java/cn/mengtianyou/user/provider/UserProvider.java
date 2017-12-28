package cn.mengtianyou.user.provider;

import cn.mengtianyou.user.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liups
 * @create 2017/12/7
 */
@RequestMapping("/user")
public interface UserProvider {

    @RequestMapping("/insert_user")
    String insertUser(@RequestParam("id") Long id,@RequestParam("name") String name,@RequestParam("password") String password);

    @RequestMapping("/ds_route/find_user")
    String getUserName(@RequestParam("userId") Long userId);

    @RequestMapping("/login")
    User login(@RequestParam("name") String name);


    @RequestMapping("/ds_route/insert_order")
    String insertOrder(@RequestParam("userId") Long userId,@RequestParam("orderName") String orderName);


    @RequestMapping("/ds_route/find_order")
    String findOrder(@RequestParam("orderId") Long orderId,@RequestParam("userId") Long userId);


}
