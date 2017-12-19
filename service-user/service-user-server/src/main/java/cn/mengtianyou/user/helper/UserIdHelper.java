package cn.mengtianyou.user.helper;

import java.util.Random;

/**
 * @author liups
 * @create 2017/11/27
 */
public class UserIdHelper {

    public static Long getRandomUserId(){
        long l = System.currentTimeMillis();
        int i = new Random().nextInt(2);
        return Long.parseLong(l + "" + i) ;
    }

    public static String calDataSourceNameByUserName(Long userId){
        if(userId == null){
            throw new RuntimeException("id 不能为空");
        }
        if(userId % 2 == 0){
            return "druid_demo_1";
        }else{
            return "druid_demo_2";
        }
    }
}
