package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.AllOrderView;

/**
 * @Description:
 * @Time:2020/4/2$
 * @Author:pk$
 */
public class AllOrderPresenter extends MvpPresenter<AllOrderView> {

    public void getOrderListData(int page, int limit, int type) {
        addToRxLife(MainRequest.getOrderListData(page, limit, type, new RequestBackListener<AllOrderListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, AllOrderListBean data) {
                if (isAttachView())
                    getBaseView().getOrderListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getOrderListFail(code, msg);
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

    //删除订单
    public void getCelearOrderData(String order_id, String type) {
        addToRxLife(MainRequest.getCelearOrderData(order_id, type, new RequestBackListener<AllOrderListBean>() {
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
    //取消订单
    public void getCelearOrderData_1(String order_id, String type) {
        addToRxLife(MainRequest.getCelearOrderData_1(order_id, type, new RequestBackListener<AllOrderListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, AllOrderListBean data) {
                if (isAttachView())
                    getBaseView().getCelearOrderSuccess_1(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCelearOrderFail_1(code, msg);

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

    //取消订单
    public void getSignInOrderData(String order_id) {
        addToRxLife(MainRequest.getSignInOrderData(order_id, new RequestBackListener<SiginOrderBean>() {
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
}
