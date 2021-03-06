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
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundLinearLayout;
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
import com.vinnlook.www.surface.activity.CollectionTotalActivity;
import com.vinnlook.www.surface.activity.CouponActivity;
import com.vinnlook.www.surface.activity.EditDataActivity;
import com.vinnlook.www.surface.activity.GroupListActivity;
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
import com.vinnlook.www.surface.activity.WebActivity2;
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
 * @Description:??????
 * @Time:2020/4/1 17:02
 * @Author:pk
 */
public class MyFragment extends BaseFragment<MyFragmentPresenter> implements MyFragmentView {

    @BindView(R.id.cat_avatar)
    RoundedImageView catAvatar;//??????
    @BindView(R.id.sign_register_text)
    TextView signRegisterText;//???????????????
    @BindView(R.id.youhuiquan_layout)
    LinearLayout youhuiquanLayout;//?????????
    @BindView(R.id.all_order_text)
    TextView allOrderText;//??????????????????
    @BindView(R.id.daifukuan_layout)
    LinearLayout daifukuanLayout;//?????????
    @BindView(R.id.daifahuo_layout)
    LinearLayout daifahuoLayout;//?????????
    @BindView(R.id.daishouhuo_layout)
    LinearLayout daishouhuoLayout;//?????????
    @BindView(R.id.daipingjia_layout)
    LinearLayout daipingjiaLayout;//?????????
    @BindView(R.id.tuihuanhuo_layout)
    LinearLayout tuihuanhuoLayout;//?????????
    @BindView(R.id.me_shoucang)
    LinearLayout meShoucang;//????????????
    @BindView(R.id.me_address)
    LinearLayout meAddress;//????????????
    @BindView(R.id.me_realname)
    LinearLayout meRealname;//????????????
    @BindView(R.id.me_wenti)
    LinearLayout meWenti;//????????????
    @BindView(R.id.me_guanyu)
    LinearLayout meGuanyu;//????????????
    @BindView(R.id.me_share)
    LinearLayout meShare;//??????

    @BindView(R.id.my_member_add)
    TextView myMemberAdd;//????????????
    @BindView(R.id.my_member_date)
    TextView myMemberDate;//????????????

    UserInfoBean userInfoBean;
    @BindView(R.id.daifukuan_text)
    TextView daifukuanText;//???????????????
    @BindView(R.id.daifahuo_text)
    TextView daifahuoText;//???????????????
    @BindView(R.id.daishouhuo_text)
    TextView daishouhuoText;//???????????????
    @BindView(R.id.daipingjia_text)
    TextView daipingjiaText;//???????????????
    @BindView(R.id.tuihuanhuo_text)
    TextView tuihuanhuoText;//???????????????
    @BindView(R.id.my_collect_count)
    TextView myCollectCount;//????????????
    @BindView(R.id.my_points_count)
    TextView myPointsCount;//????????????
    @BindView(R.id.my_browse_count)
    TextView myBrowseCount;//????????????
    @BindView(R.id.my_discount_count)
    TextView myDiscountCount;//???????????????
    @BindView(R.id.my_browse_layout)
    LinearLayout myBrowseLayout;//????????????
    @BindView(R.id.my_member_level)
    RoundedImageView myMemberLevel;//??????????????????
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

    Bitmap bitmaps;//???????????????
    public PopupWindow popupwindow1;
    @BindView(R.id.my_group_all_btn)
    RelativeLayout myGroupAllBtn;
    @BindView(R.id.my_group_1_num)
    TextView myGroup1Num;
    @BindView(R.id.my_group_1_btn)
    LinearLayout myGroup1Btn;
    @BindView(R.id.my_group_2_num)
    TextView myGroup2Num;
    @BindView(R.id.my_group_2_btn)
    LinearLayout myGroup2Btn;
    @BindView(R.id.my_group_3_num)
    TextView myGroup3Num;
    @BindView(R.id.my_group_3_btn)
    LinearLayout myGroup3Btn;
    @BindView(R.id.my_guonggao)
    ViewFlipper myGuonggao;
    @BindView(R.id.my_guonggao_layout)
    RoundLinearLayout myGuonggaoLayout;
    @BindView(R.id.my_guonggao_rv)
    RecyclerView myGuonggaoRv;


