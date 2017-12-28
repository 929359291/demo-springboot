package cn.mengtianyou.user.controller;

import cn.mengtianyou.common.datasource.SelectedDatasource;
import cn.mengtianyou.common.controller.BaseController;
import cn.mengtianyou.user.consumer.BaseFeignConsumer;
import cn.mengtianyou.user.entity.Order;
import cn.mengtianyou.user.entity.User;
import cn.mengtianyou.user.helper.UserIdHelper;
import cn.mengtianyou.user.provider.UserProvider;
import cn.mengtianyou.user.service.OrderService;
import cn.mengtianyou.user.service.UserService;
import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liups
 * @create 2017/11/27
 */
@RestController
public class UserController extends BaseController implements UserProvider {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    BaseFeignConsumer baseFeignConsumer;

    @Override
    public String insertUser(Long id, String name, String password){
        String ds = UserIdHelper.calDataSourceNameByUserName(id);
        baseFeignConsumer.insertUserId(id, ds);
        SelectedDatasource.newInstance(ds);
        userService.insert(id,name,password);
        return "true";
    }

    @Override
    public String getUserName(Long userId){
        User user = userService.findById(userId);
        if(user == null){
            return "null";
        }else{
            return user.getName();
        }
    }

    @Override
    public User login(String name){
        logger.debug("login:{}" , name);
//        selectedDatasource.newInstance(new ArrayList<String>(){{
//            add("druid_demo_2");
//            add("druid_demo_1");
//        }});
        User user = userService.findByName(name);
        if(user == null){
            return null;
        }else{
            return user;
        }
    }


    @Override
    public String insertOrder(Long userId, String orderName){
        long orderId = System.currentTimeMillis();
        orderService.insert(orderId,userId,orderName);
        return "true";
    }

    @Override
    public String findOrder(Long orderId, Long userId){
        Order order = orderService.findById(orderId);
        return JSONUtils.toJSONString(order);
    }


}
