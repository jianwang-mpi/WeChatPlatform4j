package ArticleManager;

import Utils.CommonUtils;
import Utils.Token;
import net.sf.json.JSONObject;

/**
 * Created by Alchemist on 2016/5/6.
 */
public class ArticleObtain {
    public static void main(String []args){
        String test="{\n" +
                "\"media_id\":100000005\n" +
                "}";
        String getArticleURL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
        Token token = CommonUtils.getToken();
        System.out.println(token.getAccessToken());
        getArticleURL = getArticleURL.replace("ACCESS_TOKEN",token.getAccessToken());
        JSONObject jsonObject = CommonUtils.httpsRequest(getArticleURL,"POST",test);
        System.out.println(jsonObject.toString());
    }
}
