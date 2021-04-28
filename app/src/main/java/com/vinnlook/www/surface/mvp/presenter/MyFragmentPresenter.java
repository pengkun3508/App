package com.vinnlook.www.surface.mvp.presenter;


import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.MyFragmentView;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public class MyFragmentPresenter extends MvpPresenter<MyFragmentView> {
    public void getPersonalInformation() {
        addToRxLife(MainRequest.getPersonalInformation(new RequestBackListener<PersonalInformationBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, PersonalInformationBean data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getPersonalInformationSuccess(code, data);

            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPersonalInformationFail(code, msg);
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

    public void getMobileLogin(String token) {
        addToRxLife(MainRequest.getMobileLogin(token, new RequestBackListener<UserInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                dismissLoading();
                if (isAttachView())
                    getBaseView().getMobileLoginSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                dismissLoading();
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getMobileLoginFail(code, msg);
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
}
