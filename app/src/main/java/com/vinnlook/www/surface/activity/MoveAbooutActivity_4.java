package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.m7.imkfsdk.KfStartHelper;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.utils.MoorUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.ChangeDetailPriceEvent;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.event.ProblemListEvent;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.indicator.NumIndicator;
import com.vinnlook.www.surface.adapter.CommentListAdapter;
import com.vinnlook.www.surface.adapter.DetailsImags1Adapter;
import com.vinnlook.www.surface.adapter.DetailsImags2Adapter;
import com.vinnlook.www.surface.adapter.GroupMemberAdapter;
import com.vinnlook.www.surface.adapter.MoveGroupAdapter;
import com.vinnlook.www.surface.adapter.MultipleTypesAdapter;
import com.vinnlook.www.surface.adapter.ShopColourImgAdapter;
import com.vinnlook.www.surface.adapter.TuiJianAdapter;
import com.vinnlook.www.surface.adapter.WenListAdapter;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.DatasBean;
import com.vinnlook.www.surface.bean.DetailsBean;
import com.vinnlook.www.surface.bean.GroupDetailsListBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.presenter.MoveAboutPresenter;
import com.vinnlook.www.surface.mvp.view.MoveAboutView;
import com.vinnlook.www.surface.viewholder.VideoHolder;
import com.vinnlook.www.utils.AppUtils;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SavePhoto;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vinnlook.www.utils.DoubleOperationUtils.DF;

/**
 * @Description:商品详情
 * @Time:2020/4/20$
 * @Author:pk$
 */
public class MoveAbooutActivity_4 extends BaseActivity<MoveAboutPresenter> implements MoveAboutView {

    private static final int BAIDU_READ_PHONE_STATE = 100;

    @BindView(R.id.title_bars)
    RelativeLayout titleBar;
    @BindView(R.id.action_bar)
    MoveAboutBarSimple actionBar;
    private static String group_id = "";
    private static String groupId = "";
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
    private static String type;
    @BindView(R.id.details_suppliers)
    TextView detailsSuppliers;
    @BindView(R.id.move_transaction_name)
    TextView moveTransactionName;

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
    LinearLayout xiangqingLayout;//详情整体布局
    @BindView(R.id.xuzhi_layout)
    LinearLayout xuzhiLayout;//用户须知整体布局


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
    @BindView(R.id.pingjia_line)
    View pingjiaLine;
    @BindView(R.id.wendajia_line)
    View wendajiaLine;
    @BindView(R.id.move_xiaoliang_text)
    TextView moveXiaoliangText;

