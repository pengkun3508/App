package com.vinnlook.www.http;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vinnlook.www.common.Config;

import okhttp3.Interceptor;
import per.goweii.rxhttp.request.setting.DefaultRequestSetting;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/10/17
 */
public class RxHttpRequestSetting extends DefaultRequestSetting {


    @Override
    public long getTimeout() {
        return Config.HTTP_TIMEOUT;
    }

    @NonNull
    @Override
    public String getBaseUrl() {
        Log.e("请求域名","网络域名===="+ProjectApi.ApiConfig.BASE_URL);
        return ProjectApi.ApiConfig.BASE_URL;
    }

    @Override
    public int getSuccessCode() {
        return ProjectApi.ApiCode.SUCCESS;
    }

    @Override
    public int[] getMultiSuccessCode() {
        return new int[]{ProjectApi.ApiCode.SUCCESS_NO_DATA, ProjectApi.ApiCode.SUCCESS_OLD,ProjectApi.ApiCode.FAILED};
    }

    @Nullable
    @Override
    public Interceptor[] getInterceptors() {
        return new Interceptor[]{new PublicHeadersInterceptor(), new PublicParamsInterceptor()};
    }

}
