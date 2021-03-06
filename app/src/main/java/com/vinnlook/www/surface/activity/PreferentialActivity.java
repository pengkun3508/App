package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.ConponList_1Adapter;
import com.vinnlook.www.surface.adapter.PreferentialAdapter;
import com.vinnlook.www.surface.bean.PreferentialBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.presenter.PreferentialPresenter;
import com.vinnlook.www.surface.mvp.view.PreferentialView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.sp.SPTextSizeUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: ???????????????
 * @Time:2020/12/11$
 * @Author:pk$
 */
public class PreferentialActivity extends BaseActivity<PreferentialPresenter> implements PreferentialView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.prefer_text_name)
    TextView preferTextName;
    @BindView(R.id.prefer_guize_btn)
    LinearLayout preferGuizeBtn;
    @BindView(R.id.prefer_time_text_days)
    TextView preferTimeTextDays;
    @BindView(R.id.prefer_time_text_hours)
    TextView preferTimeTextHours;
    @BindView(R.id.prefer_time_text_minutes)
    TextView preferTimeTextMinutes;
    @BindView(R.id.prefer_time_text_seconds)
    TextView preferTimeTextSeconds;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.prefer_price)
    TextView preferPrice;
    @BindView(R.id.prefer_flag)
    TextView preferFlag;
    @BindView(R.id.prefer_go_btn)
    RoundTextView preferGoBtn;

    int dt;
    String dayss;
    String hourss;
    String minutess;
    String secondss;

    PreferentialBean preferentialBean;
    PreferentialAdapter adapter;

    PreferentialBean.ShopListBean listBean;
    static String actId;
    String getSearch_attr;

    public PopupWindow popupwindow;
    @BindView(R.id.title_back_btn)
    ImageView titleBackBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.prefer_text_flag)
    TextView preferTextFlag;


    public static void startSelf(Context context, String actIds) {
        Intent intent = new Intent(context, PreferentialActivity.class);
        context.startActivity(intent);
        actId = actIds;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preferential;
    }

    @Override
    protected PreferentialPresenter initPresenter() {
        return new PreferentialPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //?????????
        adapter = new PreferentialAdapter(getActivity());
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager1);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //?????????????????????????????????????????????
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter1.getData().get(position).getGoods_id(), adapter1.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");


            }
        });
        //????????????
        adapter.setPreferClickListener(new PreferentialAdapter.PreferClickListener() {
            @Override
            public void onGoReClickListener(PreferentialBean.ShopListBean data, String getGoods_id, String getSearch_attrs) {
                Log.e("rebang_add_car", "???????????????11111111111111");
                listBean = data;
                getSearch_attr = getSearch_attrs;
//                presenter.getTypeShopData(getGoods_id);
                presenter.getTypeShopData(getGoods_id);
            }
        });

        //????????????
        preferGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MainShoppingEvent("10").post();
                CacheActivity.finishActivity();
            }
        });


        //??????
        preferGuizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WebActivity.startSelf(PreferentialActivity.this, preferentialBean.getUrl());

                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


            }
        });


    }

    //????????????
    private void initmPopupWindowView() {
        ImageView per_clear;
        WebView per_web;
        ConponList_1Adapter adapter1;
        Log.e("", "");
        // // ???????????????????????????pop.xml?????????preferGuizeBtn
        View customView = getLayoutInflater().inflate(R.layout.prefer_guize_layout, null, false);
        per_clear = customView.findViewById(R.id.per_clear);//??????
        per_web = customView.findViewById(R.id.per_web);//list
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);


        WebSettings webSettings = per_web.getSettings();
        // ??????????????????????????????Javascript????????????WebView??????????????????Javascript
        webSettings.setJavaScriptEnabled(false);
        // ????????????????????????????????????
        // ????????????????????????WebView?????????
        webSettings.setUseWideViewPort(true);
        // ????????????????????????
        webSettings.setLoadWithOverviewMode(true);
        // ????????????
        // ????????????????????????true??????????????????????????????
        webSettings.setSupportZoom(false);
        // ????????????????????????????????????false?????????WebView????????????
        webSettings.setBuiltInZoomControls(false);
        // ???????????????????????????
        webSettings.setDisplayZoomControls(true);
        // ??????????????????
        // ??????WebView?????????
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // ????????????????????????
        webSettings.setAllowFileAccess(true);
        // ????????????JS???????????????
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // ????????????????????????
        webSettings.setLoadsImagesAutomatically(true);
        // ??????????????????
        webSettings.setDefaultTextEncodingName("utf-8");
        float fontSize = getResources().getDimension(R.dimen.text_size_15) * SPTextSizeUtils.instance().getTextSize();
