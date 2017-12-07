package com.jiguang.demo.user.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author liups
 * @create 2017/11/30
 */
//重点
@WebFilter(filterName = "databaseFilter", urlPatterns = "/ds_route/*")
public class DatabaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //TODO
        filterChain.doFilter(servletRequest,servletResponse);
        //TODO
    }

    @Override
    public void destroy() {

    }
}
