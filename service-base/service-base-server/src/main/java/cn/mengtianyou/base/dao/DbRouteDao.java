package cn.mengtianyou.base.dao;

import cn.mengtianyou.base.entity.DbRoute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbRouteDao {

    /**
     * @param model
     * @return
     */
    Long insert(DbRoute model);

    /**
     * @param userId 用户ID
     */
    void delete(Long userId);

    /**
     * @param userId
     * @return
     */
    DbRoute findByUserId(Long userId);
}
