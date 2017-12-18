package com.jiguang.demo.utils;

/**
 * @author liups
 * @create 2017/12/18
 */
public class ExceptionUtils {

    public static Throwable getCause(Exception re){
        Throwable resultEx;
        Throwable cause = re.getCause();
        if(cause != null){
            resultEx = cause;
        }else{
            resultEx = re;
        }
        return resultEx;
    }

}
