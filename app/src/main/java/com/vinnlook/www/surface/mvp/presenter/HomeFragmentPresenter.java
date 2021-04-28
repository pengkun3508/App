package com.vinnlook.www.surface.mvp.presenter;


import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public class HomeFragmentPresenter extends MvpPresenter<HomeFragmentView> {

    public void getHomeData() {
        addToRxLife(MainRequest.getHomeindex(new RequestBackListener<HomeDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, HomeDataBean data) {
                if (isAttachView())
                    getBaseView().getHomeDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getHomeDataFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("getHomeData","===Throwable=="+e);

            }

            @Override
            public void onFinish() {
//                dismissLoading();
            }
        }));
    }


    public void getAppUpdate() {
        addToRxLife(MainRequest.getAppUpdate( new RequestBackListener<SignBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SignBean data) {
                if (isAttachView())
                    getBaseView().getAppUpdateSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAppUpdateFail(code, msg);
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

    public void getTypeShopData_1(String getGoods_id) {
        addToRxLife(MainRequest.getTypeShopData(getGoods_id, new RequestBackListener<MoveDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveDataBean data) {
                if (isAttachView())
                    getBaseView().getTypeShopSuccess_1(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeShopFail_1(code, msg);
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
