package Utils;

import Media.Article;
import Responses.ArticleResponse;
import Responses.TextResponse;
import log4j.Log4j;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by Alchemist on 2016/5/5.
 */
public class parseMessageUtil {
    public static String parseTextMessage(Map<String,String> requestMap) throws Exception{
        String responseXML=null;
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");



        if(requestMap.get("Content").equals("安全")){
            Article article = new Article();
            article.setTitle("调查｜不容忽视的实验室安全");
            article.setDescription("2015年9月22日晚7:15左右，正在化学与分子工程学院（以下简称“化院”）B区4层实验室做实验的王波（化院2014级研究生）听到手机微信响了一声。他打开手机，看到群里有人说化院A区7层有实验室起火了。");
            article.setPicURL("http://mmbiz.qpic.cn/mmbiz/l9iadYXd83Z7kTzcqf3edgILmpKf2wB7g93JWTsAvIo4dojgkibWeAaAfBRO1ZMYXezZ6SZGSuq1CwuoqM0RhoBg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
            article.setURL("http://mp.weixin.qq.com/s?__biz=MzA3NzAzMDEyNg==&mid=400078656&idx=1&sn=590b9f2a8d3114538c07e1ba86e0b8f7&scene=20#rd");

            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setToUserName(fromUserName);
            articleResponse.setFromUserName(toUserName);
            articleResponse.setCreateTime(new Date().getTime());
            articleResponse.setArticle(article);
            responseXML = MessageUtil.articleToXML(articleResponse);

        }else{
            //回复文本消息
            TextResponse ResponseTextMessage = new TextResponse();
            ResponseTextMessage.setToUserName(fromUserName);
            ResponseTextMessage.setFromUserName(toUserName);
            ResponseTextMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            ResponseTextMessage.setCreateTime(new Date().getTime());
            ResponseTextMessage.setContent("谢谢您的留言，小编会尽快回复：）点击下方菜单栏，可以查看各栏目精选哟");
            responseXML = MessageUtil.messageToXML(ResponseTextMessage);
        }
        return responseXML;
    }
}
