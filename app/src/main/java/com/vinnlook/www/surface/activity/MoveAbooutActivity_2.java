package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.m7.imkfsdk.KfStartHelper;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.NewCardInfoAttrs;
import com.moor.imkf.utils.MoorUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.ChangeDetailPriceEvent;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.indicator.NumIndicator;
import com.vinnlook.www.surface.adapter.CommentListAdapter;
import com.vinnlook.www.surface.adapter.DetailsAdapter;
import com.vinnlook.www.surface.adapter.DetailsImags1Adapter;
import com.vinnlook.www.surface.adapter.DetailsImags2Adapter;
import com.vinnlook.www.surface.adapter.MultipleTypesAdapter;
import com.vinnlook.www.surface.adapter.ReBangImagAdapter;
import com.vinnlook.www.surface.adapter.TuiJianAdapter;
import com.vinnlook.www.surface.adapter.WenListAdapter;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.DatasBean;
import com.vinnlook.www.surface.bean.DetailsBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.presenter.MoveAboutPresenter;
import com.vinnlook.www.surface.mvp.view.MoveAboutView;
import com.vinnlook.www.surface.viewholder.VideoHolder;
import com.vinnlook.www.utils.AppUtils;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.DensityUtilss;
import com.vinnlook.www.utils.StatusBarUtil;
import com.vinnlook.www.utils.UserInfoBean;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.MoveAboutBarSimple;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:商品详情
 * @Time:2020/4/20$
 * @Author:pk$
 */
public class MoveAbooutActivity_2 extends BaseActivity<MoveAboutPresenter> implements MoveAboutView {

    private static final int BAIDU_READ_PHONE_STATE = 100;

    @BindView(R.id.move_shoucang_img)
    ImageView moveShoucangImg;//收藏
    @BindView(R.id.title_bars)
    RelativeLayout titleBar;
    @BindView(R.id.action_bar)
    MoveAboutBarSimple actionBar;
    @BindView(R.id.move_shopcat_btn)
    LinearLayout moveShopcatBtn;//购物车按钮
    @BindView(R.id.move_kefu_btn)
    LinearLayout moveKefuBtn;//客服按钮
    @BindView(R.id.move_shoucang_btn)
    LinearLayout moveShoucangBtn;//收藏按钮
    @BindView(R.id.move_add_shopcat_btn)
    TextView moveAddShopcatBtn;//加入购物车
    @BindView(R.id.tv_move_about)
    TextView tvMoveAbout;//立即购买
    @BindView(R.id.move_transaction_prices_1)
    TextView moveTransactionPrices1;
    @BindView(R.id.move_transaction_prices_yuan_1)
    TextView moveTransactionPricesYuan1;
    @BindView(R.id.details_vip_price_1)
    TextView detailsVipPrice1;
    @BindView(R.id.details_vip_zhe_1)
    TextView detailsVipZhe1;
    @BindView(R.id.details_vip_open_text_1)
    TextView detailsVipOpenText1;
    @BindView(R.id.details_vip_open_1)
    LinearLayout detailsVipOpen1;
    @BindView(R.id.move_xianshi_hour)
    TextView moveXianshiHour;
    @BindView(R.id.move_re_layout_1)
    RelativeLayout moveReLayout1;//折扣布局
    @BindView(R.id.move_transaction_prices_2)
    TextView moveTransactionPrices2;
    @BindView(R.id.details_vip_price_2)
    TextView detailsVipPrice2;
    @BindView(R.id.details_vip_zhe_2)
    TextView detailsVipZhe2;
    @BindView(R.id.details_vip_open_text_2)
    TextView detailsVipOpenText2;
    @BindView(R.id.details_vip_open_2)
    LinearLayout detailsVipOpen2;
    @BindView(R.id.move_re_layout_2)
    RelativeLayout moveReLayout2;//普通布局
    @BindView(R.id.banners)
    Banner banner;
    @BindView(R.id.details_suppliers)
    TextView detailsSuppliers;
    @BindView(R.id.move_transaction_name)
    TextView moveTransactionName;
    @BindView(R.id.id_1)
    TextView id1;
    @BindView(R.id.id_2)
    ImageView id2;
    @BindView(R.id.move_youhuiquan_btn)
    RelativeLayout moveYouhuiquanBtn;
    @BindView(R.id.fuwu_text)
    TextView fuwuText;
    @BindView(R.id.text_line_shuilv)
    View textLineShuilv;
    @BindView(R.id.text_shuilv)
    LinearLayout textShuilv;
    @BindView(R.id.kuaidi_id_1)
    TextView kuaidiId1;
    @BindView(R.id.post_fee_text)
    TextView postFeeText;
    @BindView(R.id.kuaidi_layout_btn)
    RelativeLayout kuaidiLayoutBtn;
    @BindView(R.id.select_id1)
    TextView selectId1;
    @BindView(R.id.move_type_text)
    TextView moveTypeText;
    @BindView(R.id.move_type_layout)
    RelativeLayout moveTypeLayout;
    @BindView(R.id.details_brand_name)
    TextView detailsBrandName;
    @BindView(R.id.details_goods_sn)
    TextView detailsGoodsSn;
    @BindView(R.id.details_toss_period)
    TextView detailsTossPeriod;
    @BindView(R.id.details_diameter)
    TextView detailsDiameter;
    @BindView(R.id.details_color)
    TextView detailsColor;
    @BindView(R.id.details_base_curve)
    TextView detailsBaseCurve;
    @BindView(R.id.details_water_content)
    TextView detailsWaterContent;
    @BindView(R.id.details_coloring_diameter)
    TextView detailsColoringDiameter;
    @BindView(R.id.details_origin)
    TextView detailsOrigin;
    @BindView(R.id.details_life_span)
    TextView detailsLifeSpan;
    @BindView(R.id.pingjia_number)
    TextView pingjiaNumber;
    @BindView(R.id.see_more_pingjia)
    TextView seeMorePingjia;
    @BindView(R.id.recyclerv_comment2)
    RecyclerView recyclervComment2;
    @BindView(R.id.item_xiangqing_layout)
    View itemXiangqingLayout;
    @BindView(R.id.img_list_details_1)
    RecyclerView imgListDetails1;//详情List
    @BindView(R.id.item_xuzhi_layout)
    View itemXuzhiLayout;
    @BindView(R.id.img_list_details2)
    RecyclerView imgListDetails2;//须知List
    @BindView(R.id.move_scroll)
    ScrollView moveScroll;
    @BindView(R.id.move_title_shangpin)
    TextView moveTitleShangpin;
    @BindView(R.id.move_title_pinglun)
    TextView moveTitlePinglun;
    @BindView(R.id.move_title_xiangqing)
    TextView moveTitleXiangqing;
    @BindView(R.id.move_title_xuzhi)
    TextView moveTitleXuzhi;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.shangpin_layout)
    LinearLayout shangpinLayout;//商品整体布局
    @BindView(R.id.pingjia_layout)
    LinearLayout pingjiaLayout;//评价整体布局
    @BindView(R.id.xiangqing_layout)
    LinearLayout xiangqingLayout;//限时整体布局
    @BindView(R.id.xuzhi_layout)
    LinearLayout xuzhiLayout;//用户须知整体布局
    @BindView(R.id.rebang_type_text)
    TextView rebangTypeText;//热榜类型
    @BindView(R.id.rebang_mingci_text)
    TextView rebangMingciText;//热榜名次
    @BindView(R.id.rebang_recycler)
    RecyclerView rebangRecycler;//热榜列表
    @BindView(R.id.rebang_layout_btn)
    RelativeLayout rebangLayoutBtn;
    @BindView(R.id.move_tuijian_recycler)
    RecyclerView moveTuijianRecycler;
    @BindView(R.id.wenyiwen_number)
    TextView wenyiwenNumber;
    @BindView(R.id.see_more_wenyiwen)
    TextView seeMoreWenyiwen;
    @BindView(R.id.recyclerv_wenyiwen)
    RecyclerView recyclervWenyiwen;
    @BindView(R.id.title_button)
    LinearLayout titleButton;
    @BindView(R.id.move_see_all_btn)
    ImageView moveSeeAllBtn;
    @BindView(R.id.move_huodong_short_flag)
    TextView moveHuodongShortFlag;
    @BindView(R.id.move_huodong_long_flag)
    TextView moveHuodongLongFlag;
    @BindView(R.id.move_huodong_img)
    ImageView moveHuodongImg;
    @BindView(R.id.move_huodong_btn)
    RelativeLayout moveHuodongBtn;
    @BindView(R.id.move_huodong_line_view)
    View moveHuodongLineView;
    @BindView(R.id.pingjia_line)
    View pingjiaLine;
    @BindView(R.id.wendajia_line)
    View wendajiaLine;


    private float totaldy;
    private float mRecyclerFactor;
    private List<DetailsBean> list;
    private int item1 = 0;
    private int item2 = 0;
    private int item3 = 0;
    private LinearLayoutManager manager;
    private Resources res;
    String mark = "0";


    private static String goods_id;//商品详情
    private static String search_attr;//商品规格


    MoveDataBean moveDataBean;

    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    String[] all;

    DetailsAdapter detailsAdapter;
    List<String> getBannerEvent;

    List<String> strings;
