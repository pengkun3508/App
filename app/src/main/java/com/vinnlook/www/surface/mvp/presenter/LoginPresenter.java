package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.LoginView;
import com.vinnlook.www.utils.UserInfoBean;

public class LoginPresenter extends MvpPresenter<LoginView> {

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

    //登录
    public void getCheckCode(String mobile, String code) {
        addToRxLife(MainRequest.getCheckCode(mobile, code, new RequestBackListener<UserInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                dismissLoading();
                if (isAttachView())
                    getBaseView().getCheckCodeSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                dismissLoading();
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCheckCodeFail(code, msg);
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

    public void getWechatLogin(String openId, String nickName, String headUrl) {

        addToRxLife(MainRequest.getWechatLogin(openId, nickName,headUrl, new RequestBackListener<UserInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                if (isAttachView())
                    getBaseView().getCheckCodeSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCheckCodeFail(code, msg);
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
