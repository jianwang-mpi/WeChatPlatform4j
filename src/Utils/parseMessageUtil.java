package Utils;

import Responses.BaseResponse;
import Responses.TextResponse;
import Service.QueryDB;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alchemist on 2016/5/5.
 */
public class parseMessageUtil {
    public static String parseTextMessage(Map<String,String> requestMap) throws Exception{
        String responseXML=null;
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");
        QueryDB queryDB = new QueryDB();
        String content = requestMap.get("Content");
        BaseResponse ResponseMessage=null;
        if(isQuery(fromUserName)) {
            if(content.equals("c")){
                queryDB.stopQueryArticle(fromUserName);
                TextResponse textResponse = new TextResponse();
                textResponse.setContent("成功取消查询");
                textResponse.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                ResponseMessage = textResponse;
            } else if (isNum(content)) {
                ResponseMessage = queryDB.ArticleQuery(requestMap.get("Content"));
            } else {
                ResponseMessage = queryDB.ArticleQueryByTitle(requestMap.get("Content"));
            }
        }else if(content.equals("q")) {
            queryDB.startQuery(fromUserName);
            TextResponse textResponse = new TextResponse();
            textResponse.setContent("开始查询往期文章,回复\"c\"取消查询");
            textResponse.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            ResponseMessage = textResponse;
        }else{
            TextResponse text = new TextResponse();
            text.setContent("谢谢您的留言，小编会尽快回复：）点击下方菜单栏，可以查看各栏目精选哟");
            text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            ResponseMessage = text;
        }
        ResponseMessage.setToUserName(fromUserName);
        ResponseMessage.setFromUserName(toUserName);
        ResponseMessage.setCreateTime(new Date().getTime());
        responseXML = MessageUtil.messageToXML(ResponseMessage);
        return responseXML;
    }
    private static boolean isNum(String content){
        String regex = "[1-9][0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
    private static boolean isQuery(String fromUserName){
        boolean result=false;
        QueryDB queryDB = new QueryDB();

        if(queryDB.isInQuery(fromUserName)){
            result=true;
        }
        return result;
    }
}
