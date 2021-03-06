package com.vinnlook.www.surface.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.SPUtils;
import com.flyco.roundview.RoundLinearLayout;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.GuangGaoEvent;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.event.StratMoveAbooutActivity_1;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.receiver.AppUpdateReceiver;
import com.vinnlook.www.surface.activity.BrandActivity;
import com.vinnlook.www.surface.activity.CommodityActivity;
import com.vinnlook.www.surface.activity.HaiTaoClassActivity;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.HuoDongZoneActivity_1;
import com.vinnlook.www.surface.activity.LimitedActivity;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.NoticeActivity;
import com.vinnlook.www.surface.activity.ProductDetailsActivity;
import com.vinnlook.www.surface.activity.RankingListActivity_1;
import com.vinnlook.www.surface.activity.SearchActivity;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.adapter.BannerImgAdapter;
import com.vinnlook.www.surface.adapter.BannerImgAdapter1;
import com.vinnlook.www.surface.adapter.PaoQiList_Adapter;
import com.vinnlook.www.surface.adapter.PinpaiList_Adapter;
import com.vinnlook.www.surface.adapter.XianShiList_Adapter;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.dialog.UpdateDialog;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.presenter.HomeFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SPAppUpdateUtils;
import com.vinnlook.www.utils.StatusBarUtil;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifImageView;


/**
 * @Description:??????
 * @Time:2020/4/1 10:29
 * @Author:pk
 */
