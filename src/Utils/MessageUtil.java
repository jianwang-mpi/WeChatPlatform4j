package Utils;

import Responses.TextResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 接受信息解析xml
 * Created by Alchemist on 2016/4/15.
 */
public class MessageUtil {
    //设置信息类型
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAAGE_TYPE_TEST = "text";
    //解析xml返回Map
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        InputStream inputStream = request.getInputStream();
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
