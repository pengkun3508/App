package com.vinnlook.www.utils;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.sp.SPTextSizeUtils;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/4/26
 */
public class WebViewUtils {

    public static void init(@NonNull WebView webView) {
        WebSettings webSettings = webView.getSettings();
        // 如果访问的页面中要与Javascript交互，则WebView必须设置支持Javascript
        webSettings.setJavaScriptEnabled(false);
        // 设置自适应屏幕，两者合用
        // 将图片调整到适合WebView的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        // 缩放操作
        // 支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(false);
        // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        // 隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        // 其他细节操作
        // 关闭WebView中缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        float fontSize = ResUtils.getDimens(R.dimen.text_size_15) * SPTextSizeUtils.instance().getTextSize();
//        Log.i(TAG, "initView: fontSize = " + fontSize);
        webSettings.setDefaultFontSize((int) fontSize);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        // 此方法禁止APP使用默认浏览器，必须写 不然会跳出APP 打开默认浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String s) {
                //必须重写的方法 解决了优酷 百度视频不播放视频加载失败的问题
//                if(Config.USER_AGREEMENT_URL.startsWith("intent")||Config.USER_AGREEMENT_URL.startsWith("youku")){
//                    return true;
//                }else{
//                    return super.shouldOverrideUrlLoading(view, Config.USER_AGREEMENT_URL);
//                }
                return true;
            }
        });
    }

    public static void recycle(@NonNull WebView webView) {
        // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
        // destory()
        ViewParent parent = webView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(webView);
        }
        webView.stopLoading();
        // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearHistory();
        webView.clearView();
        webView.removeAllViews();
        webView.destroy();
    }
}
