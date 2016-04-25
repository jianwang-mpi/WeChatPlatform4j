package Message;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class VoiceMessage extends BaseMessage {
    private String MediaID;
    private String format;
    private String Recognition;
    public String getMediaID(){
        return MediaID;
    }
    public String getFormat(){
        return format;
    }
    public String getRecognition(){
        return Recognition;
    }
    public void setMediaID(String mediaID){
        this.MediaID=mediaID;
    }
    public void setFormat(String format){
        this.format=format;
    }
    public void setRecognition(String recognition){
        this.Recognition=recognition;
    }
}