public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentView {


    @BindView(R.id.home_serachview)
    TextView homeSerachview;//?????????
    @BindView(R.id.camera_icon)
    ImageView cameraIcon;//??????
    @BindView(R.id.dot_0)
    View dot0;//???1
    @BindView(R.id.dot_1)
    View dot1;//???2
    @BindView(R.id.dot_2)
    View dot2;//???3
    @BindView(R.id.dot_3)
    View dot3;//???4
    @BindView(R.id.home_banner_fragment)
    FrameLayout homeBannerFragment;
    //    @BindView(R.id.gonggao)
//    VerticalTextview gonggao;//??????
    @BindView(R.id.recyclerv_1)
    RecyclerView recyclerv1;//????????????
    @BindView(R.id.recyclerv_2)
    RecyclerView recyclerv2;//????????????
    @BindView(R.id.recycler_paoqi)
    RecyclerView recyclerPaoqi;//????????????

    @BindView(R.id.xianshi_zanshi_text)
    TextView xianshiZanshiText;
    @BindView(R.id.pinpai_zanshi_text)
    TextView pinpaiZanshiText;
    @BindView(R.id.home_product)
    ImageView homeProduct;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.xianshi_layout)
    RelativeLayout xianshi_layout;


    List<String> titleList;
    @BindView(R.id.paoqi_move_xianshi)
    TextView paoqiMoveXianshi;
    @BindView(R.id.paoqi_move_pinpai)
    TextView paoqiMovePinpai;
    @BindView(R.id.pinpai_title_img)
    ImageView pinpaiTitleImg;
    @BindView(R.id.xianshi_title_img)
    ImageView xianshiTitleImg;
    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.home_haitao_btn)
    LinearLayout homeHaitaoBtn;
    @BindView(R.id.home_ziying_btn)
    LinearLayout homeZiyingBtn;
    @BindView(R.id.home_touming_btn)
    LinearLayout homeToumingBtn;
    @BindView(R.id.home_caise_btn)
    LinearLayout homeCaiseBtn;
    @BindView(R.id.home_ripao_btn)
    LinearLayout homeRipaoBtn;
    @BindView(R.id.home_shuangzhou_btn)
    LinearLayout homeShuangzhouBtn;
    @BindView(R.id.home_yuepao_btn)
    LinearLayout homeYuepaoBtn;
    @BindView(R.id.home_xianshi_btn)
    LinearLayout homeXianshiBtn;
    @BindView(R.id.home_xianshi_time_text_days)
    TextView homeXianshiTimeTextDays;//???
    @BindView(R.id.home_xianshi_time_text_hours)
    TextView homeXianshiTimeTextHours;//???
    @BindView(R.id.home_xianshi_time_text_minutes)
    TextView homeXianshiTimeTextMinutes;//???
    @BindView(R.id.home_xianshi_time_text_seconds)
    TextView homeXianshiTimeTextSeconds;//???
    @BindView(R.id.xianshi_title_relayout)
    LinearLayout xianshiTitleRelayout;
    @BindView(R.id.banner_1)
    Banner banner1;
    @BindView(R.id.banner_img_bg)
    ImageView bannerImgBg;
    @BindView(R.id.home_scroll)
    ScrollView homeScroll;
    @BindView(R.id.home_title)
    LinearLayout homeTitle;
    @BindView(R.id.notice_layout)
    RoundLinearLayout noticeLayout;
    @BindView(R.id.test_text)
    TextView testText;
    @BindView(R.id.home_product_2)
    GifImageView homeProduct2;


    private List<View> dots;
    private List<ImageView> images;
    //???????????????????????????
    private int oldPosition = 0;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;
    private ViewPagerAdapter adapter;
    String dataId;
    int dt;
    HomeDataBean.IndexAdBean getIndex_ad;

    List<HomeDataBean.BannerBean> bannerImage;//??????
    List<HomeDataBean.ArtcleBean> getArtcle;//??????
    List<HomeDataBean.ShopBean> getShop;//??????
    //    List<HomeDataBean.DiscountBean> getDiscount;//??????
    List<HomeDataBean.BrandBean> getBrnad;//??????
    HomeDataBean.AlertAdBean getAlert_ad;//??????

    XianShiList_Adapter adapter1;
    private PinpaiList_Adapter adapter2;

    PaoQiList_Adapter paoQiAdapter3;

    HomeDataBean homeDataBean;


    String dayss;
    String hourss;
    String minutess;
    String secondss;

    public PopupWindow popupwindow;//????????????

    List<HomeDataBean.DiscountBean.ListBeanXX> getLisss;
    HomeDataBean.DiscountBean.ListBeanXX listBeanXX;
    HomeDataBean.ShopBean.DataBean dataBeanXX;
    String xianGoods_attr;

    private UpdateDialog updateDialog = null;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeFragmentPresenter initPresenter() {
        return new HomeFragmentPresenter();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
//        StatusBarUtils.setStatusBarMode(getActivity(), true);
//        gonggao.setText(14, 2, Color.parseColor("#000000"));//????????????,??????????????????
//        gonggao.setTextStillTime(2000);//????????????????????????
//        gonggao.setAnimTime(200);//????????????????????????????????????


//        //????????????
//        ObjectAnimator anim1 = ObjectAnimator.ofFloat(testText, "scaleX", 1.2f, 0.8f);
//        anim1.setRepeatCount(-1);
//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(testText, "scaleY", 1.2f, 0.8f);
//        anim2.setRepeatCount(-1);
//        AnimatorSet set = new AnimatorSet();
//        set.play(anim1).with(anim2);
//        set.setDuration(1000);
//        set.start();


        //???????????????
        dataadpter();
        banner.post(new Runnable() {
            @Override
            public void run() {
                banner.getWidth();
                double f = Float.valueOf(banner.getWidth() + "") / (350 / 150);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(banner.getWidth(), (int) f);
                banner.setLayoutParams(layoutParams);
            }
        });
//        banner1.post(new Runnable() {
//            @Override
//            public void run() {
//                banner1.getWidth();
//                double f = Float.valueOf(banner1.getWidth() + "") / (3.0);
//                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(banner.getWidth(), (int) f);
//                banner1.setLayoutParams(layoutParams);
//
//
//                bannerImgBg.getWidth();
//                double f1 = Float.valueOf(banner1.getWidth() + "") / (1.6);
//                FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(bannerImgBg.getWidth(), (int) f1);
//                bannerImgBg.setLayoutParams(layoutParams1);
//            }
//        });


        //????????????
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getHomeData();//??????????????????

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });


        homeScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int newx, int newy, int oldx, int oldy) {
                int imgHeight = bannerImgBg.getHeight() / 2;

//                newy += newy;
                //?????????
                if (newy < bannerImgBg.getHeight()) {
                    float scale = (float) newy / bannerImgBg.getHeight();
                    float alpha = scale * 255;//0.03780069*255

                    if (alpha < 160) {//9.639175
//                        ?????????????????????160????????????????????????
//                        title_button.setVisibility(View.GONE);
                        StatusBarUtil.setTranslucentForImageView(getActivity(), (int) alpha, homeTitle);
                        homeTitle.setBackgroundColor(Color.argb((int) alpha, 160, 143, 187));
//                        StatusBarUtil.setColor(getActivity(), Color.argb((int) alpha, 160, 143, 187));
//                        StatusBarUtils.setStatusBarMode(getActivity(), true);
                    } else {
//                        title_button.setVisibility(View.VISIBLE);
                        homeTitle.setBackgroundColor(Color.argb((int) alpha, 160, 143, 187));
//                        StatusBarUtil.setColor(getActivity(), Color.argb((int) alpha, 160, 143, 187));
//                        StatusBarUtils.setStatusBarMode(getActivity(), true);
                    }
//                    homeTitle.setBackgroundColor(Color.argb((int) alpha, 160, 143, 187));
//                    title.setTextColor( Color.argb((int) alpha, 255, 255, 255));
//                    titlel.setTextColor( Color.argb((int) alpha, 255, 255, 255));
//                    titler.setTextColor( Color.argb((int) alpha, 255, 255, 255));
                } else {
//                    StatusBarUtils.setStatusBarMode(getActivity(), true);
                    homeTitle.setBackgroundColor(Color.parseColor("#A08FBB"));
//                    StatusBarUtil.setColor(getActivity(), Color.parseColor("#A08FBB"));
                }


            }


        });


    }


    /**
     * ???????????????
     */
    private void dataadpter() {
        //????????????
        adapter1 = new XianShiList_Adapter(getActivity());
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        recyclerv1.setLayoutManager(manager1);
        recyclerv1.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerv1.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerv1.setNestedScrollingEnabled(false);
        recyclerv1.setHasFixedSize(true);
        recyclerv1.setAdapter(adapter1);
        //?????????????????????????????????????????????
        adapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter1.getData().get(position).getGoods_id(), adapter1.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter1.getData().get(position).getGoods_id(), adapter1.getData().get(position).getSearch_attr(),"");


            }
        });
        adapter1.setXianShiGoShoppingClickListener(new XianShiList_Adapter.XianShiGoShoppingClickListener() {
            @Override
            public void onGoClickListener(HomeDataBean.DiscountBean.ListBeanXX data, String getGoods_id, String getSearch_attr) {
                xianGoods_attr = getSearch_attr;
                listBeanXX = data;
                presenter.getTypeShopData(getGoods_id);
            }
        });


        //????????????
        adapter2 = new PinpaiList_Adapter(getActivity());
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 2);
        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerv2.setLayoutManager(manager2);
        recyclerv2.setNestedScrollingEnabled(false);
        recyclerv2.setHasFixedSize(true);
        recyclerv2.setAdapter(adapter2);
        adapter2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {//????????????????????????
                CommodityActivity.startSelf(getContext(), adapter2.getData().get(position).getBrand_id(), "", adapter2.getData().get(position).getBrand_name(), "0");

            }
        });


        //????????????
        paoQiAdapter3 = new PaoQiList_Adapter(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        recyclerPaoqi.setLayoutManager(manager3);
        recyclerPaoqi.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerPaoqi.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerPaoqi.setNestedScrollingEnabled(false);
        recyclerPaoqi.setHasFixedSize(true);
        recyclerPaoqi.setAdapter(paoQiAdapter3);
        paoQiAdapter3.setPaoQiAdapter_1ClickListener(new PaoQiList_Adapter.PaoQiAdapter_1ClickListener() {
            @Override
            public void onGoClickListener(HomeDataBean.ShopBean.DataBean data, String getGoods_id, String getSearch_attr) {
                xianGoods_attr = getSearch_attr;
                dataBeanXX = data;
                presenter.getTypeShopData_1(getGoods_id);
            }

        });


    }

    @Override
    protected void loadData() {
        presenter.getHomeData();//??????????????????
    }

    @Override
    public void onClickCheckLogin(View v) {

    }

    @OnClick({R.id.home_serachview, R.id.xianshi_title_img, R.id.paoqi_move_xianshi, R.id.pinpai_title_img, R.id.paoqi_move_pinpai, R.id.home_product,
            R.id.home_haitao_btn, R.id.home_ziying_btn, R.id.home_touming_btn, R.id.home_caise_btn, R.id.home_ripao_btn, R.id.home_shuangzhou_btn,
            R.id.home_yuepao_btn, R.id.home_xianshi_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_serachview://?????????
                SearchActivity.startSelf(getContext());
                break;
            case R.id.xianshi_title_img://??????Title--????????????
                LimitedActivity.startSelf(getContext());
                break;
//            case R.id.paoqi_move_xianshi://????????????
//                LimitedActivity.startSelf(getContext());
//                break;
            case R.id.pinpai_title_img://??????Title--????????????
                BrandActivity.startSelf(getContext(), "0");
                break;
            case R.id.home_haitao_btn://????????????
//                HomePublicClassActivity.startSelf(getActivity(), "????????????", "2", "", "");
                HaiTaoClassActivity.startSelf(getActivity(), "????????????", "2");

                break;
            case R.id.home_ziying_btn://????????????
//                HomePublicClassActivity.startSelf(getActivity(), "????????????", "1", "", "","");

                HaiTaoClassActivity.startSelf(getActivity(), "????????????", "1");
                break;
            case R.id.home_touming_btn://???????????????
                HomePublicClassActivity.startSelf(getActivity(), "???????????????", "", "1", "", "", "");
                break;
            case R.id.home_caise_btn://??????????????????--?????????
                HomePublicClassActivity.startSelf(getActivity(), "???????????????", "", "2", "", "", "");
                break;
            case R.id.home_ripao_btn://????????????--????????????--????????????
                HomePublicClassActivity.startSelf(getActivity(), "??????????????????", "", "", "1", "", "");
//                Toast.makeText(getActivity(), "????????????????????????......", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_shuangzhou_btn://????????????--????????????--????????????
                HomePublicClassActivity.startSelf(getActivity(), "????????????", "", "", "2", "", "");
//                Toast.makeText(getActivity(), "????????????????????????......", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_yuepao_btn://????????????--?????????
//                RankingListActivity.startSelf(getActivity());
                RankingListActivity_1.startSelf(getActivity());
                break;
            case R.id.home_xianshi_btn://????????????--????????????--Plus ??????
//                LimitedActivity.startSelf(getContext());
//                HuoDongZoneActivity.startSelf(getActivity());
//                HuoDongZoneActivity_1.startSelf(getActivity());

                if (!UserUtils.getInstance().getUserId().equals("")) {
//                        MemberActivity.startSelf(getContext(), "2");//??????????????????
                    MemberActivity_1.startSelf(getContext(), "2");//??????????????????
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


    //????????????---???H5??????????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(StratMoveAbooutActivity_1 event) {
        Log.e("HomeFragment", "=???H5??????????????????==");
        Log.e("HomeFragment", "=event.getGood_id()==" + event.getGood_id());

//        MoveAbooutActivity_1.startSelf(getActivity(), event.getGood_id(), event.getSearch_attr());
        MoveAbooutActivity_3.startSelf(getActivity(), event.getGood_id(), event.getSearch_attr(),"");

//        }
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
     * @param bean
     */
    @Override
    public void getAppUpdateSuccess(int code, SignBean bean) {
        if (bean.getVersion() != null && !TextUtils.isEmpty(bean.getVersion().getUrl())) {
            if (bean.getVersion().getMust() == 2) {
                AppUpdateReceiver.send(bean.getVersion());
            } else if (bean.getVersion().getMust() == 3) {
                if (SPAppUpdateUtils.instance().isShouldUpdate1()) {//??????????????????
                    Log.e("getAppUpdateSuccess", "updateTime===" + "?????????????????????");
                    //1??????????????????2??????????????????3??????????????????4???????????????
                    AppUpdateReceiver.send(bean.getVersion());
                }
            } else {
                if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//?????????
                    return;
                } else {//????????????
                    Log.e("getAppUpdateSuccess", "isShouldUpdate1_1===" + SPAppUpdateUtils.instance().isShouldUpdate1_1());
                    if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//??????????????????
                        if (popupwindow != null && popupwindow.isShowing()) {
                            popupwindow.dismiss();
                            return;
                        } else {
                            initmPopupWindowView();
                            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                        }
                    }
                }
            }
        } else {
            if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//?????????
                return;
            } else {//????????????
                Log.e("getAppUpdateSuccess", "isShouldUpdate1_2===" + SPAppUpdateUtils.instance().isShouldUpdate1_1());
                if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//??????????????????
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView();
                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                }
            }
        }

        String userInfoJson = new Gson().toJson(bean);
        SPUtils.getInstance().save(Constant.KEY_SIGN_BEAN, userInfoJson);//???????????????

    }

    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GuangGaoEvent event) {
        Log.e("HomeFragment", "=??????????????????==");
        Boolean getRefresh = event.getRefresh();
//        if (getRefresh) {
        if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//??????????????????
            if (popupwindow != null && popupwindow.isShowing()) {
                popupwindow.dismiss();
                return;
            } else {
                if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//?????????
                } else {//????????????
                    initmPopupWindowView();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
            }
        }