//    String getAttr_name;

    String sdktoken;
    private CheckBox use_http;
    //相差多少时间 - ms
    private long dt = 0;
    UserInfoBean getUserInfo;
    private int mScreenWidthDp;
    private int mScreenHeightDp;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

    public PopupWindow popupwindow;

    String hourss;
    String minutess;
    String secondss;
    String mmark;//选择规格弹框路径；1--商品详情页面；“”--购物车
    String product_ids;
    String nums;


    CommentListAdapter commentAdapter;//评价适配器
    WenListAdapter wenAdapter;//问一问适配器
    DetailsImags1Adapter adapter;//详情适配器
    DetailsImags2Adapter adapter2;//须知适配器
    ReBangImagAdapter adapter3;//热榜适配器
    TuiJianAdapter adapter4;//大家都在买

    int shangPinHeight;
    int pingJiaHeight;
    int xiangQingHeight;
    int xuZhiHeight;
    int titleBarHeight;

    MoveDataBean.RecommendBean ecommendBean;
    StandardGSYVideoPlayer player;

    public static void startSelf(Activity context, String goods_ids, String search_attrs) {
        Intent intent = new Intent(context, MoveAbooutActivity_2.class);
//        context.startActivity(intent);
        context.startActivityForResult(intent, 1);
        goods_id = goods_ids;
        search_attr = search_attrs;
        Log.e("goods_id", "=-goods_id=-" + goods_id);
        Log.e("search_attr", "=-search_attr=-" + search_attr);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_about_2;
    }

    @Override
    protected MoveAboutPresenter initPresenter() {
        return new MoveAboutPresenter();
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        actionBar.getTvRight().setVisibility(View.VISIBLE);
        //分享
        actionBar.getTvRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!UserUtils.getInstance().getUserId().equals("")) {
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView();
                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                } else {
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
            }
        });


        initAdapter();//创建适配器
        initTitleBtn();//title点击事件
        initTitle();
        getUserInfo = UserUtils.getInstance().getUserInfo();
        inits();


        Log.e("详情页面视频", "===getTestDataVideo===" + DatasBean.getTestDataVideo());


    }

    private void initTitle() {
        res = getResources();
        //        图片的高度-状态栏的高度
        mRecyclerFactor = (DensityUtilss.dp2px(this, 400.0F) - DensityUtilss.getStatusBarHeight(this));
    }

    //Title点击事件
    @SuppressLint("NewApi")
    private void initTitleBtn() {
        //点击商品
        moveTitleShangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shangPinHeight != 0) {
                    moveScroll.scrollBy(0, (int) -totaldy);

                    moveTitleShangpin.setTextColor(res.getColor(R.color.them));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));
                    titleBar.setVisibility(View.GONE);

                }
            }
        });
        //点击评价
        moveTitlePinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pingJiaHeight != 0) {
                    if (pingJiaHeight > shangPinHeight) {
                        moveScroll.scrollBy(0, (int) -(totaldy - shangPinHeight));
                    } else {
                        moveScroll.scrollBy(0, (int) -(shangPinHeight - totaldy));
                    }
                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.them));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));

                }
            }
        });
        //点击详情
        moveTitleXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xiangQingHeight != 0) {
                    if (xiangQingHeight > pingJiaHeight) {
                        moveScroll.scrollBy(0, (int) -(totaldy - pingJiaHeight));
                    } else {
                        moveScroll.scrollBy(0, (int) -(pingJiaHeight - totaldy));
                    }

                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.them));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));
                }
            }
        });
        //点击用户须知
        moveTitleXuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xuZhiHeight != 0) {
                    if (xuZhiHeight > xiangQingHeight) {
                        moveScroll.scrollBy(0, (int) -(totaldy - xiangQingHeight));
                    } else {
                        moveScroll.scrollBy(0, (int) -(xiangQingHeight - totaldy));
                    }
                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.them));
                }
            }
        });

        //滑动监听
        moveScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int newx, int newy, int oldx, int oldy) {
                totaldy = newy;
                if (newy > 0) {
                    titleBar.setVisibility(View.VISIBLE);
                } else {
                    titleBar.setVisibility(View.GONE);
                }

                //滑动的距离

                //商品
                if (newy < shangPinHeight) {
                    moveTitleShangpin.setTextColor(res.getColor(R.color.them));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));

                } else if (newy > shangPinHeight && newy < pingJiaHeight) {
                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.them));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));
                } else if (newy > pingJiaHeight && newy < xiangQingHeight) {
                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.them));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.black));
                } else if (newy > xiangQingHeight) {
                    moveTitleShangpin.setTextColor(res.getColor(R.color.black));
                    moveTitlePinglun.setTextColor(res.getColor(R.color.black));
                    moveTitleXiangqing.setTextColor(res.getColor(R.color.black));
                    moveTitleXuzhi.setTextColor(res.getColor(R.color.them));
                }

                shangpinLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        shangPinHeight = shangpinLayout.getHeight() - 80;
                    }
                });
                pingjiaLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        pingJiaHeight = pingjiaLayout.getHeight() + shangPinHeight;
                    }
                });
                xiangqingLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        xiangQingHeight = xiangqingLayout.getHeight() + pingJiaHeight;
                    }
                });
                xuzhiLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        xuZhiHeight = xuzhiLayout.getHeight() + xiangQingHeight;
                    }
                });

                titleBar.post(new Runnable() {
                    @Override
                    public void run() {
                        titleBarHeight = titleBar.getHeight();
                    }
                });

                if (totaldy < titleBarHeight) {
                    float scale = (float) totaldy / titleBarHeight;
                    float alpha = scale * 255;//0.03780069*255

                    if (alpha < 160) {//9.639175
                        // 如果透明度小于160设置为顶部是图片
                        StatusBarUtil.setTranslucentForImageView(getActivity(), (int) alpha, titleBar);
                        titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        moveTitleShangpin.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitlePinglun.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitleXiangqing.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitleXuzhi.setTextColor(Color.argb((int) alpha, 0, 0, 0));

                    } else {
                        titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        moveTitleShangpin.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitlePinglun.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitleXiangqing.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        moveTitleXuzhi.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                    }
                } else {
                    titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
                    StatusBarUtil.setColor(MoveAbooutActivity_2.this, Color.parseColor("#ffffff"));
                }


            }
        });


    }

    //创建适配器
    private void initAdapter() {
        //评价适配器
        commentAdapter = new CommentListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(this, 1);
        recyclervComment2.setLayoutManager(manager3);
        recyclervComment2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        recyclervComment2.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclervComment2.setNestedScrollingEnabled(false);
        recyclervComment2.setHasFixedSize(false);

        //问一问适配器
        wenAdapter = new WenListAdapter(this);
        final GridLayoutManager managesWen = new GridLayoutManager(this, 1);
        recyclervWenyiwen.setLayoutManager(managesWen);
        recyclervWenyiwen.setNestedScrollingEnabled(false);
        recyclervWenyiwen.setHasFixedSize(false);

        //详情适配器
        adapter = new DetailsImags1Adapter(this);
//            final GridLayoutManager manager2 = new GridLayoutManager(context, 1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        imgListDetails1.setLayoutManager(layoutManager);
        imgListDetails1.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        imgListDetails1.addItemDecoration(new SpaceItemDecoration(0, 0));
        imgListDetails1.setNestedScrollingEnabled(false);
        imgListDetails1.setFocusable(false);

        //详情适配器
        adapter2 = new DetailsImags2Adapter(this);
//            final GridLayoutManager manager2 = new GridLayoutManager(context, 1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        imgListDetails2.setLayoutManager(layoutManager1);
        imgListDetails2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        imgListDetails2.addItemDecoration(new SpaceItemDecoration(0, 0));
        imgListDetails2.setNestedScrollingEnabled(false);
        imgListDetails2.setFocusable(false);

        //热榜适配器
        adapter3 = new ReBangImagAdapter(this);
//            final GridLayoutManager manager2 = new GridLayoutManager(context, 1);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rebangRecycler.setLayoutManager(layoutManager2);
        rebangRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        rebangRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        rebangRecycler.setNestedScrollingEnabled(false);
        rebangRecycler.setFocusable(false);

        //大家都在买
        adapter4 = new TuiJianAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        moveTuijianRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        moveTuijianRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        moveTuijianRecycler.setLayoutManager(manager1);
        moveTuijianRecycler.setNestedScrollingEnabled(false);
        moveTuijianRecycler.setFocusable(false);


    }


    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//下载时间
        presenter.getMoveDatas(goods_id, search_attr);//下载商品详情数据
