package cn.mengtianyou.common.filter;

import cn.mengtianyou.common.utils.JsonUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

/**
 * 将请求的信息打印出来
 * @author liups
 * @create 2017/12/26
 */
@Component
@Order(-1)
@Profile({"dev","test"})//只有开发测试环境才创建这个过滤器
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("Header:");
        if(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String header1 = request.getHeader(header);
            System.out.println(header + ":" + header1);
        }
        System.out.println("body:");
        System.out.println(JsonUtils.object2Json(request.getParameterMap()));
        filterChain.doFilter(request,response);
    }

}
