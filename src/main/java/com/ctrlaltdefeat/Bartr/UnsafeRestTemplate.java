package com.ctrlaltdefeat.Bartr;

import javax.net.ssl.*;

import org.springframework.web.client.RestTemplate;

import java.security.cert.X509Certificate;
 
public class UnsafeRestTemplate {
    public static RestTemplate createUnsafeRestTemplate() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
            };
 
            SSLContext sslContext = SSLContext.getInstance("TLS");
sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
 
            return new RestTemplate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}