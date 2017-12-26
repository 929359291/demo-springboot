package cn.mengtianyou.auth.webcontroller;

import cn.mengtianyou.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liups
 * @create 2017/12/25
 */
@Controller
public class LoginController extends BaseController {
    @RequestMapping("/login")
    public ModelAndView login(){
        String toUrl = "login";
        Map<String,String> model = new HashMap<String,String>(){{
            put("h3","自定义登录界面");
        }};

        return new ModelAndView(toUrl,model);
    }

}
