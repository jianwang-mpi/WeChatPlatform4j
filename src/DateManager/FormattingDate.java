package DateManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangjian on 16-5-22.
 */
public class FormattingDate {
    private static final SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String FormattingDate(String date) throws Exception{

        String regex1="20[0-9][0-9]/[0-1][0-9]/[0-3][0-9]";
        String regex2 = "20[0-9][0-9][0-1][0-9][0-3][0-9]";
        if(isMatchDate(date,regex1)){
            date = date.replace('/','-');
            return date;
        }else if(isMatchDate(date,regex2)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date time = simpleDateFormat.parse(date);
            String newDate = standardDateFormat.format(time);
            return newDate;
        }else{
            return date;
        }
    }
    public static boolean isMatchDate(String content,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
}
