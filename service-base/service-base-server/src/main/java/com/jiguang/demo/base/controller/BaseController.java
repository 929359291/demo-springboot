package com.jiguang.demo.base.controller;

import com.jiguang.demo.base.provider.BaseProvider;
import com.jiguang.demo.base.service.DbRouteService;
import com.jiguang.demo.exceptions.AppUserException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liups
 * @create 2017/11/27
 */
@RestController
public class BaseController implements BaseProvider {
    Logger logger = LoggerFactory.getLogger(BaseController.class);
    public static Long sysId = System.currentTimeMillis();

    @Autowired
    DbRouteService dbRouteService;

    @Override
    public String insertUserId(Long id,String ds){
        dbRouteService.insert(id,ds);
        return "true";
    }

    @Override
    public String getDsByUserId(Long userId){
        logger.debug("getDsByUserId:{}",sysId);
        if(userId != null && userId == 250){
            throw new RuntimeException(new HystrixTimeoutException());
//            throw new RuntimeException(new AppUserException("error","NFC0","400"));
//            throw new AppUserException("error","NFC0","400");
        }
        String ds = dbRouteService.findByUserId(userId).getDs();
        return ds;

    }

}
