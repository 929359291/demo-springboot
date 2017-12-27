package cn.mengtianyou.portal.filter;

import cn.mengtianyou.common.aspect.SelectedDatabase;
import cn.mengtianyou.common.constants.HeaderDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author liups
 * @create 2017/12/27
 */
public class SetDatabaseFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            String dsRoute = (String) session.getAttribute(HeaderDefinition.DS_ROUTE);
            if(!StringUtils.isEmpty(dsRoute)){
                List<String> requestDatabases = Arrays.asList(dsRoute.split(HeaderDefinition.DS_ROUTE_SPLIT));
                SelectedDatabase.newInstance(requestDatabases);
            }
        }
        filterChain.doFilter(request,response);
    }
}
