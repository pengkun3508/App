package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.utils.RegexUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.PostWechatEvent;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.mvp.presenter.LoginPresenter;
import com.vinnlook.www.surface.mvp.view.LoginView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.StringUtils;
import com.vinnlook.www.utils.UserInfoBean;
import com.vinnlook.www.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:登录页面
 * @Time:2020/4/14 9:41
 * @Author:pk
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    String mobile;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_code_number)
    EditText loginCodeNumber;
    @BindView(R.id.login_code_huoqu)
    TextView loginCodeHuoqu;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.login_wechat)
    ImageView loginWechat;
    @BindView(R.id.login_qq)
    ImageView loginQq;
    @BindView(R.id.login_weibo)
    ImageView loginWeibo;
    @BindView(R.id.switch_login)
    LinearLayout switchLogin;

    boolean flag;
    @BindView(R.id.fangke_text_btn)
    TextView fangkeTextBtn;
    @BindView(R.id.couns_text)
    TextView counsText;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean isRegisterEventBus() {
        return true;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_1;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(LoginActivity.this);

        String courseName = "《用户协议》";
        String courseName2 = "《隐私政策》";

        SpannableString courseSpannable = new SpannableString(courseName);
        SpannableString courseSpannable2 = new SpannableString(courseName2);

        ClickableSpan courseSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
//                Toast.makeText(StartActivity.this, "触发点击事件2222!", Toast.LENGTH_SHORT).show();
                WebActivity.startSelf(LoginActivity.this, "http://shop.jealook.com/v1/html/article-info?id=119");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.them));
                ds.setUnderlineText(false);
            }


        };
        ClickableSpan lessonSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

//                Toast.makeText(StartActivity.this, "触发点击事件1111!", Toast.LENGTH_SHORT).show();
                WebActivity.startSelf(LoginActivity.this, "http://shop.jealook.com/v1/html/article-info?id=117");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.them));
                ds.setUnderlineText(false);
            }

        };
        courseSpannable.setSpan(courseSpan, 0, courseName.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        courseSpannable2.setSpan(lessonSpan, 0, courseName2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        counsText.append("登录即同意");
        counsText.append(courseSpannable);
        counsText.append("和");
        counsText.append(courseSpannable2);
        counsText.setMovementMethod(LinkMovementMethod.getInstance());
        counsText.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:验证码获取成功
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerificationCodeSuccess(int code, Object data) {
        Toast.makeText(this,"发送成功",Toast.LENGTH_SHORT).show();

    }

    /**
     * @Description:验证码获取失败
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerificationCodeFail(int code, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    /**
     * @Description:登录成功
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getCheckCodeSuccess(int code, UserInfoBean data) {
        Log.e("登录成功", "==登录成功==" + data.getMobile());
        // 用户登录埋点
        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount("usernick", data.getUser_id());
//        new LoginDataEvent(data).post();
        UserUtils.getInstance().login(data);

        if (data.getMobile().equals("") && data.getMobile() != null) {//未绑定手机号
            ModifyPhoneActivity.startSelf(LoginActivity.this, "2");
        }
        finish();
//        CacheActivity.finishActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * @Description:登录失败
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getCheckCodeFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_code_huoqu, R.id.login_btn, R.id.login_wechat, R.id.login_qq, R.id.login_weibo, R.id.switch_login, R.id.fangke_text_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_code_huoqu://获取验证码
                if (!TextUtils.isEmpty(loginPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(loginPhone.getText().toString().trim())) {
                        if (flag) {
                            return;
                        }
                        timerStart();
                        presenter.getVerificationCode(loginPhone.getText().toString().trim());//获取倒计时
                    } else {
                        Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请先填写手机号", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.login_btn://登录

                if (!TextUtils.isEmpty(loginPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(loginPhone.getText().toString().trim())) {
                        if (!TextUtils.isEmpty(loginCodeNumber.getText().toString().trim())) {
                            presenter.getCheckCode(loginPhone.getText().toString().trim(), loginCodeNumber.getText().toString().trim());
                        } else {
                            Toast.makeText(this, "请先填写验证码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请先填写手机号", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.login_wechat://微信
                login();
                break;
            case R.id.login_qq://QQ
                break;
            case R.id.login_weibo://微博
                break;
            case R.id.switch_login://切换页面
                finish();
                break;
            case R.id.fangke_text_btn://以访客身份
//                new MainHomeActivityEvent("1").post();
                finish();

                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            loginCodeHuoqu.setText(StringUtils.format(getString(R.string.get_verify_code), millisUntilFinished / 1000) + "");
            flag = true;
            loginCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
        }

        @Override
        public void onFinish() {
            loginCodeHuoqu.setText("获取验证码");
            flag = false;
            loginCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
        }
    };

    /**
     * 停止倒计时
     */
    public void timerStop() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
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
            Toast.makeText(this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = String.valueOf(System.currentTimeMillis());
            App.getwxApi().sendReq(req);
        }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("onActivityResult", "requestCode:===" + requestCode);
//        Log.e("onActivityResult", "resultCode:===" + resultCode);
//        if (resultCode == 0) {
//            String headUrl = data.getStringExtra("headUrl");
//            Log.e("onActivityResult", "url:===" + headUrl);
////            presenter.getWechatLogin(openId,nickName,headUrl);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostWechatEvent event) {
        Log.e("LoginActivity", "==登录回调==");
//        presenter.getWechatLogin(event.getOpenId(), event.getNickName(), event.getHeadUrl());
    }
}