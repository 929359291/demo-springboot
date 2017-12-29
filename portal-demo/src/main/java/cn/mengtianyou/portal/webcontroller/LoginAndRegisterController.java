package cn.mengtianyou.portal.webcontroller;

import cn.mengtianyou.common.ResponseResult;
import cn.mengtianyou.common.constants.CustomHttpStatus;
import cn.mengtianyou.common.controller.BaseController;
import cn.mengtianyou.common.messages.ErrorMessage;
import cn.mengtianyou.common.utils.JsonUtils;
import cn.mengtianyou.portal.comsumer.UserFeignConsumer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liups
 * @create 2017/12/25
 */
@Controller
public class LoginAndRegisterController extends BaseController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserFeignConsumer userFeignConsumer;

    @RequestMapping("/login")
    public ModelAndView login(){
        String xRequestedWith = getRequest().getHeader("X-Requested-With");
        //处理ajax异常的时候返回登陆页面
        if (StringUtils.isNotBlank(xRequestedWith) && "XMLHttpRequest".equals(xRequestedWith)) {
            //TODO 登录异常
            String result = JsonUtils.object2Json(ResponseResult.buildError(new ErrorMessage("00010", "portal", "登录超时", "U",
                    "登录超时", null)));
            try {
                getResponse().setStatus(CustomHttpStatus.UNAUTHORIZED.getStatus());
                getResponse().getWriter().write(result);
            }catch (Exception e){}
            return null;
        }else {
            String toUrl = "login";
            Map<String,String> model = new HashMap<String,String>(){{
                put("h3","自定义登录界面");
            }};
            return new ModelAndView(toUrl,model);
        }
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public ModelAndView register(){
        String toUrl = "register";
        Map<String,String> model = new HashMap<String,String>(){{
            put("h3","自定义注册界面");
        }};

        return new ModelAndView(toUrl,model);
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String register(Long id, String name, String password){
        String encode = passwordEncoder.encode(password);
        userFeignConsumer.insertUser(id,name,encode);
        return "/index";
    }

}
