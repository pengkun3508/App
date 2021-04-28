package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.adapter.vp.FixedFragmentPagerAdapter;
import com.dm.lib.core.dialog.TipDialog;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.DoubleBackUtils;
import com.m7.imkfsdk.utils.PickUtils;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.PreLoginResultListener;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.common.ConstantData;
import com.vinnlook.www.event.LoginDataEvent;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.event.MyPersonalJudgeEvent;
import com.vinnlook.www.event.ShopCarJudgeEvent;
import com.vinnlook.www.eventbas.LoginStateChangeEvent;
import com.vinnlook.www.surface.fragment.ClassifyFragment_1;
import com.vinnlook.www.surface.fragment.HomeFragment;
import com.vinnlook.www.surface.fragment.HomeFragment_1;
import com.vinnlook.www.surface.fragment.MyFragment;
import com.vinnlook.www.surface.fragment.ShoppingCheFragment_1;
import com.vinnlook.www.surface.mvp.presenter.MainPresenter;
import com.vinnlook.www.surface.mvp.view.MainView;
import com.vinnlook.www.utils.AppUtils;
import com.vinnlook.www.utils.UserInfoBean;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import per.goweii.percentimageview.percentimageview.PercentImageView;

import static com.vinnlook.www.utils.AppUtils.dp2px;


