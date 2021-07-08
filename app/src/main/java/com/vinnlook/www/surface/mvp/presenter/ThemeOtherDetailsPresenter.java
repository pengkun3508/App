package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ThemeOtherDetailsBean;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ThemeOtherDetailsView;
import com.vinnlook.www.surface.mvp.view.ThemeOtherView;

/**
 * @Description:新挖宝藏/热销安利详情
 * @Time:2021/7/7$
 * @Author:pk$
 */
public class ThemeOtherDetailsPresenter  extends MvpPresenter<ThemeOtherDetailsView> {
    public void getThemeOtherDetails(String iD) {
        addToRxLife(MainRequest.getThemeOtherDetails(iD,new RequestBackListener<ThemeOtherDetailsBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ThemeOtherDetailsBean data) {
                if (isAttachView())
                    getBaseView().getThemeOtherDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getThemeOtherDetailsFail(code, msg);
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
