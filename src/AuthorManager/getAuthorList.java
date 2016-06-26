package AuthorManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangjian on 16-5-26.
 */
public class getAuthorList {
    private static String readFromURL(String urlString) throws Exception{
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }
    public static List<String> getAuthorFromURLType1(String urlString) throws Exception{

        String result = readFromURL(urlString);
        result = result.replaceAll("<[^<>]+>"," ");
        //result = result.replaceAll("[^\\u4e00-\\u9fa50-9]+"," ");
        result = result.replaceAll("\\s+"," ");
        result = result.replaceAll("&nbsp;"," ");
        String regex = "(([\\u4e00-\\u9fa5]{2,7}[ 　][^\\s]+[0-9]{2,4}级[\\u4e00-\\u9fa5]{2,4}\\s+)+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);
        String matchResult = null;
        System.out.println(result);
        if(matcher.find()){
            matchResult=matcher.group(1);
        }
        String []peoples=null;
        List<String> resultList = new ArrayList<>();
        if(matchResult!=null){
            peoples = matchResult.split("[ 　]+");

            for(int i=0;i<peoples.length;i+=2){
                resultList.add(peoples[i]);
            }
        }
        return resultList;
    }

}