//        }
    }

    /**
     * ????????????
     * getFeeMsg1,getMobile1, getFeeMsg2,getMobile2
     */
    public void initmPopupWindowView() {
        ImageView dialog_update_img, dialog_update_close;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.dialog_update_1, null, false);
        dialog_update_img = customView.findViewById(R.id.dialog_update_img);//??????
        dialog_update_close = customView.findViewById(R.id.dialog_update_close);//??????

        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
        popupwindow.setAnimationStyle(R.style.DialogWindowStyle);
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
//        Matrix matrix = new Matrix();           //????????????????????????
//        matrix.setTranslate(0, 0);          //??????x???y???100??????
//        matrix.preRotate(0);                   //???????????????30???
        dialog_update_img.setScaleType(ImageView.ScaleType.FIT_XY);
//        dialog_update_img.setImageMatrix(matrix);
        ImageLoader.image(getActivity(), dialog_update_img, getAlert_ad.getPhoto());

        //??????
        dialog_update_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
                final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                utils.setIgnore1();
            }
        });

        //????????????
        dialog_update_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
//                WebActivity.startSelf(getActivity(), url);

                //type--1??????????????????2??????????????????3???url;4:?????????5???????????????
                if (getAlert_ad.getType().equals("1")) {//1???????????????
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), getAlert_ad.getList().getGoods_id(), getAlert_ad.getList().getSearch_attr(),"");

                } else if (getAlert_ad.getType().equals("2")) {//2???????????????
                    ProductDetailsActivity.startSelf(getContext(), getAlert_ad.getList().getActive_id());//????????????????????????
                } else if (getAlert_ad.getType().equals("3")) {//3???url
                    WebActivity.startSelf(getActivity(), getAlert_ad.getList().getUrl());

                } else if (getAlert_ad.getType().equals("4")) {//4?????????

                } else if (getAlert_ad.getType().equals("5")) {//5?????????????????????
//                    RecommendActivity_1.startSelf(getContext(), "", getAlert_ad.getList().getId());//????????????????????????
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", getAlert_ad.getList().getId());
                } else if (getAlert_ad.getType().equals("6")) {//6???????????????
//                    MemberActivity.startSelf(getContext(), "2");//
                    MemberActivity_1.startSelf(getContext(), "2");//??????????????????  1---??????????????????2--????????????????????????????????????
                } else if (getAlert_ad.getType().equals("7")) {//7?????????????????????
                    CommodityActivity.startSelf(getContext(), getAlert_ad.getList().getActive_id(), getAlert_ad.getList().getId(), "????????????", "0");//????????????????????????
                }
                final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                utils.setIgnore1();
            }
        });


    }


    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }


    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @SuppressLint("NewApi")
    @Override
    public void getHomeDataSuccess(int code, HomeDataBean data) {
        homeDataBean = data;
        smartRefreshLayout.finishRefresh();
        if (data.getDiscount() != null && !data.getDiscount().equals("")) {
            getLisss = data.getDiscount().getList();
            if (data.getDiscount().getList().size() == 0) {
                xianshiTitleRelayout.setVisibility(View.GONE);
                xianshiTitleImg.setVisibility(View.GONE);
                recyclerv1.setVisibility(View.GONE);
            } else {
                xianshiTitleRelayout.setVisibility(View.VISIBLE);
                xianshiTitleImg.setVisibility(View.VISIBLE);
                recyclerv1.setVisibility(View.VISIBLE);
                ImageLoader.image(getActivity(), xianshiTitleImg, data.getDiscount_img());
            }
        } else {
            xianshiTitleRelayout.setVisibility(View.GONE);
            xianshiTitleImg.setVisibility(View.GONE);
            recyclerv1.setVisibility(View.GONE);
        }
        adapter1.setData(getLisss);//??????
        dt = data.getDiscount().getCount_down();

        getIndex_ad = data.getIndex_ad();
        //????????????
        if (getIndex_ad.getId().equals("") || getIndex_ad.getId() == null) {

            homeProduct2.setVisibility(View.GONE);
        } else {
            homeProduct2.setVisibility(View.VISIBLE);
//            Matrix matrix = new Matrix();           //????????????????????????
//            matrix.setTranslate(0, 0);          //??????x???y???100??????
//            matrix.preRotate(0);                   //???????????????30???
//            homeProduct2.setScaleType(ImageView.ScaleType.MATRIX);
//            homeProduct2.setImageMatrix(matrix);

            homeProduct2.setScaleType(ImageView.ScaleType.FIT_XY);

            ImageLoader.image(getActivity(), homeProduct2, getIndex_ad.getPhoto());


        }

        //????????????
        homeProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIndex_ad.getType().equals("1")) {
//                    MoveAbooutActivity_1.startSelf(getActivity(), getIndex_ad.getList().getGoods_id(), getIndex_ad.getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), getIndex_ad.getList().getGoods_id(), getIndex_ad.getList().getSearch_attr(),"");

                } else if (getIndex_ad.getType().equals("2")) {
                    ProductDetailsActivity.startSelf(getContext(), getIndex_ad.getList().getActive_id());//????????????????????????
                } else if (getIndex_ad.getType().equals("3")) {
                    WebActivity.startSelf(getActivity(), getIndex_ad.getList().getUrl());
                } else if (getIndex_ad.getType().equals("4")) {

                } else if (getIndex_ad.getType().equals("5")) {
//                    RecommendActivity_1.startSelf(getContext(), "", getIndex_ad.getList().getId());//????????????
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", getIndex_ad.getList().getId());
                } else if (getIndex_ad.getType().equals("6")) {

                    if (!UserUtils.getInstance().getUserId().equals("")) {
//                        MemberActivity.startSelf(getContext(), "2");//??????????????????
                        MemberActivity_1.startSelf(getContext(), "2");//??????????????????
                    } else {
                        Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                } else if (getIndex_ad.getType().equals("7")) {//7?????????????????????
                    CommodityActivity.startSelf(getContext(), getIndex_ad.getList().getActive_id(), getIndex_ad.getList().getId(), "????????????", "0");//????????????????????????
                } else if (getIndex_ad.getType().equals("8")) {
                    HuoDongZoneActivity_1.startSelf(getActivity());
                }

            }
        });

        getAlert_ad = data.getAlert_ad();//????????????

        //?????????????????????---ms
        handler.sendEmptyMessageDelayed(0, 1000);
        ImageLoader.image(getActivity(), pinpaiTitleImg, data.getBrand_img());
        bannerImage = data.getBanner();//??????
        getArtcle = data.getArtcle();//??????
        getShop = data.getShop();//??????
        paoQiAdapter3.setData(getShop);//??????
        getBrnad = data.getBrand().get(0);//??????

        List<HomeDataBean.BrandBean> getBrnad2 = data.getBrand().get(1);//??????


        for (int i = 0; i < getBrnad2.size(); i++) {
            getBrnad.add(getBrnad2.get(i));
        }
        adapter2.setData(getBrnad);//??????

        //BannerAdapter
        banner1.setStartPosition(0);
        BannerImgAdapter1 bannerImgAdapter1 = new BannerImgAdapter1(getActivity(), gatBannetData());
        banner1.setAdapter(bannerImgAdapter1);
        banner1.isAutoLoop(false);

        banner.setStartPosition(0);
        BannerImgAdapter bannerImgAdapter = new BannerImgAdapter(getActivity(), gatBannetData());
        banner.setAdapter(bannerImgAdapter);
        banner.setIndicator(new CircleIndicator(getActivity()));
        banner.start();
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                banner1.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {
//                Log.e("BANNER??????", "??????===" + position);
                banner1.setCurrentItem(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                banner1.setCurrentItem(state);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                SharedPreferences spf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                spf.edit().putString("dataid", dataId).commit();


                //type--1??????????????????2??????????????????3???url;4:?????????5???????????????
                if (bannerImage.get(position).getType().equals("1")) {//1???????????????
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr(),"");

                } else if (bannerImage.get(position).getType().equals("2")) {//2???????????????
                    ProductDetailsActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id());//????????????????????????
                } else if (bannerImage.get(position).getType().equals("3")) {//3???url
                    WebActivity.startSelf(getActivity(), bannerImage.get(position).getList().getUrl());

                } else if (bannerImage.get(position).getType().equals("4")) {//4?????????

                } else if (bannerImage.get(position).getType().equals("5")) {//5?????????????????????
//                    RecommendActivity_1.startSelf(getContext(), "", bannerImage.get(position).getList().getId());//????????????????????????
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", bannerImage.get(position).getList().getId());
                } else if (bannerImage.get(position).getType().equals("6")) {//6???????????????
//                    MemberActivity.startSelf(getContext(), "2");//
                    MemberActivity_1.startSelf(getContext(), "2");//??????????????????  1---??????????????????2--????????????????????????????????????
                } else if (bannerImage.get(position).getType().equals("7")) {//7?????????????????????
                    CommodityActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id(), bannerImage.get(position).getList().getId(), "????????????", "0");//????????????????????????
                }

            }
        });
        Log.e("getArtcle", "==getArtcle.size()==" + getArtcle.size());
        if (getArtcle.size() > 0) {
            viewFlipper.setVisibility(View.VISIBLE);
            titleList = new ArrayList<>();
            for (int i = 0; i < getArtcle.size(); i++) {
                titleList.add(getArtcle.get(i).getTitle());
            }
            for (int i = 0; i < titleList.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10, 10, 10, 10);
                TextView textView1 = new TextView(getActivity());
                textView1.setText(titleList.get(i));
                linearLayout.addView(textView1);
                viewFlipper.addView(linearLayout);
            }
            noticeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NoticeActivity.startSelf(getActivity());
                }
            });

        } else {
            viewFlipper.setVisibility(View.GONE);
        }
        presenter.getAppUpdate();//????????????


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
//
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
            homeXianshiTimeTextDays.setText(dayss);
            homeXianshiTimeTextHours.setText(hourss);
            homeXianshiTimeTextMinutes.setText(minutess);
            homeXianshiTimeTextSeconds.setText(secondss);


            //???????????????
            adapter1.setDatas(dayss + ":" + hourss + ":" + minutess + ":" + secondss);
            adapter1.notifyDataSetChanged();
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dt <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };


    public List<String> gatBannetData() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < bannerImage.size(); i++) {
            ids.add(bannerImage.get(i).getId());
            strings.add(bannerImage.get(i).getPhoto());
        }
        SharedPreferences spf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        spf.edit().putString("dataid", dataId).commit();
        return strings;
    }


    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getHomeDataFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }


    /**
     * @Description:??????????????????--??????
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean moveDataBeas) {
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
//        List<String> strings = new ArrayList<>();
//        strings.add(listBeanXX.getGoods_thumb());
//        infoBean.setBanner(strings);

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBeanXX.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBeanXX.getSearch_attr());
        infoBean.setGoods_id(listBeanXX.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(listBeanXX.getProduct_price());
        moveDataBeas.setInfo(infoBean);

        Log.e("??????????????????", "xianGoods_attr===" + xianGoods_attr);

        TypeSelectDialog.with(getActivity(), moveDataBeas, xianGoods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, ProductBean productBean, String mmake) {
                xianGoods_attr = "";
//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();


    }

    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * ?????????????????????--??????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        Log.e("?????????????????????", "==code==" + code);
        Log.e("?????????????????????", "==msg==" + data);
        TypeSelectDialog.dismiss();
//        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
    }

    /**
     * ?????????????????????--??????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Log.e("?????????????????????", "==code==" + code);
        Log.e("?????????????????????", "==msg==" + msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


    }


    /**
     * @Description:??????????????????--??????
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShopSuccess_1(int code, MoveDataBean moveDataBeas) {
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
//        List<String> strings = new ArrayList<>();
//        strings.add(dataBeanXX.getGoods_thumb());
//        infoBean.setBanner(strings);

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(dataBeanXX.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);

        infoBean.setSearch_attr(dataBeanXX.getSearch_attr());
        infoBean.setGoods_id(dataBeanXX.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(dataBeanXX.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("??????????????????", "===??????====xianGoods_attr===" + xianGoods_attr);
        TypeSelectDialog.with(getActivity(), moveDataBeas, xianGoods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name,ProductBean productBean, String mmake) {
                xianGoods_attr = "";
//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();

    }

    /**
     * ?????????????????????--??????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopFail_1(int code, String msg) {

    }


    /*??????????????????*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }


    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

}
