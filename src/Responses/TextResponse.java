package Responses;

/**
 * Created by Alchemist on 2016/4/15.
 */
public class TextResponse extends BaseResponse {
    private String content;//回复的消息内容
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
