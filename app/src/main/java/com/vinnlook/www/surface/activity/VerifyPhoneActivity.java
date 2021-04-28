package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.utils.RegexUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.mvp.presenter.VerifyPhonePresenter;
import com.vinnlook.www.surface.mvp.view.VerifyPhoneView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.StringUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:验证手机号
 * @Time:2020/6/11$
 * @Author:pk$
 */
public class VerifyPhoneActivity extends BaseActivity<VerifyPhonePresenter> implements VerifyPhoneView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.verify_phone)
    TextView verifyPhone;
    @BindView(R.id.verify_code_number)
    EditText verifyCodeNumber;
    @BindView(R.id.verify_code_huoqu)
    TextView verifyCodeHuoqu;
    @BindView(R.id.verify_next_step_btn)
    TextView verifyNextStepBtn;
    boolean flag;
    static String getMobile;


    public static void startSelf(Context context, String getMobiles) {
        Intent intent = new Intent(context, VerifyPhoneActivity.class);
        context.startActivity(intent);
        getMobile = getMobiles;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_phone;
    }

    @Override
    protected VerifyPhonePresenter initPresenter() {
        return new VerifyPhonePresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);

        //进行加密
        String phone = getMobile.substring(0, 3) + "****" + getMobile.substring(7, getMobile.length());
        verifyPhone.setText(phone);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.verify_code_huoqu, R.id.verify_next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_code_huoqu://获取验证码
                if (!TextUtils.isEmpty(verifyPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(getMobile)) {
                        if (flag) {
                            return;
                        }
                        timerStart();
                        presenter.getVerificationCode(getMobile);//获取倒计时
                    } else {
                        Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请先填写手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.verify_next_step_btn://下一步
                if (!TextUtils.isEmpty(verifyPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(getMobile)) {
                        if (!TextUtils.isEmpty(verifyCodeNumber.getText().toString().trim())) {
                            presenter.getVerifyPhoneNumber(getMobile, verifyCodeNumber.getText().toString().trim());
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
        }
    }


    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            verifyCodeHuoqu.setText(StringUtils.format(getString(R.string.get_verify_code), millisUntilFinished / 1000) + "");
            flag = true;
            verifyCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
        }

        @Override
        public void onFinish() {
            verifyCodeHuoqu.setText("获取验证码");
            flag = false;
            verifyCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
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
     * @Description:验证码获取成功
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerificationCodeSuccess(int code, Object data) {
        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * @Description:验证码获取失败
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerificationCodeFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * @Description:验证码验证成功
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerifyPhoneNumberSuccess(int code, Object data) {
        ModifyPhoneActivity.startSelf(this, "1");
        finish();

    }

    /**
     * @Description:验证码验证失败
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getVerifyPhoneNumberFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
