package Service.DataBaseService;

import Media.Article;
import Responses.ArticleResponse;
import Responses.BaseResponse;
import Responses.MultiArticleResponse;
import Responses.TextResponse;
import Utils.DataBaseOperation;
import Utils.MessageUtil;
import log4j.Log4j;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangjian on 16-5-20.
 */
public class QueryArticle {
    private static BaseResponse queryArticle(String sql) throws Exception{
        Log4j log4j = new Log4j();
        log4j.infolog(sql);
        List<Map<String,Object>> resultList = DataBaseOperation.queryDB(sql);
        if(resultList!=null&&resultList.size()!=0){
            MultiArticleResponse multiArticleResponse = new MultiArticleResponse();
            List<Article> articleList = new ArrayList<>();
            for(int i=0;i<resultList.size();i++){
                Article article = new Article();
                article.setTitle((String) resultList.get(i).get("title"));
                article.setDescription((String) resultList.get(i).get("description"));
                article.setPicURL((String)resultList.get(i).get("picurl"));
                article.setURL((String)resultList.get(i).get("url"));
                articleList.add(article);
            }
            log4j.intlog(articleList.size());
            multiArticleResponse.setArticleList(articleList);
            log4j.infolog("is it a multi?"+String.valueOf(multiArticleResponse instanceof MultiArticleResponse));
            log4j.infolog("size:"+String.valueOf(multiArticleResponse.getArticleList().size()));
            return multiArticleResponse;
        }else{
            TextResponse textResponse = new TextResponse();
            textResponse.setContent("对不起，您查询的文章不存在");
            textResponse.setMsgType("text");
            return textResponse;
        }
    }
    public static BaseResponse queryArticleDate(String date) throws Exception{
        String sql="select * from ArticleMessage where time='"+date+"';";
        return queryArticle(sql);
    }
    private static boolean isGoodTitle(String title){
        String regex="^([0-9]|[\\u4E00-\\u9FA5])*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }
    public static BaseResponse queryArticleTitle(String title) throws Exception{
        if(isGoodTitle(title)){
            String sql="select * from ArticleMessage where title like '%"+title+"%';";
            return queryArticle(sql);

        }else{
            TextResponse textResponse = new TextResponse();
            textResponse.setContent("对不起，您查询的文章不存在");
            textResponse.setMsgType("text");
            return textResponse;
        }

    }
}
