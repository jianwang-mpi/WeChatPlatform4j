package Service.DataBaseService;

import Media.Article;
import Responses.BaseResponse;
import Responses.MultiArticleResponse;
import Responses.TextResponse;
import Utils.DataBaseOperation;
import log4j.Log4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangjian on 16-5-20.
 */
public class QueryArticle {
    private static TextResponse NullArticleTextResponse(){
        TextResponse textResponse = new TextResponse();
        textResponse.setContent("对不起，您查询的文章不存在");
        textResponse.setMsgType("text");
        return textResponse;
    }
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
                article.setMediaID(String.valueOf((int)resultList.get(i).get("ANo")));
                articleList.add(article);
            }
            log4j.intlog(articleList.size());
            multiArticleResponse.setArticleList(articleList);
            log4j.infolog("is it a multi?"+String.valueOf(multiArticleResponse instanceof MultiArticleResponse));
            log4j.infolog("size:"+String.valueOf(multiArticleResponse.getArticleList().size()));
            return multiArticleResponse;
        }else{
            return null;
        }
    }

    public static BaseResponse queryArticleDate(String date) throws Exception{
        String sql="select * from ArticleMessage where time='"+date+"';";
        BaseResponse baseResponse = queryArticle(sql);
        if(baseResponse!=null){
            return baseResponse;
        }else
            return NullArticleTextResponse();
    }
    private static boolean isGoodTitle(String title){
        String regex="^([0-9]|[\\u4E00-\\u9FA5 ])*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }
    public static BaseResponse queryArticleText(String keyWord) throws Exception{
        if(isGoodTitle(keyWord)){
            String sql="select * from ArticleMessage where title like '%"+keyWord+"%';";
            BaseResponse baseResponse = queryArticle(sql);
            if(baseResponse !=null){
                MultiArticleResponse multiArticleResponse = (MultiArticleResponse)baseResponse;
                if(multiArticleResponse.getArticleList().size()>=10)
                    return multiArticleResponse;
                else{
                    BaseResponse fullText = queryArticleFullText(keyWord);
                    if(fullText==null){
                        return NullArticleTextResponse();
                    }
                    MultiArticleResponse result = mergeMultiArticles(multiArticleResponse,(MultiArticleResponse) fullText);
                    return result;
                }
            }else{
                BaseResponse fullText = queryArticleFullText(keyWord);
                if(fullText==null){
                    return NullArticleTextResponse();
                }else{
                    MultiArticleResponse multiArticleResponse = new MultiArticleResponse();
                    multiArticleResponse.setArticleList(new ArrayList<Article>());
                    MultiArticleResponse result = mergeMultiArticles(multiArticleResponse,(MultiArticleResponse) fullText);
                    return result;
                }
            }

        }else{
            return NullArticleTextResponse();
        }
    }
    public static boolean checkDuplicate(Article article,List<Article> articleList){
        for(Article a : articleList){
            if(a.getMediaID().equals(article.getMediaID())){
                return true;
            }
        }
        return false;
    }
    public static MultiArticleResponse mergeMultiArticles(MultiArticleResponse a1,MultiArticleResponse a2){
        for(Article article : a2.getArticleList()){
            if(!checkDuplicate(article,a1.getArticleList())){
                a1.getArticleList().add(article);
            }
        }
        return a1;
    }
    public static BaseResponse queryArticleFullText(String keyWord) throws Exception{
        String sql = "select ANo from articlefulltext where -length(text)+length(replace(text,'"+keyWord+"',''))<0 order by -length(text)+length(replace(text,'"+keyWord+"',''));";
        List<Map<String,Object>> resultList = DataBaseOperation.queryDB(sql);
        if(resultList==null||resultList.size()==0)return null;
        MultiArticleResponse multiArticleResponse  = new MultiArticleResponse();
        List<Article> articleList = new ArrayList<>();
        for(int i=0;i<resultList.size();i++){
            int ANo = (int)resultList.get(i).get("ANo");
            String sqlByNo = "select * from ArticleMessage where ANo="+ANo+";";
            List<Map<String,Object>> resultByNo = DataBaseOperation.queryDB(sqlByNo);

            Article article = new Article();
            article.setTitle((String) resultByNo.get(0).get("title"));
            article.setDescription((String) resultByNo.get(0).get("description"));
            article.setPicURL((String)resultByNo.get(0).get("picurl"));
            article.setURL((String)resultByNo.get(0).get("url"));
            article.setMediaID(String.valueOf(ANo));
            articleList.add(article);
        }
        multiArticleResponse.setArticleList(articleList);
        return multiArticleResponse;
    }
}
