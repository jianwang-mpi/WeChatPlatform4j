package Utils;

import log4j.Log4j;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import TrustManager.MyX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class CommonUtils {

    public final static String tokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx35cc5c069f5a4b26&secret=6b46aa501df92713726aeed5f85ff70b";
    //发送https请求
    public static JSONObject httpsRequest(String requestURL,String requestMethod,String outputString){
        JSONObject jsonObject = null;
        Log4j log4j = new Log4j();
        try{
            TrustManager[] trustManagers = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,trustManagers,new SecureRandom());
            SSLSocketFactory sslSocketFactory  = sslContext.getSocketFactory();

            URL url = new URL(requestURL);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setSSLSocketFactory(sslSocketFactory);

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);

            if(outputString!=null){
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(outputString.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
            }
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line=null;
            StringBuffer stringBuffer = new StringBuffer();
            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
            log4j.debuglog("The json string is:"+stringBuffer.toString());
            jsonObject=JSONObject.fromObject(stringBuffer.toString());
        }catch (ConnectException e){
            log4j.infolog("Overtime:"+e.toString());
            e.printStackTrace();
        }catch(Exception e){
            log4j.debuglog("Strange Exception");
            for( StackTraceElement stackTraceElement : e.getStackTrace()){
                log4j.debuglog(stackTraceElement.toString()+" line: "+stackTraceElement.getLineNumber());
            }
            e.printStackTrace();
        }
        return jsonObject;
    }
    //获取接口访问凭证
    public static Token getToken(){
        return getToken(tokenURL);
    }
    public static Token getToken(String n_tokenurl){
        Token token=null;
        JSONObject jsonObject = httpsRequest(n_tokenurl,"GET",null);
        Log4j log4j = new Log4j();
        if(jsonObject!=null){
            try{
                token=new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }catch (JSONException e){
                token  = null;
                log4j.debuglog("获取token失败");
            }
        }
        return token;
    }
}
