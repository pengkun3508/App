package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
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
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
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
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.NewCardInfoAttrs;
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
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.event.ProblemListEvent;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.indicator.NumIndicator;
import com.vinnlook.www.surface.adapter.CommentListAdapter;
import com.vinnlook.www.surface.adapter.DetailsImags1Adapter;
import com.vinnlook.www.surface.adapter.DetailsImags2Adapter;
import com.vinnlook.www.surface.adapter.GuangImagAdapter;
import com.vinnlook.www.surface.adapter.MultipleTypesAdapter;
import com.vinnlook.www.surface.adapter.ReBangImagAdapter;
import com.vinnlook.www.surface.adapter.ShopColourImgAdapter;
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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:????????????
 * @Time:2020/4/20$
 * @Author:pk$
 */
public class MoveAbooutActivity_3 extends BaseActivity<MoveAboutPresenter> implements MoveAboutView {

    private static final int BAIDU_READ_PHONE_STATE = 100;

    @BindView(R.id.move_shoucang_img)
    ImageView moveShoucangImg;//??????
    @BindView(R.id.title_bars)
    RelativeLayout titleBar;
    @BindView(R.id.action_bar)
    MoveAboutBarSimple actionBar;
    @BindView(R.id.move_shopcat_btn)
    LinearLayout moveShopcatBtn;//???????????????
    @BindView(R.id.move_kefu_btn)
    LinearLayout moveKefuBtn;//????????????
    @BindView(R.id.move_shoucang_btn)
    LinearLayout moveShoucangBtn;//????????????
    @BindView(R.id.move_add_shopcat_btn)
    TextView moveAddShopcatBtn;//???????????????
    @BindView(R.id.tv_move_about)
    TextView tvMoveAbout;//????????????
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
    LinearLayout moveReLayout1;//????????????
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
    @BindView(R.id.rebang_type_text)
    TextView rebangTypeText;//????????????
    @BindView(R.id.rebang_mingci_text)
    TextView rebangMingciText;//????????????
    @BindView(R.id.rebang_recycler)
    RecyclerView rebangRecycler;//????????????
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
    @BindView(R.id.move_xiaoliang_text)
    TextView moveXiaoliangText;
    @BindView(R.id.activity_name_text)
    TextView activityNameText;
    @BindView(R.id.move_xianshi_layout)
    LinearLayout moveXianshiLayout;

    List<MoveDataBean.QuestionListBean> getQuestion_list;
    @BindView(R.id.pingjia_see_all)
    LinearLayout pingjiaSeeAll;
    @BindView(R.id.wen_see_all)
    LinearLayout wenSeeAll;
    @BindView(R.id.move_transaction_supi)
    RoundTextView moveTransactionSupi;
    @BindView(R.id.move_limit_long_flag)
    TextView moveLimitLongFlag;
    @BindView(R.id.move_limit_btn)
    RelativeLayout moveLimitBtn;
    @BindView(R.id.move_xianshi_day)
    TextView moveXianshiDay;
    @BindView(R.id.move_xianshi_min)
    TextView moveXianshiMin;
    @BindView(R.id.move_xianshi_second)
    TextView moveXianshiSecond;
    @BindView(R.id.id_11)
    TextView id11;
    @BindView(R.id.limit_11)
    TextView limit11;
    @BindView(R.id.move_limit_short_flag)
    TextView moveLimitShortFlag;
    @BindView(R.id.move_xiangou_line_view)
    View moveXiangouLineView;
    @BindView(R.id.move_yushou_long_flag)
    TextView moveYushouLongFlag;
    @BindView(R.id.move_yushou_btn)
    RelativeLayout moveYushouBtn;
    @BindView(R.id.move_yushou_line_view)
    View moveYushouLineView;
    @BindView(R.id.move3_huodong_img)
    ImageView move3HuodongImg;
    @BindView(R.id.move_shop_explain)
    TextView moveShopExplain;
    @BindView(R.id.move_shop_attr_recycler)
    RecyclerView moveShopAttrRecycler;
    @BindView(R.id.move_shop_explain_layout)
    LinearLayout moveShopExplainLayout;
    private static String articleId;//????????????ID
    @BindView(R.id.guang_all)
    TextView guangAll;
    @BindView(R.id.guang_recy)
    RecyclerView guangRecy;


