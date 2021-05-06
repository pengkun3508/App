package com.vinnlook.www.surface.fragment;


import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.m7.imkfsdk.KfStartHelper;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.utils.MoorUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.LoginDataEvent;
import com.vinnlook.www.event.MyPersonalJudgeEvent;
import com.vinnlook.www.event.PostWechatEvent;
import com.vinnlook.www.surface.activity.AddressActivity;
import com.vinnlook.www.surface.activity.AllOrderActivity;
import com.vinnlook.www.surface.activity.ApplyRefundListActivity;
import com.vinnlook.www.surface.activity.BrowseActivity;
import com.vinnlook.www.surface.activity.CollectionActivity;
import com.vinnlook.www.surface.activity.CouponActivity;
import com.vinnlook.www.surface.activity.EditDataActivity;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.LoginActivity;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.ModifyPhoneActivity;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.MsggingBoxActivity;
import com.vinnlook.www.surface.activity.PointsMallActivity;
import com.vinnlook.www.surface.activity.PointsMallListActivity;
import com.vinnlook.www.surface.activity.ProblemFeedbackActivity;
import com.vinnlook.www.surface.activity.ProductDetailsActivity;
import com.vinnlook.www.surface.activity.RealNameActivity;
import com.vinnlook.www.surface.activity.SettingActivity;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.adapter.BannerImgAdapter2;
import com.vinnlook.www.surface.adapter.FlipperAdapter;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.surface.mvp.presenter.MyFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.MyFragmentView;
import com.vinnlook.www.utils.AppUtils;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SavePhoto;
import com.vinnlook.www.utils.UserInfoBean;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.VerticalScrollLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.vinnlook.www.surface.activity.MoveAbooutActivity_3.bmpToByteArray;


/**
 * @Description:我的
 * @Time:2020/4/1 17:02
 * @Author:pk
 */
public class MyFragment extends BaseFragment<MyFragmentPresenter> implements MyFragmentView {

    @BindView(R.id.cat_avatar)
    RoundedImageView catAvatar;//头像
    @BindView(R.id.sign_register_text)
    TextView signRegisterText;//登录、注册
    @BindView(R.id.youhuiquan_layout)
    LinearLayout youhuiquanLayout;//优惠券
    @BindView(R.id.all_order_text)
    TextView allOrderText;//查看全部订单
    @BindView(R.id.daifukuan_layout)
    LinearLayout daifukuanLayout;//代付款
    @BindView(R.id.daifahuo_layout)
    LinearLayout daifahuoLayout;//待发货
    @BindView(R.id.daishouhuo_layout)
    LinearLayout daishouhuoLayout;//待收货
    @BindView(R.id.daipingjia_layout)
    LinearLayout daipingjiaLayout;//待评价
    @BindView(R.id.tuihuanhuo_layout)
    LinearLayout tuihuanhuoLayout;//退换货
    @BindView(R.id.me_shoucang)
    LinearLayout meShoucang;//我的收藏
    @BindView(R.id.me_address)
    LinearLayout meAddress;//收货地址
    @BindView(R.id.me_realname)
    LinearLayout meRealname;//实名认证
    @BindView(R.id.me_wenti)
    LinearLayout meWenti;//问题反馈
    @BindView(R.id.me_guanyu)
    LinearLayout meGuanyu;//关于我们
    @BindView(R.id.me_share)
    LinearLayout meShare;//分享

    @BindView(R.id.my_member_add)
    TextView myMemberAdd;//立即开通
    @BindView(R.id.my_member_date)
    TextView myMemberDate;//到期时间

