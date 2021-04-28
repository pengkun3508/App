package com.vinnlook.www.surface.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.SPUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.GuangGaoEvent;
import com.vinnlook.www.event.StratMoveAbooutActivity_1;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.receiver.AppUpdateReceiver;
import com.vinnlook.www.surface.activity.CommodityActivity;
import com.vinnlook.www.surface.activity.CouponActivity;
import com.vinnlook.www.surface.activity.GroupWorkGoActivity;
import com.vinnlook.www.surface.activity.HaiTaoClassActivity;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.HuoDongZoneActivity_1;
import com.vinnlook.www.surface.activity.LimitedActivity_1;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.ProductDetailsActivity;
import com.vinnlook.www.surface.activity.RankingListActivity_1;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.adapter.BannerImgAdapter;
import com.vinnlook.www.surface.adapter.BannerImgAdapter1;
import com.vinnlook.www.surface.adapter.BannerImgAdapter4;
import com.vinnlook.www.surface.adapter.BannerImgAdapter6;
import com.vinnlook.www.surface.adapter.Discount_Adapter_1;
import com.vinnlook.www.surface.adapter.PaoQiList_New_Adapter;
import com.vinnlook.www.surface.adapter.Recommend_Adapter;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.fragment.adapter.Title_New_Adapter;
import com.vinnlook.www.surface.mvp.presenter.HomeTab1FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab1FragmentView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SPAppUpdateUtils;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 首页--商城
 * @Time:2021/3/26$
 * @Author:pk$
 */
public class HomeTab1Fragment extends BaseFragment<HomeTab1FragmentPresenter> implements HomeTab1FragmentView {
    @BindView(R.id.banner_1)
    Banner banner1;
    @BindView(R.id.huodong_img)
    ImageView huodongImg;
    @BindView(R.id.home_scroll)
    ScrollView homeScroll;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.home_xianshi_time_text_days)
    TextView homeXianshiTimeTextDays;
    @BindView(R.id.home_xianshi_time_text_hours)
    TextView homeXianshiTimeTextHours;
    @BindView(R.id.home_xianshi_time_text_minutes)
    TextView homeXianshiTimeTextMinutes;
    @BindView(R.id.home_xianshi_time_text_seconds)
    TextView homeXianshiTimeTextSeconds;
    @BindView(R.id.xianshi_title_relayout)
    LinearLayout xianshiTitleRelayout;
    @BindView(R.id.discount_recycler)
    RecyclerView discountRecycler;
    @BindView(R.id.banner_2)
    Banner banner2;
    @BindView(R.id.recycler_paoqi)
    RecyclerView recyclerPaoqi;
    @BindView(R.id.recommend_img)
    ImageView recommendImg;
    @BindView(R.id.banner_3)
    Banner banner3;
    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerRecommend;


    private final String[] mTitles = {"", ""};


    HomeTab1Bean homeTab1Bean;
    List<HomeTab1Bean.BannerBeanX> bannerImage;
    List<HomeTab1Bean.HeadBannerBean> getHead_banner;
    List<HomeTab1Bean.RecommendBean.BannerBean> recommBanner;

    BannerImgAdapter bannerImgAdapter;
    Discount_Adapter_1 discAdapter;
    int dt;
    String dayss;
    String hourss;
    String minutess;
    String secondss;


    Bitmap bitmaps;
    List<Bitmap> bannerList = null;
    Bitmap newBintmaps;

    int height;
    List<HomeTab1Bean.HeadBannerBean> gatBannetData2;
    PaoQiList_New_Adapter paoQiAdapter3;
    Recommend_Adapter paoQiAdapter4;
    Title_New_Adapter titleAdapter;
    List<HomeTab1Bean.DiscountBean.ListBeanXX> getDiscounts;
    HomeTab1Bean.AlertAdBean getAlert_ad;//广告
    public PopupWindow popupwindow;//广告弹框
    @BindView(R.id.title_recycler)
    RecyclerView titleRecycler;


    HashMap<String, Object> map;
    List<HashMap<String, Object>> list;
    @BindView(R.id.viewpage_layout2)
    RelativeLayout viewpageLayout2;

    List<String> bannerList1_1;


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_1_fragment;
    }

    @Override
    protected HomeTab1FragmentPresenter initPresenter() {
        return new HomeTab1FragmentPresenter();
    }

    @Override
    protected void initView() {
        addTitleList();//添加数据

        banner1.addBannerLifecycleObserver(this);
        banner2.addBannerLifecycleObserver(this);
        banner3.addBannerLifecycleObserver(this);
        banner1.post(new Runnable() {
            @Override
            public void run() {
                banner1.getWidth();
                double f = Float.valueOf(banner1.getWidth() + "") / (1.8);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(banner1.getWidth(), (int) f);
                banner1.setLayoutParams(layoutParams);
            }
        });


        banner2.post(new Runnable() {
            @Override
            public void run() {
                banner2.getWidth();
                double f = Float.valueOf(banner2.getWidth() + "") / (350 / 150);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(banner2.getWidth(), (int) f);
                banner2.setLayoutParams(layoutParams);
            }
        });

        banner3.post(new Runnable() {
            @Override
            public void run() {
                banner3.getWidth();
                double f = Float.valueOf(banner3.getWidth() + "") / (350 / 175);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(banner3.getWidth(), (int) f);
                banner3.setLayoutParams(layoutParams);
            }
        });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getHomeTab1Data();//下载首页数据

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });


