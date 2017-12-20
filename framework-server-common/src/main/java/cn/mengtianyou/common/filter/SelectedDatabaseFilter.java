package cn.mengtianyou.common.filter;

import cn.mengtianyou.common.aspect.SelectedDatabase;
import cn.mengtianyou.common.constants.HeaderDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author liups
 * @create 2017/12/20
 */
@Component
public class SelectedDatabaseFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //将传递过来的数据库选择存入到本地线程变量
        String dsRoute = request.getHeader(HeaderDefinition.DS_ROUTE);
        if(!StringUtils.isEmpty(dsRoute)){
            List<String> requestDatabases = Arrays.asList(dsRoute.split(HeaderDefinition.DS_ROUTE_SPLIT));
            SelectedDatabase.newInstance(requestDatabases);
        }
        filterChain.doFilter(request, response);
        //清除本地线程变量
        SelectedDatabase.clearCurrentInstance();
    }
}