    private float totaldy;
    private float mRecyclerFactor;
    private List<DetailsBean> list;
    private LinearLayoutManager manager;
    private Resources res;
    String mark = "0";


    private static String goods_id;//????????????
    private static String search_attr;//????????????
    @BindView(R.id.guang_layout)
    LinearLayout guangLayout;


    MoveDataBean moveDataBean;

    //?????????????????? - ms
    private long dt = 0;
    UserInfoBean getUserInfo;
    private int mScreenWidthDp;
    private int mScreenHeightDp;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

    public PopupWindow popupwindow;
    public PopupWindow popupwindow1;

    String mmark;//???????????????????????????1--???????????????????????????--?????????
    String product_ids;
    String nums;

    String goods_attr;

    ShopColourImgAdapter shopColourImgAdapter;
    CommentListAdapter commentAdapter;//???????????????
    GuangImagAdapter guangAdapter;
    WenListAdapter wenAdapter;//??????????????????
    DetailsImags1Adapter adapter;//???????????????
    DetailsImags2Adapter adapter2;//???????????????
    ReBangImagAdapter adapter3;//???????????????
    TuiJianAdapter adapter4;//???????????????

    int shangPinHeight;
    int pingJiaHeight;
    int xiangQingHeight;
    int xuZhiHeight;
    int titleBarHeight;

    MoveDataBean.RecommendBean ecommendBean;
    StandardGSYVideoPlayer player;

    Bitmap bitmaps;//?????????

    public static void startSelf(Activity context, String goods_ids, String search_attrs, String articleIds) {
        Intent intent = new Intent(context, MoveAbooutActivity_3.class);
//        context.startActivity(intent);
        intent.putExtra("good_id", goods_ids);
        intent.putExtra("search_attr", search_attrs);
        context.startActivityForResult(intent, 1);
        goods_id = goods_ids;
        search_attr = search_attrs;
        articleId = articleIds;
        Log.e("??????ID", "=-articleId=" + articleId);
        Log.e("goods_id", "=-goods_id=" + goods_id);
        Log.e("search_attr", "=-search_attr=" + search_attr);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_about_3;
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
                        if (moveDataBean != null) {
                            initmPopupWindowView();
                            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                        }
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
        mRecyclerFactor = (DensityUtilss.dp2px(this, 400.0F) - DensityUtilss.getStatusBarHeight(this));
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
                    StatusBarUtil.setColor(MoveAbooutActivity_3.this, Color.parseColor("#ffffff"));
                }


            }
        });


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
            moveXianshiSecond.setText(secondss);

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dt <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };


    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//????????????
        presenter.getMoveDatas(goods_id, search_attr);//????????????????????????
//        presenter.getMoveDatas("2", "26|27");//????????????????????????
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
                banner.addBannerLifecycleObserver(MoveAbooutActivity_3.this)
                        .setAdapter(new MultipleTypesAdapter(MoveAbooutActivity_3.this, banlist, moveDataBean.getInfo().getProduct_price(), moveDataBean.getInfo().getBorder_image()))
                        .setIndicator(new NumIndicator(MoveAbooutActivity_3.this))
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

                Log.e("???????????????", "===??????:===11111111=====");
                Log.e("???????????????", "===??????:========" + moveDataBean.getInfo().getShop_name() + shopColourImgAdapter.getData().get(position).getShop_attr_name());
                moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + shopColourImgAdapter.getData().get(position).getShop_attr_name());
                moveTypeText.setText("?????? " + shopColourImgAdapter.getData().get(position).getShop_attr_name());
