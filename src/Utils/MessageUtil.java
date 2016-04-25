package Utils;

import Responses.TextResponse;
import log4j.Log4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        //logger
        Log4j log4j = new Log4j();
        //我简直要疯了，微信给的调试接口的xml是一整个字符串，但是用户输入进来的是按行分开的，在dom4j不通的情况下，只能先暂时这么搞了，以后再弄清楚
        //这里的方法是先将inputstream中的每一行转换为字符串拼接起来,然后再转换为inputstream,避免了java自带的dom工具只能处理一整行的xml的情况
        //new一个hashmap,准备返回相应的xml中的key-value对
        Map<String,String> map = new HashMap<String,String>();
        //得到InputStream
        InputStream inputStream = request.getInputStream();
        //这里建立一个BufferedReader 就是要读取inputstream里面的字符串
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line=null;
        //这里用了一个StringBuffer,就是要把得到的每一行字符串拼接起来
        StringBuffer stringBuffer=new StringBuffer("");
        while((line=bufferedReader.readLine())!=null){
            stringBuffer.append(line);
        }
        //得到拼接起来的字符串以后,再将其转换为inputstream
        inputStream=new ByteArrayInputStream(stringBuffer.toString().getBytes("UTF-8"));
        //这里调用了java自带的dom处理工具,基本上按照网上说了来.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            Element elementNode = (Element)node;
            map.put(elementNode.getNodeName(),elementNode.getTextContent());
        }
        inputStream.close();
        return map;
    }
    /*
    * 文本消息转换为xml
    * */
    public static String messageToXML(TextResponse message){
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
}
