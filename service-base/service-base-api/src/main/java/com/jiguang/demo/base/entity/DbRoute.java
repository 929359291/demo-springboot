package com.jiguang.demo.base.entity;

/**
 * @author liups
 * @create 2017/11/27
 */
public class DbRoute {
    private Long userId;

    private String ds;

    public DbRoute() {
    }

    public DbRoute(Long userId, String ds) {
        this.userId = userId;
        this.ds = ds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
