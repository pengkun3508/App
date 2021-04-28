package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.OrderLogisticsBean;
import com.vinnlook.www.surface.bean.WayBillLogisticsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.LoginView;
import com.vinnlook.www.surface.mvp.view.LogisticsView;
import com.vinnlook.www.utils.UserInfoBean;

public class LogisticsPresenter extends MvpPresenter<LogisticsView> {
    public void getOrderLogistics(String order_id) {
        addToRxLife(MainRequest.getOrderLogistics(order_id, new RequestBackListener<OrderLogisticsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, OrderLogisticsBean data) {
                if (isAttachView())
                    getBaseView().getOrderLogisticsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getOrderLogisticsFail(code, msg);
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

    public void getWayBillLogistics(String wayId) {
        addToRxLife(MainRequest.getWayBillLogistics(wayId, new RequestBackListener<WayBillLogisticsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, WayBillLogisticsBean data) {
                if (isAttachView())
                    getBaseView().getWayBillLogisticsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getWayBillLogisticsFail(code, msg);
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
