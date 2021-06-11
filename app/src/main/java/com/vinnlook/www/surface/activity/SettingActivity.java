package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.event.PostWechatEvent1;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.dialog.UpdateDialog;
import com.vinnlook.www.surface.mvp.presenter.SettingPresenter;
import com.vinnlook.www.surface.mvp.view.SettingView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DataCleanManager;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.IntoShopUtils;
import com.vinnlook.www.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:首页设置
 * @Time:2021/3/2$
 * @Author:pk$
 */
public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingView {


    @BindView(R.id.setting_version)
    TextView settingVersion;
    @BindView(R.id.setting_version_layout)
    RelativeLayout settingVersionLayout;
    @BindView(R.id.settingd_clear)
    TextView settingdClear;
    @BindView(R.id.setting_pingfen_layout)
    RelativeLayout settingPingfenLayout;
    @BindView(R.id.setting_above_layout)
    RelativeLayout settingAboveLayout;

    SignBean signBean;
    @BindView(R.id.personal_out_btn)
    TextView personalOutBtn;
    @BindView(R.id.personal_head_img)
    RoundedImageView personalHeadImg;
    @BindView(R.id.personal_setting_name)
    TextView personalSettingName;
    @BindView(R.id.personal_head_layout)
    RelativeLayout personalHeadLayout;
    @BindView(R.id.personal_address_layout)
    RelativeLayout personalAddressLayout;
    @BindView(R.id.personal_account_layout)
    RelativeLayout personalAccountLayout;
    @BindView(R.id.personal_privacy_layout)
    RelativeLayout personalPrivacyLayout;
    @BindView(R.id.personal_set_layout)
    RelativeLayout personalSetLayout;
    @BindView(R.id.personal_wenti_layout)
    RelativeLayout personalWentiLayout;
    @BindView(R.id.personal_clear_layout)
    RelativeLayout personalClearLayout;
    private UpdateDialog updateDialog = null;
    public PopupWindow popupwindow;
    String getNickName;

    static String getIs_wechat;
    static String getMobile;
    static String wechatNickname;

    public static void startSelf(Context context, String getIs_wechats, String getMobiles, String wechatNicknames) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
        getIs_wechat = getIs_wechats;
        getMobile = getMobiles;
        wechatNickname = wechatNicknames;
        Log.e("SettingActivity", "==getIs_wechat==" + getIs_wechat);
        Log.e("SettingActivity", "==wechatNickname==" + wechatNickname);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_1;
    }

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        CacheActivity.addActivity(this);
        try {
            settingdClear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        settingVersion.setText("V" + AppInfoUtils.getVersionName());


    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfoData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_version_layout, R.id.setting_pingfen_layout, R.id.setting_above_layout, R.id.personal_out_btn,
            R.id.personal_head_layout, R.id.personal_address_layout, R.id.personal_account_layout, R.id.personal_privacy_layout,
            R.id.personal_set_layout, R.id.personal_wenti_layout, R.id.personal_clear_layout, R.id.qualifications_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_head_layout://个人信息
                EditDataActivity.startSelf(getContext());
                break;
            case R.id.personal_address_layout://收货地址
                AddressActivity.startSelf(getActivity(), "1");
                break;
            case R.id.personal_account_layout://账户安全
                AccountSecurityActivity.startSelf(this, getIs_wechat, getMobile, wechatNickname);
                break;
            case R.id.personal_privacy_layout://隐私
                ProtocolPrivacyActivity.startSelf(this);
                break;
            case R.id.personal_set_layout://系统设置
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
            case R.id.personal_wenti_layout://问题反馈
                ProblemFeedbackActivity.startSelf(getContext());
                break;
            case R.id.personal_clear_layout://清除缓存
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;

            case R.id.setting_version_layout://更新版本
                String signBeanSting = SPUtils.getInstance().get(Constant.KEY_SIGN_BEAN, "");
                if (signBeanSting != null && !signBeanSting.equals("")) {
                    try {
                        Log.e("更新版本", "==signBeanSting===" + signBeanSting);
                        signBean = new Gson().fromJson(signBeanSting, SignBean.class);
                        Log.e("更新版本", "==signBean===" + signBean);
                        Log.e("更新版本", "==getVersion===" + signBean.getVersion());
                        Log.e("更新版本", "==getVersion===" + signBean.getVersion());
                        Log.e("更新版本", "==getMust===" + signBean.getVersion().getMust());
                        //1：最新版本；2：强制更新；3：提示更新；4：手动更新
                        if (signBean.getVersion().getMust() == 3 || signBean.getVersion().getMust() == 2) {
                            if (updateDialog == null) {
                                updateDialog = UpdateDialog.with(getActivity(), signBean.getVersion())
                                        .setOnDismissListener(new SimpleCallback<Void>() {
                                            @Override
                                            public void onResult(Void data) {
                                                updateDialog = null;
                                            }
                                        });
                                updateDialog.show();
                            }
                        } else {
                            Toast.makeText(this, "当前版本为：V" + AppInfoUtils.getVersionName() + "，暂无新版本", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JsonSyntaxException ignore) {
                        Log.e("更新版本", "==JsonSyntaxException===" + ignore);
                    }
                } else {
                    Toast.makeText(this, "当前版本为：V" + AppInfoUtils.getVersionName() + "，暂无新版本", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.setting_pingfen_layout://评分
                String name = Build.MANUFACTURER;
                Log.e("获取手机品牌", "===name===" + name);
                Log.e("获取手机品牌", "===BRAND===" + Build.BRAND);
                switch (name) {
                    case "HUAWEI":
                        IntoShopUtils.toHuaWei(this, "com.vinnlook.www");
                        break;
                    case "Xiaomi":
                        IntoShopUtils.toXiaoMi(this, "com.vinnlook.www");
                        break;
                    case "vivo":
                        IntoShopUtils.toVivo(this, "com.vinnlook.www");
                        break;
                    case "OPPO":
                        IntoShopUtils.toOppo(this, "com.vinnlook.www");
                        break;
                    case "samsung":
                        IntoShopUtils.goToSamsungMarket(this, "com.vinnlook.www");
                        break;
                    case "Meizu":
                        IntoShopUtils.toMeizu(this, "com.vinnlook.www");
                        break;
                    case "Coolpad":
                    case "Sony":
                    case "LG":
                    default:
                        //安装了应用宝
                        if (isMobile_spExist()) {
                            //跳到应用宝下载安装
                            IntoShopUtils.toQQDownload(this, "com.vinnlook.www");
                        } else {
                            //没安装
                            Toast.makeText(this, "您的设备未安装腾讯应用宝", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
            case R.id.setting_above_layout://关于我们
//                AboutUsActivity.startSelf(getContext());
                CompanyActivity.startSelf(getActivity());
                break;
            case R.id.qualifications_layout://资质证书
                QualificationsActivity.startSelf(this);
                break;
            case R.id.personal_out_btn://退出登录
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView4();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
        }
    }

    public boolean isMobile_spExist() {
        PackageManager manager = this.getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo pI = pkgList.get(i);
            if (pI.packageName.equalsIgnoreCase("com.tencent.android.qqdownloader"))
                return true;
        }
        return false;
    }


    /**
     * 清理缓存提醒
     */
    private void initmPopupWindowView() {
        TextView clear_btn, sure_btn, content_text;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.clear_cache_layout, null, false);
        content_text = customView.findViewById(R.id.content_text);
        clear_btn = customView.findViewById(R.id.clear_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);

        try {
            content_text.setText("缓存大小为" + DataCleanManager.getTotalCacheSize(SettingActivity.this) + ",确定要清理缓存吗？");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        //取消
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                DataCleanManager.clearAllCache(SettingActivity.this);
                try {
                    settingdClear.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 解绑微信
     */
    private void initmPopupWindowView2() {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.unbundling_wechat_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
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
        //返回
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                presenter.getBindingWechat("2", "", "", "");
            }
        });

    }


    /**
     * 确定退出登录？
     */
    private void initmPopupWindowView4() {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.login_out_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
//        customView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    popupwindow = null;
//                }
//                return false;
//            }
//        });
        //返回
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserUtils.getInstance().getUserInfo();
                UserUtils.getInstance().logout();
//                new MyPersonalJudgeEvent(1).post();
                new MainHomeActivityEvent("1").post();
                finish();
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

    }

    /**
     * 微信登录(三个步骤)
     * 1.微信授权登录
     * 2.根据授权登录code 获取该用户token
     * 3.根据token获取用户资料
     */
    public void login() {
        if (!App.getwxApi().isWXAppInstalled()) {
            Toast.makeText(this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = String.valueOf(System.currentTimeMillis());
            App.getwxApi().sendReq(req);
        }

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //接收消息--绑定微信
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostWechatEvent1 event) {
        Log.e("SettingActivity", "==绑定微信==");
        getNickName = event.getNickName();
        SPUtils.getInstance().save("logss", "1");
        presenter.getBindingWechat("1", event.getOpenId(), event.getNickName(), event.getHeadUrl());

    }


    /**
     * 绑定--解绑微信
     *
     * @param code
     * @param data
     */
    @Override
    public void getBindingWechatSuccess(int code, Object data) {
//        if (getIs_wechat.equals("0")) {//未绑定
//            Toast.makeText(this, "绑定成功！", Toast.LENGTH_SHORT).show();
//            settingWechat.setText(getNickName);
//        } else if (getIs_wechat.equals("1")) {//已绑定
//            Toast.makeText(this, "解绑成功！", Toast.LENGTH_SHORT).show();
//            settingWechat.setText("未绑定");
//        }
    }

    @Override
    public void getBindingWechatFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserInfoSuccess(int code, UserInfo data) {

        getMobile = data.getMobile();
        ImageLoader.userIcon(this, personalHeadImg, data.getImg_url());
        personalSettingName.setText(data.getUser_name());


    }

    @Override
    public void getUserInfoFail(int code, String msg) {

    }
}
