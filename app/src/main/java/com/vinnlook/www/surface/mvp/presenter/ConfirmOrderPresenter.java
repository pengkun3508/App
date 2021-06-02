package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ConfirmOrderView;

import java.util.List;

import retrofit2.http.Field;

public class ConfirmOrderPresenter extends MvpPresenter<ConfirmOrderView> {

    public void getConfirmOrderData(String recId, String goods_id, String product_id, String num, String wayBillId, String integralNum, String address_id,
                                    String id, String ht_sendid, String zy_sendid, String proIdSb,String group_info,String group_id) {

        addToRxLife(MainRequest.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, integralNum, address_id, id, ht_sendid, zy_sendid, proIdSb,group_info, group_id,new RequestBackListener<ConfirmOrderBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ConfirmOrderBean data) {
                if (isAttachView())
                    getBaseView().getConfirmOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getConfirmOrderFail(code, msg);
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

    public void getConfirmOrderData1(String recId, String goods_id, String product_id, String num, String mark, String address_id) {
        addToRxLife(MainRequest.getConfirmOrderData1(recId, goods_id, product_id, num, mark, address_id, new RequestBackListener<ConfirmOrderBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ConfirmOrderBean data) {
                if (isAttachView())
                    getBaseView().getConfirmOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getConfirmOrderFail(code, msg);
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

    public void getTypeShopData(String getGoods_id) {
        addToRxLife(MainRequest.getTypeShopData(getGoods_id, new RequestBackListener<MoveDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveDataBean data) {
                if (isAttachView())
                    getBaseView().getTypeShopSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeShopFail(code, msg);
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

    public void getConfirmTypeData(String recId, String goods_id, String product_id, String num, String ht_sendid, String zy_sendid, String proIdSb) {

        addToRxLife(MainRequest.getConfirmTypeData(recId, goods_id, product_id, num, ht_sendid, zy_sendid, proIdSb, new RequestBackListener<ConfirmOrderBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ConfirmOrderBean data) {
                if (isAttachView())
                    getBaseView().getConfirmOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getConfirmOrderFail(code, msg);
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

    public void getCouponListData1(String recId, String goods_ids, String product_ids, String nums,String group_info) {
        addToRxLife(MainRequest.getCouponListData1(recId, goods_ids, product_ids, nums,group_info, new RequestBackListener<List<AlreadyCouponListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AlreadyCouponListBean> data) {
                if (isAttachView())
                    getBaseView().getCouponList1Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCouponList1Fail(code, msg);
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