//        discAdapter = new Discount_Adapter_1(getActivity(), getDiscounts);
//        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
//        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
//        discountRecycler.setLayoutManager(manager1);
//        discountRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
//        discountRecycler.addItemDecoration(new SpaceItemDecoration(10, 10));
//        discountRecycler.setNestedScrollingEnabled(false);
//        discountRecycler.setHasFixedSize(true);
//        discountRecycler.setAdapter(discAdapter);


        //抛期专区
        paoQiAdapter3 = new PaoQiList_New_Adapter(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        recyclerPaoqi.setLayoutManager(manager3);
        recyclerPaoqi.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerPaoqi.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerPaoqi.setNestedScrollingEnabled(false);
        recyclerPaoqi.setHasFixedSize(true);

        recyclerPaoqi.setAdapter(paoQiAdapter3);

        //为你推荐
        paoQiAdapter4 = new Recommend_Adapter(getActivity());
        final LinearLayoutManager manager4 = new LinearLayoutManager(getActivity());
        recyclerRecommend.setLayoutManager(manager4);
        recyclerRecommend.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerRecommend.addItemDecoration(new SpaceItemDecoration(0, 20));
        recyclerRecommend.setNestedScrollingEnabled(false);
        recyclerRecommend.setHasFixedSize(true);
        recyclerRecommend.setAdapter(paoQiAdapter4);
        paoQiAdapter4.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), paoQiAdapter4.getData().get(position).getGoods_id(), paoQiAdapter4.getData().get(position).getSearch_attr());
            }
        });


    }

    private void addTitleList() {
        list = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<>();
        map.put("name", "海淘专区");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "自营专区");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "限时折扣");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "排行榜");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "护理液");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "佩戴工具");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "医美专区");
        list.add(map);

        Log.e("TitleDatas", "==list==" + list);

        //Title适配器
        titleAdapter = new Title_New_Adapter(getActivity(), list);
        final GridLayoutManager titleManager = new GridLayoutManager(getActivity(), 4);
