package cn.mengtianyou.common.filter;

import cn.mengtianyou.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String separator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append(separator).append("uri:").append(request.getRequestURI()).append(separator).append("Header:");
        Enumeration<String> headerNames = request.getHeaderNames();
        if(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String headerValue = request.getHeader(header);
            builder.append(header);
            builder.append("=");
            builder.append(headerValue);
            builder.append(";");
        }
        builder.append(separator);
        builder.append("parameters:").append(separator);
        builder.append(JsonUtils.object2Json(request.getParameterMap()));
        logger.debug("请求信息：{}",builder.toString());

        filterChain.doFilter(request,response);
    }

}
