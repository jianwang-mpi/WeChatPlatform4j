package ArticleManager;

import DateManager.DateManagment;
import Media.Article;
import Service.QueryDB;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by wangjian on 16-5-17.
 */
public class ArticleToSQL {
    private static void writeSQL(String sql) throws Exception{
        File file = new File("list.sql");
        FileWriter fileWriter = new FileWriter(file.getName());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(sql);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
    public static String createInsertSQL(String time, String title, String description, String picurl, String url){
        String sql="insert into ArticleMessage (time,title,description,picurl,url) values ('_time','_title','_desc','_picurl','_url');";
        sql = sql.replace("_time",time)
                .replace("_title",title)
                .replace("_desc",description)
                .replace("_picurl",picurl)
                .replace("_url",url);
        return sql;
    }
    public static void main(String []args) throws Exception{
        File file = new File("list.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        File outFile = new File("list.sql");
        FileWriter fileWriter = new FileWriter(outFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while((line=bufferedReader.readLine())!=null){
            JSONObject jsonObject = JSONObject.fromObject(line);
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("multi_item"));
            for(int i=0;i<jsonArray.size();i++){
                String articleJson = jsonArray.getString(i);
                JSONObject article = JSONObject.fromObject(articleJson);
                String sql = createInsertSQL(DateManagment.Convert1970ToStdFormat(jsonObject.getString("date_time")),article.getString("title"),article.getString("digest"),article.getString("cover"),article.getString("content_url"));
                System.out.println(sql);
                bufferedWriter.append(sql);
                bufferedWriter.flush();
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
