package ArticleAddition;

import Utils.DataBaseOperation;

import java.util.List;
import java.util.Map;

/**
 * Created by Alchemist on 2016/6/25.
 */
public class ArticleIfExist {
    public static boolean ifExistArticle(String articleName){
        String sql = "select * from ArticleMessage where title = '"+articleName+"';";
        List<Map<String,Object>> resultList = DataBaseOperation.queryDB(sql);
        if(resultList==null || resultList.size()==0){
            return  false;
        }else{
            return  true;
        }
    }
}