//        titleManager.setOrientation(GridLayoutManager.HORIZONTAL);
        titleRecycler.setLayoutManager(titleManager);
        titleRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        titleRecycler.addItemDecoration(new SpaceItemDecoration(0, 20));
        titleRecycler.setNestedScrollingEnabled(false);
        titleRecycler.setHasFixedSize(true);
        titleRecycler.setAdapter(titleAdapter);
        titleAdapter.setOnItemClickListener(new Title_New_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0) {//海淘专区
                    HaiTaoClassActivity.startSelf(getActivity(), "海淘专区", "2");
                } else if (position == 1) {//自营专区
                    HaiTaoClassActivity.startSelf(getActivity(), "自营专区", "1");
                } else if (position == 2) {//限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (position == 3) {//排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (position == 4) {//护理液
                    HomePublicClassActivity.startSelf(getActivity(), "护理液专区", "", "2", "", "", "");
                } else if (position == 5) {//佩戴工具
                    HomePublicClassActivity.startSelf(getActivity(), "佩戴工具专区", "", "", "1", "", "");
                } else if (position == 6) {//医美专区
                    HomePublicClassActivity.startSelf(getActivity(), "医美专区", "", "", "2", "", "");
                }

            }
        });
    }


    @Override
    protected void loadData() {
        presenter.getHomeTab1Data();//下载首页数据
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //更新UI
            switch (msg.what) {
                case 1:
//                    if (banner1 != null) {
//                        Log.e("mHandler", "==banner1==" + banner1);
//                        Log.e("mHandler", "==bannerList==" + bannerList);
//                        banner1.setDatas(bannerList);
//                        Bitmap bitm1 = bannerList.get(0);
//                        int bitmHeight = bitm1.getHeight();
//                        Log.e("handleMessage", "bitmHeight=111=" + bitmHeight);
//                        int bitmHeight2 = DensityUtil.px2dip(getActivity(), bitmHeight);
//                        Log.e("handleMessage", "bitmHeight=222=" + bitmHeight2);
//
//
//                        int height = banner1.getMeasuredHeight();
////                        Log.e("handleMessage", "height==-----==" + height);
////                        height = getViewHeight(banner1, true);
//                        Log.e("handleMessage", "height==000==" + height);
//
//                        int height4 = height / 3;
//                        Log.e("handleMessage", "height4==444==" + height4);
//                        int height5 = height - height4;
//                        Log.e("handleMessage", "height5==555==" + height5);
//
//
//                        int height1 = DensityUtil.px2dip(getActivity(), height);
//                        Log.e("handleMessage", "height==111==" + height1);
//                        int height2 = height1 / 2 + 50;
//                        Log.e("handleMessage", "height==222==" + height2);
//                        int height3 = height1 + height2;
//                        Log.e("handleMessage", "height1==333==" + height3);
////                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
////                        layoutParams.setMargins(0, height5, 0, 0);
////                        viewpageLayout2.setLayoutParams(layoutParams);
//                    }

                    break;
            }

        }

    };

    /**
     * 首页数据请求成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getHomeTab1DataSuccess(int code, HomeTab1Bean data) {
        smartRefreshLayout.finishRefresh();
        homeTab1Bean = data;
        bannerImage = data.getBanner();//轮播1
        gatBannetData2 = data.getHead_banner();//轮播2
        recommBanner = data.getRecommend().getBanner();//轮播3

        new Thread(new Runnable() {
            @Override
            public void run() {
//                bannerList = gatBannetData1();
                bannerList1_1 = gatBannetData1_1();
                Log.e("onBindView", "bannerList====" + bannerList);
                //更新UI方法  1
                Message message = new Message();
                message.what = 1;
                message.obj = bannerList;
                mHandler.sendMessage(message);
            }
        }).start();
        banner1.setStartPosition(0);
        BannerImgAdapter4 bannerImgAdapter = new BannerImgAdapter4(getActivity(), gatBannetData1_1());
        banner1.setAdapter(bannerImgAdapter);
//        banner1.setIndicator(new CircleIndicator(getActivity()));
        banner1.start();

        banner1.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (bannerImage.get(position).getType().equals("1")) {//1：商品详情
                    MoveAbooutActivity_3.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                } else if (bannerImage.get(position).getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id());//进入活动详情页面
                } else if (bannerImage.get(position).getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), bannerImage.get(position).getList().getUrl());
                } else if (bannerImage.get(position).getType().equals("4")) {//4：文字
                } else if (bannerImage.get(position).getType().equals("5")) {//5：广告商品列表
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", bannerImage.get(position).getList().getId());
                } else if (bannerImage.get(position).getType().equals("6")) {//6：会员入口
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                } else if (bannerImage.get(position).getType().equals("7")) {//7：品牌商品列表
                    CommodityActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id(), bannerImage.get(position).getList().getId(), "商品列表", "0");//进入活动详情页面
                } else if (bannerImage.get(position).getType().equals("8")) {//新活动专区
                    HuoDongZoneActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("9")) {//9：品团go
                    GroupWorkGoActivity.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("10")) {//10：限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("11")) {//11：排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("12")) {//12：优惠券
                    CouponActivity.startSelf(getActivity());
                }
            }
        });


        banner3.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (recommBanner.get(position).getType().equals("1")) {//1：商品详情
                    MoveAbooutActivity_3.startSelf(getActivity(), recommBanner.get(position).getList().getGoods_id(), recommBanner.get(position).getList().getSearch_attr());
                } else if (recommBanner.get(position).getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), recommBanner.get(position).getList().getActive_id());//进入活动详情页面
                } else if (recommBanner.get(position).getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), recommBanner.get(position).getList().getUrl());
                } else if (recommBanner.get(position).getType().equals("4")) {//4：文字
                } else if (recommBanner.get(position).getType().equals("5")) {//5：广告商品列表
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", recommBanner.get(position).getList().getId());
                } else if (recommBanner.get(position).getType().equals("6")) {//6：会员入口
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                } else if (recommBanner.get(position).getType().equals("7")) {//7：品牌商品列表
                    CommodityActivity.startSelf(getContext(), recommBanner.get(position).getList().getActive_id(), recommBanner.get(position).getList().getId(), "商品列表", "0");//进入活动详情页面
                } else if (recommBanner.get(position).getType().equals("8")) {//新活动专区
                    HuoDongZoneActivity_1.startSelf(getActivity());
                } else if (recommBanner.get(position).getType().equals("9")) {//9：品团go
                    GroupWorkGoActivity.startSelf(getActivity());
                } else if (recommBanner.get(position).getType().equals("10")) {//10：限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (recommBanner.get(position).getType().equals("11")) {//11：排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (recommBanner.get(position).getType().equals("12")) {//12：优惠券
                    CouponActivity.startSelf(getActivity());
                }
            }
        });


        banner2.setStartPosition(1);
//        BannerImgAdapter6 bannerAdapter = new BannerImgAdapter6(getActivity(), gatBannetData(), gatBannetData2);
        BannerImgAdapter6 bannerAdapter = new BannerImgAdapter6(getActivity(), gatBannetData2);
        banner2.setAdapter(bannerAdapter);
//        banner1.setIndicator(new CircleIndicator(getActivity()));
//            banner2.setBannerGalleryMZ(20, 0);
        banner2.setBannerGalleryEffect(20, 20, 10);
//        banner2.setPageTransformer(Transformer.Default);//设置viewpager的切换效果
        banner2.isAutoLoop(false);
        banner2.setIntercept(true);
//        banner2.setIndicator(new CircleIndicator(getActivity()));//圆形指示器
        banner2.setIndicator(new RectangleIndicator(getActivity()));//矩形（条形）指示器
        banner2.setIndicatorSelectedColor(getActivity().getResources().getColor(R.color.them));
        banner2.setIndicatorNormalColor(getActivity().getResources().getColor(R.color.shop_line_2));
        banner2.setIndicatorSpace(1);
        banner2.setIndicatorWidth(40, 40);
        banner2.setIndicatorHeight(7);
        banner2.setIndicatorRadius(0);
        banner2.start();

        banner2.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (gatBannetData2.get(position).getType().equals("1")) {//1：商品详情
                    MoveAbooutActivity_3.startSelf(getActivity(), gatBannetData2.get(position).getList().getGoods_id(), gatBannetData2.get(position).getList().getSearch_attr());
                } else if (gatBannetData2.get(position).getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), gatBannetData2.get(position).getList().getActive_id());//进入活动详情页面
                } else if (gatBannetData2.get(position).getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), gatBannetData2.get(position).getList().getUrl());
                } else if (gatBannetData2.get(position).getType().equals("4")) {//4：文字
                } else if (gatBannetData2.get(position).getType().equals("5")) {//5：广告商品列表
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", gatBannetData2.get(position).getList().getId());
                } else if (gatBannetData2.get(position).getType().equals("6")) {//6：会员入口
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                } else if (gatBannetData2.get(position).getType().equals("7")) {//7：品牌商品列表
                    CommodityActivity.startSelf(getContext(), gatBannetData2.get(position).getList().getActive_id(), gatBannetData2.get(position).getList().getId(), "商品列表", "0");//进入活动详情页面
                } else if (gatBannetData2.get(position).getType().equals("8")) {//新活动专区
                    HuoDongZoneActivity_1.startSelf(getActivity());
                } else if (gatBannetData2.get(position).getType().equals("9")) {//9：品团go
                    GroupWorkGoActivity.startSelf(getActivity());
                } else if (gatBannetData2.get(position).getType().equals("10")) {//10：限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (gatBannetData2.get(position).getType().equals("11")) {//11：排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (gatBannetData2.get(position).getType().equals("12")) {//12：优惠券
                    CouponActivity.startSelf(getActivity());
                }
            }
        });


        HomeTab1Bean.IndexAdBean getIndex_ad = data.getIndex_ad();

        if (getIndex_ad.getId().equals("") || getIndex_ad.getId() == null) {
            huodongImg.setVisibility(View.GONE);
        } else {
            huodongImg.setVisibility(View.VISIBLE);
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            huodongImg.setScaleType(ImageView.ScaleType.MATRIX);
            huodongImg.setImageMatrix(matrix);
//            huodongImg.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.image(getActivity(), huodongImg, data.getIndex_ad().getPhoto());
            huodongImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getIndex_ad.getType().equals("1")) {
//                    MoveAbooutActivity_1.startSelf(getActivity(), getIndex_ad.getList().getGoods_id(), getIndex_ad.getList().getSearch_attr());
                        MoveAbooutActivity_3.startSelf(getActivity(), getIndex_ad.getList().getGoods_id(), getIndex_ad.getList().getSearch_attr());

                    } else if (getIndex_ad.getType().equals("2")) {
                        ProductDetailsActivity.startSelf(getContext(), getIndex_ad.getList().getActive_id());//进入活动详情页面
                    } else if (getIndex_ad.getType().equals("3")) {
                        WebActivity.startSelf(getActivity(), getIndex_ad.getList().getUrl());
                    } else if (getIndex_ad.getType().equals("4")) {

                    } else if (getIndex_ad.getType().equals("5")) {
//                    RecommendActivity_1.startSelf(getContext(), "", getIndex_ad.getList().getId());//商品列表
                        HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", getIndex_ad.getList().getId());
                    } else if (getIndex_ad.getType().equals("6")) {

                        if (!UserUtils.getInstance().getUserId().equals("")) {
//                        MemberActivity.startSelf(getContext(), "2");//会员购买入口
                            MemberActivity_1.startSelf(getContext(), "2");//会员购买入口
                        } else {
                            Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                        }
                    } else if (getIndex_ad.getType().equals("7")) {//7：品牌商品列表
                        CommodityActivity.startSelf(getContext(), getIndex_ad.getList().getActive_id(), getIndex_ad.getList().getId(), "商品列表", "0");//进入活动详情页面
                    } else if (getIndex_ad.getType().equals("8")) {//新活动专区
                        HuoDongZoneActivity_1.startSelf(getActivity());
                    } else if (getIndex_ad.getType().equals("9")) {//9：品团go
                        GroupWorkGoActivity.startSelf(getActivity());
                    } else if (getIndex_ad.getType().equals("10")) {//10：限时折扣
                        LimitedActivity_1.startSelf(getActivity());
                    } else if (getIndex_ad.getType().equals("11")) {//11：排行榜
                        RankingListActivity_1.startSelf(getActivity());
                    } else if (getIndex_ad.getType().equals("12")) {//12：优惠券
                        CouponActivity.startSelf(getActivity());
                    }
                }
            });


        }

//        ImageLoader.image(getActivity(), huodongImg, data.getIndex_ad().getPhoto());//活动图片
        dt = data.getDiscount().getCount_down();
        //计算秒杀倒计时---ms
        handler.sendEmptyMessageDelayed(0, 1000);
        //限时折扣
        getDiscounts = data.getDiscount().getList();
        discAdapter = new Discount_Adapter_1(getActivity(), getDiscounts);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        discountRecycler.setLayoutManager(manager1);
        discountRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        discountRecycler.addItemDecoration(new SpaceItemDecoration(10, 10));
        discountRecycler.setNestedScrollingEnabled(false);
        discountRecycler.setHasFixedSize(true);
        discountRecycler.setAdapter(discAdapter);


//        discAdapter.setPostion(data.getDiscount().getList().size());
//        discAdapter.setData(data.getDiscount().getList());//限时折扣
//
//        discAdapter.addOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                MoveAbooutActivity_3.startSelf(getActivity(), discAdapter.getData().get(position).getGoods_id(), discAdapter.getData().get(position).getSearch_attr());
//            }
//        });
        paoQiAdapter3.setData(data.getShop());//抛期


        ImageLoader.image(getActivity(), recommendImg, data.getRecommend().getImage());//为你推荐

        banner3.setStartPosition(1);
        BannerImgAdapter1 bannerAdapter3 = new BannerImgAdapter1(getActivity(), gatBannetData3());
        banner3.setAdapter(bannerAdapter3);
//        banner1.setIndicator(new CircleIndicator(getActivity()));
//        banner3.setBannerGalleryMZ(30, 0);
        banner3.setBannerGalleryEffect(80, 80, 0);
        banner3.isAutoLoop(true);
        banner3.start();
        paoQiAdapter4.setData(data.getRecommend().getList());

        getAlert_ad = data.getAlert_ad();//广告弹框
        presenter.getAppUpdate();//下载更新


    }

    /**
     * 数据请求失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getHomeTab1DataFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }

    /**
     * 更新版本--成功
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
                if (SPAppUpdateUtils.instance().isShouldUpdate1()) {//大于一天时间
                    Log.e("getAppUpdateSuccess", "updateTime===" + "大于一天时间了");
                    //1：最新版本；2：强制更新；3：提示更新；4：手动更新
                    AppUpdateReceiver.send(bean.getVersion());
                }
            } else {
                if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//无广告
                    return;
                } else {//广告弹框
                    Log.e("getAppUpdateSuccess", "isShouldUpdate1_1===" + SPAppUpdateUtils.instance().isShouldUpdate1_1());
                    if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//大于一天时间
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
            if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//无广告
                return;
            } else {//广告弹框
                Log.e("getAppUpdateSuccess", "isShouldUpdate1_2===" + SPAppUpdateUtils.instance().isShouldUpdate1_1());
                if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//大于一天时间
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
        SPUtils.getInstance().save(Constant.KEY_SIGN_BEAN, userInfoJson);//保存版本号

    }

    /**
     * 更新版本--失败
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    public List<String> gatBannetData() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < gatBannetData2.size(); i++) {
            ids.add(gatBannetData2.get(i).getId());
            strings.add(gatBannetData2.get(i).getPhoto());
        }
        return strings;
    }

    public List<String> gatBannetData1_1() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < bannerImage.size(); i++) {
            ids.add(bannerImage.get(i).getId());
            strings.add(bannerImage.get(i).getPhoto());
        }
        return strings;
    }

    public List<Bitmap> gatBannetData1() {

        List<Bitmap> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < bannerImage.size(); i++) {
            ids.add(bannerImage.get(i).getId());
            try {
                bitmaps = BitmapFactory.decodeStream(new URL(bannerImage.get(i).getPhoto()).openStream());
                Log.e("bitmaps", "=bitmaps==" + bitmaps);
                newBintmaps = getReverseBitmapById(bitmaps, getActivity());
                strings.add(newBintmaps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strings;
    }

    //为你推荐
    public List<String> gatBannetData3() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < recommBanner.size(); i++) {
            ids.add(recommBanner.get(i).getId());
            strings.add(recommBanner.get(i).getPhoto());
        }
        return strings;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        mHandler.removeCallbacksAndMessages(null);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dt = dt - 1;
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

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dt <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };


    public static Bitmap getReverseBitmapById(Bitmap sourceBitmap, Context context) {

        //绘制原图的下一半图片
        Matrix matrix = new Matrix();
        //倒影翻转
        matrix.setScale(1, -1);
        //创建反方向Bitmap
        Bitmap inverseBitmap = Bitmap.createBitmap(sourceBitmap, 0, sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(), sourceBitmap.getHeight() / 2, matrix, false);
        //合成图片
        Bitmap groupbBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(), sourceBitmap.getHeight() + sourceBitmap.getHeight() / 2 + 5, sourceBitmap.getConfig());
        //以合成图片为画布
        Canvas gCanvas = new Canvas(groupbBitmap);
        //将原图和倒影图片画在合成图片上
        gCanvas.drawBitmap(sourceBitmap, 0, 0, null);
        gCanvas.drawBitmap(inverseBitmap, 0, sourceBitmap.getHeight() + 5, null);
        //添加遮罩
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        LinearGradient shader = new LinearGradient(0, sourceBitmap.getHeight() + 30, 0,
                groupbBitmap.getHeight(), Color.BLACK, Color.TRANSPARENT, tileMode);
        paint.setShader(shader);
        //这里取矩形渐变区和图片的交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        gCanvas.drawRect(0, sourceBitmap.getHeight() + 5, sourceBitmap.getWidth(), groupbBitmap.getHeight(), paint);
        return groupbBitmap;
    }

    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     *
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight) {
        int result;
        if (view == null) return 0;
        if (isHeight) {
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h, 0);
            result = view.getMeasuredHeight();
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0, w);
            result = view.getMeasuredWidth();
        }
        return result;
    }


    /**
     * 广告弹框
     * getFeeMsg1,getMobile1, getFeeMsg2,getMobile2
     */
    public void initmPopupWindowView() {
        ImageView dialog_update_img, dialog_update_close;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.dialog_update_1, null, false);
        dialog_update_img = customView.findViewById(R.id.dialog_update_img);//图片
        dialog_update_close = customView.findViewById(R.id.dialog_update_close);//关闭

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow.setAnimationStyle(R.style.DialogWindowStyle);
        // 自定义view添加触摸事件
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
//        Matrix matrix = new Matrix();           //创建一个单位矩阵
//        matrix.setTranslate(0, 0);          //平移x和y各100单位
//        matrix.preRotate(0);                   //顺时针旋转30度
        dialog_update_img.setScaleType(ImageView.ScaleType.FIT_XY);
