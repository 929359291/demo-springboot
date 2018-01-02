package cn.mengtianyou.common.utils;

import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断是否是multipart/form-data请求
     * @param request
     * @return
     */
    public static boolean isMultipartContent(HttpServletRequest request) {
        String method = "post";
        String contentTypeStart = "multipart/";
        if(!method.equals(request.getMethod().toLowerCase())) {
            return false;
        }
        //获取Content-Type
        String contentType = request.getContentType();
        if((contentType != null) && (contentType.toLowerCase().startsWith(contentTypeStart))) {
            return true;
        }else {
            return false;
        }
    }

}
