package cn.mengtianyou.common.exceptions;

import cn.mengtianyou.common.constants.CustomHttpStatus;
import cn.mengtianyou.common.messages.AppMessageServiceType;

/**
 * 数据库中表现为F：Final 代表系统奔溃级别，一般为JVM内存溢出或底层的错误（这种能处理吗？）
 * @author liups
 * @create 2017/12/14
 */
public class AppFinalException extends BaseException {

    public AppFinalException(String sysCode, String code, String msgTxt, String message) {
        super(sysCode, code, msgTxt, AppMessageServiceType.F.name(), CustomHttpStatus.SERVICE_UNAVAILABLE.getStatus(), message);
    }

    public AppFinalException(String sysCode, String code, String msgTxt, String message, Throwable cause) {
        super(sysCode, code, msgTxt, AppMessageServiceType.F.name(), CustomHttpStatus.SERVICE_UNAVAILABLE.getStatus(), message, cause);
    }
}
