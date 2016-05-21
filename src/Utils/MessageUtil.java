package Utils;

import Media.Article;
import Responses.ArticleResponse;
import Responses.BaseResponse;
import Responses.MultiArticleResponse;
import Responses.TextResponse;

import log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接受信息解析xml
 * Created by Alchemist on 2016/4/15.
 */
public class MessageUtil {
    //设置信息类型
    //文本消息
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    //图片消息
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    //语音消息
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    //视频消息
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    //图文消息
    public static final String REQ_MESSAGE_TYPE_ARTICLE = "news";
    public static final String RESP_MESSAGE_TYPE_ARTICLE = "news";
    //事件消息
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    public static final String RESP_MESSAGE_TYPE_EVENT = "event";

    //事件类型：订阅
    public static final String EVENT_TYPE_SUBSCRIBE="subscribe";
    //事件类型：取消订阅
    public static final String EVENT_TYPE_UNSUBSCRIBE="unsubscribe";
    //事件类型：上报地理位置
    public static final String EVENT_TYPE_LOCATION="LOCATION";
    //事件类型：点击自定义菜单
    public static final String EVENT_TYPE_CLICK="Click";

    //解析xml返回Map
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();

        for(Element e:elementList){
            map.put(e.getName(),e.getText());
        }
        inputStream.close();
        return map;
    }

    public static String messageToXML(BaseResponse message){
        if(message instanceof TextResponse){
            return TextResponseToXML((TextResponse)message);
        }else if(message instanceof ArticleResponse){
            return ArticleResponseToXML((ArticleResponse)message);
        }else if(message instanceof MultiArticleResponse){
            return multiArticleResponseToXML((MultiArticleResponse)message);
        }else{
            return null;
        }
    }
    /*
    * 文本消息转换为xml
    * */
    public static String TextResponseToXML(TextResponse message){
        String  textMessageModel = "<xml>" +
                "<ToUserName><![CDATA[_toUser_]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>time</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[content]]></Content>" +
                "</xml>";
        textMessageModel = textMessageModel.replace("_toUser_", message.getToUserName())
                .replace("fromUser", message.getFromUserName())
                .replace("content", message.getContent())
                .replace("time", String.valueOf(message.getCreateTime()));
        return textMessageModel;
    }
    public static String ArticleResponseToXML(ArticleResponse message){
        String articleMessageModel="<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "<CreateTime>_time_</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>1</ArticleCount>\n" +
                "<Articles>\n" +
                "<item>\n" +
                "<Title><![CDATA[title]]></Title> \n" +
                "<Description><![CDATA[description]]></Description>\n" +
                "<PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                "<Url><![CDATA[_url]]></Url>\n" +
                "</item>\n" +
                "</Articles>\n" +
                "</xml>";
        articleMessageModel = articleMessageModel.replace("toUser", message.getToUserName())
                .replace("fromUser", message.getFromUserName())
                .replace("_time_", String.valueOf(message.getCreateTime()))
                .replace("title", message.getArticle().getTitle())
                .replace("description", message.getArticle().getDescription())
                .replace("picurl", message.getArticle().getPicURL())
                .replace("_url", message.getArticle().getURL());
        return articleMessageModel;
    }
    public static String multiArticleResponseToXML(MultiArticleResponse multiArticleResponse){
        String multiArticleMessageHead="<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "<CreateTime>_time_</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>_count</ArticleCount>\n"+
                "<Articles>\n";
        multiArticleMessageHead = multiArticleMessageHead.replace("toUser", multiArticleResponse.getToUserName())
                .replace("fromUser", multiArticleResponse.getFromUserName())
                .replace("_time_", String.valueOf(multiArticleResponse.getCreateTime()))
                .replace("_count",String.valueOf(multiArticleResponse.getArticleList().size()));
        String multiArticleMessageEnd="</Articles>\n" +
                "</xml>";
        List<Article> articleList = multiArticleResponse.getArticleList();
        String multiArticleMessageBody = "<item>\n" +
                "<Title><![CDATA[title]]></Title> \n" +
                "<Description><![CDATA[description]]></Description>\n" +
                "<PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                "<Url><![CDATA[_url]]></Url>\n" +
                "</item>\n";
        for(int i=0;i<articleList.size();i++){
            String temp = multiArticleMessageBody.replace("title", articleList.get(i).getTitle())
                    .replace("description", articleList.get(i).getDescription())
                    .replace("picurl", articleList.get(i).getPicURL())
                    .replace("_url", articleList.get(i).getURL());
            multiArticleMessageHead=multiArticleMessageHead+temp;
        }
        String result = multiArticleMessageHead+multiArticleMessageEnd;
        Log4j log4j = new Log4j();
        log4j.infolog(result);
        return result;
    }
}