    UserInfoBean userInfoBean;
    @BindView(R.id.daifukuan_text)
    TextView daifukuanText;//待付款数量
    @BindView(R.id.daifahuo_text)
    TextView daifahuoText;//待发货数量
    @BindView(R.id.daishouhuo_text)
    TextView daishouhuoText;//待收货数量
    @BindView(R.id.daipingjia_text)
    TextView daipingjiaText;//待评价数量
    @BindView(R.id.tuihuanhuo_text)
    TextView tuihuanhuoText;//退换货数量
    @BindView(R.id.my_collect_count)
    TextView myCollectCount;//收藏数量
    @BindView(R.id.my_points_count)
    TextView myPointsCount;//积分数量
    @BindView(R.id.my_browse_count)
    TextView myBrowseCount;//浏览数量
    @BindView(R.id.my_discount_count)
    TextView myDiscountCount;//优惠券数量
    @BindView(R.id.my_browse_layout)
    LinearLayout myBrowseLayout;//浏览历史
    @BindView(R.id.my_member_level)
    RoundedImageView myMemberLevel;//会员标志符号
    @BindView(R.id.my_member_img1)
    ImageView myMemberImg1;
    @BindView(R.id.me_jifen)
    LinearLayout meJifen;
    @BindView(R.id.my_jifen_layout)
    LinearLayout myJifenLayout;
    @BindView(R.id.banner_my)
    Banner bannerMy;
    @BindView(R.id.wuliu_time)
    TextView wuliuTime;
    @BindView(R.id.view_flipper)
    VerticalScrollLayout viewFlipper;
    @BindView(R.id.wuliu_layout)
    LinearLayout wuliuLayout;
    @BindView(R.id.wuliu_line)
    View wuliuLine;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.my_seeting_btn)
    LinearLayout mySeetingBtn;
    @BindView(R.id.me_msg)
    LinearLayout meMsg;
    @BindView(R.id.xiaoxi_text)
    TextView xiaoxiText;
//    @BindView(R.id.auto_recylerview)
//    RExLoopRecyclerView autoRecylerview;

    Bitmap bitmaps;//二维码图片
    public PopupWindow popupwindow1;


    private int mScreenWidthDp;
    private int mScreenHeightDp;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

//    private IWXAPI wxAPI;

    public PopupWindow popupwindow;
    List<PersonalInformationBean.BannerBean> bannerImage;//轮播

    PersonalInformationBean personalInformationBean;

    KfStartHelper helper;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_1;
    }

    @Override
    protected MyFragmentPresenter initPresenter() {
        return new MyFragmentPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        ImageLoader.userIcon(getActivity(), catAvatar, UserUtils.getInstance().getUserInfo().getImg_url());
//        if (UserUtils.getInstance().getUserInfo().getUser_name().equals("登录/注册")) {
//            signRegisterText.setText(UserUtils.getInstance().getUserInfo().getUser_name());
//        }
  /*
          第一步:初始化help
         */
        helper = new KfStartHelper((AppCompatActivity) getActivity());

        inits();

        bannerMy.post(new Runnable() {
            @Override
            public void run() {
                bannerMy.getWidth();
                double f = Float.valueOf(bannerMy.getWidth() + "") / (4.2);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(bannerMy.getWidth(), (int) f);
                bannerMy.setLayoutParams(layoutParams);
            }
        });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getPersonalInformation();//下载数据

            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });

    }


    @Override
    protected void loadData() {
        presenter.getPersonalInformation();//下载数据

    }


    @OnClick({R.id.cat_avatar, R.id.sign_register_text, R.id.my_seeting_btn, R.id.youhuiquan_layout, R.id.all_order_text, R.id.daifukuan_layout, R.id.daifahuo_layout, R.id.daishouhuo_layout,
            R.id.me_realname, R.id.daipingjia_layout, R.id.tuihuanhuo_layout, R.id.me_shoucang, R.id.me_address, R.id.me_wenti, R.id.me_guanyu, R.id.me_jifen, R.id.me_share, R.id.my_member_add,
            R.id.my_browse_layout, R.id.my_jifen_layout, R.id.me_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cat_avatar://头像s
            case R.id.sign_register_text://登录注册
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    EditDataActivity.startSelf(getContext());
                } else {
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);

                }
                break;
            case R.id.my_seeting_btn://设置
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    SettingActivity.startSelf(getContext(), personalInformationBean.getUser().getIs_wechat(), personalInformationBean.getUser().getMobile(), personalInformationBean.getUser().getWechat_nickname());
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.youhuiquan_layout://优惠券
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    CouponActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.all_order_text://查看全部订单
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 0);
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daifukuan_layout://代付款
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 1);
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daifahuo_layout://待发货
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 2);
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daishouhuo_layout://待收货
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 3);
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daipingjia_layout://待评价
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 4);
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.tuihuanhuo_layout://退换货
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    AllOrderActivity.startSelf(getContext(), 4);
                    ApplyRefundListActivity.startSelf(getActivity());//进入列表页面
