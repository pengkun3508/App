package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PayMemberView;

/**
 * @Description:
 * @Time:2020/8/19$
 * @Author:pk$
 */
public class PayMemberPresenter extends MvpPresenter<PayMemberView> {


    /**
     * @Description:支付购买会员
     * @Time:2020/8/19 17:18
     * @Author:pk
     */
    public void postPayMember(String type, String memberId) {
        addToRxLife(MainRequest.postPayMember(type, memberId, new RequestBackListener<WeCatPayBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, WeCatPayBean data) {
                Log.e("code", "=code===" + code);
                Log.e("msg", "=msg===" + data);
                if (isAttachView())
                    getBaseView().getPostPayMemberSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPostPayMemberFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));


    }

    //支付宝支付
    public void postPayMember_2(String type, String memberId) {
        addToRxLife(MainRequest.postPayMember_2(type, memberId, new RequestBackListener<ALiPayBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ALiPayBean data) {
                Log.e("code", "=code===" + code);
                Log.e("msg", "=msg===" + data);
                if (isAttachView())
                    getBaseView().getPostPayMember_2Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPostPayMember_2Fail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));

    }
}
