package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ExchangeBean;
import com.vinnlook.www.surface.bean.PointsMallBean;
import com.vinnlook.www.surface.bean.SavingOrderBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PointsMallView;
import com.vinnlook.www.surface.mvp.view.SavingOrdersView;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallPresenter extends MvpPresenter<PointsMallView> {
    public void getPointsMall() {
        addToRxLife(MainRequest.getPointsMall( new RequestBackListener<PointsMallBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, PointsMallBean data) {
                if (isAttachView())
                    getBaseView().getPointsMallSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPointsMallFail(code, msg);
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

    public void getExchange(String id) {
        addToRxLife(MainRequest.getExchange( id,new RequestBackListener<ExchangeBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ExchangeBean data) {
                if (isAttachView())
                    getBaseView().getExchangeSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getExchangeFail(code, msg);
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