    List<MoveDataBean.QuestionListBean> getQuestion_list;
    @BindView(R.id.pingjia_see_all)
    LinearLayout pingjiaSeeAll;
    @BindView(R.id.wen_see_all)
    LinearLayout wenSeeAll;
    @BindView(R.id.move_transaction_supi)
    RoundTextView moveTransactionSupi;
    @BindView(R.id.move_add_shopcat_btn)
    RoundLinearLayout moveAddShopcatBtn;//加入购物车
    @BindView(R.id.move_xianshi_min)
    TextView moveXianshiMin;
    @BindView(R.id.tv_move_about)
    RoundLinearLayout tvMoveAbout;//立即购买
    @BindView(R.id.banners)
    Banner banners;
    @BindView(R.id.move_xianshi_day)
    TextView moveXianshiDay;
    @BindView(R.id.group_text_fuhao)
    TextView groupTextFuhao;
    @BindView(R.id.group_text_price)
    TextView groupTextPrice;
    @BindView(R.id.group_text_yuan_price)
    TextView groupTextYuanPrice;
    @BindView(R.id.group_text_number1)
    TextView groupTextNumber1;
    @BindView(R.id.group_text_people1)
    TextView groupTextPeople1;
    @BindView(R.id.group_text_people2)
    TextView groupTextPeople2;
    @BindView(R.id.group_text_number2)
    TextView groupTextNumber2;
    @BindView(R.id.group_add_type)
    ImageView groupAddType;
    @BindView(R.id.group_recyclerView)
    RecyclerView groupRecyclerView;
    @BindView(R.id.group_yuan_price_text)
    TextView groupYuanPriceText;
    @BindView(R.id.group_price_text)
    TextView groupPriceText;
    @BindView(R.id.move_group_hour)
    RoundTextView moveGroupHour;
    @BindView(R.id.move_group_min)
    RoundTextView moveGroupMin;
    @BindView(R.id.move_group_dec)
    RoundTextView moveGroupDec;
    @BindView(R.id.move_group_recy)
    RecyclerView moveGroupRecy;
    @BindView(R.id.move_group_fuhao)
    TextView moveGroupFuhao;
    @BindView(R.id.move_group_price)
    TextView moveGroupPrice;
    @BindView(R.id.move_group_price_yuan)
    TextView moveGroupPriceYuan;
    @BindView(R.id.move_group_peo)
    RoundTextView moveGroupPeo;
    @BindView(R.id.group_layout_yes)
    LinearLayout groupLayoutYes;
    @BindView(R.id.move_group_front)
    LinearLayout moveGroupFront;
    @BindView(R.id.select_num_layout)
    LinearLayout selectNumLayout;
    @BindView(R.id.yaoqing_cantuan_text)
    TextView yaoqingCantuanText;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.move_shop_explain)
    TextView moveShopExplain;
    @BindView(R.id.move_shop_explain_layout)
    LinearLayout moveShopExplainLayout;
    @BindView(R.id.move_shop_attr_recycler)
    RecyclerView moveShopAttrRecycler;
    @BindView(R.id.move3_huodong_img)
    ImageView move3HuodongImg;
    @BindView(R.id.move_group_peo2)
    RoundTextView moveGroupPeo2;
    @BindView(R.id.group_success_no)
    LinearLayout groupSuccessNo;
    @BindView(R.id.group_success_yes)
    LinearLayout groupSuccessYes;
    @BindView(R.id.group_ing_no)
    LinearLayout groupIngNo;
    @BindView(R.id.group_ing_yes)
    LinearLayout groupIngYes;
    @BindView(R.id.group_fail_no)
    LinearLayout groupFailNo;
    @BindView(R.id.group_fail_yes)
    LinearLayout groupFailYes;
    @BindView(R.id.move_success_no_layout_btn1)
    RoundLinearLayout moveSuccessNoLayoutBtn1;
    @BindView(R.id.move_success_no_layout_btn2)
    LinearLayout moveSuccessNoLayoutBtn2;
    @BindView(R.id.move_success_no_layout_btn)
    LinearLayout moveSuccessNoLayoutBtn;
    @BindView(R.id.move_success_yes_layout_btn1)
    RoundLinearLayout moveSuccessYesLayoutBtn1;
    @BindView(R.id.move_success_yes_layout_btn2)
    LinearLayout moveSuccessYesLayoutBtn2;
    @BindView(R.id.move_success_yes_layout_btn)
    LinearLayout moveSuccessYesLayoutBtn;
    @BindView(R.id.move_group_ing_no_btn1)
    RoundLinearLayout moveGroupIngNoBtn1;
    @BindView(R.id.move_group_ing_no_btn2)
    LinearLayout moveGroupIngNoBtn2;
    @BindView(R.id.move_group_ing_no_layout)
    LinearLayout moveGroupIngNoLayout;
    @BindView(R.id.move_group_ing_yes_btn1)
    RoundLinearLayout moveGroupIngYesBtn1;
    @BindView(R.id.move_group_ing_yes_btn2)
    LinearLayout moveGroupIngYesBtn2;
    @BindView(R.id.move_group_ing_yes_layout)
    LinearLayout moveGroupIngYesLayout;


    private float totaldy;
    private List<DetailsBean> list;
    private Resources res;
    @BindView(R.id.activity_name_text)
    TextView activityNameText;


    private static String goods_id;//商品详情
    private static String search_attr;//商品规格
    @BindView(R.id.group_time_layout)
    RoundLinearLayout groupTimeLayout;
    @BindView(R.id.group_time_text_over)
    TextView grouTimeTextOver;
    String group_info;
    int finalNumss;


    MoveDataBean moveDataBean;
    String product_ids = "";
    String nums = "";
    UserInfoBean getUserInfo;
    private int mScreenWidthDp;
    private int mScreenHeightDp;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

    public PopupWindow popupwindow;
    public PopupWindow popupwindow1;

    String mmark;//选择规格弹框路径；1--商品详情页面；“”--购物车
    ShopColourImgAdapter shopColourImgAdapter;
    MoveGroupAdapter adapterGroup;//选择商品适配器

    String goods_attr;
    GroupMemberAdapter adapterMember;//团购成员
    CommentListAdapter commentAdapter;//评价适配器
    WenListAdapter wenAdapter;//问一问适配器
    DetailsImags1Adapter adapter;//详情适配器
    DetailsImags2Adapter adapter2;//须知适配器
    TuiJianAdapter adapter4;//大家都在买
    List<String> hasProductList = new ArrayList<>();
    List<GroupDetailsListBean> listBeant = new ArrayList<>();

    int shangPinHeight;
    int pingJiaHeight;
    int xiangQingHeight;
    int xuZhiHeight;
    int titleBarHeight;

    MoveDataBean.RecommendBean ecommendBean;
    StandardGSYVideoPlayer player;

    Bitmap bitmaps;//二维码
    GroupDetailsListBean groupDetailsBean;
    String urlselect;
    String orderId;
    int position4;
    private long surpTime = 0;
    private long endTime = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            surpTime = surpTime - 1;
            long days = surpTime / (60 * 60 * 24);
            long hours1 = surpTime % (60 * 60 * 24) / (60 * 60);
            long minutess1 = surpTime % (60 * 60) / 60;
            long secondss1 = surpTime % 60;

            String dayss;
            String hourss;
            String minutess;
            String secondss;
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
            moveXianshiDay.setText(dayss);
            moveXianshiHour.setText(hourss);
            moveXianshiMin.setText(minutess);

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (surpTime <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_about_4;
    }

    @Override
    protected MoveAboutPresenter initPresenter() {
        return new MoveAboutPresenter();
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);

        Intent intent = getIntent();
        goods_id = intent.getStringExtra("good_id");
        search_attr = intent.getStringExtra("search_attr");
        Log.e("goods_id", "=-goods_id==111=====" + goods_id);
        Log.e("search_attr", "=-search_attr==111====" + search_attr);
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
//        mRecyclerFactor = (DensityUtilss.dp2px(this, 400.0F) - DensityUtilss.getStatusBarHeight(this));
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
                        titleBarHeight = titleBar.getHeight() + 160;
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
                    StatusBarUtil.setColor(MoveAbooutActivity_4.this, Color.parseColor("#ffffff"));
                }


            }
        });


    }

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            endTime = endTime - 1;
            long days = endTime / (60 * 60 * 24);
            long hours1 = endTime % (60 * 60 * 24) / (60 * 60);
            long minutess1 = endTime % (60 * 60) / 60;
            long secondss1 = endTime % 60;

            String dayss;
            String hourss;
            String minutess;
            String secondss;
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
//
//            Log.e("团", "倒计时===hourss===" + hourss);
//            Log.e("团", "倒计时===minutess===" + minutess);
//            Log.e("团", "倒计时===secondss===" + secondss);
            moveGroupHour.setText(hourss);
            moveGroupMin.setText(minutess);
            moveGroupDec.setText(secondss);

            handler1.removeMessages(1);
            handler1.sendEmptyMessageDelayed(1, 1000);
            if (endTime <= 0) {
                handler1.removeCallbacksAndMessages(null);
            }
        }
    };

    public static void startSelf(Activity context, String goods_ids, String search_attrs, String group_ids, String types) {
        Intent intent = new Intent(context, MoveAbooutActivity_4.class);
        intent.putExtra("good_id", goods_ids);
        intent.putExtra("search_attr", search_attrs);
        context.startActivityForResult(intent, 1);
        groupId = goods_ids;
        goods_id = goods_ids;
        search_attr = search_attrs;
        group_id = group_ids;
        type = types;
        Log.e("goods_id", "=-group_id=" + group_id);
        Log.e("goods_id", "=-goods_id=" + goods_id);
        Log.e("search_attr", "=-search_attr=" + search_attr);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //创建适配器
    private void initAdapter() {
        //颜色图片
        shopColourImgAdapter = new ShopColourImgAdapter(this);
        final GridLayoutManager manag = new GridLayoutManager(this, 1);
        manag.setOrientation(LinearLayoutManager.HORIZONTAL);
        moveShopAttrRecycler.setLayoutManager(manag);
        moveShopAttrRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        moveShopAttrRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        moveShopAttrRecycler.setNestedScrollingEnabled(false);
        moveShopAttrRecycler.setHasFixedSize(false);
        shopColourImgAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getShopBanner = shopColourImgAdapter.getData().get(position).getBanner();
                //判断是不是视频， 改变主页数据
                List<MoveDataBean.InfoBean.BannerBean> banlist = new ArrayList<>();
                for (int i = 0; i < getShopBanner.size(); i++) {
                    MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
                    bannerBeans.setUrl(getShopBanner.get(i).getUrl());
                    bannerBeans.setType(getShopBanner.get(i).getType());
                    banlist.add(bannerBeans);
                }

                banners.addBannerLifecycleObserver(MoveAbooutActivity_4.this)
                        .setAdapter(new MultipleTypesAdapter(MoveAbooutActivity_4.this, banlist, moveDataBean.getInfo().getProduct_price(), moveDataBean.getInfo().getBorder_image()))
                        .setIndicator(new NumIndicator(MoveAbooutActivity_4.this))
                        .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                        .addOnPageChangeListener(new OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            }

                            @Override
                            public void onPageSelected(int position) {
                                Log.e("--", "position:" + position);
                                if (player == null) {

                                    RecyclerView.ViewHolder viewHolder = banners.getAdapter().getViewHolder();
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

                Log.e("选择的颜色", "===名称:===11111111=====");
                Log.e("选择的颜色", "===名称:========" + moveDataBean.getInfo().getShop_name() + shopColourImgAdapter.getData().get(position).getShop_attr_name());
                moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + shopColourImgAdapter.getData().get(position).getShop_attr_name());
                goods_attr = shopColourImgAdapter.getData().get(position).getGoods_attr_id() + "|" + moveDataBean.getAttr().get(1).getValue().get(0).getGoods_attr_id();
                if (shopColourImgAdapter.getData().get(position).getFlage().equals("0")) {
                    shopColourImgAdapter.getData().get(position).setFlage("1");
                } else if (shopColourImgAdapter.getData().get(position).getFlage().equals("1")) {
                    shopColourImgAdapter.getData().get(position).setFlage("0");
                }
                shopColourImgAdapter.notifyDataSetChanged();


            }
        });

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
        imgListDetails1.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
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
        imgListDetails2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        imgListDetails2.addItemDecoration(new SpaceItemDecoration(0, 0));
        imgListDetails2.setNestedScrollingEnabled(false);
        imgListDetails2.setFocusable(false);


        //大家都在买
        adapter4 = new TuiJianAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        moveTuijianRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        moveTuijianRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        moveTuijianRecycler.setLayoutManager(manager1);
        moveTuijianRecycler.setNestedScrollingEnabled(false);
        moveTuijianRecycler.setFocusable(false);

        //拼团购买的商品
        adapterGroup = new MoveGroupAdapter(this);
        final GridLayoutManager manager11 = new GridLayoutManager(getActivity(), 1);
        groupRecyclerView.setLayoutManager(manager11);
        groupRecyclerView.setNestedScrollingEnabled(false);
        groupRecyclerView.setFocusable(false);
        //减掉
        adapterGroup.setOnItemClickListener(new MoveGroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapterGroup.getData().remove(position);
                hasProductList.remove(position);
                if (adapterGroup.getData().size() < 1) {
                    groupRecyclerView.setVisibility(View.GONE);
                } else {
                    groupRecyclerView.setVisibility(View.VISIBLE);
                }
                adapterGroup.notifyDataSetChanged();
            }
        });

        //已选择的商品--修改
        adapterGroup.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                String getGoods_attr = adapterGroup.getData().get(position).getSearch_attr();