//        presenter.getMoveDatas("2", "26|27");//下载商品详情数据
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 商品详情返回成功
     *
     * @param code
     * @param
     */
    @Override
    public void getMoveDataSuccess(int code, MoveDataBean data) {

        moveDataBean = data;

        int getIs_collect = moveDataBean.getInfo().getIs_collect();
        if (getIs_collect == 0) {//没有收藏
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            mark = "0";
        } else if (getIs_collect == 1) {//收藏
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            mark = "1";
        }

        //加载不同的布局
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        //计算秒杀倒计时---ms
        dt = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);

        //加载Banner数据
//        BannerImgAdapter1 bannerImgAdapter = new BannerImgAdapter1(this, data.getInfo().getBanner());
//        banner.setAdapter(bannerImgAdapter);
//        banner.setIndicator(new CircleIndicator(getActivity()));
//        banner.start();

//        data.getInfo().getBanner().get(0).setUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        banner.addBannerLifecycleObserver(this)
                .setAdapter(new MultipleTypesAdapter(this, data.getInfo().getBanner(),data.getInfo().getProduct_price(),data.getInfo().getBorder_image()))
                .setIndicator(new NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .addOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.e("--", "position:" + position);
                        if (player == null) {
                            RecyclerView.ViewHolder viewHolder = banner.getAdapter().getViewHolder();
                            if (viewHolder instanceof VideoHolder) {
                                VideoHolder holder = (VideoHolder) viewHolder;
                                player = holder.player;
                            }
                            return;
                        }
                        if (position != 0) {
                            player.onVideoReset();
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


        //判断是否折扣商品--折扣与普通布局的切换
        if (data.getInfo().getIs_promote().equals("1")) {//显示限时页面
            moveReLayout1.setVisibility(View.VISIBLE);
            moveReLayout2.setVisibility(View.GONE);
            moveTransactionPrices1.setText(Html.fromHtml("&yen") + data.getInfo().getPreferential_price());
            moveTransactionPricesYuan1.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());
            detailsVipPrice1.setText(Html.fromHtml("&yen") + data.getInfo().getMember_discount());
        } else if (data.getInfo().getIs_promote().equals("0")) {//显示普通页面
            moveReLayout1.setVisibility(View.GONE);
            moveReLayout2.setVisibility(View.VISIBLE);
            moveTransactionPrices2.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());
            detailsVipPrice2.setText(Html.fromHtml("&yen") + data.getInfo().getMember_discount());
        }

        //判断是否登录--是否开通会员
        if (UserUtils.getInstance().getUserId().equals("")) {//未登录
            detailsVipOpenText1.setText("立即开卡");
            detailsVipOpenText2.setText("立即开卡");
            detailsVipZhe1.setText("开通VIP会员，全年包邮再享9.5折");
            detailsVipZhe2.setText("开通VIP会员，全年包邮再享9.5折");
            //会员入口
            detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoveAbooutActivity_2.this, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            });
            //会员入口
            detailsVipOpen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoveAbooutActivity_2.this, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            });
        } else {//已登录
            if (UserUtils.getInstance().getUserInfo().getIs_member() == 0) {//未开通会员
                detailsVipOpenText1.setText("立即开卡");
                detailsVipOpenText2.setText("立即开卡");
                detailsVipZhe1.setText("开通VIP会员，全年包邮再享9.5折");
                detailsVipZhe2.setText("开通VIP会员，全年包邮再享9.5折");
                //会员入口
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });
                //会员入口
                detailsVipOpen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });
            } else if (UserUtils.getInstance().getUserInfo().getIs_member() == 1) {//已开通会员
                detailsVipOpenText1.setText("会员中心");
                detailsVipOpenText2.setText("会员中心");
                detailsVipZhe1.setText("全年包邮再享9.5折");
                detailsVipZhe2.setText("全年包邮再享9.5折");
                //会员入口
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });
                detailsVipOpen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });
            }
        }

        //原价空间上加横线
        moveTransactionPricesYuan1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        //显示倒计时
        //计算秒杀倒计时---ms
        dt = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);


        //商品名称
        moveTransactionName.setText(data.getInfo().getShop_name() + " " + data.getInfo().getShop_attr_name());
        //规格
        if (data.getInfo().getShop_attr_name().equals("") || data.getInfo().getShop_attr_name() == null) {
            moveTypeText.setText("选择 颜色 规格（片数） 度数");
        } else {
            moveTypeText.setText("已选 " + data.getInfo().getShop_attr_name());
        }

        detailsBrandName.setText(data.getInfo().getBrand_name());
        detailsGoodsSn.setText(data.getInfo().getGoods_sn());
        detailsTossPeriod.setText(data.getInfo().getToss_period());
        detailsColor.setText(data.getInfo().getColor());
        detailsOrigin.setText(data.getInfo().getOrigin());
        detailsLifeSpan.setText(data.getInfo().getLife_span());
        postFeeText.setText(data.getInfo().getPost_fee());

        detailsDiameter.setText(data.getInfo().getDiameter());
        detailsBaseCurve.setText(data.getInfo().getBase_curve());
        detailsWaterContent.setText(data.getInfo().getWater_content());
        detailsColoringDiameter.setText(data.getInfo().getColoring_diameter());
        //自营--海淘
        if (data.getInfo().getSuppliers_id().equals("1")) {
            detailsSuppliers.setText("自营");
            fuwuText.setText("自营仓发货");
            textLineShuilv.setVisibility(View.GONE);
            textShuilv.setVisibility(View.GONE);
            detailsSuppliers.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.classify_list_item_1));
        } else if (data.getInfo().getSuppliers_id().equals("2")) {
            detailsSuppliers.setText("海淘");
            fuwuText.setText("保税仓直邮·不支持无理由退货");
            textLineShuilv.setVisibility(View.VISIBLE);
            textShuilv.setVisibility(View.VISIBLE);
            detailsSuppliers.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.classify_list_item));
        }

        //领取优惠券
        moveYouhuiquanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CouponActivity.startSelf(MoveAbooutActivity_2.this);//优惠券页面
            }
        });

        //包邮规则
        kuaidiLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WebActivity.startSelf(MoveAbooutActivity_2.this, "http:h5.jealook.com/vinnlook/Package.html");
                WebActivity.startSelf(MoveAbooutActivity_2.this, data.getInfo().getWaybill_url());
            }
        });

        //选规格
        moveTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TypeSelectDialog.with(MoveAbooutActivity_2.this, data, "", "", new TypeSelectDialog.AddShopCarClickListener() {
                    @Override
                    public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
//                            dataBean.getInfo().setShop_attr_name(getAttr_names);
//                            move_type_text.setText(getAttr_names);
//                            notifyDataSetChanged();
                        TypeSelectDialog.dismiss();
                    }
                }).show();
            }
        });


        //评价
        pingjiaNumber.setText("(" + data.getComment().size() + ")");
        commentAdapter.setData(data.getComment());
        recyclervComment2.setAdapter(commentAdapter);
        if (data.getComment().size() > 0) {
            pingjiaLine.setVisibility(View.VISIBLE);
        } else {
            pingjiaLine.setVisibility(View.GONE);
        }
        seeMorePingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EvaluateListActivity.startSelf(getContext(), data.getInfo().getGoods_id());
            }
        });
        //问一问
        wenyiwenNumber.setText("（" + data.getInfo().getQuestion_count() + "）");
        wenAdapter.setData(data.getQuestion_list());
        recyclervWenyiwen.setAdapter(wenAdapter);
        if (data.getQuestion_list().size() > 0) {
            wendajiaLine.setVisibility(View.VISIBLE);
        } else {
            wendajiaLine.setVisibility(View.GONE);
        }

        //详情
        adapter.setData(data.getInfo().getDetails());
        imgListDetails1.setAdapter(adapter);
        //须知
        adapter2.setData(data.getInfo().getUser_notes());
        imgListDetails2.setAdapter(adapter2);
        //大家都在买
        adapter4.setData(data.getRecommend());
        moveTuijianRecycler.setAdapter(adapter4);
        adapter4.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_2.startSelf(getActivity(), adapter4.getData().get(position).getGoods_id(), adapter4.getData().get(position).getSearch_attr());

            }
        });
        adapter4.setTuiJianClickListener(new TuiJianAdapter.TuiJianClickListener() {
            @Override
            public void onGoClickListener(MoveDataBean.RecommendBean data, String getGoods_id, String getSearch_attr) {
                ecommendBean = data;
                presenter.getTypeShopData(getGoods_id);
            }
        });

        //热榜
        //1--新品；2--全部；3--日抛；4--双周抛；5--月抛；
        if (data.getSalable().getType().equals("1")) {
            rebangTypeText.setText("新品畅销榜");
        } else if (data.getSalable().getType().equals("2")) {
            rebangTypeText.setText("全部畅销榜");
        } else if (data.getSalable().getType().equals("3")) {
            rebangTypeText.setText("日抛畅销榜");
        } else if (data.getSalable().getType().equals("4")) {
            rebangTypeText.setText("双周抛畅销榜");
        } else if (data.getSalable().getType().equals("5")) {
            rebangTypeText.setText("月抛销榜");
        }
        //名次
        rebangMingciText.setText(data.getSalable().getRanking());
        if (Integer.parseInt(data.getSalable().getRanking()) > 10) {
            rebangLayoutBtn.setVisibility(View.GONE);
        } else {
            rebangLayoutBtn.setVisibility(View.VISIBLE);
        }

        adapter3.setData(data.getSalable().getImage());
        rebangRecycler.setAdapter(adapter3);
        adapter3.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ReBangListActivity.startSelf(MoveAbooutActivity_2.this, data.getSalable().getType());
            }
        });

        //活动
        if (!data.getShopActiveInfo().getShort_flag().equals("")) {
            moveHuodongBtn.setVisibility(View.VISIBLE);
            moveHuodongLineView.setVisibility(View.VISIBLE);
            moveHuodongShortFlag.setText(data.getShopActiveInfo().getShort_flag());
            moveHuodongLongFlag.setText(data.getShopActiveInfo().getLong_flag());
            if (!data.getShopActiveInfo().getAct_id().equals("")) {
                moveHuodongImg.setVisibility(View.VISIBLE);
                moveHuodongBtn.setOnClickListener(this);
                moveHuodongBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (data.getShopActiveInfo().getType().equals("1")) {
                            PreferentialActivity.startSelf(MoveAbooutActivity_2.this, data.getShopActiveInfo().getAct_id());
                        } else if (data.getShopActiveInfo().getType().equals("2")) {
                            //进入套餐列表
                            SetMealActivity.startSelf(MoveAbooutActivity_2.this, data.getInfo().getGoods_id());
                        }

                    }
                });
            } else {
                moveHuodongBtn.setOnClickListener(null);
                moveHuodongImg.setVisibility(View.GONE);
            }
        } else {
            moveHuodongBtn.setVisibility(View.GONE);
            moveHuodongLineView.setVisibility(View.GONE);
        }


    }


    /**
     * 商品详情返回失败
     *
     * @param code
     * @param
     */
    @Override
    public void getMoveDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 收藏商品成功
     *
     * @param code
     * @param
     */
    @Override
    public void getCollectionShopSuccess(int code, Object data) {
        Log.e("收藏商品成功", "==code==" + code);
        Log.e("收藏商品成功", "==data==" + data);
        if (mark.equals("1")) {
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        } else if (mark.equals("0")) {
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 收藏商品失败
     *
     * @param code
     * @param
     */
    @Override
    public void getCollectionShopFail(int code, String msg) {
        Log.e("收藏商品失败", "==code==" + code);
        Log.e("收藏商品失败", "==msg==" + msg);

        if (mark.equals("1")) {
            mark = "0";
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            Toast.makeText(this, "收藏失败", Toast.LENGTH_SHORT).show();
        } else if (mark.equals("0")) {
            mark = "1";
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            Toast.makeText(this, "取消收藏失败", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 添加购物车成功
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        Log.e("添加购物车成功", "==code==" + code);
        Log.e("添加购物车成功", "==msg==" + data);
        TypeSelectDialog.dismiss();
//        TypeSelectDialog.with(getActivity(), moveDataBean, this).dismiss();
        Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 添加购物车失败
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Log.e("添加购物车失败", "==code==" + code);
        Log.e("添加购物车失败", "==msg==" + msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("MissingPermission")
    @OnClick({R.id.move_shopcat_btn, R.id.move_kefu_btn, R.id.move_shoucang_btn, R.id.move_add_shopcat_btn, R.id.tv_move_about, R.id.rebang_layout_btn,
            R.id.see_more_wenyiwen, R.id.move_see_all_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.move_shopcat_btn://购物车
                CacheActivity.finishSingleActivityByClass(RecommendActivity.class);
                CacheActivity.finishSingleActivityByClass(SearchActivity.class);
                CacheActivity.finishSingleActivityByClass(SearchListActivity.class);
                CacheActivity.finishSingleActivityByClass(CommodityActivity.class);
                CacheActivity.finishSingleActivityByClass(BrandActivity.class);
                CacheActivity.finishSingleActivityByClass(LimitedActivity.class);
                CacheActivity.finishSingleActivityByClass(CollectionTotalActivity.class);
//                setResult(10);
                new MainShoppingEvent("10").post();
                finish();
                break;
            case R.id.move_kefu_btn://客服
                PermissionHelper.with(getContext()).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                if (!UserUtils.getInstance().getUserId().equals("")) {
                                    initSdk();
                                } else {
                                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });


                break;
            case R.id.move_shoucang_btn://收藏
                if (mark.equals("0")) {
                    mark = "1";
                    presenter.getMoveCollectionShop(moveDataBean.getInfo().getGoods_id(), mark);
                } else if (mark.equals("1")) {
                    mark = "0";
                    presenter.getMoveCollectionShop(moveDataBean.getInfo().getGoods_id(), mark);
                }
                break;
            case R.id.move_add_shopcat_btn://加入购物车
                if (moveDataBean == null) {
                    return;
                }
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                String getSearch_attr = moveDataBean.getInfo().getSearch_attr();//默认选中的规格和颜色
                    TypeSelectDialog.with(getActivity(), moveDataBean, "", "", new TypeSelectDialog.AddShopCarClickListener() {
                        @Override
                        public void onBtnClickListener(String goods_id,String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean,String mmake) {
                            Log.e("onBtnClLister=购物车==", "==getAttr_names==" + getAttr_names);
//                            getAttr_name = getAttr_names;
                            presenter.getAddShopCar(goods_id, product_id, num,"");
                        }
                    }).show();
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();

//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }


                break;
            case R.id.tv_move_about://立即购买
                if (moveDataBean == null) {
                    return;
                }

                if (!UserUtils.getInstance().getUserId().equals("")) {
                    TypeSelectDialog.with(getActivity(), moveDataBean, "", "1", new TypeSelectDialog.AddShopCarClickListener() {
                        @Override
                        public void onBtnClickListener(String goods_id, String getRec_id,String product_id, String num, String getAttr_names,ProductBean productBean, String mmake) {
                            Log.e("onBtnClickListen=即购买==", "==getAttr_names==" + getAttr_names);
//                            getAttr_name = getAttr_names;
//                            presenter.getAddShopCar(goods_id, product_id, num);
                            mmark = mmake;
//                            presenter.getConfirmOrderData("", goods_id, product_id, num, "", "", "");
                            presenter.getConfirmOrderData("", goods_id, product_id, num, "", "", "", "", "", "", "","","");
                            product_ids = product_id;
                            nums = num;

                        }
                    }).show();
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.rebang_layout_btn://进入热榜列表
                ReBangListActivity.startSelf(MoveAbooutActivity_2.this, moveDataBean.getSalable().getType());

                break;

            case R.id.see_more_wenyiwen://问一问查看更多
                ProblemActivity.startSelf(MoveAbooutActivity_2.this, moveDataBean);

                break;
            case R.id.move_see_all_btn://为您推荐查看更多
                new MainHomeActivityEvent("4").post();
                finish();
                break;
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dt = dt - 1;

            long hours = dt / (60 * 60);
            long minutes = (dt / 60) % 60;
            long seconds = dt % 60;

            hourss = String.valueOf(hours);
            minutess = String.valueOf(minutes);
            secondss = String.valueOf(seconds);

            if (hours < 10) {
                hourss = "0" + hours;
            }
            if (minutes < 10) {
                minutess = "0" + minutes;
            }
            if (seconds < 10) {
                secondss = "0" + seconds;
            }
//            Log.e("倒计时--详情页", "seconds====" + secondss);


            //设置倒计时
            moveXianshiHour.setText(hourss + ":" + minutess + ":" + secondss);
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dt <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };


    //分享
    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//好友
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//朋友圈
        share_cancel_btn = customView.findViewById(R.id.share_cancel_btn);//取消

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
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

        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        //http://h5.vinnlook.com/detail-share/index.html?good_id=6&search_attr=133|134
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr();//分享商品链接


        WXMediaMessage msg = new WXMediaMessage(webpage);
//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                msg.title = "vinnlook美瞳 ";
                msg.description = moveDataBean.getInfo().getShop_name() + "\t" + moveDataBean.getInfo().getShop_attr_name();
                Bitmap thumbBmp = null;
                try {
                    if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
                        thumbBmp = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getBanner().get(0).getUrl()).openStream());
                    } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
                        thumbBmp = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getBanner().get(1).getUrl()).openStream());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("分享", "==thumbBmp==" + thumbBmp);
                Bitmap thumbBmps = Bitmap.createScaledBitmap(thumbBmp, 120, 150, true);

                Log.e("分享", "==thumbBmps21111==" + thumbBmps);
                thumbBmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmps, true);

            }
        }).start();

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


        //微信好友
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SPUtils.getInstance().save("Good_id", moveDataBean.getInfo().getGoods_id());
//                SPUtils.getInstance().save("Search_attr", moveDataBean.getInfo().getSearch_attr());


                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }


            }
        });
        //微信朋友圈
        wx_pyq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
            }
        });
        //取消
        share_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }


    //接收消息
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

        //判断是不是视频， 改变主页数据
