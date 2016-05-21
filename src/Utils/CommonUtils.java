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

    public final static String tokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx520c0e769bf47b3f&secret=01471f066a11046572933ae63a655904";
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
            jsonObject=JSONObject.fromObject(stringBuffer.toString());
        }catch (ConnectException e){
            log4j.infolog("连接超时:"+e.toString());
        }catch(Exception e){
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
        if(jsonObject!=null){
            try{
                token=new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }catch (JSONException e){
                token  = null;
                Log4j log4j = new Log4j();
                log4j.infolog("获取token失败");
            }
        }
        return token;
    }
}
