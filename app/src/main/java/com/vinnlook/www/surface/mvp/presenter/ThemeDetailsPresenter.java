package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ThemeDetailsBean;
import com.vinnlook.www.surface.bean.ThemeListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ThemeDetailsView;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeDetailsPresenter extends MvpPresenter<ThemeDetailsView> {
    public void getThemeDetails(String iD) {
        addToRxLife(MainRequest.getThemeDetails(iD,new RequestBackListener<ThemeDetailsBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ThemeDetailsBean data) {
                if (isAttachView())
                    getBaseView().getThemeDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getThemeDetailsFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
//                dismissLoading();
            }
        }));
    }
}
