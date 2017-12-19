package cn.mengtianyou.user.dao;

import cn.mengtianyou.user.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {

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
