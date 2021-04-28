package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.bean.ApplyDetailsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ApplyDetailsView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class ApplyDetailsPresenter extends MvpPresenter<ApplyDetailsView> {
    public void getApplyDetailsData(String order_id, String id) {
        addToRxLife(MainRequest.getApplyDetailsData(order_id, id, new RequestBackListener<ApplyDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ApplyDetailsBean data) {
                if (isAttachView())
                    getBaseView().getApplyDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getApplyDetailsFail(code, msg);
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

    //撤销退款请求
    public void getApplyCancel(String order_id, String id) {
        addToRxLife(MainRequest.getApplyCancel(order_id, id, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getApplyCancelSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getApplyCancelFail(code, msg);
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

    public void getOederDetailsData(String order_id) {
        addToRxLife(MainRequest.getOederDetailsData(order_id, new RequestBackListener<OrderDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, OrderDetailsBean data) {
                if (isAttachView())
                    getBaseView().getOederDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getOederDetailsFail(code, msg);
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
