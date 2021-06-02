package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.NotCouponListBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.NewNotCouponListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.CouponView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CouponPresenter extends MvpPresenter<CouponView> {
    /**
     * @Description:下载优惠券列表--未领取
     * @Time:2020/5/8 9:39
     * @Author:pk
     */
    public void getCouponListData(String recIds, String goods_ids, String product_ids, String nums) {
        addToRxLife(MainRequest.getCouponListData(recIds, goods_ids, product_ids, nums, new RequestBackListener<List<NotCouponListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<NotCouponListBean> data) {
                if (isAttachView())
                    getBaseView().getCouponListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCouponListFail(code, msg);
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

//    /**
//     * @Description:下载优惠券列表--已领取
//     * @Time:2020/5/8 9:39
//     * @Author:pk
//     */
//    public void getCouponListData1(String recIds, String goods_ids, String product_ids, String nums) {
//        addToRxLife(MainRequest.getCouponListData1(recIds, goods_ids, product_ids, nums, new RequestBackListener<List<AlreadyCouponListBean>>() {
//            @Override
//            public void onStart() {
//                showLoading();
//            }
//
//            @Override
//            public void onSuccess(int code, List<AlreadyCouponListBean> data) {
//                if (isAttachView())
//                    getBaseView().getCouponList1Success(code, data);
//            }
//
//            @Override
//            public void onFailed(int code, String msg) {
//                Log.e("code", "code===" + code);
//                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getCouponList1Fail(code, msg);
//            }
//
//            @Override
//            public void onNoNet() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                dismissLoading();
//            }
//        }));
//
//    }

    /**
     * 领取优惠券
     *
     * @param type_id
     */
    public void getCollectCoupons(String type_id) {
        addToRxLife(MainRequest.getCollectCoupons(type_id, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {

                if (isAttachView())
                    getBaseView().getCollectCouponsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCollectCouponsFail(code, msg);
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

//    public void getConfirmOrderData1(String recId, String goods_id, String product_id, String num, String type_id, String address_id) {
//
//        addToRxLife(MainRequest.getConfirmOrderData1(recId, goods_id, product_id, num, type_id, address_id, new RequestBackListener<ConfirmOrderBean>() {
//            @Override
//            public void onStart() {
//                showLoading();
//            }
//
//            @Override
//            public void onSuccess(int code, ConfirmOrderBean data) {
//                if (isAttachView())
//                    getBaseView().getConfirmOrderSuccess(code, data);
//            }
//
//            @Override
//            public void onFailed(int code, String msg) {
//                Log.e("code", "code===" + code);
//                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getConfirmOrderFail(code, msg);
//            }
//
//            @Override
//            public void onNoNet() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                dismissLoading();
//            }
//        }));
//
//    }

    //新优惠券列表-待领取，已领取，已使用，已过期
    public void getNewCounponList(String type) {
        addToRxLife(MainRequest.getNewCounponList(type, new RequestBackListener<List<NewNotCouponListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<NewNotCouponListBean> data) {
                if (isAttachView())
                    getBaseView().getNewCouponListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "获取优惠券==code===" + code);
                Log.e("msg", "获取优惠券==msg===" + msg);
                if (isAttachView())
                    getBaseView().getNewCouponListFail(code, msg);
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