//                goods_attr = shopColourImgAdapter.getData().get(position).getGoods_attr_id() + "|" + moveDataBean.getAttr().get(1).getValue().get(0).getGoods_attr_id();
                goods_attr = shopColourImgAdapter.getData().get(position).getGoods_attr_id();
                Log.e("???????????????", "===goods_attr=====" + goods_attr);
                if (shopColourImgAdapter.getData().get(position).getFlage().equals("0")) {
                    for (int i = 0; i < shopColourImgAdapter.getData().size(); i++) {
                        shopColourImgAdapter.getData().get(i).setFlage("0");
                    }
                    shopColourImgAdapter.getData().get(position).setFlage("1");

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
                SelectEyeChartActivity.startSelf(MoveAbooutActivity_3.this,guangAdapter.getData().get(position).getId());
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

        //???????????????
        adapter4 = new TuiJianAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        moveTuijianRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        moveTuijianRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        moveTuijianRecycler.setLayoutManager(manager1);
        moveTuijianRecycler.setNestedScrollingEnabled(false);
        moveTuijianRecycler.setFocusable(false);


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
        if (mark.equals("1")) {
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        } else if (mark.equals("0")) {
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
        }


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

        if (mark.equals("1")) {
            mark = "0";
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        } else if (mark.equals("0")) {
            mark = "1";
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
        }

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


    @SuppressLint("MissingPermission")
    @OnClick({R.id.move_shopcat_btn, R.id.move_kefu_btn, R.id.move_shoucang_btn, R.id.move_add_shopcat_btn, R.id.tv_move_about, R.id.rebang_layout_btn,
            R.id.wen_see_all, R.id.move_see_all_btn, R.id.guang_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.move_shopcat_btn://?????????
                CacheActivity.finishActivity();
//                setResult(10);
                new MainShoppingEvent("10").post();
                finish();
                break;
            case R.id.move_kefu_btn://??????
                PermissionHelper.with(getContext()).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                if (!UserUtils.getInstance().getUserId().equals("")) {
                                    initSdk();
                                } else {
                                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
                break;
            case R.id.move_shoucang_btn://??????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    if (mark.equals("0")) {
                        mark = "1";
                        presenter.getMoveCollectionShop(moveDataBean.getInfo().getGoods_id(), mark);
                    } else if (mark.equals("1")) {
                        mark = "0";
                        presenter.getMoveCollectionShop(moveDataBean.getInfo().getGoods_id(), mark);
                    }
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.move_add_shopcat_btn://???????????????
                if (moveDataBean == null) {
                    return;
                }
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                String getSearch_attr = moveDataBean.getInfo().getSearch_attr();//??????????????????????????????
                    presenter.getTypeShopData5(goods_id);

                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();

//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }


                break;
            case R.id.tv_move_about://????????????
                if (moveDataBean == null) {
                    return;
                }

                if (!UserUtils.getInstance().getUserId().equals("")) {
                    presenter.getTypeShopData6(goods_id);
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
//                    loginDialog();
//                    getPhoneNumber();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.rebang_layout_btn://??????????????????
                ReBangListActivity.startSelf(MoveAbooutActivity_3.this, moveDataBean.getSalable().getType());

                break;

            case R.id.wen_see_all://?????????????????????
                ProblemActivity.startSelf(MoveAbooutActivity_3.this, moveDataBean);

                break;
            case R.id.move_see_all_btn://????????????????????????
                new MainHomeActivityEvent("4").post();
                finish();
                break;
            case R.id.guang_all://??????????????????
                MoveGuangListActivity.startSelf(this,moveDataBean.getInfo().getGoods_id());
                break;
        }
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
        moveShopExplain.setText(data.getInfo().getGoods_brief());
        for (int i = 0; i < data.getAttr().get(0).getValue().size(); i++) {
            data.getAttr().get(0).getValue().get(i).setFlage("0");
        }
        data.getAttr().get(0).getValue().get(0).setFlage("1");
        shopColourImgAdapter.setData(data.getAttr().get(0).getValue());
        moveShopAttrRecycler.setAdapter(shopColourImgAdapter);

        int getIs_collect = moveDataBean.getInfo().getIs_collect();
        if (getIs_collect == 0) {//????????????
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.move_img1));
            mark = "0";
        } else if (getIs_collect == 1) {//??????
            moveShoucangImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shoucang_img1));
            mark = "1";
        }

