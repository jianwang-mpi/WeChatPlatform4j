package Media;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class Article extends BaseMedia {
    private String Title;
    private String Description;
    private String PicURL;
    private String URL;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null==Description ? "":Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicURL() {
        return PicURL==null? "":PicURL;
    }

    public void setPicURL(String picURL) {
        PicURL = picURL;
    }

    public String getURL() {
        return URL==null? "":URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
