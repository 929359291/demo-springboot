package cn.mengtianyou.portal.filter;

import cn.mengtianyou.common.datasource.SelectedDatasource;
import cn.mengtianyou.common.constants.HeaderDefinition;
import cn.mengtianyou.portal.security.SessionUser;
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
            SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER);
            if(sessionUser != null && !StringUtils.isEmpty(sessionUser.getDsRoute())){
                List<String> requestDatabases = Arrays.asList(sessionUser.getDsRoute().split(HeaderDefinition.DS_ROUTE_SPLIT));
                SelectedDatasource.newInstance(requestDatabases);
            }
        }
        filterChain.doFilter(request,response);
    }
}
