package cn.mengtianyou.portal.webcontroller;

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
public class DemoController extends BaseController{
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/securedPage")
    public ModelAndView securedPage(String info){
        Map<String,String> model = new HashMap<String,String>(){{
           put("info",info);
        }};
        return new ModelAndView("securedPage",model);
    }

}
