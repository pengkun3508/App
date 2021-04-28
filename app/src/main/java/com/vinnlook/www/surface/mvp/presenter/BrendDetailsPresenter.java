package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.surface.bean.BrandDetailsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.BrendDetailsView;
import com.vinnlook.www.surface.mvp.view.BrowseView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class BrendDetailsPresenter extends MvpPresenter<BrendDetailsView> {
    public void getBrandDetailsList(String page,String limit,String getBrand_id) {
        addToRxLife(MainRequest.getBrandDetailsList(page,limit,getBrand_id,new RequestBackListener<BrandDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, BrandDetailsBean data) {
                if (isAttachView())
                    getBaseView().getBrandDetailsListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getBrandDetailsListFail(code, msg);
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
