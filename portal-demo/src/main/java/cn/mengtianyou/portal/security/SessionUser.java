package cn.mengtianyou.portal.security;

import java.io.Serializable;

/**
 * @author liups
 * @create 2017/12/28
 */
public class SessionUser implements Serializable{
    private static final long serialVersionUID = 1L;

    public static String SESSION_USER = "SESSION_USER";

    private Long id;
    private String name;
    private String dsRoute;

    public SessionUser(Long id, String name, String dsRoute) {
        this.id = id;
        this.name = name;
        this.dsRoute = dsRoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsRoute() {
        return dsRoute;
    }

    public void setDsRoute(String dsRoute) {
        this.dsRoute = dsRoute;
    }
}
