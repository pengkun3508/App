package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ThemeListBean;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.surface.bean.WaybillListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.StartView;
import com.vinnlook.www.surface.mvp.view.ThemeListView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeListPresenter  extends MvpPresenter<ThemeListView> {
    public void getThemeList(int page, int limit) {
        addToRxLife(MainRequest.getThemeList(page,limit,new RequestBackListener<ThemeListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ThemeListBean data) {
                if (isAttachView())
                    getBaseView().getThemeListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getThemeListFail(code, msg);
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
