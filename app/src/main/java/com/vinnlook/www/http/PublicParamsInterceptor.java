package com.vinnlook.www.http;

import android.text.TextUtils;

import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.DeviceIdUtils;
import com.dm.lib.utils.LogUtils;
import com.vinnlook.www.base.App;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.jpush.JPushHelper;
import com.vinnlook.www.utils.PreferenceHelper;
import com.vinnlook.www.utils.UserUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import per.goweii.rxhttp.request.utils.JsonObjUtils;


/**
 * 描述：为请求添加公共参数，把正式参数和公共请求参数合并为一个参数传入后台（为方便以后的双向加密和解密）
 * 分为两种请求方式：GET和POST
 * 1、GET：get请求参数以url后拼接参数的形式传入
 * 在retrofit2中定义API时为给参数添加{@link retrofit2.http.Field}注解
 * 2、POST：post请求参数以请求体方式传入
 * 在retrofit2中定义API时为给参数添加{@link retrofit2.http.Query}注解
 * 在POST请求有参数传入时，记得为API方法加上{@link retrofit2.http.FormUrlEncoded}注解
 * 无论是GET还是POST请求，在这个拦截器中统一将参数处理为一个单一的参数，KEY为data，VALUE为正式参数和公共请求参数合并后的json字符串
 * 该json的生成方法见{@link JsonObjUtils}
 *
 * @author Cuizhen
 * @date 2018/11/30
 */
public class PublicParamsInterceptor implements Interceptor {

    private static final String GET = "GET";
    private static final String POST = "POST";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        if (TextUtils.equals(method, GET)) {
            request = addForGet(request);
        } else if (TextUtils.equals(method, POST)) {
            request = addForPost(request);
        }
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        String responseBodys = null;
        if (responseBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(Charset.forName("UTF-8"));
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            assert charset != null;
            responseBodys = buffer.clone().readString(charset);
        }
        LogUtils.e("PublicParamsInterceptor", "返回数据=" + responseBodys);
        return response;
    }

    private Request addForGet(Request request) {
        List<Param> params = getPublicParams();
        HttpUrl httpUrl = request.url();
        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
        for (int i = 0; i < httpUrl.querySize(); i++) {
            String name = httpUrl.queryParameterName(i);
            String value = httpUrl.queryParameterValue(i);
            params.add(new Param(name, value));
            httpUrlBuilder.removeAllQueryParameters(name);
        }
        JsonObjUtils jsonObjUtils = JsonObjUtils.create();
        for (Param param : params) {
            jsonObjUtils.add(param.getKey(), param.getValue());
        }
        String json = jsonObjUtils.toJson();
        PublicHeadersInterceptor.updateToken(json);
        httpUrlBuilder.setQueryParameter(Constant.PUBLIC_PARAM_KEY, json);
        LogUtils.e("PublicParamsInterceptor", "==data=" + json);
        return request.newBuilder()
                .url(httpUrlBuilder.build())
                .header(Constant.PUBLIC_HEADER_SIGN_NAME, PublicHeadersInterceptor.getSign(json))
                .build();
    }

    private Request addForPost(Request request) {
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return request;
        } else if (requestBody instanceof FormBody) {
            List<Param> params = getPublicParams();
            FormBody formBody = (FormBody) requestBody;
            for (int i = 0; i < formBody.size(); i++) {
                params.add(new Param(formBody.name(i), formBody.value(i)));
            }
            JsonObjUtils jsonObjUtils = JsonObjUtils.create();
            for (Param param : params) {
                jsonObjUtils.add(param.getKey(), param.getValue());
            }
            String json = jsonObjUtils.toJson();
            LogUtils.i("PublicParamsInterceptor", "data=" + json);
            PublicHeadersInterceptor.updateToken(json);
            FormBody.Builder formBodyBuilder = new FormBody.Builder()
                    .add(Constant.PUBLIC_PARAM_KEY, json);
            return request.newBuilder()
                    .header(Constant.PUBLIC_HEADER_SIGN_NAME, PublicHeadersInterceptor.getSign(json))
                    .post(formBodyBuilder.build())
                    .build();
        } else if (requestBody instanceof MultipartBody) {
            MultipartBody multipartBody = (MultipartBody) requestBody;
//            return request;
            return request.newBuilder().post(multipartBody).build();
        } else {
            try {
                if (requestBody.contentLength() == 0) {
                    List<Param> params = getPublicParams();
                    JsonObjUtils jsonObjUtils = JsonObjUtils.create();
                    for (Param param : params) {
                        jsonObjUtils.add(param.getKey(), param.getValue());
                    }
                    String json = jsonObjUtils.toJson();
                    LogUtils.i("PublicParamsInterceptor", "data=" + json);
                    FormBody.Builder formBodyBuilder = new FormBody.Builder()
                            .add(Constant.PUBLIC_PARAM_KEY, json);
                    return request.newBuilder()
                            .post(formBodyBuilder.build())
                            .build();
                } else {
                    return request;
                }
            } catch (IOException e) {
                return request;
            }
        }
    }

    public static List<Param> getPublicParams() {
        List<Param> params = new ArrayList<>();

        params.add(new Param(Constant.PUBLIC_PARAM_SYSTEM_KEY, Constant.PUBLIC_PARAM_SYSTEM_VALUE));
        params.add(new Param(Constant.PUBLIC_PARAM_VERSION_KEY, String.valueOf(AppInfoUtils.getVersionName())));
        params.add(new Param(Constant.PUBLIC_PARAM_USER_ID_KEY, UserUtils.getInstance().getUserId()));
        params.add(new Param(Constant.PUBLIC_PARAM_USER_DEVICE_KEY, PreferenceHelper.readString(App.getInstance(),
                Constant.Sharepre_Name, Constant.PUBLIC_PARAM_USER_DEVICE_KEY)));
//        params.add(new Param(Constant.PUBLIC_PARAM_USER_ID_KEY, "46683"));
//        params.add(new Param(Constant.PUBLIC_PARAM_USER_DEVICE_KEY, DeviceIdUtils.getId()));
//        params.add(new Param(Constant.PUBLIC_PARAM_JPUSH_DEVICE_KEY, JPushHelper.getId()));
//        params.add(new Param(Constant.PUBLIC_PARAM_DEVICE_ID_KEY, App.getDeviceId));//设备ID


        return params;
    }
}
