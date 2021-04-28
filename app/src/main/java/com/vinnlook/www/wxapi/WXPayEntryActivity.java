package com.vinnlook.www.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.opensdk.utils.Log;
import com.vinnlook.www.common.ConstantData;
import com.vinnlook.www.surface.activity.AllOrderActivity;
import com.vinnlook.www.surface.activity.ConfirmOrderActivity_1;
import com.vinnlook.www.surface.activity.MemberCompleteActivity;
import com.vinnlook.www.surface.activity.PayOrderActivity;
import com.vinnlook.www.surface.activity.PayResultsActivity;
import com.vinnlook.www.utils.CacheActivity;

/**
 * Created by ${NOW} on 2018/4/18.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
        Log.e(TAG, "onPayFinish, errCode = ");
        api = WXAPIFactory.createWXAPI(this, ConstantData.APP_ID);

//        api = WXAPIFactory.createWXAPI(this, Constant.WECHAT_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {


    }

    @Override
    public void onResp(BaseResp resp) {//微信支付成功
//        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (ConstantData.CHANNEL.equals("2")) {//购买商品
                if (resp.errCode == 0) {//返回参数意思 参考文档即可
                    //支付成功  执行相关操作
                    //支付成功
                    Toast.makeText(this, "微信支付成功", Toast.LENGTH_SHORT).show();
                    CacheActivity.finishSingleActivityByClass(PayOrderActivity.class);
                    CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                    PayResultsActivity.startSelf(this);
                    finish();
                } else {
                    //支付失败
                    Toast.makeText(this, "微信支付失败", Toast.LENGTH_SHORT).show();
                    CacheActivity.finishSingleActivityByClass(PayOrderActivity.class);
                    CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                    AllOrderActivity.startSelf(this, 1);
                    finish();
                }
            } else if (ConstantData.CHANNEL.equals("1")) {//购买会员
                if (resp.errCode == 0) {//返回参数意思 参考文档即可
                    //支付成功  执行相关操作
                    //支付成功
                    Toast.makeText(this, "微信支付成功", Toast.LENGTH_SHORT).show();
                    CacheActivity.finishSingleActivityByClass(PayOrderActivity.class);
                    CacheActivity.finishSingleActivityByClass(ConfirmOrderActivity_1.class);
                    MemberCompleteActivity.startSelf(this, ConstantData.CHANNELS);//channel会员购买入口:  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
                    finish();
                } else {
                    //支付失败
                    Toast.makeText(this, "微信支付失败", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        }

//        Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
//        }
    }
}