//        Log.i(TAG, "initView: fontSize = " + fontSize);
        webSettings.setDefaultFontSize((int) fontSize);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true); // ?????????
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // ?????????????????????
        // ???????????????APP????????????????????????????????? ???????????????APP ?????????????????????
        per_web.setWebViewClient(new WebViewClient() {

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
                return true;
            }
        });
        Log.e("????????????", "===getUrl===" + preferentialBean.getUrl());

        per_web.loadUrl(preferentialBean.getUrl());

        // ?????????view??????????????????
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //??????
        per_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getPreferentialList(actId);//????????????

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * ??????????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getPreferentialListSuccess(int code, PreferentialBean data) {
        preferentialBean = data;
//        titleName.setText(data.getActive_name());
        preferTextFlag.setText(data.getActive_name());
        preferTextName.setText(data.getAct_name());
        preferPrice.setText("?????????" + Html.fromHtml("&yen") + data.getPrice());
        preferFlag.setText(data.getFlag());
        //?????????????????????---ms
        dt = data.getCount_down();
        handler.sendEmptyMessageDelayed(0, 1000);
        adapter.setData(data.getShop_list());


    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getPreferentialListFail(int code, String msg) {
    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        Log.e("getTypeShopSuccess", "??????????????????");
        Log.e("getTypeShopSuccess", "MoveDataBea======" + data.getAttr().get(0).getValue().get(0).getBanner());
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();

        Log.e("getTypeShopSuccess", "getGoods_thumb" + listBean.getGoods_thumb());
        Log.e("getTypeShopSuccess", "getSearch_attr" + listBean.getSearch_attr());
        Log.e("getTypeShopSuccess", "getGoods_thumb" + listBean.getGoods_thumb());


        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBean.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBean.getSearch_attr());
        infoBean.setGoods_id(listBean.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(listBean.getProduct_price());
        data.setInfo(infoBean);

        TypeSelectDialog.with(getActivity(), data, listBean.getSearch_attr(), "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, ProductBean productBean, String mmake) {

//                presenter.getModifyType(mark, getRec_id, num, product_id);
                Log.e("getTypeShopSuccess", "TypeSelectDialog");
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();


    }


    //????????????--??????????????????????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ShopTypeDataEvent event) {
        List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBannerEvents = event.getBanner().getBanner();
        if (getBannerEvents.size() > 0) {
            if (getBannerEvents.size() == 1) {
                if (getBannerEvents.get(0).getType() == 1) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(0).getUrl());
                } else if (getBannerEvents.get(0).getType() == 2) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(1).getUrl());
                }
            } else if (getBannerEvents.size() > 1) {
                if (getBannerEvents.get(0).getType() == 1) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(1).getUrl());
                } else if (getBannerEvents.get(0).getType() == 2) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(2).getUrl());
                }
            }

        }
    }


    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * ?????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        presenter.getPreferentialList(actId);//????????????
        TypeSelectDialog.dismiss();
        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
    }


    /**
     * ?????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dt = dt - 1;
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//            String time = format.format(new Date(dt));

//            long hours = dt / (60 * 60);
//            long minutes = (dt / 60) % 60;
//            long seconds = dt % 60;

//            long days = dt / (1000 * 60 * 60 * 24);
//            long hours1 = dt % (1000 * 60 * 60 * 24) / (1000 * 60 * 60);
//            long minutess1 = dt % (1000 * 60 * 60) / (1000 * 60);
//            long secondss1 = ((dt % (1000 * 60)) / 1000);

            long days = dt / (60 * 60 * 24);
            long hours1 = dt % (60 * 60 * 24) / (60 * 60);
            long minutess1 = dt % (60 * 60) / 60;
            long secondss1 = dt % 60;

            if (days < 10) {
                dayss = "0" + days;
            } else {
                dayss = String.valueOf(days);
            }
            if (hours1 < 10) {
                hourss = "0" + hours1;
            } else {
                hourss = String.valueOf(hours1);
            }
            if (minutess1 < 10) {
                minutess = "0" + minutess1;
            } else {
                minutess = String.valueOf(minutess1);
            }
            if (secondss1 < 10) {
                secondss = "0" + secondss1;
            } else {
                secondss = String.valueOf(secondss1);
            }
            preferTimeTextDays.setText(dayss);
            preferTimeTextHours.setText(hourss);
            preferTimeTextMinutes.setText(minutess);
            preferTimeTextSeconds.setText(secondss);

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dt <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

}
