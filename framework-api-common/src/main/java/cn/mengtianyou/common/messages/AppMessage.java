package cn.mengtianyou.common.messages;

/**
 * 与数据库对应的实体类
 * @author liups
 * @create 2017/12/14
 */
public class AppMessage {
    private int msgId;
    private String sysCode;
    private String msgTxt;
    private String serviceType;

    public AppMessage() {
    }

    public AppMessage(int msgId, String sysCode, String msgTxt, String serviceType) {
        this.msgId = msgId;
        this.sysCode = sysCode;
        this.msgTxt = msgTxt;
        this.serviceType = serviceType;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
