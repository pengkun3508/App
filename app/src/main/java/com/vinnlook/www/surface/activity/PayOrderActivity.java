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
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.ConstantData;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;
import com.vinnlook.www.surface.mvp.presenter.PayOrderPresenter;
import com.vinnlook.www.surface.mvp.view.PayOrderView;
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
 * @Description: 选择支付页面--订单
 * @Time:2020/5/14$
 * @Author:pk$
 */
public class PayOrderActivity extends BaseActivity<PayOrderPresenter> implements PayOrderView {

    static String recId;
    static String goods_id;
    static String product_id;
    static String num;
    static String real_id;
    static String address_id;
    static String type;
    static String confirmMessage1;
    static String confirmMessage2;
    static String order_id;
    static String payPrice;
    static String bonus_id;
    static String waybill_id;
    static String zYSb;
    static String hTSb;

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


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    public static void startSelf(Context context, String recIds, String goods_ids, String product_ids,
                                 String nums, String real_ids, String address_ids, String types, String confirmMessages1, String confirmMessages2,
                                 String order_ids, String payPrices, String bonus_ids, String waybill_ids, String zYSbs, String hTSbs) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        context.startActivity(intent);
        recId = recIds;
        goods_id = goods_ids;
        product_id = product_ids;
        num = nums;
        real_id = real_ids;
        address_id = address_ids;
        type = types;
        confirmMessage1 = confirmMessages1;
        confirmMessage2 = confirmMessages2;
        order_id = order_ids;
        payPrice = payPrices;
        bonus_id = bonus_ids;
        waybill_id = waybill_ids;
        zYSb = zYSbs;
        hTSb = hTSbs;


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_order;
    }

    @Override
    protected PayOrderPresenter initPresenter() {
        return new PayOrderPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(PayOrderActivity.this);
        payPrices.setText(Html.fromHtml("&yen") + payPrice);
        type = "2";
        aliPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
        payBtn.setText("支付宝支付" + Html.fromHtml("&yen") + payPrice);
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
                payBtn.setText("支付宝支付" + Html.fromHtml("&yen") + payPrice);
                break;
            case R.id.wechat_pay://微信支付
                type = "1";
                aliPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                wechatPay.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                payBtn.setText("微信支付" + Html.fromHtml("&yen") + payPrice);
                break;
            case R.id.pay_btn://支付按钮
                Log.e("支付按钮", "==recId==" + recId);
                Log.e("支付按钮", "==goods_id==" + goods_id);
                Log.e("支付按钮", "==product_id==" + product_id);
                Log.e("支付按钮", "==num==" + num);
                Log.e("支付按钮", "==real_id==" + real_id);
                Log.e("支付按钮", "==address_id==" + address_id);
                Log.e("支付按钮", "==type==" + type);
                Log.e("支付按钮", "==confirmMessage==" + confirmMessage1);
                Log.e("支付按钮", "==order_id==" + order_id);
                Log.e("支付按钮", "==bonus_id==" + bonus_id);
                Log.e("支付按钮", "==waybill_id==" + waybill_id);

                if (!type.equals("")) {
                    if (type.equals("2")) {//支付宝支付
                        if (recId.equals("")) {
                            presenter.postALiSubmitOrder(recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1, confirmMessage2, order_id, bonus_id, waybill_id,zYSb,hTSb);
                        } else {
                            goods_id = "";
                            product_id = "";
                            num = "";
                            presenter.postALiSubmitOrder(recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1, confirmMessage2, order_id, bonus_id, waybill_id,zYSb,hTSb);
                        }
                    } else if (type.equals("1")) {//微信支付
                        if (recId.equals("")) {
                            presenter.postSubmitOrder(recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1, confirmMessage2, order_id, bonus_id, waybill_id,zYSb,hTSb);
                        } else {
                            goods_id = "";
                            product_id = "";
                            num = "";
                            presenter.postSubmitOrder(recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1, confirmMessage2, order_id, bonus_id, waybill_id,zYSb,hTSb);
                        }
                    }

                } else {
                    Toast.makeText(this, "请选择一种支付方式", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    /**
     * @Description:微信支付--提交订单成功
     * @Time:2020/5/15 11:47
     * @Author:pk
     */
    @Override
    public void getPostSubmitOrderSuccess(int code, WeCatPayBean data) {

        ConstantData.APP_ID = data.getAppid();
        ConstantData.CHANNEL = "2";
        String getAppid = data.getAppid();
        String getPartnerid = data.getPartnerid();
        String getPrepayid = data.getPrepayid();
        String getPackageX = data.getPackageX();
        String getNoncestr = data.getNoncestr();
        String getTimestamp = data.getTimestamp() + "";
        String getSign = data.getSign();
        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
        builder.setAppId(getAppid)
                .setPartnerId(data.getPartnerid())
                .setPrepayId(getPrepayid)
                .setPackageValue(getPackageX)
                .setNonceStr(getNoncestr)
                .setTimeStamp(getTimestamp)
                .setSign(getSign)
                .build().toWXPayNotSign(PayOrderActivity.this, getAppid);

    }

    //    提交订单失败

    /**
     * @Description:微信支付--提交订单失败
     * @Time:2020/5/15 11:47
     * @Author:pk
     */
    @Override
    public void getPostSubmitOrderFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Description:支付宝支付--提交订单成功
     * @Time:2020/5/15 11:47
     * @Author:pk
     */
    @Override
    public void getPostALiSubmitOrderSuccess(int code, ALiPayBean data) {
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayOrderActivity.this);
                Map<String, String> result = alipay.payV2(data.getZfb_info(), true);
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
     * @Description:支付宝支付--提交订单失败
     * @Time:2020/5/15 11:47
     * @Author:pk
     */
    @Override
    public void getPostALiSubmitOrderFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
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
                        showAlert(PayOrderActivity.this, "支付成功");
                        PayResultsActivity.startSelf(PayOrderActivity.this);
                        CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                        finish();

                    } else if (TextUtils.equals(resultStatus, "4000")) {//订单支付失败
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "支付失败");
                        CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);

                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {//用户中途取消
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "取消支付");
                        CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6002")) {//网络连接出错
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "网络连接错误");
                        CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                        finish();

                    } else {
                        showAlert(PayOrderActivity.this, "" + payResult);
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
                        showAlert(PayOrderActivity.this, "支付成功");
                        PayResultsActivity.startSelf(PayOrderActivity.this);
                        CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "4000")) {//订单支付失败
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "支付失败");
                        AllOrderActivity.startSelf(getContext(), 1);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {//用户中途取消
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "取消支付");
                        AllOrderActivity.startSelf(getContext(), 1);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6002")) {//网络连接出错
                        // 其他状态值则为授权失败
                        showAlert(PayOrderActivity.this, "网络连接错误");
                        AllOrderActivity.startSelf(getContext(), 1);
                        finish();
                    } else {
                        showAlert(PayOrderActivity.this, "" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

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
