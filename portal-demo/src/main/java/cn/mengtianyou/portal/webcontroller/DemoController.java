package cn.mengtianyou.portal.webcontroller;

import cn.mengtianyou.common.controller.BaseController;
import cn.mengtianyou.portal.comsumer.UserFeignConsumer;
import cn.mengtianyou.portal.security.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liups
 * @create 2017/12/25
 */
@Controller
public class DemoController extends BaseController{
    @Autowired
    UserFeignConsumer userFeignConsumer;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/securedPage")
    public ModelAndView securedPage(){
        HttpSession session = getRequest().getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER);
        String userName = userFeignConsumer.getUserName(sessionUser.getId());
        Map<String,String> model = new HashMap<String,String>(){{
           put("info",userName);
        }};
        return new ModelAndView("securedPage",model);
    }

}
