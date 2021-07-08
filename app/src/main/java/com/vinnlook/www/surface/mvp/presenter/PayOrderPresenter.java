package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PayOrderView;

/**
 * @Description:
 * @Time:2020/5/14$
 * @Author:pk$
 */
public class PayOrderPresenter extends MvpPresenter<PayOrderView> {
    public void postSubmitOrder(String recIds, String goods_ids, String product_ids, String nums, String real_ids, String address_ids, String types,
                                String confirmMessages, String confirmMessage2, String order_ids, String bonus_id, String waybill_id, String zYSb,
                                String hTSb,String group_info,String group_id,String articleId) {

        addToRxLife(MainRequest.postSubmitOrder(recIds, goods_ids, product_ids, nums, real_ids, address_ids, types, confirmMessages, confirmMessage2, order_ids, bonus_id, waybill_id,zYSb,hTSb,
                group_info,group_id,articleId,new RequestBackListener<WeCatPayBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, WeCatPayBean data) {
                Log.e("code", "=code===" + code);
                Log.e("msg", "=msg===" + data);
                if (isAttachView())
                    getBaseView().getPostSubmitOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPostSubmitOrderFail(code, msg);
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


    public void postALiSubmitOrder(String recIds, String goods_ids, String product_ids, String nums, String real_ids, String address_ids, String types, String confirmMessages,
                                   String confirmMessage2, String order_ids, String bonus_id, String waybill_id, String zYSb, String hTSb,String group_info,String group_id,String articleId) {

        addToRxLife(MainRequest.postALiSubmitOrder(recIds, goods_ids, product_ids, nums, real_ids, address_ids, types, confirmMessages, confirmMessage2, order_ids, bonus_id,
                waybill_id, zYSb,hTSb,group_info,group_id,articleId,new RequestBackListener<ALiPayBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ALiPayBean data) {
                dismissLoading();
                Log.e("code", "=code===" + code);
                Log.e("msg", "=msg===" + data);
                if (isAttachView())
                    getBaseView().getPostALiSubmitOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                dismissLoading();
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPostALiSubmitOrderFail(code, msg);
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
