package cn.mengtianyou.rest.security.webcontroller;

import cn.mengtianyou.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String securedPage(){
        return "securedPage";

    }

}
