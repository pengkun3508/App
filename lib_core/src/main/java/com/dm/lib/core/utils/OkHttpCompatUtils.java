package com.dm.lib.core.utils;

import android.os.Build;

import com.dm.lib.utils.https.HttpsCompat;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/1/4
 */
public class OkHttpCompatUtils {

    public static void ignoreSSLForOkHttp(OkHttpClient.Builder builder) {
        builder.hostnameVerifier(HttpsCompat.getIgnoreHostnameVerifier())
                .sslSocketFactory(HttpsCompat.getIgnoreSSLSocketFactory());
    }

    public static void enableTls12ForOkHttp(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            SSLSocketFactory ssl = HttpsCompat.getEnableTls12SSLSocketFactory();
            if (ssl != null) {
                builder.sslSocketFactory(ssl);
            }
        }
    }
}
