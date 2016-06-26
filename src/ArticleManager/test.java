package ArticleManager;

import Media.Article;
import Responses.MultiArticleResponse;
import Service.DataBaseService.QueryArticle;

/**
 * Created by Alchemist on 2016/6/15.
 */
public class test {
    public static void main(String args[]) throws Exception{
        MultiArticleResponse multiArticleResponse = (MultiArticleResponse) QueryArticle.queryArticleFullText("郑媛");
        for (Article article:multiArticleResponse.getArticleList()){
            System.out.println(article.getMediaID());
        }
    }
}
