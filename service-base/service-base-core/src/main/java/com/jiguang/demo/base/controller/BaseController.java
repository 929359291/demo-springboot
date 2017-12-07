package com.jiguang.demo.base.controller;

import com.jiguang.demo.base.api.client.BaseServiceClient;
import com.jiguang.demo.base.service.DbRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liups
 * @create 2017/11/27
 */
@RestController
public class BaseController implements BaseServiceClient {

    @Autowired
    DbRouteService dbRouteService;

    @Override
    public String insertUserId(Long id,String ds){
        dbRouteService.insert(id,ds);
        return "true";
    }

    @Override
    public String getDsByUserId(Long userId){
        String ds = dbRouteService.findByUserId(userId).getDs();
        return ds;

    }

}
