package com.jiguang.demo.base.repository;

import com.jiguang.demo.base.entity.DbRoute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbRouteRepository {

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
