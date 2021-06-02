package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.ConstantData;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;
import com.vinnlook.www.surface.mvp.presenter.PayMemberPresenter;
import com.vinnlook.www.surface.mvp.view.PayMemberView;
import com.vinnlook.www.surface.zfbapi.AuthResult;
import com.vinnlook.www.surface.zfbapi.PayResult;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.WXPayUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:选择支付页面--会员
 * @Time:2020/8/19$
 * @Author:pk$
 */
public class PayMemberActivity extends BaseActivity<PayMemberPresenter> implements PayMemberView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.pay_prices)
    TextView payPrices;
    @BindView(R.id.ali_pay)
    ImageView aliPay;
    @BindView(R.id.wechat_pay)
    ImageView wechatPay;
    @BindView(R.id.pay_btn)
    TextView payBtn;


    static String type = "";//支付类型；2==支付宝；1==微信
    static String memberId;//会员卡ID
    static String memberPrice;//会员卡价格
    static String channel;//渠道；1==详情页面

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    public static void startSelf(Context context, String memberIds, String memberPrices, String channels) {
        Intent intent = new Intent(context, PayMemberActivity.class);
        context.startActivity(intent);
        memberId = memberIds;
        memberPrice = memberPrices;
        channel = channels;


    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_member;
    }

    @Override
    protected PayMemberPresenter initPresenter() {
        return new PayMemberPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(PayMemberActivity.this);
        payPrices.setText(Html.fromHtml("&yen") + memberPrice);
        type = "2";
        aliPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
        payBtn.setText("支付宝支付" + Html.fromHtml("&yen") + memberPrice);

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

    @OnClick({R.id.ali_pay, R.id.wechat_pay, R.id.pay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ali_pay://支付宝支付
                type = "2";
                aliPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                wechatPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                payBtn.setText("支付宝支付" + Html.fromHtml("&yen") + memberPrice);
                break;
            case R.id.wechat_pay://微信支付
                type = "1";
                aliPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                wechatPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                payBtn.setText("微信支付" + Html.fromHtml("&yen") + memberPrice);
                break;
            case R.id.pay_btn:
                if (type.equals("1")) {//微信支付
                    presenter.postPayMember(type, memberId);
                } else if (type.equals("2")) {//支付宝支付
                    presenter.postPayMember_2(type, memberId);

                }

                break;
        }
    }

    /**
     * @Description:会员购买成功--微信
     * @Time:2020/8/19 17:22
     * @Author:pk
     */
    @Override
    public void getPostPayMemberSuccess(int code, WeCatPayBean data) {
//        MemberCompleteActivity.startSelf(this, channel);

        ConstantData.APP_ID = data.getContent().getAppid();
        ConstantData.CHANNEL = "1";
        ConstantData.CHANNEL = channel;//channel==会员购买入口  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
        String getAppid = data.getContent().getAppid();
        String getPartnerid = data.getContent().getPartnerid();
        String getPrepayid = data.getContent().getPrepayid();
        String getPackageX = data.getContent().getPackageX();
        String getNoncestr = data.getContent().getNoncestr();
        String getTimestamp = data.getContent().getTimestamp() + "";
        String getSign = data.getContent().getSign();
        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
        builder.setAppId(getAppid)
                .setPartnerId(data.getContent().getPartnerid())
                .setPrepayId(getPrepayid)
                .setPackageValue(getPackageX)
                .setNonceStr(getNoncestr)
                .setTimeStamp(getTimestamp)
                .setSign(getSign)
                .build().toWXPayNotSign(PayMemberActivity.this, getAppid);


    }

    /**
     * @Description:会员购买失败--微信
     * @Time:2020/8/19 17:22
     * @Author:pk
     */
    @Override
    public void getPostPayMemberFail(int code, String msg) {

    }

    /**
     * @Description:会员购买成功--支付宝
     * @Time:2020/8/19 17:22
     * @Author:pk
     */
    @Override
    public void getPostPayMember_2Success(int code, ALiPayBean data) {
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayMemberActivity.this);
                Map<String, String> result = alipay.payV2(data.getContent().getZfb_info(), true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * @Description:会员购买失败--支付宝
     * @Time:2020/8/19 17:22
     * @Author:pk
     */
    @Override
    public void getPostPayMember_2Fail(int code, String msg) {

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {//支付宝支付成功回调
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(PayMemberActivity.this, "支付成功");
                        MemberCompleteActivity.startSelf(getContext(), channel);
                        finish();

                    } else if (TextUtils.equals(resultStatus, "4000")) {//订单支付失败
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "支付失败");
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6001")) {//用户中途取消
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "取消支付");
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6002")) {//网络连接出错
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "网络连接错误");
                        finish();

                    } else {
                        showAlert(PayMemberActivity.this, "" + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {//支付成功
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(PayMemberActivity.this, "支付成功");
                        MemberCompleteActivity.startSelf(getContext(), channel);
                        CacheActivity.finishActivity();
                        finish();
                    } else if (TextUtils.equals(resultStatus, "4000")) {//订单支付失败
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "支付失败");
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {//用户中途取消
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "取消支付");
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6002")) {//网络连接出错
                        // 其他状态值则为授权失败
                        showAlert(PayMemberActivity.this, "网络连接错误");
                        finish();
                    } else {
                        showAlert(PayMemberActivity.this, "" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}
