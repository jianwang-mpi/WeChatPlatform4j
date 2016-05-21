package Service.DataBaseService;

import Media.Article;
import Responses.ArticleResponse;
import Responses.BaseResponse;
import Responses.MultiArticleResponse;
import Responses.TextResponse;
import Utils.DataBaseOperation;
import Utils.MessageUtil;
import log4j.Log4j;

import java.util.*;

/**
 * Created by wangjian on 16-5-20.
 */
public class QueryArticle {
    public static BaseResponse queryArticleDate(String date) throws Exception{
        String sql="select * from ArticleMessage where time='"+date+"';";
        Log4j log4j = new Log4j();
        log4j.infolog(sql);
        List<Map<String,Object>> resultList = DataBaseOperation.queryDB(sql);
        if(resultList!=null&&resultList.size()!=0){
            MultiArticleResponse multiArticleResponse = new MultiArticleResponse();
            List<Article> articleList = new ArrayList<>();
            log4j.intlog(resultList.size());
            log4j.infolog((String)resultList.get(0).get("url"));
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
}
