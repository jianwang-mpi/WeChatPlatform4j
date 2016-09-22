package TrustManager;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Alchemist on 2016/4/24.
 * java发送https请求需要继承X509TrustManager
 * 需要重写三个方法，这三个方法都是trustManager是否信任的方法，方法体不写任何代码，表示无条件信任，如果不信任则需要抛出异常
 */
public class MyX509TrustManager implements X509TrustManager{

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
