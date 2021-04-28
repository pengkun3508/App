package com.dm.lib.utils.https;

import android.os.Build;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 描述：HTTPS兼容类
 * -------------------------
 * --- 如果服务器为HTTP请求 ---
 * -------------------------
 * android9.0以上不支持HTTP请求，默认情况下启用网络传输层安全协议 (TLS)，需在AndroidManifest中添加一个XML文件：
 * 如果您的应用以 Android 9 或更高版本为目标平台，则默认情况下 isCleartextTrafficPermitted() 函数返回 false。
 * 如果您的应用需要为特定域名启用明文，您必须在应用的网络安全性配置中针对这些域名将 cleartextTrafficPermitted 显式设置为 true。
 * 具体解决方案共二步
 * 1、在清单文件AndroidManifest.xml的application标签里面设置networkSecurityConfig属性如下:
 * <?xml version="1.0" encoding="utf-8"?>
 * <manifest ... >
 *     <application
 *         android:networkSecurityConfig="@xml/network_security_config">
 *     </application>
 * </manifest>
 * 2、在资源文件夹res/xml下面创建network_security_config.xml如下：
 * <?xml version="1.0" encoding="utf-8"?>
 * <network-security-config>
 *     <base-config cleartextTrafficPermitted="true">
 *         <trust-anchors>
 *             <certificates src="system" />
 *         </trust-anchors>
 *     </base-config>
 * </network-security-config>
 *
 * --------------------------
 * --- 如果服务器为HTTPS请求 ---
 * --------------------------
 * 第一种情况：服务器未配置SSL证书
 * 可以选择忽略证书的验证，这样请求就和HTTP一样，失去了安全保障，不建议使用
 * 第二种情况：服务器正确配置SSL证书
 * 1、服务器打开TLS1.1和TLS1.2
 * 2、在android4.4及以下版本默认不支持TLS1.2，需要开启对TLS1.2的支持
 *
 * @author Cuizhen
 * @date 2019/1/4
 */
public class HttpsCompat {

    public static void ignoreSSLForHttpsURLConnection() {
        HttpsURLConnection.setDefaultHostnameVerifier(HttpsCompat.getIgnoreHostnameVerifier());
        HttpsURLConnection.setDefaultSSLSocketFactory(HttpsCompat.getIgnoreSSLSocketFactory());
    }

    public static void enableTls12ForHttpsURLConnection() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            SSLSocketFactory ssl = HttpsCompat.getEnableTls12SSLSocketFactory();
            if (ssl != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl);
            }
        }
    }

    /**
     * 获取开启TLS1.2的SSLSocketFactory
     * 建议在android4.4及以下版本调用
     */
    public static SSLSocketFactory getEnableTls12SSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
            return new Tls12SocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取忽略证书的HostnameVerifier
     * 与{@link #getIgnoreSSLSocketFactory()}同时配置使用
     */
    public static HostnameVerifier getIgnoreHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }

    /**
     * 获取忽略证书的SSLSocketFactory
     * 与{@link #getIgnoreHostnameVerifier()}同时配置使用
     */
    public static SSLSocketFactory getIgnoreSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static TrustManager[] getTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }
}