//                moveDataBean.getInfo().setSearch_attr(getGoods_attr);
                position4 = position;
                goods_attr = getGoods_attr;
                presenter.getTypeShopData4(goods_id);


            }
        });


        //拼团成员
        adapterMember = new GroupMemberAdapter(this);
        final GridLayoutManager managergroup = new GridLayoutManager(getActivity(), 3);
        moveGroupRecy.setLayoutManager(managergroup);
        moveGroupRecy.setNestedScrollingEnabled(false);
        moveGroupRecy.setFocusable(false);

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

    @Override
    protected void loadData() {
        presenter.getMove4Datas(goods_id, search_attr, group_id, type);//下载商品详情数据
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
        Log.e("商品详情返回成功", "===getIs_promote===" + moveDataBean.getInfo().getIs_promote());
        //加载不同的布局
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        //计算秒杀倒计时---ms
        surpTime = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);


        //判断是否有活动
        if (!data.getAd_info().getPhoto().equals("") && data.getAd_info().getPhoto() != null) {
            move3HuodongImg.setVisibility(View.VISIBLE);
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            move3HuodongImg.setScaleType(ImageView.ScaleType.MATRIX);
            move3HuodongImg.setImageMatrix(matrix);
            ImageLoader.image(this, move3HuodongImg, data.getAd_info().getPhoto());
            move3HuodongImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url;
                    url = data.getAd_info().getList().getUrl();

                    if (url.contains("?")) {
                        url = url + "&userId=" + UserUtils.getInstance().getUserId();
                    } else {
                        url = url + "?userId=" + UserUtils.getInstance().getUserId();
                    }
                    WebActivity3.startSelf(getActivity(), url, data.getAd_info().getTitle_color());

                }
            });
        } else {
            move3HuodongImg.setVisibility(View.GONE);
        }

        //是否显示副标题
        if (data.getInfo().getGoods_brief().equals("")) {
            moveShopExplainLayout.setVisibility(View.GONE);
        } else {
            moveShopExplainLayout.setVisibility(View.VISIBLE);
        }

        //是否显示眼睛图片，0：不显示；1：显示
        if (data.getInfo().getIs_show_sye().equals("1")) {
            moveShopAttrRecycler.setVisibility(View.VISIBLE);
        } else if (data.getInfo().getIs_show_sye().equals("0")) {
            moveShopAttrRecycler.setVisibility(View.GONE);
        }
        for (int i = 0; i < data.getAttr().get(0).getValue().size(); i++) {
            data.getAttr().get(0).getValue().get(i).setFlage("0");
        }
        data.getAttr().get(0).getValue().get(0).setFlage("1");
        moveShopExplain.setText(data.getInfo().getGoods_brief());
        shopColourImgAdapter.setData(data.getAttr().get(0).getValue());
        moveShopAttrRecycler.setAdapter(shopColourImgAdapter);

        //加载Banner数据