/**
 * @Description:首页
 * @Time:2020/4/14 9:42
 * @Author:pk
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainView, ViewPager.OnPageChangeListener {

    private static final int BAIDU_READ_PHONE_STATE = 100;
    @BindView(R.id.main_relayout)
    RelativeLayout mainRelayout;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @BindView(R.id.ll_bottom_bar_home)
    LinearLayout llBottomBarable;
    @BindView(R.id.ll_bottom_bar_my)
    LinearLayout llBottomBarMy;
    @BindView(R.id.iv_bottom_bar_home)
    PercentImageView ivBottomBarHome;
    @BindView(R.id.iv_bottom_bar_classify)
    PercentImageView ivBottomBarClassify;
    @BindView(R.id.iv_bottom_bar_organization)
    PercentImageView ivBottomBarOrganization;
    @BindView(R.id.ll_bottom_bar_organization)
    LinearLayout llBottomBarOrganization;
    @BindView(R.id.ll_bottom_bar)
    LinearLayout llBottomBar;
    @BindView(R.id.iv_bottom_bar_my)
    PercentImageView ivBottomBarMy;
    @BindView(R.id.home_text)
    TextView homeText;//首页
    @BindView(R.id.classify_text)
    TextView classifyText;//分类
    @BindView(R.id.shop_cat_text)
    TextView shopCatText;//购物车
    @BindView(R.id.me_text)
    TextView meText;//我的

    private TextView switchTV;
    private int mScreenWidthDp;
    private int mScreenHeightDp;

    private HomeFragment mHomeFragment;
    private HomeFragment_1 mHomeFragment1;
    private ClassifyFragment_1 mclassifyFragment_1;
    private ShoppingCheFragment_1 mTableFragment;
    private MyFragment mMyFragment;
    List<Fragment> fragmentList;

    private TipDialog forceOfflineDialog = null;
    private PhoneNumberAuthHelper mAlicomAuthHelper;
    private TokenResultListener mTokenListener;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 登录
     *
     * @param event LoginStateChangeEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginStateChangeEvent(LoginStateChangeEvent event) {
        if (!UserUtils.getInstance().isLogin()) {
//            viewPager.setCurrentItem(0);
        }
    }

    @Override
    public boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWindow() {
        super.initWindow();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        //处理权限
//        handlePermission();
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        Log.e("JumpActivity", "==appLinkIntent==" + appLinkIntent);
        Log.e("JumpActivity", "==appLinkAction==" + appLinkAction);
        Log.e("JumpActivity", "==appLinkData==" + appLinkData);

        if (appLinkData != null) {
            String good_id = appLinkData.getQueryParameter("good_id");//7
            String search_attr = appLinkData.getQueryParameter("search_attr");//7
            Log.e("JumpActivity", "==good_id==" + good_id);
            Log.e("JumpActivity", "==search_attr==" + search_attr);
//            MoveAbooutActivity_1.startSelf(getActivity(), good_id, search_attr);
            MoveAbooutActivity_3.startSelf(getActivity(), good_id, search_attr);
        }


//        StatusBarUtils.setStatusBarMode(getActivity(), true);
        fragmentList = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mHomeFragment1 = new HomeFragment_1();
        mclassifyFragment_1 = new ClassifyFragment_1();
        mTableFragment = new ShoppingCheFragment_1();
        mMyFragment = new MyFragment();
        fragmentList.add(mHomeFragment1);
        fragmentList.add(mclassifyFragment_1);
        fragmentList.add(mTableFragment);
        fragmentList.add(mMyFragment);
        FixedFragmentPagerAdapter mainPagerAdapter = new FixedFragmentPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.setFragmentList(fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setCurrentItem(0);
        setSelectedBottomBar(0);
        inits();

//        applicationAuthority();//申请权限


    }


    /**
     * 文件写入权限 （初始化需要写入文件，点击在线客服按钮之前需打开文件写入权限）
     * 读取设备 ID 权限 （初始化需要获取用户的设备 ID）
     */
    private void handlePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PickUtils.PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                PickUtils.PermissionUtils.requestPermissions(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PickUtils.PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(MainActivity.this, com.m7.imkfsdk.R.string.notpermession, Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                });
            }
        }
    }

    /**
     * 权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            PickUtils.PermissionUtils.onRequestPermissionsResult(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);
        }
    }

    //申请权限
    private void applicationAuthority() {
        PermissionHelper.with(this).permissions(Manifest.permission.READ_PHONE_STATE).request(new PermissionHelper.PermissionListener() {
            @Override
            public void onSuccess() {
                Log.e("==MainActivity==", "权限申请成功");
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void loadData() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (DoubleBackUtils.onKeyDown(keyCode, event)) {
            return super.onKeyDown(keyCode, event);
        } else {
            return false;
        }
    }


    @OnClick({R.id.ll_bottom_bar_home, R.id.ll_bottom_bar_classify, R.id.ll_bottom_bar_organization, R.id.ll_bottom_bar_my})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean onClickWithoutLogin(View v) {
        switch (v.getId()) {
            default:
                return false;
            case R.id.ll_bottom_bar_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_bottom_bar_classify:
                viewPager.setCurrentItem(1);
//                new ShopCarJudgeEvent(2).post();
                break;
            case R.id.ll_bottom_bar_organization:
                viewPager.setCurrentItem(2);
                new ShopCarJudgeEvent(1).post();

                break;
            case R.id.ll_bottom_bar_my:
                viewPager.setCurrentItem(3);
                new MyPersonalJudgeEvent(1).post();

                break;
        }
        return true;
    }

    @Override
    public void onClickCheckLogin(View v) {
        switch (v.getId()) {
            default:
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setSelectedBottomBar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void setSelectedBottomBar(int position) {
        if (position == 0) {//首页
            ivBottomBarHome.setImageResource(R.mipmap.home_1);
            ivBottomBarClassify.setImageResource(R.mipmap.classify);
            ivBottomBarOrganization.setImageResource(R.mipmap.shop_cat__2);
            ivBottomBarMy.setImageResource(R.mipmap.me_fill);
            homeText.setTextColor(getResources().getColor(R.color.them));
            classifyText.setTextColor(getResources().getColor(R.color.black));
            shopCatText.setTextColor(getResources().getColor(R.color.black));
            meText.setTextColor(getResources().getColor(R.color.black));
            doBottomBarIconAnim(ivBottomBarHome);
        } else if (position == 1) {//分类
            ivBottomBarHome.setImageResource(R.mipmap.home);
            ivBottomBarClassify.setImageResource(R.mipmap.classify_1);
            ivBottomBarOrganization.setImageResource(R.mipmap.shop_cat__2);
            ivBottomBarMy.setImageResource(R.mipmap.me_fill);
            homeText.setTextColor(getResources().getColor(R.color.black));
            classifyText.setTextColor(getResources().getColor(R.color.them));
            shopCatText.setTextColor(getResources().getColor(R.color.black));
            meText.setTextColor(getResources().getColor(R.color.black));
            doBottomBarIconAnim(ivBottomBarOrganization);
        } else if (position == 2) {//购物车
            ivBottomBarHome.setImageResource(R.mipmap.home);
            ivBottomBarClassify.setImageResource(R.mipmap.classify);
            ivBottomBarOrganization.setImageResource(R.mipmap.shop_cat__1);
            ivBottomBarMy.setImageResource(R.mipmap.me_fill);
            homeText.setTextColor(getResources().getColor(R.color.black));
            classifyText.setTextColor(getResources().getColor(R.color.black));
            shopCatText.setTextColor(getResources().getColor(R.color.them));
            meText.setTextColor(getResources().getColor(R.color.black));
            doBottomBarIconAnim(ivBottomBarMy);

        } else if (position == 3) {//我的
            ivBottomBarHome.setImageResource(R.mipmap.home);
            ivBottomBarClassify.setImageResource(R.mipmap.classify);
            ivBottomBarOrganization.setImageResource(R.mipmap.shop_cat__2);
            ivBottomBarMy.setImageResource(R.mipmap.me_fill_1);
            homeText.setTextColor(getResources().getColor(R.color.black));
            classifyText.setTextColor(getResources().getColor(R.color.black));
            shopCatText.setTextColor(getResources().getColor(R.color.black));
            meText.setTextColor(getResources().getColor(R.color.them));
            doBottomBarIconAnim(ivBottomBarMy);

        }
    }

    private void doBottomBarIconAnim(View target) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, "scaleX", 1.0F, 0.9F, 1.0F, 1.1F, 1.0F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, "scaleY", 1.0F, 0.9F, 1.0F, 1.1F, 1.0F);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(350);
        set.start();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 10) {
            Log.e("返回来的数据", "resultCode===" + resultCode + "requestCode===" + requestCode);
            viewPager.setCurrentItem(2);
            setSelectedBottomBar(2);


        }


    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)//跳入购物车
    public void onEventMainThread(MainShoppingEvent event) {
        String getMark = event.getMarkNum();
        Log.e("接收消息", "MainShoppingEvent===" + getMark);
        if (getMark.endsWith("10")) {
            viewPager.setCurrentItem(2);
            setSelectedBottomBar(2);
        }
    }


    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainHomeActivityEvent event) {
        String getMark = event.getMark();
        if (getMark.endsWith("1")) {//以访客身份
            viewPager.setCurrentItem(0);
            setSelectedBottomBar(0);
        } else if (getMark.endsWith("2")) {
            viewPager.setCurrentItem(3);
            setSelectedBottomBar(3);
        } else if (getMark.endsWith("4")) {
            viewPager.setCurrentItem(1);
            setSelectedBottomBar(1);
        } else if (getMark.endsWith("5")) {//从购物车进入分类
            viewPager.setCurrentItem(1);
            setSelectedBottomBar(1);
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
                MainActivity.this.runOnUiThread(new Runnable() {
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
                MainActivity.this.runOnUiThread(new Runnable() {
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
                    }
                });
            }
        };
        /*
         *   2.init AlicomAuthHelper with tokenListener
         */
        mAlicomAuthHelper = PhoneNumberAuthHelper.getInstance(this, mTokenListener);
        mAlicomAuthHelper.setAuthListener(mTokenListener);
        /*
         *   3.set debugMode when app is in debug mode, sdk will print log in debug mode
         */
        mAlicomAuthHelper.setLoggerEnable(true);//设置SDK是否开启日
        mAlicomAuthHelper.setAuthSDKInfo(Constant.LONIG_PHONE_NUMBER_KEY);


    }


    //预约取手机号
    public void getPhoneNumber() {
        mAlicomAuthHelper.accelerateLoginPage(5000, new PreLoginResultListener() {
            @Override
            public void onTokenSuccess(final String vendor) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                                    mRetTV.setText(vendor + "预取号成功！");
                        Log.e("MainActivity", "===获取手机号成功:===" + vendor);

                        ConstantData.Number_Phone = "";
//                        configLoginTokenPortDialog();
//                        showLoadingDialog();
//                        dismissLoadingDialog();
                        mAlicomAuthHelper.getLoginToken(MainActivity.this, 5000);

                    }
                });
            }

            @Override
            public void onTokenFailed(final String vendor, final String ret) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                                    mRetTV.setText(vendor + "预取号失败:\n" + ret);
                        Log.e("MainActivity", "===获取手机号失败:===" + vendor + "Msg===" + ret);
                        LoginActivity.startSelf(MainActivity.this);
                    }
                });
            }
        });

    }

    private void initDynamicView() {
        switchTV = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams mLayoutParams2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, dp2px(this, 50));
        mLayoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mLayoutParams2.setMargins(0, dp2px(this, 450), 0, 0);