//        List<MoveDataBean.InfoBean.BannerBean> banlist = new ArrayList<>();
//        MoveDataBean.InfoBean.BannerBean baner = null;
//        for (int i = 0; i < event.getBanner().getBanner().size(); i++) {
//            baner.setUrl(event.getBanner().getBanner().get(i).getUrl());
//            baner.setType(event.getBanner().getBanner().get(i).getType());
//            banlist.add(baner);
//        }
//        BannerImgAdapter bannerImgAdapter = new BannerImgAdapter(getActivity(), strings);
//        banner.setAdapter(bannerImgAdapter);
//        banner.setIndicator(new CircleIndicator(getActivity()));
//        banner.addBannerLifecycleObserver(this)
//                .setAdapter(new MultipleTypesAdapter(this, banlist))
//                .setIndicator(new NumIndicator(this))
//                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
//                .addOnPageChangeListener(new OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    }
//                    @Override
//                    public void onPageSelected(int position) {
//                        Log.e("--", "position:" + position);
//                        if (player == null) {
//                            RecyclerView.ViewHolder viewHolder = banner.getAdapter().getViewHolder();
//                            if (viewHolder instanceof VideoHolder) {
//                                VideoHolder holder = (VideoHolder) viewHolder;
//                                player = holder.player;
//                            }
//                            return;
//                        }
//                        if (position != 0) {
//                            player.onVideoReset();
//                        }
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//                    }
//                });