//        BannerImgAdapter1 bannerImgAdapter = new BannerImgAdapter1(this, data.getInfo().getBanner());
//        banner.setAdapter(bannerImgAdapter);
//        banner.setIndicator(new CircleIndicator(getActivity()));
//        banner.start();
//        data.getInfo().getBanner().get(0).setUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        banners.addBannerLifecycleObserver(this)
                .setAdapter(new MultipleTypesAdapter(this, data.getInfo().getBanner(), data.getInfo().getProduct_price(), data.getInfo().getBorder_image()))
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
                            RecyclerView.ViewHolder viewHolder = banners.getAdapter().getViewHolder();
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

        List<MoveDataBean.InfoBean.BannerBean> getBanner = data.getInfo().getBanner();

        if (getBanner.size() > 0) {
            if (getBanner.size() == 1) {
                if (getBanner.get(0).getType() == 1) {
                    urlselect = getBanner.get(0).getUrl();
                } else if (getBanner.get(0).getType() == 2) {
                    urlselect = getBanner.get(1).getUrl();
                }
            } else if (getBanner.size() > 1) {
                if (getBanner.get(0).getType() == 1) {
                    urlselect = getBanner.get(1).getUrl();
                } else if (getBanner.get(0).getType() == 2) {
                    urlselect = getBanner.get(2).getUrl();
                }
            }
        }


        detailsVipPrice1.setText(Html.fromHtml("&yen") + data.getInfo().getMember_discount());

        //活动名称-标签
        activityNameText.setText(data.getInfo().getActive_name());
        //判断是否登录--是否开通会员
        if (UserUtils.getInstance().getUserId().equals("")) {//未登录
            detailsVipOpenText1.setText("立即开卡＞");
            detailsVipZhe1.setText("开通Plus会员，全年包邮再享9.5折");
            //会员入口
            detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoveAbooutActivity_4.this, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            });

        } else {//已登录
            if (UserUtils.getInstance().getUserInfo().getIs_member() == 0) {//未开通会员
                detailsVipOpenText1.setText("立即开卡＞");
                detailsVipZhe1.setText("开通Plus会员，全年包邮再享9.5折");
                //会员入口
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });

            } else if (UserUtils.getInstance().getUserInfo().getIs_member() == 1) {//已开通会员
                detailsVipOpenText1.setText("会员中心＞");
                detailsVipZhe1.setText("Plus会员全年包邮再享9.5折");
                //会员入口
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//会员购买入口
                        MemberActivity_1.startSelf(getContext(), "1");//会员购买入口
                    }
                });
            }
        }

        moveTransactionPrices1.setText(Html.fromHtml("&yen") + data.getShopActiveInfo().getGroup_price());
        moveTransactionPricesYuan1.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());
        //原价空间上加横线
        moveTransactionPricesYuan1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


        moveXiaoliangText.setText("总销量：" + data.getInfo().getVirtual_sales());//销量
        //商品名称
        moveTransactionName.setText(data.getInfo().getShop_name() + " " + data.getInfo().getShop_attr_name());
