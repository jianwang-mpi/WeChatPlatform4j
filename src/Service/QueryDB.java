package Service;

import Media.Article;
import Responses.ArticleResponse;
import Responses.BaseResponse;
import Responses.TextResponse;
import Utils.MessageUtil;
import com.sun.corba.se.impl.orb.ParserTable;
import log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by Alchemist on 2016/5/6.
 */
public class QueryDB {
    Connection conn=null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String dbURL = "jdbc:mysql://sqld.duapp.com:4050/gLqNBKGAHFWicVlogCWf";
    String user="7b3c4fc0ad1e432c83befab9ba1a0ee0";
    String password = "3b05da2171d54f108cc97fa35618a21e";
    public QueryDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void finalize(){
        try {
            resultSet.close();
            preparedStatement.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public BaseResponse ArticleQuery(String articleNo) throws Exception{
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_ARTICLE);
        try{
            String sql="select title,description,picurl,url from ArticleMessage where ANo=\'"+articleNo+"\';";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                articleResponse.setArticle(new Article());
                articleResponse.getArticle().setTitle(resultSet.getString("title"));
                articleResponse.getArticle().setDescription(resultSet.getString("description"));
                articleResponse.getArticle().setPicURL(resultSet.getString("picurl"));
                articleResponse.getArticle().setURL(resultSet.getString("url"));
            }
            if(articleResponse.getArticle()==null){
                TextResponse textResponse = new TextResponse();
                textResponse.setContent("对不起，您查询的文章不存在");
                textResponse.setMsgType("text");
                return textResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return articleResponse;
    }
    public BaseResponse ArticleQueryByTitle(String title) throws Exception{
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_ARTICLE);
        try{
            String sql="select title,description,picurl,url from ArticleMessage where title like \'%"+title+"%\';";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                articleResponse.setArticle(new Article());
                articleResponse.getArticle().setTitle(resultSet.getString("title"));
                articleResponse.getArticle().setDescription(resultSet.getString("description"));
                articleResponse.getArticle().setPicURL(resultSet.getString("picurl"));
                articleResponse.getArticle().setURL(resultSet.getString("url"));
            }
            if(articleResponse.getArticle()==null){
                TextResponse textResponse = new TextResponse();
                textResponse.setContent("对不起，您查询的文章不存在");
                textResponse.setMsgType("text");
                return textResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return articleResponse;
    }
    public boolean isInQuery(String fromUserName){
        boolean result=false;
        try {
            String sql = "select stat from query where userName = \'"+fromUserName+"\'";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int isquery=resultSet.getInt("stat");
                if(isquery > 0)
                    return true;
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public void startQuery(String fromUserName){
        try{
            String sql = "SELECT userName FROM query where userName = \'"+fromUserName+"\'";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            if(!resultSet.next()){
                String insertion = "INSERT INTO query(userName, lastTime,stat) VALUES (\'"+fromUserName+"\',\'"+String.valueOf(new Date().getTime())+"\',1)";
                preparedStatement = conn.prepareStatement(insertion);
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
            }else{
                String update="UPDATE query SET userName=\'"+fromUserName+"\',lastTime=\'"+String.valueOf(new Date().getTime())+"\',stat=1 WHERE userName = \'"+fromUserName+"\'";
                preparedStatement = conn.prepareStatement(update);
                preparedStatement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stopQueryArticle(String fromUserName){
        try{
            String update="UPDATE query SET userName=\'"+fromUserName+"\',lastTime=\'"+String.valueOf(new Date().getTime())+"\',stat=0 WHERE userName = \'"+fromUserName+"\'";
            preparedStatement = conn.prepareStatement(update);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
