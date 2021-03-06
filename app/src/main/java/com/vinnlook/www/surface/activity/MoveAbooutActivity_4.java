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
import com.vinnlook.www.surface.adapter.GuangImagAdapter;
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
 * @Description:????????????
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
    RecyclerView imgListDetails1;//??????List
    @BindView(R.id.item_xuzhi_layout)
    View itemXuzhiLayout;
    @BindView(R.id.img_list_details2)
    RecyclerView imgListDetails2;//??????List
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
    LinearLayout shangpinLayout;//??????????????????
    @BindView(R.id.pingjia_layout)
    LinearLayout pingjiaLayout;//??????????????????
    @BindView(R.id.xiangqing_layout)
    LinearLayout xiangqingLayout;//??????????????????
    @BindView(R.id.xuzhi_layout)
    LinearLayout xuzhiLayout;//????????????????????????


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
    RoundLinearLayout moveAddShopcatBtn;//???????????????
    @BindView(R.id.move_xianshi_min)
    TextView moveXianshiMin;
    @BindView(R.id.tv_move_about)
    RoundLinearLayout tvMoveAbout;//????????????
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
    @BindView(R.id.guang_all)
    TextView guangAll;
    @BindView(R.id.guang_recy)
    RecyclerView guangRecy;
    @BindView(R.id.guang_layout)
    LinearLayout guangLayout;


    private float totaldy;
    private List<DetailsBean> list;
    private Resources res;
    @BindView(R.id.activity_name_text)
    TextView activityNameText;


    private static String goods_id;//????????????
    private static String search_attr;//????????????
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

    String mmark;//???????????????????????????1--???????????????????????????--?????????
    ShopColourImgAdapter shopColourImgAdapter;
    MoveGroupAdapter adapterGroup;//?????????????????????

    String goods_attr;
    GroupMemberAdapter adapterMember;//????????????
    CommentListAdapter commentAdapter;//???????????????
    GuangImagAdapter guangAdapter;
    WenListAdapter wenAdapter;//??????????????????
    DetailsImags1Adapter adapter;//???????????????
    DetailsImags2Adapter adapter2;//???????????????
    TuiJianAdapter adapter4;//???????????????
    List<String> hasProductList = new ArrayList<>();
    List<GroupDetailsListBean> listBeant = new ArrayList<>();

    int shangPinHeight;
    int pingJiaHeight;
    int xiangQingHeight;
    int xuZhiHeight;
    int titleBarHeight;

    MoveDataBean.RecommendBean ecommendBean;
    StandardGSYVideoPlayer player;

    Bitmap bitmaps;//?????????
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
        //??????
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


        initAdapter();//???????????????
        initTitleBtn();//title????????????
        initTitle();
        getUserInfo = UserUtils.getInstance().getUserInfo();
        inits();


        Log.e("??????????????????", "===getTestDataVideo===" + DatasBean.getTestDataVideo());


    }

    private void initTitle() {
        res = getResources();
        //        ???????????????-??????????????????
//        mRecyclerFactor = (DensityUtilss.dp2px(this, 400.0F) - DensityUtilss.getStatusBarHeight(this));
    }

    //Title????????????
    @SuppressLint("NewApi")
    private void initTitleBtn() {
        //????????????
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
        //????????????
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
        //????????????
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
        //??????????????????
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

        //????????????
        moveScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int newx, int newy, int oldx, int oldy) {
                totaldy = newy;
                if (newy > 0) {
                    titleBar.setVisibility(View.VISIBLE);
                } else {
                    titleBar.setVisibility(View.GONE);
                }

                //???????????????

                //??????
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
                        // ?????????????????????160????????????????????????
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
//            Log.e("???", "?????????===hourss===" + hourss);
//            Log.e("???", "?????????===minutess===" + minutess);
//            Log.e("???", "?????????===secondss===" + secondss);
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

    //???????????????
    private void initAdapter() {
        //????????????
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
                //???????????????????????? ??????????????????
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

                Log.e("???????????????", "===??????:===11111111=====");
                Log.e("???????????????", "===??????:========" + moveDataBean.getInfo().getShop_name() + shopColourImgAdapter.getData().get(position).getShop_attr_name());
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

        //???????????????
        commentAdapter = new CommentListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(this, 1);
        recyclervComment2.setLayoutManager(manager3);
        recyclervComment2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        recyclervComment2.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclervComment2.setNestedScrollingEnabled(false);
        recyclervComment2.setHasFixedSize(false);

        //???????????????
        guangAdapter = new GuangImagAdapter(this);
        final GridLayoutManager guangmanager = new GridLayoutManager(this, 1);
        guangmanager.setOrientation(GridLayoutManager.HORIZONTAL);
        guangRecy.setLayoutManager(guangmanager);
        guangRecy.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        guangRecy.addItemDecoration(new SpaceItemDecoration(0, 0));
        guangRecy.setNestedScrollingEnabled(false);
        guangRecy.setFocusable(false);
        guangRecy.setAdapter(guangAdapter);
        guangAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                SelectEyeChartActivity.startSelf(MoveAbooutActivity_4.this, guangAdapter.getData().get(position).getId());
            }
        });

        //??????????????????
        wenAdapter = new WenListAdapter(this);
        final GridLayoutManager managesWen = new GridLayoutManager(this, 1);
        recyclervWenyiwen.setLayoutManager(managesWen);
        recyclervWenyiwen.setNestedScrollingEnabled(false);
        recyclervWenyiwen.setHasFixedSize(false);

        //???????????????
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

        //???????????????
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


        //???????????????
        adapter4 = new TuiJianAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        moveTuijianRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        moveTuijianRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        moveTuijianRecycler.setLayoutManager(manager1);
        moveTuijianRecycler.setNestedScrollingEnabled(false);
        moveTuijianRecycler.setFocusable(false);

        //?????????????????????
        adapterGroup = new MoveGroupAdapter(this);
        final GridLayoutManager manager11 = new GridLayoutManager(getActivity(), 1);
        groupRecyclerView.setLayoutManager(manager11);
        groupRecyclerView.setNestedScrollingEnabled(false);
        groupRecyclerView.setFocusable(false);
        //??????
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

        //??????????????????--??????
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


        //????????????
        adapterMember = new GroupMemberAdapter(this);
        final GridLayoutManager managergroup = new GridLayoutManager(getActivity(), 3);
        moveGroupRecy.setLayoutManager(managergroup);
        moveGroupRecy.setNestedScrollingEnabled(false);
        moveGroupRecy.setFocusable(false);

    }


    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getMoveDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getCollectionShopSuccess(int code, Object data) {
        Log.e("??????????????????", "==code==" + code);
        Log.e("??????????????????", "==data==" + data);


    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getCollectionShopFail(int code, String msg) {
        Log.e("??????????????????", "==code==" + code);
        Log.e("??????????????????", "==msg==" + msg);


    }

    /**
     * ?????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        Log.e("?????????????????????", "==code==" + code);
        Log.e("?????????????????????", "==msg==" + data);
        TypeSelectDialog.dismiss();
//        TypeSelectDialog.with(getActivity(), moveDataBean, this).dismiss();
        Toast.makeText(this, "?????????????????????", Toast.LENGTH_SHORT).show();
    }

    /**
     * ?????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Log.e("?????????????????????", "==code==" + code);
        Log.e("?????????????????????", "==msg==" + msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void loadData() {
        presenter.getMove4Datas(goods_id, search_attr, group_id, type);//????????????????????????
    }

    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getMoveDataSuccess(int code, MoveDataBean data) {

        moveDataBean = data;
        //?????????????????????
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        //?????????????????????---ms
        surpTime = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);


        //?????????????????????
        if (!data.getAd_info().getPhoto().equals("") && data.getAd_info().getPhoto() != null) {
            move3HuodongImg.setVisibility(View.VISIBLE);
            Matrix matrix = new Matrix();           //????????????????????????
            matrix.setTranslate(0, 0);          //??????x???y???100??????
            matrix.preRotate(0);                   //???????????????30???
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

        //?????????????????????
        if (data.getInfo().getGoods_brief().equals("")) {
            moveShopExplainLayout.setVisibility(View.GONE);
        } else {
            moveShopExplainLayout.setVisibility(View.VISIBLE);
        }

        //???????????????????????????0???????????????1?????????
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

        //??????Banner??????
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

        //????????????-??????
        activityNameText.setText(data.getInfo().getActive_name());
        //??????????????????--??????????????????
        if (UserUtils.getInstance().getUserId().equals("")) {//?????????
            detailsVipOpenText1.setText("???????????????");
            detailsVipZhe1.setText("??????Plus???????????????????????????9.5???");
            //????????????
            detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoveAbooutActivity_4.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                }
            });

        } else {//?????????
            if (UserUtils.getInstance().getUserInfo().getIs_member() == 0) {//???????????????
                detailsVipOpenText1.setText("???????????????");
                detailsVipZhe1.setText("??????Plus???????????????????????????9.5???");
                //????????????
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//??????????????????
                        MemberActivity_1.startSelf(getContext(), "1");//??????????????????
                    }
                });

            } else if (UserUtils.getInstance().getUserInfo().getIs_member() == 1) {//???????????????
                detailsVipOpenText1.setText("???????????????");
                detailsVipZhe1.setText("Plus????????????????????????9.5???");
                //????????????
                detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        MemberActivity.startSelf(getContext(), "1");//??????????????????
                        MemberActivity_1.startSelf(getContext(), "1");//??????????????????
                    }
                });
            }
        }

        moveTransactionPrices1.setText(Html.fromHtml("&yen") + data.getShopActiveInfo().getGroup_price());
        moveTransactionPricesYuan1.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());
        //????????????????????????
        moveTransactionPricesYuan1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


        moveXiaoliangText.setText("????????????" + data.getInfo().getVirtual_sales());//??????
        //????????????
        moveTransactionName.setText(data.getInfo().getShop_name() + " " + data.getInfo().getShop_attr_name());
//        getPurchasing = "????????????1???";


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


        //??????
        pingjiaNumber.setText("(" + data.getInfo().getComment_count() + ")");
        commentAdapter.setData(data.getComment());
        recyclervComment2.setAdapter(commentAdapter);
        if (data.getComment().size() > 0) {
            pingjiaLine.setVisibility(View.VISIBLE);
        } else {
            pingjiaLine.setVisibility(View.GONE);
        }
        //????????????
        pingjiaSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EvaluateListActivity.startSelf(getContext(), data.getInfo().getGoods_id());
            }
        });

        //??????
        if (data.getArticleList().size() > 0) {
            guangLayout.setVisibility(View.VISIBLE);
            guangAdapter.setData(data.getArticleList());
        } else {
            guangLayout.setVisibility(View.GONE);
        }

        //?????????
        wenyiwenNumber.setText("???" + data.getInfo().

                getQuestion_count() + "???");

        wenAdapter.setData(data.getQuestion_list());
        recyclervWenyiwen.setAdapter(wenAdapter);
        if (data.getQuestion_list().

                size() > 0) {
            wendajiaLine.setVisibility(View.VISIBLE);
        } else {
            wendajiaLine.setVisibility(View.GONE);
        }

        //??????
        adapter.setData(data.getInfo().

                getDetails());
        imgListDetails1.setAdapter(adapter);
        //??????
        adapter2.setData(data.getInfo().

                getUser_notes());
        imgListDetails2.setAdapter(adapter2);
        //???????????????
        adapter4.setData(data.getRecommend());
        moveTuijianRecycler.setAdapter(adapter4);
        adapter4.addOnClickListener(new

                                            OnClickListener() {
                                                @Override
                                                public void onClick(View view, int position) {
                                                    MoveAbooutActivity_3.startSelf(getActivity(), adapter4.getData().get(position).getGoods_id(), adapter4.getData().get(position).getSearch_attr(), "");
                                                }
                                            });
        adapter4.setTuiJianClickListener(new TuiJianAdapter.TuiJianClickListener() {
            @Override
            public void onGoClickListener(MoveDataBean.RecommendBean data, String getGoods_id, String
                    getSearch_attr) {
                ecommendBean = data;
                ecommendBean.setSearch_attr(goods_attr);
                presenter.getTypeShopData(getGoods_id);
            }
        });

        if (group_id.equals("")) {
            group_id = data.getShopActiveInfo().getGroup_id();
        }

        orderId = data.getShopActiveInfo().

                getOrder_id();

        //??????????????????
        if (!group_id.equals("")) {//?????????
            int perop = Integer.parseInt(data.getShopActiveInfo().getGroup_people()) - Integer.parseInt(data.getShopActiveInfo().getAgain_invite());

            groupLayoutYes.setVisibility(View.VISIBLE);

            //1:????????????2??????????????????3??????????????????4???????????????
            if (data.getShopActiveInfo().getGroup_status().equals("1")) {//?????????
                grouTimeTextOver.setVisibility(View.GONE);
                groupTimeLayout.setVisibility(View.VISIBLE);
                //??????????????????
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//?????????
                    selectNumLayout.setVisibility(View.VISIBLE);//????????????????????????
                    groupIngNo.setVisibility(View.VISIBLE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.GONE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupFailNo.setVisibility(View.GONE);//??????????????????????????????
                    groupFailYes.setVisibility(View.GONE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.VISIBLE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.GONE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//?????????
                    selectNumLayout.setVisibility(View.GONE);//????????????????????????
                    groupIngNo.setVisibility(View.GONE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.VISIBLE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.GONE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupFailNo.setVisibility(View.GONE);//??????????????????????????????
                    groupFailYes.setVisibility(View.GONE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.VISIBLE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.GONE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????
                }

            } else if (data.getShopActiveInfo().getGroup_status().equals("2")) {//????????????
                grouTimeTextOver.setVisibility(View.VISIBLE);
                groupTimeLayout.setVisibility(View.GONE);
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//?????????
                    selectNumLayout.setVisibility(View.GONE);//????????????????????????
                    groupIngNo.setVisibility(View.GONE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.VISIBLE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.GONE);//?????????????????????
                    groupFailNo.setVisibility(View.GONE);//??????????????????????????????
                    groupFailYes.setVisibility(View.GONE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//?????????
                    selectNumLayout.setVisibility(View.GONE);//????????????????????????
                    groupIngNo.setVisibility(View.GONE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.GONE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.VISIBLE);//?????????????????????
                    groupFailNo.setVisibility(View.GONE);//??????????????????????????????
                    groupFailYes.setVisibility(View.GONE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????
                }

            } else if (data.getShopActiveInfo().getGroup_status().equals("3")) {//????????????
                grouTimeTextOver.setVisibility(View.VISIBLE);
                groupTimeLayout.setVisibility(View.GONE);
                if (data.getShopActiveInfo().getIs_join_group().equals("0")) {//?????????
                    selectNumLayout.setVisibility(View.GONE);//????????????????????????
                    groupIngNo.setVisibility(View.GONE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.GONE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.GONE);//?????????????????????
                    groupFailNo.setVisibility(View.VISIBLE);//??????????????????????????????
                    groupFailYes.setVisibility(View.GONE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????

                } else if (data.getShopActiveInfo().getIs_join_group().equals("1")) {//?????????
                    selectNumLayout.setVisibility(View.GONE);//????????????????????????
                    groupIngNo.setVisibility(View.GONE);//?????????????????????X???????????????
                    groupIngYes.setVisibility(View.GONE);//?????? X??????????????????
                    groupSuccessNo.setVisibility(View.GONE);//???????????????????????????????????????????????????????????????
                    groupSuccessYes.setVisibility(View.GONE);//?????????????????????
                    groupFailNo.setVisibility(View.GONE);//??????????????????????????????
                    groupFailYes.setVisibility(View.VISIBLE);//????????????????????????????????????

                    moveGroupIngNoLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupIngYesLayout.setVisibility(View.GONE);//???????????????????????????
                    moveGroupFront.setVisibility(View.GONE);//????????????????????????
                    moveSuccessNoLayoutBtn.setVisibility(View.VISIBLE);//???????????????????????????
                    moveSuccessYesLayoutBtn.setVisibility(View.GONE);//???????????????????????????
                }

            }


        } else {//?????????
            groupLayoutYes.setVisibility(View.GONE);
            moveGroupFront.setVisibility(View.VISIBLE);

            List<MoveDataBean.GroupListBean> getGroup_list = data.getGroup_list();
            if (getGroup_list != null) {
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


            //???????????????--???????????????
            //?????????????????????---ms
            endTime = Integer.valueOf(data.getShopActiveInfo().getEnd_time());
            handler1.sendEmptyMessageDelayed(1, 1000);
            moveGroupPrice.setText(data.getShopActiveInfo().getGroup_price());
            moveGroupPriceYuan.setText("?????????" + Html.fromHtml("&yen") + data.getInfo().getProduct_price());

            groupTextFuhao.setText(Html.fromHtml("&yen"));
            groupTextPrice.setText(data.getShopActiveInfo().getGroup_price());
            groupTextYuanPrice.setText("??????" + Html.fromHtml("&yen") + data.getInfo().getProduct_price() + "/???");
            groupTextNumber1.setText("??????" + data.getShopActiveInfo().getAstrict_num() + "???/???");
            groupTextPeople1.setText(data.getShopActiveInfo().getGroup_people() + "??????");
            groupTextYuanPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

            groupTextPeople2.setText(data.getShopActiveInfo().getGroup_people() + "??????");
            groupTextNumber2.setText("??????" + data.getShopActiveInfo().getAstrict_num() + "???/???");
            groupYuanPriceText.setText(data.getInfo().getProduct_price());
            groupPriceText.setText(data.getShopActiveInfo().getGroup_price());
            moveGroupPeo.setText(data.getShopActiveInfo().getAgain_invite() + "???");
            moveGroupPeo2.setText(data.getShopActiveInfo().getAgain_invite() + "???");


            final int[] num = {0};
            //??????
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
                        Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.move_add_shopcat_btn, R.id.tv_move_about, R.id.wen_see_all, R.id.move_see_all_btn, R.id.move_success_no_layout_btn1, R.id.move_success_no_layout_btn2,
            R.id.move_success_yes_layout_btn1, R.id.move_group_ing_no_btn1, R.id.move_group_ing_no_btn2, R.id.move_group_ing_yes_btn1, R.id.move_group_ing_yes_btn2, R.id.guang_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.move_add_shopcat_btn://??????????????????
                if (moveDataBean == null) {
                    return;
                }
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MoveAbooutActivity_3.startSelf(this, moveDataBean.getInfo().getGoods_id(), moveDataBean.getInfo().getSearch_attr(), "");
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();

//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.tv_move_about://?????????
            case R.id.move_group_ing_no_btn2://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    //??????????????????????????????--?????????????????????????????????????????????????????????????????????????????????
                    if (listBeant.size() < 1) {//????????????
                        if (moveDataBean != null) {
                            int numss = 0;
                            for (int i = 0; i < listBeant.size(); i++) {
                                numss = numss + Integer.parseInt(listBeant.get(i).getNum());
                            }
                            if (numss < Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                                presenter.getTypeShopData6(goods_id);
                            } else {
                                Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
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

                        Log.e("??????==", "==group_info==" + group_info);
                        Log.e("??????==", "==group_id==" + group_id);
                        presenter.getConfirmOrderData("", goods_id, "", "", "", "", "", "", "", "", "", group_info, group_id);
                    }

                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.wen_see_all://?????????????????????
                ProblemActivity.startSelf(MoveAbooutActivity_4.this, moveDataBean);

                break;
            case R.id.move_see_all_btn://????????????????????????
                new MainHomeActivityEvent("4").post();
                finish();
                break;
            case R.id.move_success_no_layout_btn1://????????????
            case R.id.move_success_yes_layout_btn1://????????????
            case R.id.move_group_ing_no_btn1://????????????
            case R.id.move_group_ing_yes_btn1://????????????
                GroupWorkGoActivity.startSelf(this);
                CacheActivity.finishActivity();
                break;
            case R.id.move_success_no_layout_btn2://????????????
                MoveAbooutActivity_4.startSelf(this, goods_id, search_attr, "", "");
//                presenter.getMove4Datas(goods_id, search_attr, "");//????????????????????????
                finish();
                break;
            case R.id.move_success_yes_layout_btn2://????????????
                OrderDetailsActivity.startSelf(this, orderId);
//                finish();
                break;

            case R.id.move_group_ing_yes_btn2://????????????
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
            case R.id.guang_all://??????
                MoveGuangListActivity.startSelf(this,moveDataBean.getInfo().getGoods_id());
                break;
        }
    }

    //??????
    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn, copy_btn, wx_qrcode_btn;

        TextView share_cancel_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//??????
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//?????????
        share_cancel_btn = customView.findViewById(R.id.share_cancel_btn);//??????
        copy_btn = customView.findViewById(R.id.copy_btn);//????????????
        wx_qrcode_btn = customView.findViewById(R.id.wx_qrcode_btn);//???????????????

        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
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

        StringBuilder sb = new StringBuilder();

        String[] asdas = moveDataBean.getInfo().getSearch_attr().split("\\|");

        sb.append(asdas[0] + "_");
        sb.append(asdas[1]);


        //???????????????WXWebpageObject?????????url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//??????????????????
        String url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//??????????????????


//        webpage.webpageUrl = "http://h5.jealook.com/test/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//??????????????????
//        String url = "http://h5.jealook.com/test/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";//??????????????????

//        String url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr();//??????????????????

        WXMediaMessage msg = new WXMediaMessage(webpage);
//??? WXWebpageObject ????????????????????? WXMediaMessage ??????
        new Thread(new Runnable() {
            @Override
            public void run() {
                msg.title = "vinnlook?????? ";
                msg.description = moveDataBean.getInfo().getShop_name() + "\t" + moveDataBean.getInfo().getShop_attr_name();
                Bitmap thumbBmp = null;
                try {
                    if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
                        thumbBmp = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getBanner().get(0).getUrl()).openStream());
                    } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
                        thumbBmp = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getBanner().get(1).getUrl()).openStream());
                    }
                    //???????????????
                    bitmaps = BitmapFactory.decodeStream(new URL(moveDataBean.getInfo().getImage_code()).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //????????????
                Bitmap thumbBmps = Bitmap.createScaledBitmap(thumbBmp, 150, 150, true);
                thumbBmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmps, true);

            }
        }).start();

        //????????????Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


        //????????????
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SPUtils.getInstance().save("Good_id", moveDataBean.getInfo().getGoods_id());
//                SPUtils.getInstance().save("Search_attr", moveDataBean.getInfo().getSearch_attr());

                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneSession;//????????????
                    req.userOpenId = UserUtils.getInstance().getUserId();//????????????ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }


            }
        });
        //???????????????
        wx_pyq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//????????????
                    req.userOpenId = UserUtils.getInstance().getUserId();//????????????ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
            }
        });
        //???????????????
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

        //??????
        copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(MoveAbooutActivity_4.this, "????????????", Toast.LENGTH_LONG).show();
            }
        });
        //??????
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

    //???????????????
    private void initmPopupWindowView1(Bitmap coreBmps) {
        ImageView core_img;
        LinearLayout wx_hy_btn, wx_py_btn, save_core_btn;

        TextView share_cancel_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.share_2_layout, null, false);
        core_img = customView.findViewById(R.id.core_img);//?????????
        wx_hy_btn = customView.findViewById(R.id.wx_hy_btn);//??????
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//?????????
        save_core_btn = customView.findViewById(R.id.save_core_btn);//???????????????

        // ??????PopupWindow??????,?????????????????????
        popupwindow1 = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        Log.e("???????????????", "coreBmps====" + coreBmps);
        ImageLoader.image(this, core_img, moveDataBean.getInfo().getImage_code());


        //????????? WXImageObject ??? WXMediaMessage ??????
        WXImageObject imgObj = new WXImageObject(coreBmps);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
//        //???????????????
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(coreBmps, 500, 500, true);
        msg.thumbData = bmpToByteArray(coreBmps, true);
        //????????????Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;


        //????????????
        wx_hy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                } else {

                    req.scene = SendMessageToWX.Req.WXSceneSession;//????????????
                    req.userOpenId = UserUtils.getInstance().getUserId();//????????????ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //?????????
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//????????????
                    req.userOpenId = UserUtils.getInstance().getUserId();//????????????ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //???????????????
        save_core_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        "android.permission.READ_EXTERNAL_STORAGE",
                        "android.permission.WRITE_EXTERNAL_STORAGE"};
                //???????????????????????????
                int permission = ContextCompat.checkSelfPermission(MoveAbooutActivity_4.this,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // ???????????????????????????????????????????????????????????????
                    ActivityCompat.requestPermissions(MoveAbooutActivity_4.this, PERMISSIONS, 1);
                }
                try {
                    //??????savephoto???????????????
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


    //????????????
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
        Log.e("?????????ID", "===iD==000=" + getGoods_attr_id);
        for (int i = 0; i < shopColourImgAdapter.getData().size(); i++) {
            shopColourImgAdapter.getData().get(i).setFlage("0");
        }
        for (int i = 0; i < shopColourImgAdapter.getData().size(); i++) {
            Log.e("?????????ID", "===shopColourImgAdapter==000==" + shopColourImgAdapter.getData().get(i).getGoods_attr_id());
            if (getGoods_attr_id.equals(shopColourImgAdapter.getData().get(i).getGoods_attr_id())) {
                Log.e("?????????ID", "===111==000==");
                shopColourImgAdapter.getData().get(i).setFlage("1");
                Log.e("?????????ID", "===222==000==");
                shopColourImgAdapter.notifyDataSetChanged();
            }
        }


//        Log.e("???????????????", "==???????????????================" + moveDataBean.getInfo().getShop_name() + " " + event.getArrt_name());
//        moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getArrt_name());

        //???????????????????????? ??????????????????
        List<MoveDataBean.InfoBean.BannerBean> banlist = new ArrayList<>();
        Log.e("?????????????????????", "getBannerEvents.size()====" + getBannerEvents.size());
        for (int i = 0; i < getBannerEvents.size(); i++) {
            MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
            Log.e("?????????????????????", "getUrl====" + getBannerEvents.get(i).getUrl());
            bannerBeans.setUrl(getBannerEvents.get(i).getUrl());
            bannerBeans.setType(getBannerEvents.get(i).getType());
            banlist.add(bannerBeans);
        }

        Log.e("?????????????????????", "getProduct_price====" + event.getProduct_price());

        Log.e("?????????????????????", "getProduct_price====" + event.getBanner());

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
        //????????????
        if (player != null)
            player.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    //????????????????????????????????????????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ChangeDetailPriceEvent event) {
        Log.e("ChangeDetailPriceEvent", "==event===" + event);
//        Log.e("ChangeDetailPriceEvent", "==getAttr_name===" + event.getProductBean().getAttr_name());
        //????????????????????????--??????????????????????????????
        if (event.getMark().equals("1")) {
            goods_attr = event.getProductBean().getSearch_attr();
            Log.e("???????????????", "==???????????????===goods_attr==" + goods_attr);
            Log.e("???????????????", "==???????????????=======1111==" + moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            detailsDiameter.setText(event.getProductBean().getDiameter());//??????
            detailsBaseCurve.setText(event.getProductBean().getBase_curve());//??????
            detailsWaterContent.setText(event.getProductBean().getWater_content());//?????????
            detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//????????????
        } else if (event.getMark().equals("2")) {
            goods_attr = event.getProductBean().getSearch_attr();
            Log.e("???????????????", "==???????????????===goods_attr==" + goods_attr);
            Log.e("???????????????", "==???????????????=======222==" + moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name_info());
            moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name_info());
            detailsDiameter.setText(event.getProductBean().getDiameter());//??????
            detailsBaseCurve.setText(event.getProductBean().getBase_curve());//??????
            detailsWaterContent.setText(event.getProductBean().getWater_content());//?????????
            detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//????????????
        }


    }

    /**
     * ?????????????????????
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProblemListEvent event) {
        Log.e("ChangeDetailPriceEvent", "==event===" + event);
        Log.e("ChangeDetailPriceEvent", "==event===" + event.getData().size());
        wenyiwenNumber.setText("???" + event.getCount() + "???");
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
            Log.e("?????????", "==getQuestion==333=" + getQuestion_list.get(i).getQuestion());
        }
        wenAdapter.setData(getQuestion_list);
        recyclervWenyiwen.setAdapter(wenAdapter);

    }


    /**
     * ?????????SDK
     */
    private void initSdk() {
        //??????sdk ??????????????????
//        initLanguage("en");
        /*
          ?????????:?????????help
         */
        final KfStartHelper helper = new KfStartHelper(this);
        /*
          ?????????????????????????????????????????????????????????
         */
        handleCardInfo(helper);
         /*
          ????????????????????????????????????????????????????????????
         */
//        handleNewCardInfo(helper);
        /*
          ?????????:????????????
          ?????????sdk???????????????????????????????????????????????????????????????IM????????????
          @param accessId       ??????id???????????????????????????
          @param userName       ?????????
          @param userId         ??????id
         */
        /*
         * ?????????????????? ???????????? ????????????????????? ??????
         */
//        helper.setChatActivityLeftText("??????");

        helper.setOrderHead("", UserUtils.getInstance().getUserInfo().getImg_url());

        /*
         * ?????????????????? ???????????? emoji?????? ?????????
         */
//        helper.setChatActivityEmoji(true);
        String userID = UserUtils.getInstance().getUserInfo().getUser_id();
        if (UserUtils.getInstance().getUserInfo().getUser_id().length() < 2) {
            userID = "0" + userID;
        }
        helper.initSdkChat("97e623b0-f404-11ea-938a-2d31778d2422", UserUtils.getInstance().getUserInfo().getUser_name(), userID);


    }

    /**
     * ???????????????????????????
     */
    private void getUnReadCount() {
        if (MoorUtils.isInitForUnread(getApplicationContext())) {

            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
                    Toast.makeText(MoveAbooutActivity_4.this, "?????????????????????" + acount, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //?????????????????????????????? ???0
            Toast.makeText(MoveAbooutActivity_4.this, "???????????????", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ??????????????????
     *
     * @param helper
     */
    private void handleCardInfo(KfStartHelper helper) {
        String rigth3 = null;
        CardInfo ci = null;
        String icon = null;
//        String details = "https://wap.boosoo.com.cn/bobishop/goodsdetail?id=10160&mid=36819";//????????????

        String details = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr() + "&channel=" + Build.MANUFACTURER + "&group_id=" + group_id + "&is_group=1";
//        CardInfo ci = new CardInfo("http://seopic.699pic.com/photo/40023/0579.jpg_wh1200.jpg", "??????????????????????????????", "??????name???????????????", "?????? 1000-9999", "https://www.baidu.com");


        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            icon = moveDataBean.getInfo().getBanner().get(0).getUrl();
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            icon = moveDataBean.getInfo().getBanner().get(1).getUrl();
        }

        String title = moveDataBean.getInfo().getShop_name();
        String content = moveDataBean.getInfo().getShop_attr_name();
        Log.e("??????", "===getShop_attr_name===" + moveDataBean.getInfo().getShop_attr_name());
        if (moveDataBean.getInfo().getIs_promote().equals("1")) {//??????????????????
            rigth3 = Html.fromHtml("&yen") + moveDataBean.getInfo().getPreferential_price();
        } else if (moveDataBean.getInfo().getIs_promote().equals("0")) {//??????????????????
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


    //Android6.0???????????????????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode????????????????????????????????????checkSelfPermission?????????
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // ????????????????????????????????????????????????SDK????????????????????????????????????????????????????????????????????????
//                    CustomerService();

                    break;
                } else {
                    // ???????????????????????????????????????
                    Toast.makeText(getApplicationContext(), "??????????????????????????????????????????", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "?????????????????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * ????????????
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
                         *   setText just show the result for get token???
                         *   use ret to verfiy number???
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
                            Log.e("????????????Token???", "===??????????????????:\n==Token???===" + ret);
                        }

                        if (tokenRet != null && ("600001").equals(tokenRet.getCode())) {
                            Log.e("????????????Token???", "===?????????????????????:\n==Token???===" + ret);
                        }

                        if (tokenRet != null && ("600000").equals(tokenRet.getCode())) {
                            String token = tokenRet.getToken();
                            mAlicomAuthHelper.quitLoginPage();
                            Log.e("????????????Token???", "===??????token??????:\n==Token???=11==" + ret);
                            presenter.getMobileLogin(token);

                            //???Token???

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
                        Log.e("????????????Token???", "===??????token??????:\n==Token???=22==" + ret);
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
        mAlicomAuthHelper.setLoggerEnable(true);//??????SDK???????????????
        mAlicomAuthHelper.setAuthSDKInfo(Constant.LONIG_PHONE_NUMBER_KEY);//????????????


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
                            public void onClick(View v) {//??????????????????
                                mAlicomAuthHelper.hideLoginLoading();
                                LoginActivity.startSelf(getActivity());
                                mAlicomAuthHelper.quitLoginPage();
                            }
                        });

                        findViewById(R.id.login_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                login();//????????????

                            }
                        });

                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.id_2).getLayoutParams();

                        layoutParams.topMargin = findViewById(R.id.id_2).getTop() + logBtnOffset_1 + 200;
                        findViewById(R.id.id_2).setLayoutParams(layoutParams);


                    }
                })
                .build());


        mAlicomAuthHelper.setAuthUIConfig(new AuthUIConfig.Builder()
                .setAppPrivacyOne("??????????????????", "http://shop.jealook.com/v1/html/article-info?id=117")
                .setAppPrivacyTwo("??????????????????", "http://shop.jealook.com/v1/html/article-info?id=119")
                .setAppPrivacyColor(Color.BLACK, Color.parseColor("#A08FBB"))
                .setPrivacyState(true)
                .setCheckboxHidden(true)
                .setNavHidden(false)
                .setNavColor(getResources().getColor(R.color.them))
                .setWebNavColor(Color.parseColor("#A08FBB"))//????????????????????????????????????
                .setStatusBarColor(Color.parseColor("#A08FBB"))//?????????????????????
                .setLightColor(false)
                .setAuthPageActIn("in_activity", "out_activity")
                .setAuthPageActOut("in_activity", "out_activity")
                .setVendorPrivacyPrefix("???")
                .setVendorPrivacySuffix("???")
                .setLogBtnWidth(dialogWidth - 30)
                .setLogBtnMarginLeftAndRight(15)
                .setNavReturnHidden(true)//?????????????????????
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
                .setLogBtnText("????????????????????????")
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
     * ????????????(????????????)
     * 1.??????????????????
     * 2.??????????????????code ???????????????token
     * 3.??????token??????????????????
     */
    public void login() {
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = String.valueOf(System.currentTimeMillis());
//        wxAPI.sendReq(req);

        if (!App.getwxApi().isWXAppInstalled()) {
            Toast.makeText(getActivity(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = String.valueOf(System.currentTimeMillis());
            App.getwxApi().sendReq(req);
        }

    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginSuccess(int code, UserInfoBean data) {
        UserUtils.getInstance().login(data);
        // ??????????????????
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
//        new LoginDataEvent(data).post();
//        new MainHomeActivityEvent("2").post();
//        presenter.getPersonalInformation();//??????
//        finish();


    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginFail(int code, String msg) {

    }

    /**
     * @Description:????????????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
//        if (mmark.equals("1")) {

        ConfirmOrderActivity_1.startSelf(MoveAbooutActivity_4.this, "", goods_id, product_ids, nums, "2", group_info, group_id,"");
//        }
    }

    /**
     * @Description:????????????????????????
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
     * @Description:??????????????????--???????????????
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
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();

    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * ????????????????????????
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
                //?????????????????????????????????????????????????????????
                Log.e("??????==", "==getAttr_names==" + getAttr_names);
                Log.e("??????==", "==num==" + num);
                Log.e("??????==", "==productBean==" + productBean.getAttr_name());
                Log.e("??????==", "==product_id==" + productBean.getProduct_id());
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
                    Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                }

                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }

                Log.e("???????????????", "===number===" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//????????????
                groupYuanPriceText.setText(String.valueOf(price2_2));//??????????????????

            }


        }).show();

    }


    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop4Fail(int code, String msg) {

    }

    /**
     * ????????????????????????--??????
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
                //?????????????????????????????????????????????????????????
                Log.e("??????==", "==getAttr_names==" + getAttr_names);
                Log.e("??????==", "==num==" + num);
                Log.e("??????==", "==productBean==" + productBean.getAttr_name());
                Log.e("??????==", "==product_id==" + product_id);
                String velue = productBean.getAttr_name();
                if (listBeant.size() > 0) {
                    Log.e("??????==", "==111111111=num====" + num);
                    Log.e("??????==", "==product_id=num====" + product_id);
                    Log.e("??????==", "==hasProductList=num====" + hasProductList);
                    if (hasProductList.contains(product_id)) {
                        for (int i = 0; i < listBeant.size(); i++) {
                            Log.e("??????==", "==22222222222=num====" + num);
                            Log.e("??????==", "==product_id====" + product_id);
                            if (listBeant.get(i).getProduct_id().equals(product_id)) {
                                Log.e("??????==", "==333333333333=num====" + num);
                                int num2 = Integer.parseInt(listBeant.get(i).getNum()) + Integer.parseInt(num);
                                Log.e("??????==", "==4444444444444=num====" + num);
                                if (finalNumss + Integer.parseInt(num) <= Integer.parseInt(moveDataBean.getShopActiveInfo().getAstrict_num())) {
                                    Log.e("??????==", "==55555555555555=num====" + num);

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
                                    Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                }

                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }

                Log.e("???????????????", "===number==000=" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//????????????
                groupYuanPriceText.setText(String.valueOf(price2_2));//??????????????????

            }
        }).show();


    }

    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop5Fail(int code, String msg) {

    }

    /**
     * ????????????????????????
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
                Log.e("??????==", "==getAttr_names==" + getAttr_names);
                Log.e("??????==", "==num==" + num);
                Log.e("??????==", "==productBean==" + productBean.getAttr_name());
                Log.e("??????==", "==product_id==" + product_id);
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
                    Toast.makeText(MoveAbooutActivity_4.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                }


                int number = 0;
                for (int i = 0; i < adapterGroup.getData().size(); i++) {
                    number = number + Integer.parseInt(adapterGroup.getData().get(i).getNum());
                }
                Log.e("???????????????", "===number==111=" + number);
                double price1 = Double.valueOf(moveDataBean.getShopActiveInfo().getGroup_price()) * number;
                double price2 = Double.valueOf(moveDataBean.getInfo().getProduct_price()) * number;
                String price1_1 = DF(price1);
                String price2_2 = DF(price2);
                groupPriceText.setText(String.valueOf(price1_1));//????????????
                groupYuanPriceText.setText(String.valueOf(price2_2));//??????????????????


            }

        }).show();


    }

    /**
     * ????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShop6Fail(int code, String msg) {

    }


}
