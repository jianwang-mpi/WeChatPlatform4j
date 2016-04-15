package Utils;

import Message.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
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
        Map<String,String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();//利用dom4j来解析xml
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for(int i=0;i<elementList.size();i++){
            map.put(elementList.get(i).getName(),elementList.get(i).getText());
        }
        inputStream.close();
        return map;
    }
    private static XStream xstream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                boolean cdata = true;
                @SuppressWarnings("unchecked")
                public void startNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }
                protected void writerText(QuickWriter writer,String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });
    /*
    * 文本消息转换为xml
    * */
    public static String messageToXML(TextMessage textMessage){
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }
}
