package cn.mengtianyou.base.controller;

import cn.mengtianyou.base.provider.BaseProvider;
import cn.mengtianyou.base.service.DbRouteService;
import cn.mengtianyou.common.exceptions.AppUserException;
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
//            throw new RuntimeException(new HystrixTimeoutException());
            throw new RuntimeException(new AppUserException("error","NFC0","error","error"));
//            throw new AppUserException("error","NFC0","400");
        }
        String ds = dbRouteService.findByUserId(userId).getDs();
        return ds;

    }

}
