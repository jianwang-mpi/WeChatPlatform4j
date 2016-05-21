package Event;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class BaseEvent {
    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String MsgType;
    private String Event;
    public String getToUserName() {
        return toUserName;
    }
    public String getFromUserName(){return fromUserName;}
    public long getCreateTime(){return createTime;}
    public String getMsgType(){return MsgType;}
    public String getEvent(){return Event;}
    public void setToUserName(String toUserName){this.toUserName=toUserName;}
    public void setFromUserName(String fromUserName){this.fromUserName=fromUserName;}
    public void setCreateTime(long createTime){this.createTime=createTime;}
    public void setMsgType(String msgType){this.MsgType=msgType;}
    public void setEvent(String event){this.Event=event;}
}
