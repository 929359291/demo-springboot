package cn.mengtianyou.auth.security;

import cn.mengtianyou.common.utils.JsonUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;

/**
 * @author liups
 * @create 2017/12/26
 */
@Component
@Order(-1)
public class MyFilter extends OncePerRequestFilter {

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
        Collection<String> reHeaderNames = response.getHeaderNames();
        System.out.println("ReHeader:");
        for(String header : reHeaderNames){
            String header1 = response.getHeader(header);
            System.out.println(header + ":" + header1);
        }
    }
}
