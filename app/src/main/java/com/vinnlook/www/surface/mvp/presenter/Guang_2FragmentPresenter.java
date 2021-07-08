package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GuangSelectBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.Guang_2FragmentView;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class Guang_2FragmentPresenter extends MvpPresenter<Guang_2FragmentView> {
    public void getSelectData(int page, int limit) {
        addToRxLife(MainRequest.getSelectData(page,limit,new RequestBackListener<GuangSelectBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, GuangSelectBean data) {
                if (isAttachView())
                    getBaseView().getSelectDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code==精选眼图==" + code);
                Log.e("msg", "msg==精选眼图==" + msg);
                if (isAttachView())
                    getBaseView().getSelectDataFail(code, msg);
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