//        getPurchasing = "每日限购1件";


        detailsBrandName.setText(data.getInfo().getBrand_name());
        detailsGoodsSn.setText(data.getInfo().getGoods_sn());
        detailsTossPeriod.setText(data.getInfo().getToss_period());
        detailsColor.setText(data.getInfo().getColor());
        detailsOrigin.setText(data.getInfo().getOrigin());
        detailsLifeSpan.setText(data.getInfo().getLife_span());

        detailsDiameter.setText(data.getInfo().getDiameter());
        detailsBaseCurve.setText(data.getInfo().getBase_curve());
        detailsWaterContent.setText(data.getInfo().getWater_content());
        detailsColoringDiameter.setText(data.getInfo().getColoring_diameter());


        //评价
        pingjiaNumber.setText("(" + data.getInfo().getComment_count() + ")");
        commentAdapter.setData(data.getComment());
        recyclervComment2.setAdapter(commentAdapter);
        if (data.getComment().size() > 0) {
            pingjiaLine.setVisibility(View.VISIBLE);
        } else {
            pingjiaLine.setVisibility(View.GONE);
        }
        //查看评价
        pingjiaSeeAll.setOnClickListener(new View.OnClickListener() {
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
                MoveAbooutActivity_3.startSelf(getActivity(), adapter4.getData().get(position).getGoods_id(), adapter4.getData().get(position).getSearch_attr());
            }
        });
        adapter4.setTuiJianClickListener(new TuiJianAdapter.TuiJianClickListener() {
            @Override
            public void onGoClickListener(MoveDataBean.RecommendBean data, String getGoods_id, String getSearch_attr) {
                ecommendBean = data;
                ecommendBean.setSearch_attr(goods_attr);
                presenter.getTypeShopData(getGoods_id);
            }
        });

        if (group_id.equals("")) {
            group_id = data.getShopActiveInfo().getGroup_id();
        }
        orderId = data.getShopActiveInfo().getOrder_id();

        //判断是否开团
        if (!group_id.equals("")) {//已开团
            int perop = Integer.parseInt(data.getShopActiveInfo().getGroup_people()) - Integer.parseInt(data.getShopActiveInfo().getAgain_invite());

            groupLayoutYes.setVisibility(View.VISIBLE);

            //1:拼团中；2：拼团成功；3：拼团失败；4：活动结束
            if (data.getShopActiveInfo().getGroup_status().equals("1")) {//拼团中
                grouTimeTextOver.setVisibility(View.GONE);
                groupTimeLayout.setVisibility(View.VISIBLE);
                //选择规格布局
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//未参团
                    selectNumLayout.setVisibility(View.VISIBLE);//选择商品规格布局
                    groupIngNo.setVisibility(View.VISIBLE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.GONE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupFailNo.setVisibility(View.GONE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.GONE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.VISIBLE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.GONE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.GONE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//已参团
                    selectNumLayout.setVisibility(View.GONE);//选择商品规格布局
                    groupIngNo.setVisibility(View.GONE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.VISIBLE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.GONE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupFailNo.setVisibility(View.GONE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.GONE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.GONE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.VISIBLE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.GONE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单
                }

            } else if (data.getShopActiveInfo().getGroup_status().equals("2")) {//拼团成功
                grouTimeTextOver.setVisibility(View.VISIBLE);
                groupTimeLayout.setVisibility(View.GONE);
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//未参团
                    selectNumLayout.setVisibility(View.GONE);//选择商品规格布局
                    groupIngNo.setVisibility(View.GONE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.VISIBLE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.GONE);//恭喜您，拼团成
                    groupFailNo.setVisibility(View.GONE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.GONE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.GONE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.GONE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//已参团
                    selectNumLayout.setVisibility(View.GONE);//选择商品规格布局
                    groupIngNo.setVisibility(View.GONE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.GONE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.VISIBLE);//恭喜您，拼团成
                    groupFailNo.setVisibility(View.GONE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.GONE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.GONE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.GONE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单
                }

            } else if (data.getShopActiveInfo().getGroup_status().equals("3")) {//拼团失败
                grouTimeTextOver.setVisibility(View.VISIBLE);
                groupTimeLayout.setVisibility(View.GONE);
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//未参团
                    selectNumLayout.setVisibility(View.GONE);//选择商品规格布局
                    groupIngNo.setVisibility(View.GONE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.GONE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.GONE);//恭喜您，拼团成
                    groupFailNo.setVisibility(View.VISIBLE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.GONE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.GONE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.GONE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//已参团
                    selectNumLayout.setVisibility(View.GONE);//选择商品规格布局
                    groupIngNo.setVisibility(View.GONE);//马上参团，还差X人，成团拉
                    groupIngYes.setVisibility(View.GONE);//还需 X人，即可成团
                    groupSuccessNo.setVisibility(View.GONE);//您来迟了，目前已成团，请主动发起新的拼团！
                    groupSuccessYes.setVisibility(View.GONE);//恭喜您，拼团成
                    groupFailNo.setVisibility(View.GONE);//很遗憾，该团已经解散
                    groupFailYes.setVisibility(View.VISIBLE);//很遗憾，已过时，拼团失败

                    moveGroupIngNoLayout.setVisibility(View.GONE);//更多拼团，马上参团
                    moveGroupIngYesLayout.setVisibility(View.GONE);//更多拼团，邀请好友
                    moveGroupFront.setVisibility(View.GONE);//单独购买，拼团价
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//更多拼团，重新开团
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//更多拼团，查看订单
                }

            }


        } else {//未开团
            groupLayoutYes.setVisibility(View.GONE);
            moveGroupFront.setVisibility(View.VISIBLE);


//            //选择规格布局
//            if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//未参团
//                selectNumLayout.setVisibility(View.VISIBLE);
//                moveGroupFront.setVisibility(View.VISIBLE);
//                moveGroupAfter.setVisibility(View.GONE);
//                moveGroupCantuanLayout.setVisibility(View.GONE);
//            } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//已参团
//                selectNumLayout.setVisibility(View.GONE);
//                moveGroupFront.setVisibility(View.GONE);
//                moveGroupAfter.setVisibility(View.VISIBLE);
//                moveGroupCantuanLayout.setVisibility(View.GONE);
//            }
        }


        //点击链接进来的
//        if (!groupId.equals("")) {
//            yaoqingCantuanText.setText("马上参团");
//        }

        List<MoveDataBean.GroupListBean> getGroup_list = data.getGroup_list();
        if (getGroup_list!=null){
            if (data.getGroup_list().size() < 3) {
                final GridLayoutManager managergroup = new GridLayoutManager(getActivity(), 2);
                moveGroupRecy.setLayoutManager(managergroup);
            } else {
                final GridLayoutManager managergroup = new GridLayoutManager(getActivity(), 3);
                moveGroupRecy.setLayoutManager(managergroup);
            }
        }

        adapterMember.setData(data.getGroup_list());
        moveGroupRecy.setAdapter(adapterMember);




        //显示倒计时--拼团倒计时
        //计算秒杀倒计时---ms
        endTime = Integer.valueOf(data.getShopActiveInfo().getEnd_time());
        handler1.sendEmptyMessageDelayed(1, 1000);
        moveGroupPrice.setText(data.getShopActiveInfo().getGroup_price());
        moveGroupPriceYuan.setText("单买价" + Html.fromHtml("&yen") + data.getInfo().getProduct_price());

        groupTextFuhao.setText(Html.fromHtml("&yen"));
        groupTextPrice.setText(data.getShopActiveInfo().getGroup_price());
        groupTextYuanPrice.setText("原价" + Html.fromHtml("&yen") + data.getInfo().getProduct_price() + "/件");
        groupTextNumber1.setText("限购" + data.getShopActiveInfo().getAstrict_num() + "件/人");
        groupTextPeople1.setText(data.getShopActiveInfo().getGroup_people() + "人团");
        groupTextYuanPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        groupTextPeople2.setText(data.getShopActiveInfo().getGroup_people() + "人团");
        groupTextNumber2.setText("限购" + data.getShopActiveInfo().getAstrict_num() + "件/人");
        groupYuanPriceText.setText(data.getInfo().getProduct_price());
        groupPriceText.setText(data.getShopActiveInfo().getGroup_price());
        moveGroupPeo.setText(data.getShopActiveInfo().getAgain_invite() + "人");
        moveGroupPeo2.setText(data.getShopActiveInfo().getAgain_invite() + "人");


        final int[] num = {0};
        //加号
        groupAddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numss = 0;
                for (int i = 0; i < listBeant.size(); i++) {
                    numss = numss + Integer.parseInt(listBeant.get(i).getNum());
                }
                if (numss < Integer.parseInt(data.getShopActiveInfo().getAstrict_num())) {
                    finalNumss = numss;
                    presenter.getTypeShopData5(goods_id);
                } else {
                    Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.move_add_shopcat_btn, R.id.tv_move_about, R.id.wen_see_all, R.id.move_see_all_btn, R.id.move_success_no_layout_btn1, R.id.move_success_no_layout_btn2,
            R.id.move_success_yes_layout_btn1, R.id.move_group_ing_no_btn1, R.id.move_group_ing_no_btn2, R.id.move_group_ing_yes_btn1, R.id.move_group_ing_yes_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.move_add_shopcat_btn://进入商品详情
                if (moveDataBean == null) {
                    return;
                }
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MoveAbooutActivity_3.startSelf(this, moveDataBean.getInfo().getGoods_id(), moveDataBean.getInfo().getSearch_attr());
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();

//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.tv_move_about://拼团价
            case R.id.move_group_ing_no_btn2://马上参团
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    //先判断是否有添加数据--若没有，直接添加数据，有已经添加，直接进入确认订单页面
                    if (listBeant.size() < 1) {//选择商品
                        if (moveDataBean != null) {
                            int numss = 0;
                            for (int i = 0; i < listBeant.size(); i++) {
                                numss = numss + Integer.parseInt(listBeant.get(i).getNum());
                            }
                            if (numss < Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                                presenter.getTypeShopData6(goods_id);
                            } else {
                                Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        StringBuilder sbString = new StringBuilder();
                        for (int i = 0; i < listBeant.size(); i++) {
                            sbString.append(listBeant.get(i).getProduct_id());
                            sbString.append(":");
                            sbString.append(listBeant.get(i).getNum() + ",");
                        }
                        group_info = sbString.toString();

                        Log.e("团购==", "==group_info==" + group_info);
                        Log.e("团购==", "==group_id==" + group_id);
                        presenter.getConfirmOrderData("", goods_id, "", "", "", "", "", "", "", "", "", group_info, group_id);
                    }

                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.wen_see_all://问一问查看更多
                ProblemActivity.startSelf(MoveAbooutActivity_4.this, moveDataBean);

                break;
            case R.id.move_see_all_btn://为您推荐查看更多
                new MainHomeActivityEvent("4").post();
                finish();
                break;
            case R.id.move_success_no_layout_btn1://更多拼团
            case R.id.move_success_yes_layout_btn1://更多拼团
            case R.id.move_group_ing_no_btn1://更多拼团
            case R.id.move_group_ing_yes_btn1://更多拼团
                GroupWorkGoActivity.startSelf(this);
                CacheActivity.finishActivity();
                break;
            case R.id.move_success_no_layout_btn2://重新开团
                MoveAbooutActivity_4.startSelf(this, goods_id, search_attr, "", "");
//                presenter.getMove4Datas(goods_id, search_attr, "");//下载商品详情数据
                finish();
                break;
            case R.id.move_success_yes_layout_btn2://查看订单
                OrderDetailsActivity.startSelf(this, orderId);
//                finish();
                break;

            case R.id.move_group_ing_yes_btn2://邀请好友
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
                break;
        }
    }

    //分享
    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn, copy_btn, wx_qrcode_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//好友
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//朋友圈
        share_cancel_btn = customView.findViewById(R.id.share_cancel_btn);//取消
        copy_btn = customView.findViewById(R.id.copy_btn);//复制链接
        wx_qrcode_btn = customView.findViewById(R.id.wx_qrcode_btn);//分享二维码

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

        StringBuilder sb = new StringBuilder();

        String[] asdas = moveDataBean.getInfo().getSearch_attr().split("\\|");

        sb.append(asdas[0] + "_");
        sb.append(asdas[1]);


        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//分享商品链接
        String url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//分享商品链接


//        webpage.webpageUrl = "http://h5.jealook.com/test/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//分享商品链接
//        String url = "http://h5.jealook.com/test/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//分享商品链接

//        String url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr();//分享商品链接

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
                    //二维码图片
                    bitmaps = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getImage_code()).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //商品图片
                Bitmap thumbBmps = Bitmap.createScaledBitmap(thumbBmp, 150, 150, true);
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
        //分享二维码
        wx_qrcode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        return;
                    } else {
                        initmPopupWindowView1(bitmaps);
                        popupwindow1.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }

                }


            }
        });

        //复制
        copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(MoveAbooutActivity_4.this, "复制成功", Toast.LENGTH_LONG).show();
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

    //分享二维码
    private void initmPopupWindowView1(Bitmap coreBmps) {
        ImageView core_img;
        LinearLayout wx_hy_btn, wx_py_btn, save_core_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_2_layout, null, false);
        core_img = customView.findViewById(R.id.core_img);//二维码
        wx_hy_btn = customView.findViewById(R.id.wx_hy_btn);//好友
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//朋友圈
        save_core_btn = customView.findViewById(R.id.save_core_btn);//保存到相册

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow1 = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow1 != null && popupwindow1.isShowing()) {
                    popupwindow1.dismiss();
                    popupwindow1 = null;
                }
                return false;
            }
        });
        Log.e("分享二维码", "coreBmps====" + coreBmps);
        ImageLoader.image(this, core_img, moveDataBean.getInfo().getImage_code());


        //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(coreBmps);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(coreBmps, 500, 500, true);
        msg.thumbData = bmpToByteArray(coreBmps, true);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;


        //微信好友
        wx_hy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {

                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //朋友圈
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //保存到相册
        save_core_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        "android.permission.READ_EXTERNAL_STORAGE",
                        "android.permission.WRITE_EXTERNAL_STORAGE"};
                //检测是否有写的权限
                int permission = ContextCompat.checkSelfPermission(MoveAbooutActivity_4.this,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(MoveAbooutActivity_4.this, PERMISSIONS, 1);
                }
                try {
                    //创建savephoto类保存图片
                    SavePhoto savePhoto = new SavePhoto(MoveAbooutActivity_4.this);
                    savePhoto.SaveBitmapFromView(core_img);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (popupwindow1 != null && popupwindow1.isShowing()) {
                    popupwindow1.dismiss();
                    popupwindow1 = null;
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
                    urlselect = getBannerEvents.get(0).getUrl();
                } else if (getBannerEvents.get(0).getType() == 2) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(1).getUrl());
                    urlselect = getBannerEvents.get(1).getUrl();
                }
            } else if (getBannerEvents.size() > 1) {
                if (getBannerEvents.get(0).getType() == 1) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(1).getUrl());
                    urlselect = getBannerEvents.get(1).getUrl();
                } else if (getBannerEvents.get(0).getType() == 2) {
                    TypeSelectDialog.setUrl(getBannerEvents.get(2).getUrl());
                    urlselect = getBannerEvents.get(2).getUrl();
                }
            }
        }


        String getGoods_attr_id = event.getBanner().getGoods_attr_id();
        Log.e("返回的ID", "===iD==000=" + getGoods_attr_id);
        for (int i = 0; i < shopColourImgAdapter.getData().size(); i++) {
            shopColourImgAdapter.getData().get(i).setFlage("0");
        }
        for (int i = 0; i < shopColourImgAdapter.getData().size(); i++) {
            Log.e("返回的ID", "===shopColourImgAdapter==000==" + shopColourImgAdapter.getData().get(i).getGoods_attr_id());
            if (getGoods_attr_id.equals(shopColourImgAdapter.getData().get(i).getGoods_attr_id())) {
                Log.e("返回的ID", "===111==000==");
                shopColourImgAdapter.getData().get(i).setFlage("1");
                Log.e("返回的ID", "===222==000==");
                shopColourImgAdapter.notifyDataSetChanged();
            }
        }


