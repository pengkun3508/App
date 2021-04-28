package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.LoginView;
import com.vinnlook.www.surface.mvp.view.MainView;
import com.vinnlook.www.utils.UserInfoBean;

public class MainPresenter extends MvpPresenter<MainView> {
    public void getMobileLogin(String token) {
        addToRxLife(MainRequest.getMobileLogin(token, new RequestBackListener<UserInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                if (isAttachView())
                    getBaseView().getMainSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getMainFail(code, msg);
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
