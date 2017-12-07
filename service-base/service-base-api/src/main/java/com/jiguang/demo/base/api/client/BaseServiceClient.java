package com.jiguang.demo.base.api.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liups
 * @create 2017/12/7
 */
@RequestMapping("/base")
public interface BaseServiceClient {

    @RequestMapping("/ds-route/insert-user-id")
    String insertUserId(@RequestParam("id") Long id,@RequestParam("ds") String ds);

    @RequestMapping("/ds-route/find-user")
    String getDsByUserId(@RequestParam("userId") Long userId);

}
