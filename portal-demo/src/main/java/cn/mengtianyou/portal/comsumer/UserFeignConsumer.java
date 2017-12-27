package cn.mengtianyou.portal.comsumer;

import cn.mengtianyou.user.provider.UserProvider;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author liups
 * @create 2017/12/27
 */
@FeignClient("service-user")
public interface UserFeignConsumer extends UserProvider{
}
