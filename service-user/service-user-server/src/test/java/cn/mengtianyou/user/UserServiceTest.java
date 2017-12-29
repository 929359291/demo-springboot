package cn.mengtianyou.user;

import cn.mengtianyou.common.utils.JsonUtils;
import cn.mengtianyou.user.entity.User;
import cn.mengtianyou.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liups
 * @create 2017/12/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindById(){
        User user = userService.findById(50L);
        System.out.println(JsonUtils.object2Json(user));
    }
}
