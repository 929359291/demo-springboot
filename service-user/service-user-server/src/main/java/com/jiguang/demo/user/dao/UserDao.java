package com.jiguang.demo.user.dao;

import com.jiguang.demo.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    /**
     * @param model
     * @return
     */
    Long insert(User model);

    /**
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     *
     * @param name
     * @return
     */
    User findByName(String name);
}