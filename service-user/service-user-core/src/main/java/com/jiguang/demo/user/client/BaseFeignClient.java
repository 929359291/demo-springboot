package com.jiguang.demo.user.client;

import com.jiguang.demo.base.api.client.BaseServiceClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author liups
 * @create 2017/12/7
 */
@FeignClient("service-base")
public interface BaseFeignClient extends BaseServiceClient {
}
