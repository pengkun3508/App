package com.vinnlook.www.surface.member;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.presenter.HomeFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;
import com.vinnlook.www.utils.sp.SPTextSizeUtils;

import butterknife.BindView;

/**
 * @Description:神秘礼券
 * @Time:2020/10/16$
 * @Author:pk$
 */
public class FragmentShengRi extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentView {
    @BindView(R.id.see_member_text1)
    TextView seeMemberText1;
    @BindView(R.id.wv_web)
    WebView wvWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member_see;
    }

    @Override
    protected HomeFragmentPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        seeMemberText1.setText("尊享神秘礼券");

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
        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
        wvWeb.setVerticalScrollBarEnabled(false); //垂直不显示
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
        wvWeb.loadUrl("http:h5.jealook.com/vinnlook/vip7.html");
    }

    @Override
    public void getAppUpdateSuccess(int code, SignBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    @Override
    public void getHomeDataSuccess(int code, HomeDataBean data) {

    }

    @Override
    public void getHomeDataFail(int code, String msg) {

    }

    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {

    }

    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    @Override
    public void getAddShopCarSuccess(int code, Object data) {

    }

    @Override
    public void getAddShopCarFail(int code, String msg) {

    }

    @Override
    public void getTypeShopSuccess_1(int code, MoveDataBean data) {

    }

    @Override
    public void getTypeShopFail_1(int code, String msg) {

    }
}
