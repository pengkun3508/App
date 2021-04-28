package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ModifyPhoneView;
import com.vinnlook.www.surface.mvp.view.VerifyPhoneView;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * @Description:
 * @Time:2020/6/11$
 * @Author:pk$
 */
public class ModifyPhonePresenter extends MvpPresenter<ModifyPhoneView> {
    public void getVerificationCode(String mobile) {
        addToRxLife(MainRequest.getVerificationCode(mobile, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getVerificationCodeSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getVerificationCodeFail(code, msg);
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

    public void getModifyPhoneNumber(String mobile, String code) {
        addToRxLife(MainRequest.getModifyPhoneNumber(mobile,code,new RequestBackListener<UserInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                if (isAttachView())
                    getBaseView().getModifyPhoneNumberSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getModifyPhoneNumberFail(code, msg);
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
