package com.jiguang.demo.base.controller;

import com.fasterxml.jackson.core.sym.NameN;
import com.jiguang.demo.base.provider.BaseProvider;
import com.jiguang.demo.base.service.DbRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

        String ds = dbRouteService.findByUserId(userId).getDs();
        return ds;

    }

}
