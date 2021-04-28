package com.vinnlook.www.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.dm.lib.utils.SPUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.PostWechatEvent;
import com.vinnlook.www.event.PostWechatEvent1;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/6/9 18:13
 *
 * @version 1.0
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    /**
     * 微信登录相关
     */
    private IWXAPI api;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("参数不合法", "未被SDK处理，退出=12323=");
        //通过WXAPIFactory工厂获取IWXApI的示例
//        api = WXAPIFactory.createWXAPI(this, Constant.WECHAT_APPID, true);
        api = WXAPIFactory.createWXAPI(this, null);
        //将应用的appid注册到微信
        api.registerApp(Constant.WECHAT_APPID);
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                Log.e("参数不合法", "未被SDK处理，退出==");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq req) {//从微信启动App
        Log.e("onReq", "baseReq==" + JSON.toJSONString(req));
        //获取开放标签传递的extinfo数据逻辑
        if (req.getType() == ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX && req instanceof ShowMessageFromWX.Req) {
            ShowMessageFromWX.Req showReq = (ShowMessageFromWX.Req) req;
            WXMediaMessage mediaMsg = showReq.message;
            String extInfo = mediaMsg.messageExt;
            //Handle...

            Log.e("onReq", "extInfo==" + extInfo);
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("baseResp", "--A==" + JSON.toJSONString(baseResp));
        Log.e("baseResp", "--B==" + baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode);
        WXBaseRespEntity entity = JSON.parseObject(JSON.toJSONString(baseResp), WXBaseRespEntity.class);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
//                showDialog("正在获取个人资料..");
                //现在请求获取数据 access_token https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//                showMsg(1,result);
                /*Call call = RetrofitUtils.getApiService("https://api.weixin.qq.com/").getWeiXinAccessToken(Config.APP_ID_WX,Config.APP_SECRET_WX,entity.getCode(),"authorization_code");
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        ViseLog.d("response:"+JSON.toJSONString(response));
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        closeDialog();
                    }
                });*/
                OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token")
                        .addParams("appid", Constant.WECHAT_APPID)
                        .addParams("secret", Constant.WECHAT_SECRET)
                        .addParams("code", entity.getCode())
                        .addParams("grant_type", "authorization_code")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {

                                Log.e("baseResp", "请求错误" + e);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("response", "response===" + response);
                                WXAccessTokenEntity accessTokenEntity = JSON.parseObject(response, WXAccessTokenEntity.class);
                                if (accessTokenEntity != null) {
                                    getUserInfo(accessTokenEntity);
                                } else {
                                    Log.e("response", "获取失败===");
                                }
                            }
                        });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Log.e("baseResp", "发送取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Log.e("baseResp", "发送被拒绝");
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                Log.e("baseResp", "签名错误");
                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
//        Toast.makeText(WXEntryActivity.this, result, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取个人信息
     *
     * @param accessTokenEntity
     */
    public void getUserInfo(WXAccessTokenEntity accessTokenEntity) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        OkHttpUtils.get()
                .url("https://api.weixin.qq.com/sns/userinfo")
                .addParams("access_token", accessTokenEntity.getAccess_token())
                .addParams("openid", accessTokenEntity.getOpenid())//openid:授权用户唯一标识
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {

                        Log.e("getUserInfo", "获取错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("getUserInfo", "userInfo===" + response);
                        WXUserInfo wxResponse = JSON.parseObject(response, WXUserInfo.class);

                        Log.e("微信登录资料已获取", "后续未完成===");
                        String headUrl = wxResponse.getHeadimgurl();
                        String openId = wxResponse.getOpenid();
                        String nickName = wxResponse.getNickname();

                        Log.e("微信登录资料已获取", "头像Url===" + headUrl);
                        Log.e("微信登录资料已获取", "openId===" + openId);
                        Log.e("微信登录资料已获取", "头像Url===" + nickName);
//                        App.getShared().putString("headUrl", headUrl);
//                        Intent intent = getIntent();
//                        intent.putExtra("headUrl", headUrl);
//                        intent.putExtra("openId", openId);
//                        intent.putExtra("nickName", nickName);
//                        setResult(0, intent);
//                        finish();

                        if (SPUtils.getInstance().get("logss", "").equals("2")) {
                            new PostWechatEvent1(headUrl, openId, nickName).post();
                        } else {
                            new PostWechatEvent(headUrl, openId, nickName).post();
                        }


                        finish();
                    }
                });
    }


}
