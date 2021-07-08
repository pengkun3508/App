package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.OrderDetailsView;

public class OrderDetailsPresenter extends MvpPresenter<OrderDetailsView> {

    public void getOederDetailsData(String getOrder_id) {
        addToRxLife(MainRequest.getOederDetailsData(getOrder_id, new RequestBackListener<OrderDetailsBean>() {
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

    public void getCelearOrderData(String getOrder_id, String type) {
        addToRxLife(MainRequest.getCelearOrderData(getOrder_id, type, new RequestBackListener<AllOrderListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, AllOrderListBean data) {
                if (isAttachView())
                    getBaseView().getCelearOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCelearOrderFail(code, msg);
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

    public void getSignInOrderData(String getOrder_id) {
        addToRxLife(MainRequest.getSignInOrderData(getOrder_id, new RequestBackListener<SiginOrderBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, SiginOrderBean data) {
                if (isAttachView())
                    getBaseView().getSignInOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code=签收状态==" + code);
                Log.e("msg", "msg=签收状态==" + msg);
                if (isAttachView())
                    getBaseView().getSignInOrderFail(code, msg);
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

    public void getEditAddressOrderData(String getOrder_id, String address_id) {

        addToRxLife(MainRequest.getEditAddressOrderData(getOrder_id,address_id, new RequestBackListener<OrderDetailsBean>() {
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
                Log.e("code", "code=签收状态==" + code);
                Log.e("msg", "msg=签收状态==" + msg);
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

    public void getAddShopCar(String goods_id, String product_id, String number,String articleId) {
        addToRxLife(MainRequest.getAddShopCar(goods_id, product_id, number,articleId, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getAddShopCarSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddShopCarFail(code, msg);
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
