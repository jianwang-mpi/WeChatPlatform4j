package Responses;

import Media.Article;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class ArticleResponse extends BaseResponse {
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
