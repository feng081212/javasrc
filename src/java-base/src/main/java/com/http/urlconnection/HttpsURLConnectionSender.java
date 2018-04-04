package com.http.urlconnection;

import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsURLConnectionSender extends HttpURLConnectionSender {

	public HttpsURLConnectionSender(String url) {
		super(url,null) ;
	}
	
	public HttpsURLConnectionSender(String url,Map<String,Object> head) {
		super(url, head) ;
	}
	
	@Override
	public void doBeforeBuildURLConnection(URL url) {
        if (this.url.startsWith("https")) {
            trustAllHosts();
            HttpsURLConnection.setDefaultHostnameVerifier(DO_NOT_VERIFY);
        }
	}
	
	/**
     * 覆盖java默认的证书验证
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null ;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }};

    /**
     * 设置不验证主机
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 信任所有
     * @param connection
     * @return
     */
    private void trustAllHosts() {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, null);
            SSLSocketFactory newFactory = sc.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
