package cn.mengtianyou.base.service;

import cn.mengtianyou.base.entity.DbRoute;
import cn.mengtianyou.base.dao.DbRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liups
 * @create 2017/11/27
 */
@Service
public class DbRouteService {
    @Autowired
    DbRouteDao dbRouteRepository;

    @Transactional( propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void insert(Long userId,String ds) {
        dbRouteRepository.insert(new DbRoute(userId,ds));
//        throw new RuntimeException();
    }

    public void delete(Long userId) {
        dbRouteRepository.delete(userId);
    }

    @Transactional( propagation = Propagation.NEVER,rollbackFor = Exception.class)
    public DbRoute findByUserId(Long userId) {
        DbRoute dbRoute = dbRouteRepository.findByUserId(userId);
        return dbRoute;
    }

}
