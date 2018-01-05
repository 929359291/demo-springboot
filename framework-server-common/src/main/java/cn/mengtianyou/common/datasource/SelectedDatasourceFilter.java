package cn.mengtianyou.common.datasource;

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
 * 将传递过来的数据库连接池信息保存到本地线程变量（如果有）
 * @author liups
 * @create 2017/12/20
 */
@Component
public class SelectedDatasourceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //将传递过来的数据库选择存入到本地线程变量
        String dsRoute = request.getHeader(HeaderDefinition.DS_ROUTE);
        //TODO 将session中取出分区也放到这个过滤器
        if(!StringUtils.isEmpty(dsRoute)){
            List<String> requestDatabases = Arrays.asList(dsRoute.split(HeaderDefinition.DS_ROUTE_SPLIT));
            SelectedDatasource.newInstance(requestDatabases);
        }
        filterChain.doFilter(request, response);
        //清除本地线程变量
        SelectedDatasource.clearCurrentInstance();
    }
}
