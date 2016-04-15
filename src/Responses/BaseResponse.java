package Responses;

/**
 * 公众平台返回的相应消息基类
 * Created by Alchemist on 2016/4/15.
 */
public class BaseResponse {
    private String toUserName;//接受者账号
    private String FromUserName;//微信公众平台账号
    private long createTime;//消息创建时间
    private String MsgType;//消息类型
    public String getToUserName(){
        return toUserName;
    }
    public void setToUserName(String toUserName){
        this.toUserName=toUserName;
    }
    public String getFromUserName(){
        return FromUserName;
    }
    public void setFromUserName(String fromUserName){
        this.FromUserName = fromUserName;
    }
    public long getCreateTime(){
        return createTime;
    }
    public void setCreateTime(long createTime){
        this.createTime = createTime;
    }
    public String getMsgType(){
        return MsgType;
    }
    public void setMsgType(String msgType){
        this.MsgType=msgType;
    }

}
