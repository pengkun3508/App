package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ThemeListView;
import com.vinnlook.www.surface.mvp.view.ThemeOtherView;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class ThemeOtherPresenter  extends MvpPresenter<ThemeOtherView> {
    public void getThemeOtherList(int page, int limit, int type) {
        addToRxLife(MainRequest.getThemeOtherList(page,limit,type,new RequestBackListener<ThemeOtherListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ThemeOtherListBean data) {
                if (isAttachView())
                    getBaseView().getThemeOtherListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getThemeOtherListFail(code, msg);
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
