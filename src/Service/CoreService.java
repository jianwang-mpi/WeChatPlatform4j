package Service;

import Responses.TextResponse;
import Utils.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务处理类
 * Created by Alchemist on 2016/4/15.
 */
public class CoreService {
    //处理微信发来的请求
    public static String processRequest(HttpServletRequest request) {
        String responseXML =null;
        String responseContent = "Unknown Message Type!";
        try{
            Map<String,String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");

            //回复文本消息
            TextResponse ResponseTextMessage = new TextResponse();
            ResponseTextMessage.setToUserName(fromUserName);
            ResponseTextMessage.setFromUserName(toUserName);
            ResponseTextMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            ResponseTextMessage.setCreateTime(new Date().getTime());

            //文本消息：
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                responseContent = "这是文本~";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
                responseContent="这是图片！";
            }else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
                responseContent="This is voice!";
            }
            ResponseTextMessage.setContent(responseContent);
            responseXML = MessageUtil.messageToXML(ResponseTextMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseXML;
    }
}
