package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.SetMealBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.SetMealView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/25$
 * @Author:pk$
 */
public class SetMealPresenter extends MvpPresenter<SetMealView> {

    public void getMealListData(String goods_id) {
        addToRxLife(MainRequest.getMealListData(goods_id, new RequestBackListener<List<SetMealBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<SetMealBean> data) {
                if (isAttachView())
                    getBaseView().getMealListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getMealListDataFail(code, msg);
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

    //加入购物车
    public void addShoppingCarData(String act_id, StringBuilder productIdSB) {
        addToRxLife(MainRequest.addShoppingCarData(act_id,productIdSB, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getAddShoppingCarSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddShoppingCarFail(code, msg);
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
