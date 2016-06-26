package ArticleAddition;

/**
 * Created by Alchemist on 2016/6/25.
 */
public class UserInformation {
    private String userID;
    private String passwd;
    private String username;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public UserInformation(String userID, String passwd, String username, int debt) {
        this.userID = userID;
        this.passwd = passwd;
        this.username = username;
    }

    public UserInformation() {
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
