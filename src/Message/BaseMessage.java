package Message;

/**
 * 请求消息基类（普通用户→公众号）
 * Created by Alchemist on 2016/4/15.
 */
public class BaseMessage {
    private String toUserName;//开发者微信号
    private String FromUserName;//发送者账号
    private long createTime;//消息创建时间
    private String MsgType;//消息类型
    private long MsgID;//消息ID
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
    public long getMsgID(){
        return MsgID;
    }
    public void setMsgID(long msgID){
        this.MsgID=msgID;
    }
}
