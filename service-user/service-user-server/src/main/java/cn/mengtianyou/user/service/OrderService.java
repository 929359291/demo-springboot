package cn.mengtianyou.user.service;

import cn.mengtianyou.user.entity.Order;
import cn.mengtianyou.user.dao.OrderDao;
import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liups
 * @create 2017/11/29
 */
@Service
public class OrderService {

    @Autowired
    OrderDao orderRepository;

    public void insert(Long id, Long userId, String orderName) {
        HintManagerHolder.clear();
        HintManager instance = HintManager.getInstance();
        instance.addDatabaseShardingValue("ORDER",HintManagerHolder.DB_COLUMN_NAME,"aaaaa");
        instance.addTableShardingValue("ORDER","ID",id);
        orderRepository.insert(new Order(id,userId,orderName));
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }
}
