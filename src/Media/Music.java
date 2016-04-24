package Media;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class Music extends BaseMedia {
    private String Title;
    private String Description;
    private String MusicURL;
    private String HQMUsicURL;
    private String ThumbMediaID;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicURL() {
        return MusicURL;
    }

    public void setMusicURL(String musicURL) {
        MusicURL = musicURL;
    }

    public String getHQMUsicURL() {
        return HQMUsicURL;
    }

    public void setHQMUsicURL(String HQMUsicURL) {
        this.HQMUsicURL = HQMUsicURL;
    }

    public String getThumbMediaID() {
        return ThumbMediaID;
    }

    public void setThumbMediaID(String thumbMediaID) {
        ThumbMediaID = thumbMediaID;
    }
}
