package AuthorManager;

import Utils.DataBaseOperation;
import Utils.DataBaseRemoteOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjian on 16-5-26.
 */
public class AuthorManager {
    public static void main(String[]args) throws Exception{
        String sql = "select url from ArticleMessage;";
        List<Map<String,Object>> queryResult = DataBaseRemoteOperation.queryDB(sql);
        Map<String,Integer> statistics = new HashMap<>();
        int i=0;
        for(Map<String,Object>map:queryResult){
            System.out.println(i++);
            String url = (String)map.get("url");
            List<String> nameList = getAuthorList.getAuthorFromURLType1(url);
            for(String name:nameList){
                if(statistics.containsKey(name)){
                    statistics.put(name,statistics.get(name)+1);
                }else{
                    statistics.put(name,1);
                }
            }
        }
        File file = new File("AuthorList");
        FileWriter fileWriter = new FileWriter(file.getName());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(String name:statistics.keySet()){
            bufferedWriter.append(name+":"+statistics.get(name).toString()+"\n");
        }
        bufferedWriter.close();
        fileWriter.close();

    }
}
