package Responses;

import Media.Article;

import java.util.List;

/**
 * Created by wangjian on 16-5-20.
 */
public class MultiArticleResponse extends BaseResponse {
    private List<Article> articleList = null;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
