package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.PostWechatEvent1;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.mvp.presenter.AccountSecurityPresenter;
import com.vinnlook.www.surface.mvp.view.AccountSecurityView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:账户安全
 * @Time:2021/4/19$
 * @Author:pk$
 */
public class AccountSecurityActivity extends BaseActivity<AccountSecurityPresenter> implements AccountSecurityView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.setting_phone)
    TextView settingPhone;
    @BindView(R.id.setting_phone_layout)
    RelativeLayout settingPhoneLayout;
    @BindView(R.id.setting_wechat_layout)
    RelativeLayout settingWechatLayout;
    @BindView(R.id.setting_wechat)
    TextView settingWechat;

    static String getIs_wechat;
    static String getMobile;
    static String wechatNickname;
    public PopupWindow popupwindow;
    String getNickName;
    String getHeadUrl;


    public static void startSelf(Context context, String getIs_wechats, String getMobiles, String wechatNicknames) {
        Intent intent = new Intent(context, AccountSecurityActivity.class);
        context.startActivity(intent);
        getIs_wechat = getIs_wechats;
        getMobile = getMobiles;
        wechatNickname = wechatNicknames;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected AccountSecurityPresenter initPresenter() {
        return new AccountSecurityPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        CacheActivity.addActivity(this);
        if (getIs_wechat.equals("0")) {//未绑定
            settingWechat.setText("未绑定");
        } else if (getIs_wechat.equals("1")) {//已绑定
            settingWechat.setText(wechatNickname);
        }
        settingPhone.setText(getMobile);

    }

    @Override
    protected void loadData() {
        presenter.getUserInfoData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_phone_layout, R.id.setting_wechat_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_phone_layout:
                VerifyPhoneActivity.startSelf(this, getMobile);
//                ModifyPhoneActivity.startSelf(this,"2");//1===修改手机号；2===新绑定手机
                break;
            case R.id.setting_wechat_layout:
                if (getIs_wechat.equals("0")) {//未绑定
                    login();
                    SPUtils.getInstance().save("logss", "2");
                } else if (getIs_wechat.equals("1")) {//已绑定
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView2();
                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                }
                break;
        }
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
        getHeadUrl = event.getHeadUrl();
        getNickName = event.getNickName();
        SPUtils.getInstance().save("logss", "1");
        presenter.getBindingWechat("1", event.getOpenId(), event.getNickName(), event.getHeadUrl());

    }

    @Override
    public void getBindingWechatSuccess(int code, Object data) {
        if (getIs_wechat.equals("0")) {//未绑定
            Toast.makeText(this, "绑定成功！", Toast.LENGTH_SHORT).show();
            getIs_wechat = "1";
            settingWechat.setText(getNickName);
        } else if (getIs_wechat.equals("1")) {//已绑定
            Toast.makeText(this, "解绑成功！", Toast.LENGTH_SHORT).show();
            getIs_wechat = "0";
            settingWechat.setText("未绑定");
            getNickName="";
        }
        CacheActivity.finishActivity();

    }

    @Override
    public void getBindingWechatFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getUserInfoSuccess(int code, UserInfo data) {

    }

    @Override
    public void getUserInfoFail(int code, String msg) {

    }
}