//-----------------------------------------------------------------------------
//        BannerImageAdapter bannerImgAdapter = new BannerImageAdapter(getActivity(), getBannerEvent);
//        banner.setAdapter(bannerImgAdapter);
//        banner.setIndicator(new CircleIndicator(this));
//        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        banner.setImages(getBannerEvent);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        banner.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null)
            player.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null)
            player.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        //释放所有
        if (player != null)
            player.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    //选完规格后返回的价格（接收消息）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ChangeDetailPriceEvent event) {
        Log.e("ChangeDetailPriceEvent", "==event===" + event);


//        move_transaction_price.setText("" + event.getPrice());
        if (moveDataBean.getInfo().getIs_promote().equals("1")) {//显示限时页面
            moveDataBean.getInfo().setPreferential_price(event.getProductBean().getProduct_price());
            moveTransactionPrices1.setText(Html.fromHtml("&yen") + event.getProductBean().getProduct_price());
        } else if (moveDataBean.getInfo().getIs_promote().equals("0")) {//显示普通页面
            moveDataBean.getInfo().setProduct_price(event.getProductBean().getProduct_price());
            moveTransactionPrices2.setText(Html.fromHtml("&yen") + event.getProductBean().getProduct_price());
        }
//        moveDataBean.getInfo().setShop_attr_name(event.getAttr_name());
        moveDataBean.getInfo().setShop_attr_name(event.getProductBean().getAttr_name());
        moveTypeText.setText("已选 " + event.getProductBean().getAttr_name());

        detailsDiameter.setText(event.getProductBean().getDiameter());//直径
        detailsBaseCurve.setText(event.getProductBean().getBase_curve());//基弧
        detailsWaterContent.setText(event.getProductBean().getWater_content());//含水量
        detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//着色直径


