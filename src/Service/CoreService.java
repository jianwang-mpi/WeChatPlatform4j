package Service;

import Responses.TextResponse;
import Utils.MessageUtil;
import Utils.parseMessageUtil;
import log4j.Log4j;

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
        String responseContent = "谢谢您的留言，小编会尽快回复：）点击下方菜单栏，可以查看各栏目精选哟";
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

            //消息分类处理：
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                responseXML = parseMessageUtil.parseTextMessage(requestMap);
            }else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){          //处理事件信息
                String eventType=requestMap.get("Event");
                if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    responseContent="欢迎关注北大青年！点击下方菜单栏，可以查看不同栏目的文章精选哟~，回复q查询往期文章";
                    ResponseTextMessage.setContent(responseContent);
                    responseXML = MessageUtil.messageToXML(ResponseTextMessage);
                }
            }else{
                ResponseTextMessage.setContent(responseContent);
                responseXML = MessageUtil.messageToXML(ResponseTextMessage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return responseXML;
    }
}
