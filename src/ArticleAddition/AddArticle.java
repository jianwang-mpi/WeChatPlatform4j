package ArticleAddition;
import Media.Article;
import Service.DataBaseService.InsertArticle;
import Utils.CommonUtils;
import Utils.Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alchemist on 2016/6/25.
 */
public class AddArticle {
    public static boolean addArticle(String input){
        String test=null;
        GetArticleList getArticleList = new GetArticleList();
        getArticleList.setType("news");
        getArticleList.setCount("5");
        getArticleList.setOffset("0");
        test=JSONObject.fromObject(getArticleList).toString();
        String getArticleURL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
        String pkuYouthToken="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx35cc5c069f5a4b26&secret=6b46aa501df92713726aeed5f85ff70b";
        Token token = CommonUtils.getToken(pkuYouthToken);
        getArticleURL = getArticleURL.replace("ACCESS_TOKEN",token.getAccessToken());
        JSONObject jsonObject = CommonUtils.httpsRequest(getArticleURL,"POST",test);
        System.out.println(jsonObject.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("item");
        List<Article> articleList = new ArrayList<>();
        List<String> contentList  =new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject singleDay = jsonArray.getJSONObject(i);
            JSONObject singleDayContent = singleDay.getJSONObject("content");
            JSONArray articleJsonList = singleDayContent.getJSONArray("news_item");
            for(int j=0;j<articleJsonList.size();j++){
                JSONObject articleJsonObject = articleJsonList.getJSONObject(j);
                Article article = new Article();
                article.setDescription(articleJsonObject.getString("digest"));
                article.setPicURL(articleJsonObject.getString("thumb_media_id"));
                article.setTitle(articleJsonObject.getString("title"));
                article.setURL(articleJsonObject.getString("url"));
                articleList.add(article);
                contentList.add(articleJsonObject.getString("content"));
            }
        }
        for(int i=0;i<articleList.size();i++){
            Article a = articleList.get(i);
            String content = contentList.get(i);
            if(a.getTitle().equals(input)){
                InsertArticle.insertArticle(a,content);
                return true;
            }
        }
        return false;
    }
}
