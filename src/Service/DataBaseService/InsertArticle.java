package Service.DataBaseService;

import Media.Article;
import Utils.DataBaseOperation;
import log4j.Log4j;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Alchemist on 2016/6/25.
 */
public class InsertArticle {
    public static void insertArticle(Article article,String content){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        String sql  = "insert into ArticleMessage (time,title,description,picurl,url) values ('"+time+"','"+article.getTitle()+"','"+article.getDescription()+"','"+article.getPicURL()+"','"+article.getURL()+"');";
        DataBaseOperation.insertDB(sql);
        try{
            insertFullText(article,content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void insertFullText(Article article,String content) throws IOException{
        String sql = "select ANo,url from ArticleMessage where title='"+article.getTitle()+"';";
        List<Map<String,Object>> queryResult = DataBaseOperation.queryDB(sql);
        for(Map<String ,Object> i :queryResult){
            int ANo = (Integer)i.get("ANo");
            content = content.replaceAll("　","");
            content = content.replaceAll("[^\\u4e00-\\u9fa5]"," ");
            content = content.replaceAll("\\s+"," ");
            String insertSQL = "insert into articlefulltext (ANo,Text) values ("+ANo+",\'"+content+"\');";
            System.out.println(insertSQL);
            DataBaseOperation.insertDB(insertSQL);
        }
    }
}
