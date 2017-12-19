package cn.mengtianyou.common.helper;

import cn.mengtianyou.common.exceptions.AppUserException;
import cn.mengtianyou.common.exceptions.BaseException;
import cn.mengtianyou.common.messages.AppMessageServiceType;
import cn.mengtianyou.common.exceptions.AppErrorException;
import cn.mengtianyou.common.exceptions.AppFinalException;
import cn.mengtianyou.common.messages.AppMessage;

/**
 * @author liups
 * @create 2017/12/14
 */
public class AppMessageHelper {

    public static BaseException getExceptionByMsgId(int msgId, String message){
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
                exception = new AppFinalException(appMessage.getSysCode(),appMessage.getMsgId()+"",appMessage.getMsgTxt(),message);
                break;
            case E:
                exception = new AppErrorException(appMessage.getSysCode(),appMessage.getMsgId()+"",appMessage.getMsgTxt(),message);
                break;
            case U:
                exception = new AppUserException(appMessage.getSysCode(),appMessage.getMsgId()+"",appMessage.getMsgTxt(),message);
                break;
            default:
                //TODO 默认异常
                appMessage = getDefaultAppMessage();
                exception = new BaseException(appMessage.getSysCode(),appMessage.getMsgId()+"", appMessage.getMsgTxt(),appMessage.getServiceType(),message);
        }
        return exception;
    }

    public static AppMessage getDefaultAppMessage(){
        return new AppMessage(0,"common","默认提示", AppMessageServiceType.U.name());
    }

}