//        detailsAdapter.setDatas(moveDataBean);
//        detailsAdapter.notifyDataSetChanged();


    }


    /**
     * 初始化SDK
     */
    private void initSdk() {
        //设置sdk 显示语言版本
//        initLanguage("en");
        /*
          第一步:初始化help
         */
        final KfStartHelper helper = new KfStartHelper(this);
        /*
          商品信息实例，若有需要，请参照此方法；
         */
        handleCardInfo(helper);
         /*
          新卡片类型示例，若有需要，请参照此方法；
         */
//        handleNewCardInfo(helper);
        /*
          第二步:设置参数
          初始化sdk方法，必须先调用该方法进行初始化后才能使用IM相关功能
          @param accessId       接入id（需后台配置获取）
          @param userName       用户名
          @param userId         用户id
         */
        /*
         * 修改会话页面 注销按钮 文案。建议两个 文字
         */
//        helper.setChatActivityLeftText("注销");

        helper.setOrderHead("", UserUtils.getInstance().getUserInfo().getImg_url());

        /*
         * 修改会话页面 是否需要 emoji表情 按钮。
         */
//        helper.setChatActivityEmoji(true);
        String userID = UserUtils.getInstance().getUserInfo().getUser_id();
        if (UserUtils.getInstance().getUserInfo().getUser_id().length() < 2) {
            userID = "0" + userID;
        }
        helper.initSdkChat("97e623b0-f404-11ea-938a-2d31778d2422", UserUtils.getInstance().getUserInfo().getUser_name(), userID);


    }

    /**
     * 获取未读消息数示例
     */
    private void getUnReadCount() {
        if (MoorUtils.isInitForUnread(getApplicationContext())) {

            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
                    Toast.makeText(MoveAbooutActivity_2.this, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //未初始化，消息当然为 ：0
            Toast.makeText(MoveAbooutActivity_2.this, "还没初始化", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 商品信息示例
     *
     * @param helper
     */
    private void handleCardInfo(KfStartHelper helper) {
        String rigth3 = null;
        CardInfo ci = null;
        String icon = null;
        String s = "https://wap.boosoo.com.cn/bobishop/goodsdetail?id=10160&mid=36819";//详情链接
//        CardInfo ci = new CardInfo("http://seopic.699pic.com/photo/40023/0579.jpg_wh1200.jpg", "我是一个标题当初读书", "我是name当初读书。", "价格 1000-9999", "https://www.baidu.com");


        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            icon = moveDataBean.getInfo().getBanner().get(0).getUrl();
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            icon = moveDataBean.getInfo().getBanner().get(1).getUrl();
        }

        String title = moveDataBean.getInfo().getShop_name();
        String content = moveDataBean.getInfo().getShop_attr_name();
        if (moveDataBean.getInfo().getIs_promote().equals("1")) {//显示限时页面
            rigth3 = Html.fromHtml("&yen") + moveDataBean.getInfo().getPreferential_price();
        } else if (moveDataBean.getInfo().getIs_promote().equals("0")) {//显示普通页面
            rigth3 = Html.fromHtml("&yen") + moveDataBean.getInfo().getProduct_price();
        }
        try {
            ci = new CardInfo(URLEncoder.encode(icon, "utf-8"), URLEncoder.encode(title, "utf-8"),
                    URLEncoder.encode(content, "utf-8"), URLEncoder.encode(rigth3, "utf-8"),
                    URLEncoder.encode(s, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.setCard(ci);
    }

    /**
     * 新卡片类型示例,{@link NewCardInfo.Builder()} Builder中默认添加了一些字段，请在此自行定制
     */
    private void handleNewCardInfo(KfStartHelper helper) {
//        NewCardInfo newCardInfo = new NewCardInfo.Builder()
//                .build();

        NewCardInfo newCardInfo = new NewCardInfo.Builder()
                .setTitle("我是标题")
                .setAttr_one(new NewCardInfoAttrs().setColor("#487903").setContent("x9"))
                .setAttr_two(new NewCardInfoAttrs().setColor("#845433").setContent("未发货"))
                .setOther_title_one("附件信息")
                .setOther_title_two(null)
                .setOther_title_three(null)
                .setSub_title("我是副标题")
                .setPrice("$999")
                .build();


        helper.setNewCardInfo(newCardInfo);
    }

    /**
     * 语言切换
     * 中文 language：""
     * 英文 language："en"
     */
    private void initLanguage(String language) {
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//更新配置
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
//                    CustomerService();

                    break;
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 一键登录
     */
    private void inits() {
        /*
         *   1.init get token callback Listener
         */
        mTokenListener = new TokenResultListener() {
            @Override
            public void onTokenSuccess(final String ret) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("xxxxxx", "onTokenSuccess:" + ret);

                        /*
                         *   setText just show the result for get token。
                         *   use ret to verfiy number。
                         */
//                        mAlicomAuthHelper.hideLoginLoading();
                        dismissLoadingDialog();
                        TokenRet tokenRet = null;
                        try {
                            tokenRet = JSON.parseObject(ret, TokenRet.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        configLoginTokenPortDialog();


                        if (tokenRet != null && ("600024").equals(tokenRet.getCode())) {
                            Log.e("获取到的Token值", "===终端自检成功:\n==Token值===" + ret);
                        }

                        if (tokenRet != null && ("600001").equals(tokenRet.getCode())) {
                            Log.e("获取到的Token值", "===唤起授权页成功:\n==Token值===" + ret);
                        }

                        if (tokenRet != null && ("600000").equals(tokenRet.getCode())) {
                            String token = tokenRet.getToken();
                            mAlicomAuthHelper.quitLoginPage();
                            Log.e("获取到的Token值", "===获取token成功:\n==Token值=11==" + ret);
                            presenter.getMobileLogin(token);

                            //将Token值

                        }
                    }
                });
            }

            @Override
            public void onTokenFailed(final String ret) {
                Log.e("xxxxxx", "onTokenFailed:" + ret);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                         *  setText just show the result for get token
                         *  do something when getToken failed, such as use sms verify code.
                         */
//                        hideLoadingDialog();
                        dismissLoadingDialog();
//                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        Log.e("获取到的Token值", "===获取token失败:\n==Token值=22==" + ret);
                        mAlicomAuthHelper.quitLoginPage();
                        LoginActivity.startSelf(getActivity());
                    }
                });
            }
        };
        /*
         *   2.init AlicomAuthHelper with tokenListener
         */
        mAlicomAuthHelper = PhoneNumberAuthHelper.getInstance(getActivity(), mTokenListener);
        mAlicomAuthHelper.setAuthListener(mTokenListener);
        /*
         *   3.set debugMode when app is in debug mode, sdk will print log in debug mode
         */
        mAlicomAuthHelper.setLoggerEnable(true);//设置SDK是否开启日
        mAlicomAuthHelper.setAuthSDKInfo(Constant.LONIG_PHONE_NUMBER_KEY);//设置秘钥


    }

    private void configLoginTokenPortDialog() {
//        initDynamicView();

        mAlicomAuthHelper.removeAuthRegisterXmlConfig();
        mAlicomAuthHelper.removeAuthRegisterViewConfig();
        int authPageOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
        if (Build.VERSION.SDK_INT == 26) {
            authPageOrientation = ActivityInfo.SCREEN_ORIENTATION_BEHIND;
        }
        updateScreenSize(authPageOrientation);
        int dialogWidth = (int) (mScreenWidthDp * 0.8f);
        int dialogHeight = (int) (mScreenHeightDp * 0.65f);
        int logBtnOffset = dialogHeight / 2;
        int logBtnOffset_1 = dialogHeight * 2;
        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
                .setLayout(R.layout.login_item_layou_1, new AbstractPnsViewDelegate() {
                    @Override
                    public void onViewCreated(View view) {
//                        login_btn1[0] = findViewById(R.id.login_btn1);
                        findViewById(R.id.login_btn1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {//其他号码登录
                                mAlicomAuthHelper.hideLoginLoading();
                                LoginActivity.startSelf(getActivity());
                                mAlicomAuthHelper.quitLoginPage();
                            }
                        });

                        findViewById(R.id.login_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                login();//微信登陆

                            }
                        });
//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1);  // , 1是可选写的
//                        lp.setMargins(0, logBtnOffset_1+280, 0, 0);
//                        findViewById(R.id.login_btn1).setLayoutParams(lp);

                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.id_2).getLayoutParams();

                        layoutParams.topMargin = findViewById(R.id.id_2).getTop() + logBtnOffset_1 + 200;
                        findViewById(R.id.id_2).setLayoutParams(layoutParams);


                    }
                })
                .build());

