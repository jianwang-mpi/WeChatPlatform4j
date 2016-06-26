package ArticleAddition;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import TrustManager.MyX509TrustManager;
import java.security.SecureRandom;

/**
 * Created by Alchemist on 2016/6/26.
 */
public class test {
    public static void main(String args[]) throws Exception{
        String requestURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx35cc5c069f5a4b26&secret=6b46aa501df92713726aeed5f85ff70b";
        String requestMethod = "GET";
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
        System.out.println(stringBuffer.toString());
    }
}
