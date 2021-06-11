package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.utils.StatusBarUtils;
import com.hjhrq1991.library.BridgeHandler;
import com.hjhrq1991.library.BridgeWebView;
import com.hjhrq1991.library.BridgeWebViewClient;
import com.hjhrq1991.library.CallBackFunction;
import com.hjhrq1991.library.DefaultHandler;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.surface.mvp.presenter.Web3Presenter;
import com.vinnlook.www.surface.mvp.view.Web3View;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:618活动页面H5
 * @Time:2021/5/20$
 * @Author:pk$
 */
public class WebActivity3 extends BaseActivity<Web3Presenter> implements Web3View {

    static String url;
    static String title_color;
    //    @BindView(R.id.action_bar)
//    ActionBarSimple actionBar;
    @BindView(R.id.wv_web)
    BridgeWebView webView;
    @BindView(R.id.web_title_back)
    ImageView webTitleBack;
    @BindView(R.id.web_title_text)
    TextView webTitleText;
    @BindView(R.id.layout_bottoms)
    LinearLayout layoutBottoms;
    @BindView(R.id.web_title_back_layout)
    LinearLayout webTitleBackLayout;

    Uri userPickedUri;

    String goods_id;
    String search_attr;
    String is_group;



    public static void startSelf(Context context, String urls, String title_colors) {
        Intent intent = new Intent(context, WebActivity3.class);
        context.startActivity(intent);
        url = urls;
        title_color = title_colors;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web3;
    }

    @Override
    protected Web3Presenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        if (title_color != null && !title_color.equals("")) {
            StatusBarUtils.setStatusBarMode(getActivity(), false);
            layoutBottoms.setBackgroundColor(Color.parseColor(title_color));//使用颜色的16进制类型值
            webTitleBack.setBackgroundResource(R.mipmap.back_white);
        } else {
            StatusBarUtils.setStatusBarMode(getActivity(), true);
            webTitleBack.setBackgroundResource(R.mipmap.back);
        }

        CacheActivity.addActivity(this);

        webTitleBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Log.e("===url===", "==url==" + url);
        userPickedUri = Uri.parse(url);
//        actionBar.setTitle("618活动");
        webView.setDefaultHandler(new DefaultHandler());
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//  支持Javascript 与js交互
        webSettings.setBlockNetworkImage(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//  支持通过JS打开新窗口
        webSettings.setDefaultTextEncodingName("utf-8");
        //  设置编码格式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setMediaPlaybackRequiresUserGesture(false);//自动播放视频
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true); //  支持缩放
        webSettings.setTextZoom(100);//缩放百分比
        webSettings.setBuiltInZoomControls(true);//  设置内置的缩放控件
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);//  自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//开启硬件加速


        BridgeWebViewClient bridgeWebViewClient = new BridgeWebViewClient(webView) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("onWindowFocusChanged", "==网络问题==111===");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onWindowFocusChanged", "==网络问题==222===");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("onWindowFocusChanged", "==网络问题=222333=" + error);
            }

            //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e("onWindowFocusChanged", "==网络问题=333=" + failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();  // 接受所有网站的证书
            }
        };

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                webTitleText.setText(title);
                Log.e("setWebChromeClient", "==标题==111===" + title);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                Log.e("==uploadMsg==", "===data====" + uploadMsg);
            }
        });


        webView.setWebViewClient(bridgeWebViewClient);

//        http://h5.jealook.com/test-activeH5/index.html?userId=27359
        webView.loadUrl(userPickedUri.toString());
//        webView.loadUrl("http://h5.jealook.com/test-activeH5/index.html?userId=27359");


        /**
         * 注册交互方法
         */
        registerClose();

    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();  // 接受所有网站的证书
//        super.onReceivedSslError(view, handler, error);
    }

    /**
     * JS调本地方法
     */
    private void registerClose() {
        /**
         * 领取优惠券
         */
//        webView.registerHandler("getCoupons", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                Log.e("==领取优惠券==", "===data====" + data);
//                Log.e("==领取优惠券==", "===function====" + function);
//                Toast.makeText(WebActivity3.this, "领取优惠券======" + data, Toast.LENGTH_SHORT).show();
//            }
//        });

        /**
         * 进入会员购买页面
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getPlusMember", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MemberActivity_1.startSelf(getContext(), "1");
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /**
         * 进入积分商城页面
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getPointsMall", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    PointsMallActivity.startSelf(getContext());
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }

            }
        });


        /**
         * 进入详情页面
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getDetails", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("==返回数据==", "===data====" + data);
                try {
                    //解析
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject datas = jsonObject.getJSONObject("data");
                    goods_id = datas.get("goods_id").toString();
                    search_attr = datas.get("search_attr").toString();
                    is_group = datas.get("is_group").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("==返回数据==", "===goods_id====" + goods_id);
                Log.e("==返回数据==", "===search_attr====" + search_attr);
                Log.e("==返回数据==", "===is_group====" + is_group);
                if (is_group.equals("0")) {//普通商品
                    MoveAbooutActivity_3.startSelf(WebActivity3.this, goods_id, search_attr);
//                    CacheActivity.finishActivity();
                } else if (is_group.equals("1")) {//拼团商品
                    MoveAbooutActivity_4.startSelf(WebActivity3.this, goods_id, search_attr, "", "");
//                    CacheActivity.finishActivity();
                }
            }
        });

        /**
         * 进入优惠券列表
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getCouponList", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    CouponActivity.startSelf(getContext());
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**
         * 进入分类
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getClassify", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                new MainHomeActivityEvent("5").post();
                CacheActivity.finishActivity();

            }
        });

        /**
         * 进入拼团列表
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getGroupList", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                GroupWorkGoActivity.startSelf(getActivity());
                CacheActivity.finishActivity();

            }
        });

        /**
         * 进入百万补贴列表
         * getCoupons--与H5对应的方法名称
         */
        webView.registerHandler("getMillionSubsidy", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                GroupWorkGoActivity.startSelf(getActivity());
                CacheActivity.finishActivity();

            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}