//        switchTV.setText("免密登录");
        switchTV.setTextColor(0xff999999);
        switchTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.0F);
        switchTV.setLayoutParams(mLayoutParams2);
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
        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
                .setLayout(R.layout.login_item_layou_1, new AbstractPnsViewDelegate() {
                    @Override
                    public void onViewCreated(View view) {
                        findViewById(R.id.login_btn1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {//其他号码登录
                                mAlicomAuthHelper.hideLoginLoading();
                                LoginActivity.startSelf(MainActivity.this);
                                mAlicomAuthHelper.quitLoginPage();
                            }
                        });
                    }
                })
                .build());

        mAlicomAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder()
                .setLayout(R.layout.login_item_layou_2, new AbstractPnsViewDelegate() {
                    @Override
                    public void onViewCreated(View view) {
                        findViewById(R.id.login_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        findViewById(R.id.login_qq).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        findViewById(R.id.login_weibo).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                })
                .build());


        int logBtnOffset = dialogHeight / 2;


        mAlicomAuthHelper.setAuthUIConfig(new AuthUIConfig.Builder()
//                .setAppPrivacyOne("《自定义隐私协议》", "https://www.baidu.com")
                .setAppPrivacyColor(Color.BLACK, Color.parseColor("#A08FBB"))
                .setAppPrivacyOne("《隐私政策》", "http://shop.jealook.com/v1/html/article-info?id=117")
                .setAppPrivacyTwo("《用户协议》", "http://shop.jealook.com/v1/html/article-info?id=119")
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
                .setLogBtnHeight(50)
                .setPrivacyOffsetY_B(110)
                .create());

    }

    private void updateScreenSize(int authPageScreenOrientation) {
        int screenHeightDp = AppUtils.px2dp(getApplicationContext(), AppUtils.getPhoneHeightPixels(MainActivity.this));
        int screenWidthDp = AppUtils.px2dp(getApplicationContext(), AppUtils.getPhoneWidthPixels(MainActivity.this));
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (authPageScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_BEHIND) {
            authPageScreenOrientation = getRequestedOrientation();
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
     * 一键登录成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getMainSuccess(int code, UserInfoBean data) {
        new LoginDataEvent(data).post();
        UserUtils.getInstance().login(data);
        // 用户登录埋点
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
    }

    @Override
    public void getMainFail(int code, String msg) {

    }
}