//        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
//                .setLayout(R.layout.login_item_layou_2, new AbstractPnsViewDelegate() {
//                    @Override
//                    public void onViewCreated(View view) {
//                        findViewById(R.id.login_wechat).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                login();//微信登陆
//                            }
//                        });
////                        findViewById(R.id.login_qq).setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////
////                            }
////                        });
////                        findViewById(R.id.login_weibo).setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////
////                            }
////                        });
//                    }
//                })
//                .build());


        mAlicomAuthHelper.setAuthUIConfig(new AuthUIConfig.Builder()
                .setAppPrivacyOne("《隐私政策》", "http://shop.jealook.com/v1/html/article-info?id=117")
                .setAppPrivacyTwo("《用户协议》", "http://shop.jealook.com/v1/html/article-info?id=119")
                .setAppPrivacyColor(Color.BLACK, Color.parseColor("#A08FBB"))
                .setPrivacyState(true)
                .setCheckboxHidden(true)
                .setNavHidden(false)
                .setNavColor(getResources().getColor(R.color.them))
                .setWebNavColor(Color.parseColor("#A08FBB"))//设置协议顶部导航栏背景色
                .setStatusBarColor(Color.parseColor("#A08FBB"))//设置状态栏颜色
                .setLightColor(false)
                .setAuthPageActIn("in_activity", "out_activity")
                .setAuthPageActOut("in_activity", "out_activity")
                .setVendorPrivacyPrefix("《")
                .setVendorPrivacySuffix("》")
                .setLogBtnWidth(dialogWidth - 30)
                .setLogBtnMarginLeftAndRight(15)
                .setNavReturnHidden(true)//隐藏导航栏按钮
                .setLogBtnOffsetY(logBtnOffset + 60)
                .setNumFieldOffsetY(logBtnOffset)
                .setPageBackgroundPath("login_bg")
                .setLogBtnTextSize(18)
                .setDialogBottom(false)
                .setScreenOrientation(authPageOrientation)
                .setNavText("")
                .setSloganText("")
                .setSloganTextColor(Color.parseColor("#00000000"))
                .setNumberColor(Color.parseColor("#A08FBB"))
                .setNumberSize(27)
                .setSwitchAccHidden(true)
                .setLogBtnText("本机号码一键登录")
                .setLogBtnBackgroundPath("classify_list_item")
                .setLogBtnHeight(40)
                .setPrivacyOffsetY_B(110)
                .create());

    }

    private void updateScreenSize(int authPageScreenOrientation) {
        int screenHeightDp = AppUtils.px2dp(getActivity().getApplicationContext(), AppUtils.getPhoneHeightPixels(getActivity()));
        int screenWidthDp = AppUtils.px2dp(getActivity().getApplicationContext(), AppUtils.getPhoneWidthPixels(getActivity()));
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if (authPageScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_BEHIND) {
            authPageScreenOrientation = getActivity().getRequestedOrientation();
        }
        if (authPageScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                || authPageScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                || authPageScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT) {
            rotation = Surface.ROTATION_180;
        }
        switch (rotation) {
            case Surface.ROTATION_180:
                mScreenWidthDp = screenWidthDp;
                mScreenHeightDp = screenHeightDp;
                break;
        }
    }

    /**
     * 微信登录(三个步骤)
     * 1.微信授权登录
     * 2.根据授权登录code 获取该用户token
     * 3.根据token获取用户资料
     */
    public void login() {
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = String.valueOf(System.currentTimeMillis());
//        wxAPI.sendReq(req);

        if (!App.getwxApi().isWXAppInstalled()) {
            Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = String.valueOf(System.currentTimeMillis());
            App.getwxApi().sendReq(req);
        }

    }

    /**
     * @Description:一键登录成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginSuccess(int code, UserInfoBean data) {
        UserUtils.getInstance().login(data);
        // 用户登录埋点
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
//        new LoginDataEvent(data).post();
//        new MainHomeActivityEvent("2").post();
//        presenter.getPersonalInformation();//下载
//        finish();


    }

    /**
     * @Description:一键登录失败
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginFail(int code, String msg) {

    }

    /**
     * @Description:结算页面接口成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
        if (mmark.equals("1")) {
            ConfirmOrderActivity_1.startSelf(MoveAbooutActivity_2.this, "", goods_id, product_ids, nums,"2","","","");

            TypeSelectDialog.dismiss();
        }
    }

    /**
     * @Description:结算页面接口失败
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getConfirmOrderFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Description:下载类型成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
        List<Banner> strings = new ArrayList<>();

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(ecommendBean.getGoods_thumb());
        banner.add(bannerBeans);
//        strings.add(ecommendBean.getGoods_thumb());
//        infoBean.setBanner(strings);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(ecommendBean.getSearch_attr());
        infoBean.setGoods_id(ecommendBean.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(ecommendBean.getProduct_price());
        data.setInfo(infoBean);

        TypeSelectDialog.with(getActivity(), data, ecommendBean.getSearch_attr(), "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id,String product_id, String num, String getAttr_name, ProductBean productBean,String mmake) {
//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();

    }

    /**
     * @Description:下载类型失败
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    @Override
    public void getTypeShop4Success(int code, MoveDataBean data) {

    }

    @Override
    public void getTypeShop4Fail(int code, String msg) {

    }

    @Override
    public void getTypeShop5Success(int code, MoveDataBean data) {

    }

    @Override
    public void getTypeShop5Fail(int code, String msg) {

    }

    @Override
    public void getTypeShop6Success(int code, MoveDataBean data) {

    }

    @Override
    public void getTypeShop6Fail(int code, String msg) {

    }

}
