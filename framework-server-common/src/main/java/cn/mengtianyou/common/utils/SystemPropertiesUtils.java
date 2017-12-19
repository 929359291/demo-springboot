package cn.mengtianyou.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author liups
 * @create 2017/12/19
 */
public class SystemPropertiesUtils {
    private static Logger logger = LoggerFactory.getLogger(SystemPropertiesUtils.class);

    public static InetAddress getInetAddress(){
        try{
            return InetAddress.getLocalHost();
        }catch(UnknownHostException e){
            logger.debug("unknown host!");
        }
        return null;

    }

    public static String getHostIp(){
        InetAddress inetAddress = getInetAddress();
        if(null == inetAddress){
            return null;
        }
        //get the ip address
        String ip = inetAddress.getHostAddress();
        return ip;
    }

    public static String getHostName(){
        InetAddress inetAddress = getInetAddress();
        if(null == inetAddress){
            return null;
        }
        //get the host address
        String name = inetAddress.getHostName();
        return name;
    }

}
