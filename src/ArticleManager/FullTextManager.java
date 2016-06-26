package ArticleManager;

import Utils.DataBaseOperation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Alchemist on 2016/6/15.
 */
public class FullTextManager {
    public static void main(String args[]) throws Exception{
        String sql = "select ANo,url from ArticleMessage;";
        List<Map<String,Object>> queryResult = DataBaseOperation.queryDB(sql);
        for(Map<String ,Object> i :queryResult){
            int ANo = (Integer)i.get("ANo");
            String urlString = (String)i.get("url");
            urlString = urlString.replaceAll("&amp;","&");
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String s;
            while((s = bufferedReader.readLine())!=null){
                stringBuffer.append(s);
            }
            String content = stringBuffer.toString();
            content = content.replaceAll("　","");
            content = content.replaceAll("[^\\u4e00-\\u9fa5]"," ");
            content = content.replaceAll("\\s+"," ");

            String insertSQL = "insert into ArticleFullText (ANo,Text) values ("+ANo+",\'"+content+"\');";
            System.out.println(insertSQL);
            DataBaseOperation.insertDB(insertSQL);
        }
    }
}