//        goods_attr = data.getInfo().getSearch_attr();

        //?????????????????????
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }

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


        //?????????????????????---ms
        dt = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);

        banner.addBannerLifecycleObserver(this)
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

        detailsVipPrice1.setText(Html.fromHtml("&yen") + data.getInfo().getMember_discount());
        //????????????????????????--??????????????????????????????
        if (data.getInfo().getIs_promote().equals("1")) {//??????????????????
            activityNameText.setText(data.getInfo().getActive_name());
            moveReLayout1.setVisibility(View.VISIBLE);
            moveTransactionPricesYuan1.setVisibility(View.VISIBLE);
            moveTransactionPrices1.setText(Html.fromHtml("&yen") + data.getInfo().getPreferential_price());
            moveTransactionPricesYuan1.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());

        } else if (data.getInfo().getIs_promote().equals("0")) {//??????????????????
            if (!data.getInfo().getActive_name().equals("") && data.getInfo().getActive_name() != null) {
                moveReLayout1.setVisibility(View.VISIBLE);
                activityNameText.setText(data.getInfo().getActive_name());
                moveXianshiLayout.setVisibility(View.GONE);
            } else {
                moveReLayout1.setVisibility(View.GONE);
            }

            moveTransactionPricesYuan1.setVisibility(View.GONE);
            moveTransactionPrices1.setText(Html.fromHtml("&yen") + data.getInfo().getProduct_price());
        }


        //??????????????????--??????????????????
        if (UserUtils.getInstance().getUserId().equals("")) {//?????????
            detailsVipOpenText1.setText("???????????????");
            detailsVipZhe1.setText("??????Plus???????????????????????????9.5???");
            //????????????
            detailsVipOpen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MoveAbooutActivity_3.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
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

        //????????????????????????
        moveTransactionPricesYuan1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        //???????????????
        //?????????????????????---ms
        dt = Integer.valueOf(data.getInfo().getSurplus_time());
        handler.sendEmptyMessageDelayed(0, 1000);

        moveXiaoliangText.setText("????????????" + data.getInfo().getVirtual_sales());//??????
        //????????????
        moveTransactionName.setText(data.getInfo().getShop_name() + " " + data.getInfo().getShop_attr_name());


//        getPurchasing = "????????????1???";

        //??????
        String getPurchasing = data.getInfo().getPurchasing();
        if (getPurchasing.equals("")) {
            moveLimitBtn.setVisibility(View.GONE);
            moveXiangouLineView.setVisibility(View.GONE);
        } else {
            moveLimitBtn.setVisibility(View.VISIBLE);
            moveXiangouLineView.setVisibility(View.VISIBLE);
            moveLimitLongFlag.setText("?????????" + getPurchasing);

        }

        //??????
        if (data.getInfo().getPresell().equals("")) {
            moveYushouBtn.setVisibility(View.GONE);
            moveYushouLineView.setVisibility(View.GONE);
        } else {
            moveYushouBtn.setVisibility(View.VISIBLE);
            moveYushouLineView.setVisibility(View.VISIBLE);
            moveYushouLongFlag.setText(data.getInfo().getPresell());
//            moveYushouLongFlag.setText("???????????? ?? ??????????????????????????????,???????????? ?? ??????????????????????????????,???????????? ?? ??????????????????????????????");

        }


        //??????
        if (data.getInfo().getShop_attr_name().equals("") || data.getInfo().getShop_attr_name() == null) {
            moveTypeText.setText("?????? ?????? ?????????????????? ??????");
        } else {
            moveTypeText.setText("?????? " + data.getInfo().getShop_attr_name());
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
        //??????--??????
        if (data.getInfo().getSuppliers_id().equals("1")) {
            detailsSuppliers.setText("??????");
            fuwuText.setText("??????????????????????????24???????????????");
            textLineShuilv.setVisibility(View.GONE);
            textShuilv.setVisibility(View.GONE);
            detailsSuppliers.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.classify_list_item_1));
        } else if (data.getInfo().getSuppliers_id().equals("2")) {
            detailsSuppliers.setText("??????");
            fuwuText.setText("???????????????????????????????????????????????");
            textLineShuilv.setVisibility(View.VISIBLE);
            textShuilv.setVisibility(View.VISIBLE);
            detailsSuppliers.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.classify_list_item));
        }

        //???????????????
        moveYouhuiquanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CouponActivity.startSelf(MoveAbooutActivity_3.this);//???????????????
            }
        });

        //????????????
        kuaidiLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WebActivity.startSelf(MoveAbooutActivity_3.this, "http:h5.jealook.com/vinnlook/Package.html");
                WebActivity.startSelf(MoveAbooutActivity_3.this, data.getInfo().getWaybill_url());
            }
        });
        //?????????
        moveTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("????????????", "==????????????==");
                presenter.getTypeShopData4(goods_id);

            }
        });


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
        wenyiwenNumber.setText("???" + data.getInfo().getQuestion_count() + "???");

        wenAdapter.setData(data.getQuestion_list());
        recyclervWenyiwen.setAdapter(wenAdapter);
        if (data.getQuestion_list().size() > 0) {
            wendajiaLine.setVisibility(View.VISIBLE);
        } else {
            wendajiaLine.setVisibility(View.GONE);
        }

        //??????
        adapter.setData(data.getInfo().getDetails());
        imgListDetails1.setAdapter(adapter);
        //??????
        adapter2.setData(data.getInfo().getUser_notes());
        imgListDetails2.setAdapter(adapter2);
        //???????????????
        adapter4.setData(data.getRecommend());
        moveTuijianRecycler.setAdapter(adapter4);
        adapter4.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), adapter4.getData().get(position).getGoods_id(), adapter4.getData().get(position).getSearch_attr(), "");

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


        Log.e("getRanking", "==getRanking==" + data.getSalable().getRanking());
        //??????
        rebangMingciText.setText(data.getSalable().getRanking());
        if (Integer.parseInt(data.getSalable().getRanking()) > 10) {
            rebangLayoutBtn.setVisibility(View.GONE);
        } else {
            rebangLayoutBtn.setVisibility(View.VISIBLE);
            //??????
            //1--?????????2--?????????3--?????????4--????????????5--?????????
            if (data.getSalable().getType().equals("1")) {
                rebangTypeText.setText("???????????????");
            } else if (data.getSalable().getType().equals("2")) {
                rebangTypeText.setText("???????????????");
            } else if (data.getSalable().getType().equals("3")) {
                rebangTypeText.setText("???????????????");
            } else if (data.getSalable().getType().equals("4")) {
                rebangTypeText.setText("??????????????????");
            } else if (data.getSalable().getType().equals("5")) {
                rebangTypeText.setText("???????????????");
            }
        }

        adapter3.setData(data.getSalable().getImage());
        rebangRecycler.setAdapter(adapter3);
        adapter3.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ReBangListActivity.startSelf(MoveAbooutActivity_3.this, data.getSalable().getType());
            }
        });

        //??????
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
                            PreferentialActivity.startSelf(MoveAbooutActivity_3.this, data.getShopActiveInfo().getAct_id());
                        } else if (data.getShopActiveInfo().getType().equals("2")) {
                            //??????????????????
                            SetMealActivity.startSelf(MoveAbooutActivity_3.this, data.getInfo().getGoods_id());
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
        //http://h5.vinnlook.com/detail-share/index.html?good_id=6&search_attr=133|134
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER;//??????????????????
        String url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER;//??????????????????
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
                    Log.e("???????????????", "bitmaps====" + bitmaps);
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
                Toast.makeText(MoveAbooutActivity_3.this, "????????????", Toast.LENGTH_LONG).show();
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
                int permission = ContextCompat.checkSelfPermission(MoveAbooutActivity_3.this,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // ???????????????????????????????????????????????????????????????
                    ActivityCompat.requestPermissions(MoveAbooutActivity_3.this, PERMISSIONS, 1);
                }
                try {
                    //??????savephoto???????????????
                    SavePhoto savePhoto = new SavePhoto(MoveAbooutActivity_3.this);
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
        banner.addBannerLifecycleObserver(this)
                .setAdapter(new MultipleTypesAdapter(MoveAbooutActivity_3.this, banlist, moveDataBean.getInfo().getProduct_price(), moveDataBean.getInfo().getBorder_image()))
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
//        move_transaction_price.setText("" + event.getPrice());
        if (event.getMark().equals("1")) {
//            if (moveDataBean.getInfo().getIs_promote().equals("1")) {//??????????????????
//                moveReLayout1.setVisibility(View.VISIBLE);
//                moveTransactionPricesYuan1.setVisibility(View.VISIBLE);
//                moveTransactionPrices1.setText(Html.fromHtml("&yen") + event.getProductBean().getPreferential_price());
//                moveTransactionPricesYuan1.setText(Html.fromHtml("&yen") + event.getProductBean().getProduct_price());
//            } else if (moveDataBean.getInfo().getIs_promote().equals("0")) {//??????????????????
//                moveReLayout1.setVisibility(View.GONE);
//                moveTransactionPricesYuan1.setVisibility(View.GONE);
//                moveDataBean.getInfo().setProduct_price(event.getProductBean().getProduct_price());
//                moveTransactionPrices1.setText(Html.fromHtml("&yen") + event.getProductBean().getProduct_price());
//            }
//        moveDataBean.getInfo().setShop_attr_name(event.getAttr_name());
//            moveDataBean.getInfo().setShop_attr_name(event.getProductBean().getAttr_name());
            moveTypeText.setText("?????? " + event.getProductBean().getAttr_name());


            goods_attr = event.getProductBean().getSearch_attr();
//            goods_attr = event.getProductBean().getGoods_attr();
            Log.e("???????????????", "==???????????????===goods_attr==" + goods_attr);
            Log.e("???????????????", "==???????????????=======1111==" + moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            moveTransactionName.setText(moveDataBean.getInfo().getShop_name() + " " + event.getProductBean().getAttr_name());
            detailsDiameter.setText(event.getProductBean().getDiameter());//??????
            detailsBaseCurve.setText(event.getProductBean().getBase_curve());//??????
            detailsWaterContent.setText(event.getProductBean().getWater_content());//?????????
            detailsColoringDiameter.setText(event.getProductBean().getColoring_diameter());//????????????


        } else if (event.getMark().equals("2")) {
            moveTypeText.setText("?????? " + event.getProductBean().getAttr_name_info());

//            goods_attr = event.getProductBean().getSearch_attr();
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
                    Toast.makeText(MoveAbooutActivity_3.this, "?????????????????????" + acount, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //?????????????????????????????? ???0
            Toast.makeText(MoveAbooutActivity_3.this, "???????????????", Toast.LENGTH_SHORT).show();
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

        String details = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + moveDataBean.getInfo().getGoods_id() + "&search_attr=" + moveDataBean.getInfo().getSearch_attr() + "&channel=" + Build.MANUFACTURER;
//        CardInfo ci = new CardInfo("http://seopic.699pic.com/photo/40023/0579.jpg_wh1200.jpg", "??????????????????????????????", "??????name???????????????", "?????? 1000-9999", "https://www.baidu.com");


        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            icon = moveDataBean.getInfo().getBanner().get(0).getUrl();
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            icon = moveDataBean.getInfo().getBanner().get(1).getUrl();
        }

        String title = moveDataBean.getInfo().getShop_name();
        String content = moveDataBean.getInfo().getShop_attr_name();
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

    /**
     * ?????????????????????,{@link NewCardInfo.Builder()} Builder??????????????????????????????????????????????????????
     */
    private void handleNewCardInfo(KfStartHelper helper) {
//        NewCardInfo newCardInfo = new NewCardInfo.Builder()
//                .build();

        NewCardInfo newCardInfo = new NewCardInfo.Builder()
                .setTitle("????????????")
                .setAttr_one(new NewCardInfoAttrs().setColor("#487903").setContent("x9"))
                .setAttr_two(new NewCardInfoAttrs().setColor("#845433").setContent("?????????"))
                .setOther_title_one("????????????")
                .setOther_title_two(null)
                .setOther_title_three(null)
                .setSub_title("???????????????")
                .setPrice("$999")
                .build();


        helper.setNewCardInfo(newCardInfo);
    }

    /**
     * ????????????
     * ?????? language???""
     * ?????? language???"en"
     */
    private void initLanguage(String language) {
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//????????????
    }


    //Android6.0???????????????????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1);  // , 1???????????????
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
//                                login();//????????????
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
        if (mmark.equals("1")) {
            ConfirmOrderActivity_1.startSelf(MoveAbooutActivity_3.this, "", goods_id, product_ids, nums, "2", "", "",articleId);

            TypeSelectDialog.dismiss();
        }
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
     * @Description:??????????????????
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
                presenter.getAddShopCar(goods_id, productBean.getProduct_id(), num,articleId);

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
        moveDataBean.setProduct(data.getProduct());
        TypeSelectDialog.with(MoveAbooutActivity_3.this, moveDataBean, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
//                        moveDataBean.getInfo().setShop_attr_name(getAttr_names);
//                        moveTypeText.setText(getAttr_names);
//                            notifyDataSetChanged();
                TypeSelectDialog.dismiss();
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
     * ????????????????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeShop5Success(int code, MoveDataBean data) {
        moveDataBean.setProduct(data.getProduct());
        TypeSelectDialog.with(getActivity(), moveDataBean, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
                Log.e("onBtnClLister=?????????==", "==getAttr_names==" + getAttr_names);
                Log.e("onBtnClLister=?????????==", "==productBean.getProduct_id(==" + productBean.getProduct_id());
                Log.e("onBtnClLister=?????????==", "==productBean.getProduct_id(==" + product_id);
//                            getAttr_name = getAttr_names;
                presenter.getAddShopCar(goods_id, productBean.getProduct_id(), num,articleId);
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
     * @param data
     */
    @Override
    public void getTypeShop6Success(int code, MoveDataBean data) {
        moveDataBean.setProduct(data.getProduct());
        TypeSelectDialog.with(getActivity(), moveDataBean, goods_attr, "1", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_names, ProductBean productBean, String mmake) {
                Log.e("onBtnClickListen=?????????==", "==getAttr_names==" + getAttr_names);
//                            getAttr_name = getAttr_names;
//                            presenter.getAddShopCar(goods_id, product_id, num);
                mmark = mmake;
//                            presenter.getConfirmOrderData("", goods_id, product_id, num, "", "", "");
                presenter.getConfirmOrderData("", goods_id, productBean.getProduct_id(), num, "", "", "", "", "", "", "", "", "");
                product_ids = productBean.getProduct_id();
                nums = num;

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
