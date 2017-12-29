package cn.mengtianyou.common.utils;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liups
 * @create 2017/12/28
 */
public class ExtendWebUtils extends WebUtils{


    /**
     * 获取调用者ip地址
     * @param request
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-real-ip");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                {
                    ip = request.getRemoteAddr();
                }
            }
        }
        return ip;
    }

}
