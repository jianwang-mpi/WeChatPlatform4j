package ArticleManager;

import Utils.CommonUtils;
import Utils.Token;
import net.sf.json.JSONObject;

/**
 * Created by Alchemist on 2016/5/6.
 */
public class ArticleObtain {
    public static void main(String []args){
        String test=null;
        GetArticleList getArticleList = new GetArticleList();
        getArticleList.setType("news");
        getArticleList.setCount("1");
        getArticleList.setOffset("0");
        test=JSONObject.fromObject(getArticleList).toString();
        String getArticleURL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
        String pkuYouthToken="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx35cc5c069f5a4b26&secret=6b46aa501df92713726aeed5f85ff70b";
        Token token = CommonUtils.getToken(pkuYouthToken);
        System.out.println(token.getAccessToken());
        System.out.println(test);
        getArticleURL = getArticleURL.replace("ACCESS_TOKEN",token.getAccessToken());
        JSONObject jsonObject = CommonUtils.httpsRequest(getArticleURL,"POST",test);
        System.out.println(jsonObject.toString());
    }
}
