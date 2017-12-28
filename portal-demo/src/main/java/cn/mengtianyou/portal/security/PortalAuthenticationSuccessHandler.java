package cn.mengtianyou.portal.security;

import cn.mengtianyou.common.constants.HeaderDefinition;
import cn.mengtianyou.portal.comsumer.BaseFeignConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author liups
 * @create 2017/12/27
 */
@Component
public class PortalAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BaseFeignConsumer baseFeignConsumer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        if(logger.isDebugEnabled()){
            logger.debug("user login success:{}",authentication.getName());
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof PortalUserDetails){
            HttpSession session = request.getSession(false);
            if(session != null){
                PortalUserDetails portalUser = (PortalUserDetails) principal;
                Long id = portalUser.getId();
                //TODO 根据用户ID来获取用户库区
                String ds = baseFeignConsumer.getDsByUserId(id);
                SessionUser sessionUser = new SessionUser(id,portalUser.getUsername(),ds);
                session.setAttribute(SessionUser.SESSION_USER,sessionUser);
            }

        }
    }
}
