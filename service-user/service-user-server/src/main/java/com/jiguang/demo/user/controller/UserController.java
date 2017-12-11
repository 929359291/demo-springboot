package com.jiguang.demo.user.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.jiguang.demo.user.provider.UserProvider;
import com.jiguang.demo.user.entity.Order;
import com.jiguang.demo.user.entity.User;
import com.jiguang.demo.user.consumer.BaseFeignConsumer;
import com.jiguang.demo.user.service.OrderService;
import com.jiguang.demo.user.service.UserService;
import com.jiguang.demo.user.sharding.SelectedDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author liups
 * @create 2017/11/27
 */
@RestController
public class UserController implements UserProvider {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    BaseFeignConsumer baseFeignConsumer;

    @Override
    public String insertUser(Long id, String name, String password){

        SelectedDatabase.newInstance(getDsByUserId(id));
        userService.insert(id,name + id,password);
        return "true";
    }

    @Override
    public String getUserName(HttpServletRequest request, Long userId, @RequestParam(required = false) String ds){
        if(StringUtils.isEmpty(ds)){
            ds = getDsByUserId(userId);
        }
        SelectedDatabase.newInstance(ds);
        request.getSession().setAttribute("userName","testUser");
        User user = userService.findById(userId);
        if(user == null){
            return "null";
        }else{
            return user.getName();
        }
    }

    @Override
    public String login(HttpServletRequest request, String name){
        logger.debug("login:{}" , name);

        SelectedDatabase.newInstance(new ArrayList<String>(){{
            add("druid_demo_2");
            add("druid_demo_1");
        }});
        User user = userService.findByName(name);
        if(user == null){
            return "null";
        }else{
            return user.getName();
        }
    }


    @Override
    public String insertOrder(Long userId, String orderName){
        SelectedDatabase.newInstance(getDsByUserId(userId));
        long orderId = System.currentTimeMillis();
        orderService.insert(orderId,userId,orderName);
        return "true";
    }

    @Override
    public String findOrder(Long orderId, Long userId){
        SelectedDatabase.newInstance(getDsByUserId(userId));
        Order order = orderService.findById(orderId);
        return JSONUtils.toJSONString(order);
    }

    private String getDsByUserId(Long userId){
        String ds = baseFeignConsumer.getDsByUserId(userId);
        return ds;
    }




}
