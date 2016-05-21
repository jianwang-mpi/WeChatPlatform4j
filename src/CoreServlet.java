
import log4j.Log4j;
import Service.CoreService;
import Utils.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求处理的核心类
 * Created by Alchemist on 2016/4/14.
 */
public class CoreServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce  = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
        out.close();

    }
    /*
    * 请求校验的处理
    * */


    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //设置编码模式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        out.print("");
        //TO DO:请求校验,现在暂时不做


        //获取待返回的xml
        String responseXML = CoreService.processRequest(request);



        //输出获得的xml
        out.print(responseXML);
        out.close();
    }

}
