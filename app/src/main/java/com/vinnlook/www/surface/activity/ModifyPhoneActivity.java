package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.utils.RegexUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.surface.mvp.presenter.ModifyPhonePresenter;
import com.vinnlook.www.surface.mvp.view.ModifyPhoneView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.StringUtils;
import com.vinnlook.www.utils.UserInfoBean;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:修改手机号
 * @Time:2020/6/11$
 * @Author:pk$
 */
public class ModifyPhoneActivity extends BaseActivity<ModifyPhonePresenter> implements ModifyPhoneView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.modify_phone)
    EditText modifyPhone;
    @BindView(R.id.modify_code_number)
    EditText modifyCodeNumber;
    @BindView(R.id.modify_code_huoqu)
    TextView modifyCodeHuoqu;
    @BindView(R.id.modify_submit_btn)
    TextView modifySubmitBtn;

    static UserInfoBean data;

    boolean flag;
    static String mark;//1===修改手机号；2===新绑定手机


    public static void startSelf(Context context, String marks) {
        Intent intent = new Intent(context, ModifyPhoneActivity.class);
        context.startActivity(intent);
        mark = marks;
    }
//
//    public static void startSelf1(Context context, String marks, UserInfoBean datas) {
//        Intent intent = new Intent(context, ModifyPhoneActivity.class);
//        context.startActivity(intent);
//        mark = marks;
//        data=datas;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @Override
    protected ModifyPhonePresenter initPresenter() {
        return new ModifyPhonePresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        Log.e("修改手机号页面", "==mark===" + mark);
        if (mark.equals("2")) {
            actionBar.getTvTitle().setText("绑定手机号");
            actionBar.getIvBack().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserUtils.getInstance().logout();
                    new MainHomeActivityEvent("1").post();
                    finish();
                }
            });

        }



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

    @OnClick({R.id.modify_code_huoqu, R.id.modify_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_code_huoqu://获取验证码
                if (!TextUtils.isEmpty(modifyPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(modifyPhone.getText().toString().trim())) {
                        if (flag) {
                            return;
                        }
                        timerStart();
                        presenter.getVerificationCode(modifyPhone.getText().toString().trim());//获取倒计时
                    } else {
                        Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请先填写手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.modify_submit_btn://确定
                if (!TextUtils.isEmpty(modifyPhone.getText().toString().trim())) {
                    if (RegexUtils.matchPhone(modifyPhone.getText().toString().trim())) {
                        if (!TextUtils.isEmpty(modifyCodeNumber.getText().toString().trim())) {
                            presenter.getModifyPhoneNumber(modifyPhone.getText().toString().trim(), modifyCodeNumber.getText().toString().trim());
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
            modifyCodeHuoqu.setText(StringUtils.format(getString(R.string.get_verify_code), millisUntilFinished / 1000) + "");
            flag = true;
            modifyCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
        }

        @Override
        public void onFinish() {
            modifyCodeHuoqu.setText("获取验证码");
            flag = false;
            modifyCodeHuoqu.setTextColor(getResources().getColor(R.color.them));
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
     * @Description:修改手机号成功
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getModifyPhoneNumberSuccess(int code, UserInfoBean data) {

        if (mark.equals("2")) {
            Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        UserUtils.getInstance().login(data);
        CacheActivity.finishActivity();
    }

    /**
     * @Description:修改手机号失败
     * @Time:2020/5/9 10:38
     * @Author:pk
     */
    @Override
    public void getModifyPhoneNumberFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * @Description:判断返回键，进行页面关闭
     * @Time:2020/9/3 10:06
     * @Author:pk
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (mark.equals("2")) {
                    CacheActivity.finishActivity();
                    UserUtils.getInstance().logout();
                }
            }
        }
        return true;
    }


}
