package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.HuoDongBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HuoDongZoneView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/31$
 * @Author:pk$
 */
public class HuoDongZonePresenter extends MvpPresenter<HuoDongZoneView> {
    public void getHuoDongList() {
        addToRxLife(MainRequest.getHuoDongList(new RequestBackListener<List<HuoDongBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<HuoDongBean> data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getHuoDongListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getHuoDongListFail(code, msg);
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

    public void getTypeShopData(String goods_id) {
        addToRxLife(MainRequest.getTypeShopData(goods_id, new RequestBackListener<MoveDataBean>() {
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

    public void getAddShopCar(String goods_id, String product_id, String num) {
        addToRxLife(MainRequest.getAddShopCar(goods_id, product_id,num, new RequestBackListener<Object>() {
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