//        dialog_update_img.setImageMatrix(matrix);
        ImageLoader.image(getActivity(), dialog_update_img, getAlert_ad.getPhoto());

        //关闭
        dialog_update_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
                final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                utils.setIgnore1();
            }
        });

        //跳转广告
        dialog_update_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
//                WebActivity.startSelf(getActivity(), url);

                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (getAlert_ad.getType().equals("1")) {//1：商品详情
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), getAlert_ad.getList().getGoods_id(), getAlert_ad.getList().getSearch_attr());

                } else if (getAlert_ad.getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), getAlert_ad.getList().getActive_id());//进入活动详情页面
                } else if (getAlert_ad.getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), getAlert_ad.getList().getUrl());

                } else if (getAlert_ad.getType().equals("4")) {//4：文字

                } else if (getAlert_ad.getType().equals("5")) {//5：广告商品列表
//                    RecommendActivity_1.startSelf(getContext(), "", getAlert_ad.getList().getId());//进入活动详情页面
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", getAlert_ad.getList().getId());
                } else if (getAlert_ad.getType().equals("6")) {//6：会员入口
//                    MemberActivity.startSelf(getContext(), "2");//
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                } else if (getAlert_ad.getType().equals("7")) {//7：品牌商品列表
                    CommodityActivity.startSelf(getContext(), getAlert_ad.getList().getActive_id(), getAlert_ad.getList().getId(), "商品列表", "0");//进入活动详情页面
                } else if (getAlert_ad.getType().equals("8")) {//新活动专区
                    HuoDongZoneActivity_1.startSelf(getActivity());
                } else if (getAlert_ad.getType().equals("9")) {//9：品团go
                    GroupWorkGoActivity.startSelf(getActivity());
                } else if (getAlert_ad.getType().equals("10")) {//10：限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (getAlert_ad.getType().equals("11")) {//11：排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (getAlert_ad.getType().equals("12")) {//12：优惠券
                    CouponActivity.startSelf(getActivity());
                }

                final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                utils.setIgnore1();
            }
        });


    }

    //接收消息---从H5页面进入程序
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(StratMoveAbooutActivity_1 event) {
        Log.e("HomeFragment", "=从H5页面进入程序==");
        Log.e("HomeFragment", "=event.getGood_id()==" + event.getGood_id());

//        MoveAbooutActivity_1.startSelf(getActivity(), event.getGood_id(), event.getSearch_attr());
        MoveAbooutActivity_3.startSelf(getActivity(), event.getGood_id(), event.getSearch_attr());

//        }
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GuangGaoEvent event) {
        Log.e("HomeFragment", "=首页传递消息==");
        Boolean getRefresh = event.getRefresh();
//        if (getRefresh) {
        if (SPAppUpdateUtils.instance().isShouldUpdate1_1()) {//大于一天时间
            if (popupwindow != null && popupwindow.isShowing()) {
                popupwindow.dismiss();
                return;
            } else {
                if (getAlert_ad.getId().equals("") || getAlert_ad.getId() == null) {//无广告
                } else {//广告弹框
                    initmPopupWindowView();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
            }
        }
//        }
    }


}
