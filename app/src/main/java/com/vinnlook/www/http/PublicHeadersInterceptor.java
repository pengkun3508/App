package com.vinnlook.www.http;

import android.util.Log;

import com.dm.lib.utils.coder.MD5Coder;
import com.vinnlook.www.common.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：添加公共请求头
 * 包含2个请求头：
 * TIME：时间戳，正式请求接口之前调用接口返回的time
 * SIGN：签名，正式请求接口之前调用接口返回的token，经过一定规则的加密后生成的签名
 *
 * @author Cuizhen
 * @date 2018/11/30
 */
public class PublicHeadersInterceptor implements Interceptor {

    private static String TIME = "";
    private static String TOKEN = "";

    public static void updateTime(String time) {
        PublicHeadersInterceptor.TIME = time;
    }

    public static void updateToken(String token) {
        PublicHeadersInterceptor.TOKEN = token;
        Log.e("return_sign", "==updateToken==" + TOKEN);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .header(Constant.PUBLIC_HEADER_TIME_NAME, TIME)
//                .header(Constant.PUBLIC_HEADER_SIGN_NAME, getSign(request))
                .build();
        return chain.proceed(request);
    }

    public static String getSign(String request) {
        Log.e("return_sign", "==TOKEN==" + TOKEN +"==Time=="+ TIME);
        return MD5Coder.encode(MD5Coder.encode(TOKEN+ TIME) );
//        return MD5Coder.encode(MD5Coder.encode(request.url().url().toString() + TIME));

    }
}