//                    Toast.makeText(getActivity(), "开发中，敬请期待.....", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.me_shoucang://我的收藏
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    CollectionActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.me_address://收货地址
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AddressActivity.startSelf(getActivity(), "1");
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_realname://实名认证
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    RealNameActivity.startSelf(getActivity(), "1");
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_wenti://问题反馈
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    ProblemFeedbackActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_guanyu://关于我们--我的客服
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    AboutUsActivity.startSelf(getContext());
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

                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.my_jifen_layout://积分
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    Toast.makeText(getActivity(), "开发中，敬请期待", Toast.LENGTH_SHORT).show();

                    PointsMallListActivity.startSelf(getContext());

                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }


                break;
            case R.id.me_jifen://积分商城
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    Toast.makeText(getActivity(), "开发中，敬请期待", Toast.LENGTH_SHORT).show();

                    PointsMallActivity.startSelf(getContext());

                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.me_share://分享
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
            case R.id.me_msg://消息盒子
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MsggingBoxActivity.startSelf(getContext());//消息盒子
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.my_member_add://立即开通会员
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    MemberActivity.startSelf(getContext(), "1");//会员购买入口
                    MemberActivity_1.startSelf(getContext(), "1");//会员购买入口(新)
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.my_browse_layout://浏览历史
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    BrowseActivity.startSelf(getActivity());
                } else {
//                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
        }
    }

    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn, wx_qrcode_btn, copy_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//好友
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//朋友圈
        wx_qrcode_btn = customView.findViewById(R.id.wx_qrcode_btn);//微信二维码
        copy_btn = customView.findViewById(R.id.copy_btn);
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

        String url;
        if (!UserUtils.getInstance().getUserId().equals("")) {//已登录
            webpage.webpageUrl = "http://h5.vinnlook.com/share/index.html?code=" + UserUtils.getInstance().getUserId();
            url = "http://h5.vinnlook.com/share/index.html?code=" + UserUtils.getInstance().getUserId();
        } else {//未登录
            webpage.webpageUrl = "http://h5.vinnlook.com/share/index.html?code=";
            url = "http://h5.vinnlook.com/share/index.html?code=";
        }


        //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "vinnlook美瞳商城 ";
        msg.description = "一家专业销售隐形眼镜彩片的电商购物平台，涵盖众多知名隐形眼镜品牌，款式全、数量多、物美价廉，秉承“正品、价优、快速”的理念，足不出户即可享受方便、快捷的购物体验。";
        Bitmap thumbBmp = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.ic_launcher_foreground);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //二维码图片
                try {
                    bitmaps = BitmapFactory.decodeStream(new URL(personalInformationBean.getShare_code()).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//         Bitmap thumbBmp = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.jeal_shear_logo);
        Log.e("分享", "==thumbBmp==" + thumbBmp);
        msg.thumbData = bmpToByteArray1(thumbBmp, true);

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


        //微信好友
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        //微信二维码
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
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(getActivity(), "复制成功", Toast.LENGTH_LONG).show();

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
        ImageLoader.image(getActivity(), core_img, personalInformationBean.getShare_code());


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
                int permission = ContextCompat.checkSelfPermission(getActivity(),
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, 1);
                }
                try {
                    //创建savephoto类保存图片
                    SavePhoto savePhoto = new SavePhoto(getActivity());
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

    public static byte[] bmpToByteArray1(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.PNG, 10,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                //F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }


    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }


    //其他登录-接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginDataEvent event) {

//        ImageLoader.userIcon(getActivity(), catAvatar, event.getUserInfoBean().getImg_url());
//        if (event.getUserInfoBean().getUser_name().equals("")) {
//            signRegisterText.setText("用户名为空");
//        } else {
//            signRegisterText.setText(event.getUserInfoBean().getUser_name());
//        }
//        daifukuanText.setText(event.getUserInfoBean().getObligationcount());
//        daishouhuoText.setText(event.getUserInfoBean().getReceivingcount());
//        daipingjiaText.setText(event.getUserInfoBean().getCommentcount());
//        tuihuanhuoText.setText(event.getUserInfoBean().getReplacementcount());


    }


    /**
     * @Description:获取订单数量及用户信息成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getPersonalInformationSuccess(int code, PersonalInformationBean data) {
        catAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
//        ImageLoader.userIcon(getActivity(), catAvatar, data.getUser().getImg_url());
        smartRefreshLayout.finishRefresh();
        personalInformationBean = data;

        if (data.getUser().getUser_id().equals("")) {
            signRegisterText.setText("登录/注册");
            daifukuanText.setText("0");
            daifahuoText.setText("0");
            daishouhuoText.setText("0");
            daipingjiaText.setText("0");
            tuihuanhuoText.setText("0");
            xiaoxiText.setText("0");
            daifukuanText.setVisibility(View.GONE);
            daifahuoText.setVisibility(View.GONE);
            daishouhuoText.setVisibility(View.GONE);
            daipingjiaText.setVisibility(View.GONE);
            tuihuanhuoText.setVisibility(View.GONE);
            xiaoxiText.setVisibility(View.GONE);
            myMemberLevel.setVisibility(View.GONE);
            myCollectCount.setText("0");//收藏数量
            myPointsCount.setText("0");//积分数量
            myBrowseCount.setText("0");//浏览数量
            myDiscountCount.setText("0");//优惠券数量
            catAvatar.setImageResource(R.mipmap.icon_heart);
        } else {
            if (data.getUser().getMobile().equals("")) {
//                ModifyPhoneActivity.startSelf(getActivity(), "2");
                UserUtils.getInstance().logout();
            }
            if (data.getUser().getImg_url().equals("")) {
                catAvatar.setImageResource(R.mipmap.icon_heart);
            } else {
                ImageLoader.userIcon(getActivity(), catAvatar, data.getUser().getImg_url());
            }

            Log.e("获取订单数量及用户信息成功", "==code=123=" + code);
            if (data.getUser().getUser_name().equals("")) {
                signRegisterText.setText("登录/注册");

            } else {
                signRegisterText.setText(data.getUser().getUser_name());
            }

            if (data.getUser().getIs_member() == 1) {//会员
                myMemberDate.setText(data.getUser().getMember_end_time() + "到期 ");
                myMemberLevel.setVisibility(View.VISIBLE);
                myMemberAdd.setText("查看更多 >");
            } else if (data.getUser().getIs_member() == 0) {//不是会员
                myMemberDate.setText("加入会员 领取会员红包");
                myMemberLevel.setVisibility(View.GONE);
                myMemberAdd.setText("立即开通");
            }
            //待发货
            if (Integer.parseInt(data.getOrder_count().getPendingcount()) > 0) {
                daifahuoText.setVisibility(View.VISIBLE);
                daifahuoText.setText(data.getOrder_count().getPendingcount());
            } else {
                daifahuoText.setVisibility(View.GONE);
            }
            //待付款
            if (Integer.parseInt(data.getOrder_count().getObligationcount()) > 0) {
                daifukuanText.setVisibility(View.VISIBLE);
                daifukuanText.setText(data.getOrder_count().getObligationcount());
            } else {
                daifukuanText.setVisibility(View.GONE);
            }
            //待收货
            if (Integer.parseInt(data.getOrder_count().getReceivingcount()) > 0) {
                daishouhuoText.setVisibility(View.VISIBLE);
                daishouhuoText.setText(data.getOrder_count().getReceivingcount());//待收货
            } else {
                daishouhuoText.setVisibility(View.GONE);
            }
            //待评价
            if (Integer.parseInt(data.getOrder_count().getCommentcount()) > 0) {
                daipingjiaText.setVisibility(View.VISIBLE);
                daipingjiaText.setText(data.getOrder_count().getCommentcount());//待评价
            } else {
                daipingjiaText.setVisibility(View.GONE);
            }
            //退换货
            if (Integer.parseInt(data.getOrder_count().getReplacementcount()) > 0) {
                tuihuanhuoText.setVisibility(View.VISIBLE);
                tuihuanhuoText.setText(data.getOrder_count().getReplacementcount());//退换货
            } else {
                tuihuanhuoText.setVisibility(View.GONE);
            }

            initImkf(xiaoxiText, data.getUser().getUnread_count());

            myCollectCount.setText(data.getCollect_count());//收藏数量
            myPointsCount.setText(data.getPoints());//积分数量
            myBrowseCount.setText(data.getBrowse_count());//浏览数量
            myDiscountCount.setText(data.getDiscount_count());//优惠券数量
        }

        bannerImage = data.getBanner();//轮播

        BannerImgAdapter2 bannerImgAdapter = new BannerImgAdapter2(getActivity(), gatBannetData());
        bannerMy.setAdapter(bannerImgAdapter);
//        bannerMy.setIndicator(new CircleIndicator(getActivity()));
        bannerMy.start();

        bannerMy.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (bannerImage.get(position).getType().equals("1")) {//1：商品详情
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());

                } else if (bannerImage.get(position).getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id());//进入活动详情页面
                } else if (bannerImage.get(position).getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), bannerImage.get(position).getList().getUrl());

                } else if (bannerImage.get(position).getType().equals("4")) {//4：文字

                } else if (bannerImage.get(position).getType().equals("5")) {//5：广告商品列表
//                    RecommendActivity_1.startSelf(getContext(), "", bannerImage.get(position).getList().getId());//进入活动详情页面
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", bannerImage.get(position).getList().getId());
                } else if (bannerImage.get(position).getType().equals("6")) {//6：会员入口
//                    MemberActivity.startSelf(getContext(), "2");//
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                }

            }
        });

        Log.e("获取订单数量及用户信息成功", "==getWaybillList===size===" + personalInformationBean.getWaybillList().size());
        if (personalInformationBean.getWaybillList().size() > 0) {
            wuliuLayout.setVisibility(View.VISIBLE);
            wuliuLine.setVisibility(View.VISIBLE);
            FlipperAdapter adapter = new FlipperAdapter(getActivity());
            adapter.setList(data.getWaybillList());
            viewFlipper.setAdapter(adapter);
            handler.sendEmptyMessageDelayed(1, 2000);
        } else {
            handler.removeMessages(1);
            wuliuLayout.setVisibility(View.GONE);
            wuliuLine.setVisibility(View.GONE);
        }


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                wuliuTime.setText(personalInformationBean.getWaybillList().get(viewFlipper.getDisplayedChild()).getInfo().getAcceptTime());
                handler.removeMessages(1);
                handler.sendEmptyMessageDelayed(1, 2000);
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
        Log.e("gatBannetData", "===strings===" + strings);
        return strings;
    }

    /**
     * @Description:获取订单数量及用户信息失败
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getPersonalInformationFail(int code, String msg) {
        Log.e("获取订单数量及用户信息失败", "==code==" + code);
        smartRefreshLayout.finishRefresh();
        if (code == 4001) {//未登录
            dismissLoadingDialog();
            signRegisterText.setText("登录/注册");
            daifukuanText.setText("0");
            daifahuoText.setText("0");
            daishouhuoText.setText("0");
            daipingjiaText.setText("0");
            tuihuanhuoText.setText("0");
            xiaoxiText.setText("0");

            daifukuanText.setVisibility(View.GONE);
            daifahuoText.setVisibility(View.GONE);
            daishouhuoText.setVisibility(View.GONE);
            daipingjiaText.setVisibility(View.GONE);
            tuihuanhuoText.setVisibility(View.GONE);
            xiaoxiText.setVisibility(View.GONE);
            myMemberLevel.setVisibility(View.GONE);

            myCollectCount.setText("0");//收藏数量
            myPointsCount.setText("0");//积分数量
            myBrowseCount.setText("0");//浏览数量
            myDiscountCount.setText("0");//优惠券数量


            catAvatar.setImageResource(R.mipmap.icon_heart);
//            catAvatar.setBackground(getResources().getDrawable(R.mipmap.ic_defult_avater));
//            catAvatar.setBackgroundResource(R.mipmap.ic_defult_avater);
        }


    }

    /**
     * @Description:一键登录成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginSuccess(int code, UserInfoBean data) {
        Log.e("一键登录成功", "dat=======" + data.getUser_id());
        UserUtils.getInstance().login(data);
        // 用户登录埋点
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
//        new LoginDataEvent(data).post();
//        new MainHomeActivityEvent("2").post();
//        presenter.getPersonalInformation();//下载
        mAlicomAuthHelper.quitLoginPage();
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


    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostWechatEvent event) {
        Log.e("MyFragment", "==登录回调==");
        presenter.getWechatLogin(event.getOpenId(), event.getNickName(), event.getHeadUrl());
    }

    /**
     * @Description:微信登录成功
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getCheckCodeSuccess(int code, UserInfoBean data) {
        Log.e("getCheckCodeSuccess", "==UserInfoBean===" + data);
        Log.e("getCheckCodeSuccess", "==getUser_id===" + data.getUser_id());
        Log.e("getCheckCodeSuccess", "==getUser_name===" + data.getUser_name());
        Log.e("getCheckCodeSuccess", "==getImg_url===" + data.getImg_url());
        Log.e("getCheckCodeSuccess", "==getIs_member===" + data.getIs_member());

        // 用户登录埋点
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
        Log.e("微信登录成功", "==手机号===" + data.getMobile());
        CacheActivity.finishActivity();
        UserUtils.getInstance().login(data);

        if (!data.getMobile().equals("") && data.getMobile() != null) {//绑定手机号
            presenter.getPersonalInformation();//下载
        } else {//未绑定手机号
            ModifyPhoneActivity.startSelf(getActivity(), "2");
        }

//        CacheActivity.finishActivity();
        mAlicomAuthHelper.quitLoginPage();


    }

    /**
     * Description:微信登录失败
     * Time:2020/5/12 13:35
     * Author:pk
     */
    @Override
    public void getCheckCodeFail(int code, String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPersonalInformation();//下载数据
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MyPersonalJudgeEvent event) {
        Log.e("MyFragment", "=首页传递消息==");
        int getJupdg = event.getJupdg();
//        if (getJupdg == 1) {
//            presenter.getAppUpdate();//下载时间
        presenter.getPersonalInformation();//下载时间
//        }
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
        int logBtnOffset_1 = dialogHeight * 2;
        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
                .setLayout(R.layout.login_item_layou_1, new AbstractPnsViewDelegate() {
                    @Override
                    public void onViewCreated(View view) {
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
                                SPUtils.getInstance().save("logss", "1");
                            }
                        });


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
//                                mAlicomAuthHelper.hideLoginLoading();
//                                login();//微信登陆
//                                mAlicomAuthHelper.quitLoginPage();
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


        int logBtnOffset = dialogHeight / 2;


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
                .setPageBackgroundPath("login_bg")//背景图
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
        int screenHeightDp = AppUtils.px2dp(getActivity(), AppUtils.getPhoneHeightPixels(getActivity()));
        int screenWidthDp = AppUtils.px2dp(getActivity(), AppUtils.getPhoneWidthPixels(getActivity()));
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
     * 微信分享(三个步骤)
     * 1.微信授权登录
     * 2.根据授权登录code 获取该用户token
     * 3.根据token获取用户资料
     */
    public void share() {
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
            mAlicomAuthHelper.quitLoginPage();

        }

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
//        final KfStartHelper helper = new KfStartHelper((AppCompatActivity) getActivity());
        /*
          商品信息实例，若有需要，请参照此方法；
         */
//        handleCardInfo(helper);
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
/**
 * 传递orderid和头像路径
 */
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
     * 初始化7陌
     *
     * @param xiaoxiText
     * @param unread_count
     */
    private void initImkf(TextView xiaoxiText, String unread_count) {
        //首先判断是否在此之前初始化过sdk,获取消息参数需userid等参数
        if (MoorUtils.isInitForUnread(getActivity())) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acounts) {

                    Log.e("MyFragment", "==acounts==" + acounts);
                    Log.e("MyFragment", "==unread_count==" + unread_count);
                    int acount = Integer.parseInt(unread_count) + acounts;
                    Log.e("MyFragment", "==acount==" + acount);

                    //消息盒子
                    if (acount > 0) {
                        xiaoxiText.setVisibility(View.VISIBLE);
                        xiaoxiText.setText(acount + "");//消息数量

                    } else {
                        xiaoxiText.setVisibility(View.GONE);
                    }

                }
            });
        } else {
            //未初始化，消息当然为 ：0
//            Toast.makeText(getActivity(), "还没初始化", Toast.LENGTH_SHORT).show();
        }
    }


}
