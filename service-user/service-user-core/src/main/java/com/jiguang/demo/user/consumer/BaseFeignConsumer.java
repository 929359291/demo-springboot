package com.jiguang.demo.user.consumer;

import com.jiguang.demo.base.provider.BaseProvider;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author liups
 * @create 2017/12/7
 */
@FeignClient("service-base")
public interface BaseFeignConsumer extends BaseProvider {
}