    private int mScreenWidthDp;
    private int mScreenHeightDp;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

//    private IWXAPI wxAPI;

    public PopupWindow popupwindow;
    List<PersonalInformationBean.BannerBean> bannerImage;//??????

    PersonalInformationBean personalInformationBean;

    KfStartHelper helper;
    List<String> gonggaoList2;
    List<String> gonggaoList3;

    LinearLayoutManager linearLayoutManager;
    List<PersonalInformationBean.ArticleBean> getArticle;
    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_1;
    }

    @Override
    protected MyFragmentPresenter initPresenter() {
        return new MyFragmentPresenter();
    }
    private Thread threads;


    @Override
    protected void loadData() {
        presenter.getPersonalInformation();//????????????

    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

        ImageLoader.userIcon(getActivity(), catAvatar, UserUtils.getInstance().getUserInfo().getImg_url());
//        if (UserUtils.getInstance().getUserInfo().getUser_name().equals("??????/??????")) {
//            signRegisterText.setText(UserUtils.getInstance().getUserInfo().getUser_name());
//        }
  /*
          ?????????:?????????help
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

        //????????????
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getPersonalInformation();//????????????

            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });


    }

    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn, wx_qrcode_btn, copy_btn;

        TextView share_cancel_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//??????
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//?????????
        wx_qrcode_btn = customView.findViewById(R.id.wx_qrcode_btn);//???????????????
        copy_btn = customView.findViewById(R.id.copy_btn);
        share_cancel_btn = customView.findViewById(R.id.share_cancel_btn);//??????

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

        //???????????????WXWebpageObject?????????url
        WXWebpageObject webpage = new WXWebpageObject();

        String url;
        if (!UserUtils.getInstance().getUserId().equals("")) {//?????????
            webpage.webpageUrl = "http://h5.vinnlook.com/share/index.html?code=" + UserUtils.getInstance().getUserId();
            url = "http://h5.vinnlook.com/share/index.html?code=" + UserUtils.getInstance().getUserId();
        } else {//?????????
            webpage.webpageUrl = "http://h5.vinnlook.com/share/index.html?code=";
            url = "http://h5.vinnlook.com/share/index.html?code=";
        }


        //??? WXWebpageObject ????????????????????? WXMediaMessage ??????
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "vinnlook???????????? ";
        msg.description = "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????";
        Bitmap thumbBmp = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.ic_launcher_foreground);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //???????????????
                try {
                    bitmaps = BitmapFactory.decodeStream(new URL(personalInformationBean.getShare_code()).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//         Bitmap thumbBmp = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.jeal_shear_logo);
        Log.e("??????", "==thumbBmp==" + thumbBmp);
        msg.thumbData = bmpToByteArray1(thumbBmp, true);

        //????????????Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


        //????????????
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_LONG).show();

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
        ImageLoader.image(getActivity(), core_img, personalInformationBean.getShare_code());


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
                int permission = ContextCompat.checkSelfPermission(getActivity(),
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // ???????????????????????????????????????????????????????????????
                    ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, 1);
                }
                try {
                    //??????savephoto???????????????
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


    //????????????-????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginDataEvent event) {

//        ImageLoader.userIcon(getActivity(), catAvatar, event.getUserInfoBean().getImg_url());
//        if (event.getUserInfoBean().getUser_name().equals("")) {
//            signRegisterText.setText("???????????????");
//        } else {
//            signRegisterText.setText(event.getUserInfoBean().getUser_name());
//        }
//        daifukuanText.setText(event.getUserInfoBean().getObligationcount());
//        daishouhuoText.setText(event.getUserInfoBean().getReceivingcount());
//        daipingjiaText.setText(event.getUserInfoBean().getCommentcount());
//        tuihuanhuoText.setText(event.getUserInfoBean().getReplacementcount());


    }

    @OnClick({R.id.cat_avatar, R.id.sign_register_text, R.id.my_seeting_btn, R.id.youhuiquan_layout, R.id.all_order_text, R.id.daifukuan_layout, R.id.daifahuo_layout, R.id.daishouhuo_layout,
            R.id.me_realname, R.id.daipingjia_layout, R.id.tuihuanhuo_layout, R.id.me_shoucang, R.id.me_address, R.id.me_wenti, R.id.me_guanyu, R.id.me_jifen, R.id.me_share, R.id.my_member_add,
            R.id.my_browse_layout, R.id.my_jifen_layout, R.id.me_msg, R.id.my_group_all_btn, R.id.my_group_1_btn, R.id.my_group_2_btn, R.id.my_group_3_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cat_avatar://??????s
            case R.id.sign_register_text://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    EditDataActivity.startSelf(getContext());
                } else {
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);

                }
                break;
            case R.id.my_seeting_btn://??????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    SettingActivity.startSelf(getContext(), personalInformationBean.getUser().getIs_wechat(), personalInformationBean.getUser().getMobile(), personalInformationBean.getUser().getWechat_nickname());
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.youhuiquan_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    CouponActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.all_order_text://??????????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 0);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daifukuan_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 1);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daifahuo_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 2);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daishouhuo_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 3);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.daipingjia_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AllOrderActivity.startSelf(getContext(), 4);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.tuihuanhuo_layout://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    AllOrderActivity.startSelf(getContext(), 4);
                    ApplyRefundListActivity.startSelf(getActivity());//??????????????????
//                    Toast.makeText(getActivity(), "????????????????????????.....", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.me_shoucang://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    CollectionActivity.startSelf(getContext());
                    CollectionTotalActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.me_address://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    AddressActivity.startSelf(getActivity(), "1");
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_realname://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    RealNameActivity.startSelf(getActivity(), "1");
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_wenti://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    ProblemFeedbackActivity.startSelf(getContext());
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.me_guanyu://????????????--????????????
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
                                        Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailed() {
                                }
                            });

                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.my_jifen_layout://??????
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    Toast.makeText(getActivity(), "????????????????????????", Toast.LENGTH_SHORT).show();

                    PointsMallListActivity.startSelf(getContext());

                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }


                break;
            case R.id.me_jifen://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    Toast.makeText(getActivity(), "????????????????????????", Toast.LENGTH_SHORT).show();

                    PointsMallActivity.startSelf(getContext());

                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.me_share://??????
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
            case R.id.me_msg://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MsggingBoxActivity.startSelf(getContext());//????????????
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }

                break;
            case R.id.my_member_add://??????????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                    MemberActivity.startSelf(getContext(), "1");//??????????????????
                    MemberActivity_1.startSelf(getContext(), "1");//??????????????????(???)
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.my_browse_layout://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    BrowseActivity.startSelf(getActivity());
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

            case R.id.my_group_all_btn://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    GroupListActivity.startSelf(getActivity(), 0);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.my_group_1_btn://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    GroupListActivity.startSelf(getActivity(), 1);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.my_group_2_btn://?????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    GroupListActivity.startSelf(getActivity(), 2);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;
            case R.id.my_group_3_btn://????????????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    GroupListActivity.startSelf(getActivity(), 3);
                } else {
//                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    showLoadingDialog();
                    mAlicomAuthHelper.getLoginToken(getActivity(), 0);
                }
                break;

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
     * @Description:???????????????????????????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getPersonalInformationFail(int code, String msg) {
        Log.e("???????????????????????????????????????", "==code==" + code);
        smartRefreshLayout.finishRefresh();
        if (code == 4001) {//?????????
            dismissLoadingDialog();
            signRegisterText.setText("??????/??????");
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

            myCollectCount.setText("0");//????????????
            myPointsCount.setText("0");//????????????
            myBrowseCount.setText("0");//????????????
            myDiscountCount.setText("0");//???????????????


            catAvatar.setImageResource(R.mipmap.icon_heart);
//            catAvatar.setBackground(getResources().getDrawable(R.mipmap.ic_defult_avater));
//            catAvatar.setBackgroundResource(R.mipmap.ic_defult_avater);
        }


    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/12 13:35
     * @Author:pk
     */
    @Override
    public void getMobileLoginSuccess(int code, UserInfoBean data) {
        Log.e("??????????????????", "dat=======" + data.getUser_id());
        UserUtils.getInstance().login(data);
        // ??????????????????
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
//        new LoginDataEvent(data).post();
//        new MainHomeActivityEvent("2").post();
//        presenter.getPersonalInformation();//??????
        mAlicomAuthHelper.quitLoginPage();
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


    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostWechatEvent event) {
        Log.e("MyFragment", "==????????????==");
        presenter.getWechatLogin(event.getOpenId(), event.getNickName(), event.getHeadUrl());
    }

    /**
     * @Description:??????????????????
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

        // ??????????????????
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
        Log.e("??????????????????", "==?????????===" + data.getMobile());
        CacheActivity.finishActivity();
        UserUtils.getInstance().login(data);

        if (!data.getMobile().equals("") && data.getMobile() != null) {//???????????????
            presenter.getPersonalInformation();//??????
        } else {//??????????????????
            ModifyPhoneActivity.startSelf(getActivity(), "2");
        }

//        CacheActivity.finishActivity();
        mAlicomAuthHelper.quitLoginPage();


    }

    /**
     * Description:??????????????????
     * Time:2020/5/12 13:35
     * Author:pk
     */
    @Override
    public void getCheckCodeFail(int code, String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPersonalInformation();//????????????
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MyPersonalJudgeEvent event) {
        Log.e("MyFragment", "=??????????????????==");
        int getJupdg = event.getJupdg();
//        if (getJupdg == 1) {
//            presenter.getAppUpdate();//????????????
        presenter.getPersonalInformation();//????????????
//        }
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
        int logBtnOffset_1 = dialogHeight * 2;
        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
                .setLayout(R.layout.login_item_layou_1, new AbstractPnsViewDelegate() {
                    @Override
                    public void onViewCreated(View view) {
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
//                                login();//????????????
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
                .setPageBackgroundPath("login_bg")//?????????
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
     * ????????????(????????????)
     * 1.??????????????????
     * 2.??????????????????code ???????????????token
     * 3.??????token??????????????????
     */
    public void share() {
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
            mAlicomAuthHelper.quitLoginPage();

        }

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
//        final KfStartHelper helper = new KfStartHelper((AppCompatActivity) getActivity());
        /*
          ?????????????????????????????????????????????????????????
         */
//        handleCardInfo(helper);
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
/**
 * ??????orderid???????????????
 */
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
     * ?????????7???
     *
     * @param xiaoxiText
     * @param unread_count
     */
    private void initImkf(TextView xiaoxiText, String unread_count) {
        //??????????????????????????????????????????sdk,?????????????????????userid?????????
        if (MoorUtils.isInitForUnread(getActivity())) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acounts) {

                    Log.e("MyFragment", "==acounts==" + acounts);
                    Log.e("MyFragment", "==unread_count==" + unread_count);
                    int acount = Integer.parseInt(unread_count) + acounts;
                    Log.e("MyFragment", "==acount==" + acount);

                    //????????????
                    if (acount > 0) {
                        xiaoxiText.setVisibility(View.VISIBLE);
                        xiaoxiText.setText(acount + "");//????????????

                    } else {
                        xiaoxiText.setVisibility(View.GONE);
                    }

                }
            });
        } else {
            //?????????????????????????????? ???0
//            Toast.makeText(getActivity(), "???????????????", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Description:???????????????????????????????????????
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
            signRegisterText.setText("??????/??????");
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
            myCollectCount.setText("0");//????????????
            myPointsCount.setText("0");//????????????
            myBrowseCount.setText("0");//????????????
            myDiscountCount.setText("0");//???????????????
            catAvatar.setImageResource(R.mipmap.icon_heart);

            myGroup1Num.setVisibility(View.GONE);
            myGroup2Num.setVisibility(View.GONE);
            myGroup3Num.setVisibility(View.GONE);
        } else {
            getArticle = data.getArticle();
            if (data.getArticle().size() > 0 && data.getArticle() != null && !data.getArticle().equals("")) {
                myGuonggaoLayout.setVisibility(View.VISIBLE);
            } else {
                myGuonggaoLayout.setVisibility(View.GONE);
            }

            linearLayoutManager = new LinearLayoutManager(this.getContext());
            myGuonggaoRv.setLayoutManager(linearLayoutManager);
            new PagerSnapHelper().attachToRecyclerView(myGuonggaoRv); //??????????????????
            if (data.getArticle().size() > 0) {
                myGuonggaoRv.setAdapter(new MAdapter(data.getArticle()));
                startRoll();
            }

            gonggaoList2 = new ArrayList<>();
            gonggaoList3 = new ArrayList<>();
            for (int i = 0; i < data.getArticle().size(); i++) {
                gonggaoList2.add(data.getArticle().get(i).getTitle());
                gonggaoList3.add(data.getArticle().get(i).getInfo_url());
            }
            for (int i = 0; i < gonggaoList2.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10, 10, 10, 10);
                TextView textView1 = new TextView(getActivity());
                textView1.setText(gonggaoList2.get(i));
                linearLayout.addView(textView1);
                myGuonggao.addView(linearLayout);
            }

//            myGuonggaoLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    WebActivity.startSelf(getActivity(), gonggaoList3.get(viewFlipper.getDisplayedChild()));
//                }
//            });
            Log.e("??????", "????????????=======" + gonggaoList2);
            Log.e("??????", "????????????=======" + gonggaoList3);


            if (data.getUser().getMobile().equals("")) {
//                ModifyPhoneActivity.startSelf(getActivity(), "2");
                UserUtils.getInstance().logout();
            }
            if (data.getUser().getImg_url().equals("")) {
                catAvatar.setImageResource(R.mipmap.icon_heart);
            } else {
                ImageLoader.userIcon(getActivity(), catAvatar, data.getUser().getImg_url());
            }

            Log.e("???????????????????????????????????????", "==code=123=" + code);
            if (data.getUser().getUser_name().equals("")) {
                signRegisterText.setText("");

            } else {
                signRegisterText.setText(data.getUser().getUser_name());
            }

            if (data.getUser().getIs_member() == 1) {//??????
                myMemberDate.setText(data.getUser().getMember_end_time() + "?????? ");
                myMemberLevel.setVisibility(View.VISIBLE);
                myMemberAdd.setText("???????????? >");
            } else if (data.getUser().getIs_member() == 0) {//????????????
                myMemberDate.setText("???????????? ??????????????????");
                myMemberLevel.setVisibility(View.GONE);
                myMemberAdd.setText("????????????");
            }
            //?????????
            if (Integer.parseInt(data.getOrder_count().getPendingcount()) > 0) {
                daifahuoText.setVisibility(View.VISIBLE);
                daifahuoText.setText(data.getOrder_count().getPendingcount());
            } else {
                daifahuoText.setVisibility(View.GONE);
            }
            //?????????
            if (Integer.parseInt(data.getOrder_count().getObligationcount()) > 0) {
                daifukuanText.setVisibility(View.VISIBLE);
                daifukuanText.setText(data.getOrder_count().getObligationcount());
            } else {
                daifukuanText.setVisibility(View.GONE);
            }
            //?????????
            if (Integer.parseInt(data.getOrder_count().getReceivingcount()) > 0) {
                daishouhuoText.setVisibility(View.VISIBLE);
                daishouhuoText.setText(data.getOrder_count().getReceivingcount());//?????????
            } else {
                daishouhuoText.setVisibility(View.GONE);
            }
            //?????????
            if (Integer.parseInt(data.getOrder_count().getCommentcount()) > 0) {
                daipingjiaText.setVisibility(View.VISIBLE);
                daipingjiaText.setText(data.getOrder_count().getCommentcount());//?????????
            } else {
                daipingjiaText.setVisibility(View.GONE);
            }
            //?????????
            if (Integer.parseInt(data.getOrder_count().getReplacementcount()) > 0) {
                tuihuanhuoText.setVisibility(View.VISIBLE);
                tuihuanhuoText.setText(data.getOrder_count().getReplacementcount());//?????????
            } else {
                tuihuanhuoText.setVisibility(View.GONE);
            }

            //?????????
            if (data.getGroup_count().getOpen_group() > 0) {
                myGroup1Num.setVisibility(View.VISIBLE);
                myGroup1Num.setText(String.valueOf(data.getGroup_count().getOpen_group()));//?????????
            } else {
                myGroup1Num.setVisibility(View.GONE);
            }
            //?????????
            if (data.getGroup_count().getJoin_group() > 0) {
                myGroup2Num.setVisibility(View.VISIBLE);
                myGroup2Num.setText(String.valueOf(data.getGroup_count().getJoin_group()));//?????????
            } else {
                myGroup2Num.setVisibility(View.GONE);
            }
            //????????????
            if (data.getGroup_count().getFinish_group() > 0) {
                myGroup3Num.setVisibility(View.VISIBLE);
                myGroup3Num.setText(String.valueOf(data.getGroup_count().getFinish_group()));//????????????
            } else {
                myGroup3Num.setVisibility(View.GONE);
            }

            initImkf(xiaoxiText, data.getUser().getUnread_count());

            myCollectCount.setText(data.getCollect_count());//????????????
            myPointsCount.setText(data.getPoints());//????????????
            myBrowseCount.setText(data.getBrowse_count());//????????????
            myDiscountCount.setText(data.getDiscount_count());//???????????????
        }

        bannerImage = data.getBanner();//??????
        BannerImgAdapter2 bannerImgAdapter = new BannerImgAdapter2(getActivity(), gatBannetData());
        bannerMy.setAdapter(bannerImgAdapter);
//        bannerMy.setIndicator(new CircleIndicator(getActivity()));
        bannerMy.start();

        bannerMy.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                //type--1??????????????????2??????????????????3???url;4:?????????5???????????????
                if (bannerImage.get(position).getType().equals("1")) {//1???????????????
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr(), "");

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
                }

            }
        });

        Log.e("???????????????????????????????????????", "==getWaybillList===size===" + personalInformationBean.getWaybillList().size());
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

    private void startRoll() {
        threads = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                if (!threads.isInterrupted()) {
                    myGuonggaoRv.smoothScrollBy(0, dp2px(20), decelerateInterpolator);
                    //                rv.smoothScrollToPosition(++cp);
                    this.run();
                }
            }
        });
        threads.start();
    }

    public int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * getActivity().getResources().getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (threads != null) threads.interrupt();
//        handler.removeCallbacksAndMessages(null);
//        handler = null;
    }

    class MAdapter extends RecyclerView.Adapter<MAdapter.VH> {
        List<PersonalInformationBean.ArticleBean> article;

        public MAdapter(List<PersonalInformationBean.ArticleBean> articles) {
            this.article = articles;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_rv, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH viewHolder, int i) {
            viewHolder.tv.setText(article.get(i % article.size()).getTitle());
            viewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebActivity2.startSelf(getActivity(), article.get(i % article.size()).getInfo_url());
                }
            });

            myGuonggaoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebActivity2.startSelf(getActivity(), article.get(i % article.size()).getInfo_url());
                }
            });
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class VH extends RecyclerView.ViewHolder {
            TextView tv;

            public VH(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }

}
