package com.jiguang.demo.helper;

import com.jiguang.demo.constants.CustomHttpStatus;
import com.jiguang.demo.exceptions.BaseException;
import com.jiguang.demo.exceptions.AppErrorException;
import com.jiguang.demo.exceptions.AppFinalException;
import com.jiguang.demo.exceptions.AppUserException;
import com.jiguang.demo.messages.AppMessage;
import com.jiguang.demo.messages.AppMessageServiceType;

/**
 * @author liups
 * @create 2017/12/14
 */
public class AppMessageHelper {

    public static BaseException getExceptionByMsgId(int msgId){
        BaseException exception;
        AppMessage appMessage;
        if(msgId == 0){
            appMessage = getDefaultAppMessage();
        }else{
            //TODO 获取信息实体类
            appMessage = getDefaultAppMessage();
        }
        AppMessageServiceType serviceType = AppMessageServiceType.valueOf(appMessage.getServiceType());
        switch (serviceType){
            case F:
                exception = new AppFinalException(appMessage.getMsgTxt(),appMessage.getSysCode(),appMessage.getMsgId()+"");
                break;
            case E:
                exception = new AppErrorException(appMessage.getMsgTxt(),appMessage.getSysCode(),appMessage.getMsgId()+"");
                break;
            case U:
                exception = new AppUserException(appMessage.getMsgTxt(),appMessage.getSysCode(),appMessage.getMsgId()+"");
                break;
            default:
                //TODO 默认异常
                appMessage = getDefaultAppMessage();
                exception = new BaseException(appMessage.getMsgTxt(),appMessage.getSysCode(),appMessage.getMsgId()+"", CustomHttpStatus.SUCCESS.getStatus());
        }
        return exception;
    }

    public static AppMessage getDefaultAppMessage(){
        return new AppMessage(0,"common","默认提示", AppMessageServiceType.U.name());
    }

}
