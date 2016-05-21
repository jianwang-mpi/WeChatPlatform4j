package Message;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class ImageMessage extends BaseMessage{
    private String picUrl;
    public String getPicUrl(){
        return picUrl;
    }
    public void setPicUrl(String picUrl){
        this.picUrl=picUrl;
    }
}
