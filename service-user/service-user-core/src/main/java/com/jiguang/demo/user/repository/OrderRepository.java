package com.jiguang.demo.user.repository;

import com.jiguang.demo.user.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository {

    /**
     * @param model
     * @return
     */
    Long insert(Order model);

    /**
     * @param id 订单ID
     */
    void delete(Long id);

    /**
     * @param id
     * @return
     */
    Order findById(Long id);
}
