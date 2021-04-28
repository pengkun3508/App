package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.BrandView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class BrandPresenter extends MvpPresenter<BrandView> {

    public void getBrandList(String type) {
        addToRxLife(MainRequest.getBrandList(type,new RequestBackListener<BrandListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, BrandListBean data) {
                if (isAttachView())
                    getBaseView().getBrandListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getBrandListFail(code, msg);
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
