package cn.mengtianyou.user.entity;

/**
 * @author liups
 * @create 2017/11/29
 */
public class Order {
    private Long id;
    private Long userId;
    private String orderName;

    public Order() {
    }

    public Order(Long id, Long userId, String orderName) {
        this.id = id;
        this.userId = userId;
        this.orderName = orderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