//        Log.e("选择的名称", "==已选择到的================" + moveDataBean.getInfo().getShop_name() + " " + event.getArrt_name());
//        moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getArrt_name());

        //判断是不是视频， 改变主页数据
        List<MoveDataBean.InfoBean.BannerBean> banlist = new ArrayList<>();
        Log.e("判断是不是视频", "getBannerEvents.size()====" + getBannerEvents.size());
        for (int i = 0; i < getBannerEvents.size(); i++) {
            MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
            Log.e("判断是不是视频", "getUrl====" + getBannerEvents.get(i).getUrl());
            bannerBeans.setUrl(getBannerEvents.get(i).getUrl());
            bannerBeans.setType(getBannerEvents.get(i).getType());
            banlist.add(bannerBeans);
        }

        Log.e("判断是不是视频", "getProduct_price====" + event.getProduct_price());

        Log.e("判断是不是视频", "getProduct_price====" + event.getBanner());

//        BannerImgAdapter bannerImgAdapter = new BannerImgAdapter(getActivity(), strings);
//        banner.setAdapter(bannerImgAdapter);
//        banner.setIndicator(new CircleIndicator(getActivity()));
        banners.addBannerLifecycleObserver(this)
                .setAdapter(new MultipleTypesAdapter(MoveAbooutActivity_4.this, banlist, moveDataBean.getInfo().getProduct_price(), moveDataBean.getInfo().getBorder_image()))
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
                            RecyclerView.ViewHolder viewHolder = banners.getAdapter().getViewHolder();
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
        handler.removeCallbacksAndMessages(0);
        handler1.removeCallbacksAndMessages(1);
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
//        Log.e("ChangeDetailPriceEvent", "==getAttr_name===" + event.getProductBean().getAttr_name());
        //判断是否折扣商品--折扣与普通布局的切换
        if (event.getMark().equals("1")) {
            goods_attr = event.getProductBean().getSearch_attr();
            Log.e("选择的名称", "==已选择到的===goods_attr==" + goods_attr);
            Log.e("选择的名称", "==已选择到的=======1111==" + moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            detailsDiameter.setText(event.getProductBean().getDiameter());//直径
            detailsBaseCurve.setText(event.getProductBean().getBase_curve());//基弧
            detailsWaterContent.setText(event.getProductBean().getWater_content());//含水量
            detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//着色直径
        } else if (event.getMark().equals("2")) {
            goods_attr = event.getProductBean().getSearch_attr();
            Log.e("选择的名称", "==已选择到的===goods_attr==" + goods_attr);
            Log.e("选择的名称", "==已选择到的=======222==" + moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name_info());
            moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name_info());
            detailsDiameter.setText(event.getProductBean().getDiameter());//直径
            detailsBaseCurve.setText(event.getProductBean().getBase_curve());//基弧
            detailsWaterContent.setText(event.getProductBean().getWater_content());//含水量
            detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//着色直径
        }


    }

    /**
     * 问大家返回数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProblemListEvent event) {
        Log.e("ChangeDetailPriceEvent", "==event===" + event);
        Log.e("ChangeDetailPriceEvent", "==event===" + event.getData().size());
        wenyiwenNumber.setText("（" + event.getCount() + "）");
        MoveDataBean.QuestionListBean questionBean = null;

        getQuestion_list = new ArrayList<>();

        for (int i = 0; i < event.getData().size(); i++) {
            questionBean = new MoveDataBean.QuestionListBean();
            questionBean.setUser_name(event.getData().get(i).getUser_name());
            questionBean.setQuestion(event.getData().get(i).getQuestion());
            questionBean.setId(event.getData().get(i).getId());
            questionBean.setContent(event.getData().get(i).getContent());
            questionBean.setAnswer_count(event.getData().get(i).getAnswer_count());
            getQuestion_list.add(questionBean);
        }

        for (int i = 0; i < getQuestion_list.size(); i++) {
            Log.e("问大家", "==getQuestion==333=" + getQuestion_list.get(i).getQuestion());
        }
        wenAdapter.setData(getQuestion_list);
        recyclervWenyiwen.setAdapter(wenAdapter);

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
                    Toast.makeText(MoveAbooutActivity_4.this, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //未初始化，消息当然为 ：0
            Toast.makeText(MoveAbooutActivity_4.this, "还没初始化", Toast.LENGTH_SHORT).show();
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
//        String details = "https://wap.boosoo.com.cn/bobishop/goodsdetail?id=10160&mid=36819";//详情链接

        String details = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";
//        CardInfo ci = new CardInfo("http://seopic.699pic.com/photo/40023/0579.jpg_wh1200.jpg", "我是一个标题当初读书", "我是name当初读书。", "价格 1000-9999", "https://www.baidu.com");


        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            icon = moveDataBean.getInfo().getBanner().get(0).getUrl();
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            icon = moveDataBean.getInfo().getBanner().get(1).getUrl();
        }

        String title = moveDataBean.getInfo().getShop_name();
        String content = moveDataBean.getInfo().getShop_attr_name();
        Log.e("分享", "===getShop_attr_name===" + moveDataBean.getInfo().getShop_attr_name());
        if (moveDataBean.getInfo().getIs_promote().equals("1")) {//显示限时页面
            rigth3 = Html.fromHtml("&yen") + moveDataBean.getInfo().getPreferential_price();
        } else if (moveDataBean.getInfo().getIs_promote().equals("0")) {//显示普通页面
            rigth3 = Html.fromHtml("&yen") + moveDataBean.getInfo().getProduct_price();
        }
        try {
            ci = new CardInfo(URLEncoder.encode(icon, "utf-8"), URLEncoder.encode(title, "utf-8"),
                    URLEncoder.encode(content, "utf-8"), URLEncoder.encode(rigth3, "utf-8"),
                    URLEncoder.encode(details, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.setCard(ci);
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
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        return;
                    } else {
                        initmPopupWindowView1(bitmaps);
                        popupwindow1.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                } else {
                    Toast.makeText(this, "您拒绝了权限申请，无法进行二维码分享！", Toast.LENGTH_SHORT).show();
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
//        if (mmark.equals("1")) {

        ConfirmOrderActivity_1.startSelf(MoveAbooutActivity_4.this, "", goods_id, product_ids, nums, "2", group_info, group_id);
//        }
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
     * @Description:下载类型成功--加入购物车
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
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, ProductBean productBean, String mmake) {
//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num);

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

    /**
     * 下载商品规格成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeShop4Success(int code, MoveDataBean data) {
//        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
//        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
//        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
//        bannerBeans.setType(1);
//        bannerBeans.setUrl(moveDataBean.getInfo().getGoods_thumb());
//        banner.add(bannerBeans);
//        infoBean.setBanner(banner);
//        infoBean.setSearch_attr(listBeans4.getSearch_attr());
//        infoBean.setGoods_id(listBeans4.getGoods_id());
//        infoBean.setProduct_number(listBeans4.getNumber());
//        infoBean.setProduct_price(listBeans4.getProduct_price());
//        moveDataBeas.setInfo(infoBean);
        moveDataBean.setProduct(data.getProduct());
        TypeSelectDialog.with(getActivity(), moveDataBean, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
                //先判断已选择的件数是否大于已选择的件数
                Log.e("团购==", "==getAttr_names==" + getAttr_names);
                Log.e("团购==", "==num==" + num);
                Log.e("团购==", "==productBean==" + productBean.getAttr_name());
                Log.e("团购==", "==product_id==" + productBean.getProduct_id());
                String velue = productBean.getAttr_name();
                int numss = 0;

                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    numss = numss + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }
                if (numss + Integer.parseInt(num) - Integer.parseInt(adapterGroup.getData().get(position4).getNum()) <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                    groupDetailsBean = new GroupDetailsListBean();
                    groupDetailsBean.setName(velue);
                    groupDetailsBean.setImg(urlselect);
                    groupDetailsBean.setNum(num);
                    groupDetailsBean.setProduct_id(productBean.getProduct_id());
                    groupDetailsBean.setSearch_attr(productBean.getGoods_attr());

                    listBeant.set(position4, groupDetailsBean);

//                            listBeant.add(groupDetailsBean);
//                            hasProductList.add(product_id);
                    hasProductList.set(position4, productBean.getProduct_id());

                    adapterGroup.setData(listBeant);
                    groupRecyclerView.setAdapter(adapterGroup);
                    if (adapterGroup.getData().size() < 1) {
                        groupRecyclerView.setVisibility(View.GONE);
                    } else {
                        groupRecyclerView.setVisibility(View.VISIBLE);
                    }

                    adapterGroup.notifyDataSetChanged();
                    TypeSelectDialog.dismiss();


                } else {
                    Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                }

                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }

                Log.e("选择的数量", "===number===" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//拼团价格
                groupYuanPriceText.setText(String.valueOf(price2_2));//单独购买价格

            }


        }).show();

    }


    /**
     * 下载商品规格失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop4Fail(int code, String msg) {

    }

    /**
     * 下载商品规格成功--加号
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop5Success(int code, MoveDataBean data) {

        moveDataBean.setProduct(data.getProduct());

        goods_attr = "";
        TypeSelectDialog.with(getActivity(), moveDataBean, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
                //先判断已选择的件数是否大于已选择的件数
                Log.e("团购==", "==getAttr_names==" + getAttr_names);
                Log.e("团购==", "==num==" + num);
                Log.e("团购==", "==productBean==" + productBean.getAttr_name());
                Log.e("团购==", "==product_id==" + product_id);
                String velue = productBean.getAttr_name();
                if (listBeant.size() > 0) {
                    Log.e("团购==", "==111111111=num====" + num);
                    Log.e("团购==", "==product_id=num====" + product_id);
                    Log.e("团购==", "==hasProductList=num====" + hasProductList);
                    if (hasProductList.contains(product_id)) {
                        for (int i = 0; i < listBeant.size(); i++) {
                            Log.e("团购==", "==22222222222=num====" + num);
                            Log.e("团购==", "==product_id====" + product_id);
                            if (listBeant.get(i).getProduct_id().equals(product_id)) {
                                Log.e("团购==", "==333333333333=num====" + num);
                                int num2 = Integer.parseInt(listBeant.get(i).getNum()) + Integer.parseInt(num);
                                Log.e("团购==", "==4444444444444=num====" + num);
                                if (finalNumss + Integer.parseInt(num) <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                                    Log.e("团购==", "==55555555555555=num====" + num);

                                    groupDetailsBean = new GroupDetailsListBean();
                                    groupDetailsBean.setName(listBeant.get(i).getName());
                                    groupDetailsBean.setImg(listBeant.get(i).getImg());
                                    groupDetailsBean.setNum(String.valueOf(num2));
                                    groupDetailsBean.setProduct_id(listBeant.get(i).getProduct_id());
                                    groupDetailsBean.setSearch_attr(listBeant.get(i).getSearch_attr());
                                    listBeant.set(i, groupDetailsBean);
//                                                listBeant.add(groupDetailsBean);
                                    hasProductList.set(i, product_id);

                                    adapterGroup.setData(listBeant);
                                    groupRecyclerView.setAdapter(adapterGroup);
                                    if (adapterGroup.getData().size() < 1) {
                                        groupRecyclerView.setVisibility(View.GONE);
                                    } else {
                                        groupRecyclerView.setVisibility(View.VISIBLE);
                                    }
                                    adapterGroup.notifyDataSetChanged();
                                    TypeSelectDialog.dismiss();


                                } else {
                                    Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            }
                        }
                    } else {
                        if (finalNumss + Integer.parseInt(num) <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {

                            groupDetailsBean = new GroupDetailsListBean();
                            groupDetailsBean.setName(velue);
                            groupDetailsBean.setImg(urlselect);
                            groupDetailsBean.setNum(num);
                            groupDetailsBean.setProduct_id(product_id);
                            groupDetailsBean.setSearch_attr(productBean.getGoods_attr());
                            listBeant.add(groupDetailsBean);
                            hasProductList.add(product_id);

                            adapterGroup.setData(listBeant);
                            groupRecyclerView.setAdapter(adapterGroup);
                            if (adapterGroup.getData().size() < 1) {
                                groupRecyclerView.setVisibility(View.GONE);
                            } else {
                                groupRecyclerView.setVisibility(View.VISIBLE);
                            }
                            adapterGroup.notifyDataSetChanged();
                            TypeSelectDialog.dismiss();


                        } else {
                            Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (finalNumss + Integer.parseInt(num) <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {

                        groupDetailsBean = new GroupDetailsListBean();
                        groupDetailsBean.setName(velue);
                        groupDetailsBean.setImg(urlselect);
                        groupDetailsBean.setNum(num);
                        groupDetailsBean.setProduct_id(product_id);
                        groupDetailsBean.setSearch_attr(productBean.getGoods_attr());
                        listBeant.add(groupDetailsBean);
                        hasProductList.add(product_id);

                        adapterGroup.setData(listBeant);
                        groupRecyclerView.setAdapter(adapterGroup);
                        if (adapterGroup.getData().size() < 1) {
                            groupRecyclerView.setVisibility(View.GONE);
                        } else {
                            groupRecyclerView.setVisibility(View.VISIBLE);
                        }
                        adapterGroup.notifyDataSetChanged();
                        TypeSelectDialog.dismiss();

                    } else {
                        Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                    }
                }

                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }

                Log.e("选择的数量", "===number==000=" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//拼团价格
                groupYuanPriceText.setText(String.valueOf(price2_2));//单独购买价格

            }
        }).show();


    }

    /**
     * 下载商品规格失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop5Fail(int code, String msg) {

    }

    /**
     * 下载商品规格成功
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop6Success(int code, MoveDataBean data) {
        moveDataBean.setProduct(data.getProduct());

        goods_attr = "";
        TypeSelectDialog.with(getActivity(), moveDataBean, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
                Log.e("团购==", "==getAttr_names==" + getAttr_names);
                Log.e("团购==", "==num==" + num);
                Log.e("团购==", "==productBean==" + productBean.getAttr_name());
                Log.e("团购==", "==product_id==" + product_id);
                String velue = productBean.getAttr_name();

                groupDetailsBean = new GroupDetailsListBean();
                groupDetailsBean.setName(velue);
                groupDetailsBean.setImg(urlselect);
                groupDetailsBean.setNum(num);
                groupDetailsBean.setProduct_id(product_id);
                groupDetailsBean.setSearch_attr(productBean.getGoods_attr());
                listBeant.add(groupDetailsBean);
                hasProductList.add(product_id);

                int numss = 0;
                for (int i = 0; i < listBeant.size(); i++) {
                    numss = numss + Integer.parseInt(listBeant.get(i).getNum());
                }
                if (numss <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                    adapterGroup.setData(listBeant);
                    groupRecyclerView.setAdapter(adapterGroup);
                    if (adapterGroup.getData().size() < 1) {
                        groupRecyclerView.setVisibility(View.GONE);
                    } else {
                        groupRecyclerView.setVisibility(View.VISIBLE);
                    }
                    adapterGroup.notifyDataSetChanged();
                    TypeSelectDialog.dismiss();
                } else {
                    listBeant.remove(groupDetailsBean);
                    hasProductList.remove(product_id);
                    Toast.makeText(MoveAbooutActivity_4.this, "您已超过限购数量", Toast.LENGTH_SHORT).show();
                }


                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }
                Log.e("选择的数量", "===number==111=" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//拼团价格
                groupYuanPriceText.setText(String.valueOf(price2_2));//单独购买价格


            }

        }).show();


    }

    /**
     * 下载商品规格失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop6Fail(int code, String msg) {

    }


}
