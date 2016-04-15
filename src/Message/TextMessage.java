package Message;

/**
 * 文本消息
 * Created by Alchemist on 2016/4/15.
 */
public class TextMessage extends BaseMessage {
    private String content;//消息内容
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
