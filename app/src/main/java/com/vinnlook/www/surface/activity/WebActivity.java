package com.vinnlook.www.surface.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dm.lib.core.mvp.MvpPresenter;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.utils.sp.SPTextSizeUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.io.File;

import butterknife.BindView;


/**
 * 描述：
 *
 * @author ANyu
 * @date 2019\4\13 0013
 */
public class WebActivity extends BaseActivity {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.wv_web)
    WebView wvWeb;
    String url;
    Uri userPickedUri;

    public static void startSelf(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected MvpPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        url = getIntent().getStringExtra("url");
        userPickedUri = Uri.parse(url);
        Log.e("WebActivity", "==userPickedUri==" + userPickedUri);
        WebSettings webSettings = wvWeb.getSettings();
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
        webSettings.setBuiltInZoomControls(false);
        // 隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(true);
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
        float fontSize = getResources().getDimension(R.dimen.text_size_15) * SPTextSizeUtils.instance().getTextSize();
//        Log.i(TAG, "initView: fontSize = " + fontSize);
        webSettings.setDefaultFontSize((int) fontSize);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        // 此方法禁止APP使用默认浏览器，必须写 不然会跳出APP 打开默认浏览器
        wvWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                actionBar.setTitle(title);
            }
        });
        wvWeb.setWebViewClient(new WebViewClient() {

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

    @Override
    protected void loadData() {
        Log.e("WebActivity", "==toString==" + userPickedUri.toString());
        wvWeb.loadUrl(userPickedUri.toString());
    }


}
