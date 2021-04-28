package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.AddressView;

import java.util.List;

public class AddressPresenter extends MvpPresenter<AddressView> {



    public void getAddressData() {
        addToRxLife(MainRequest.getAddressindex(new RequestBackListener<List<AddressListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AddressListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getAddressDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddressDataFail(code, msg);
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


    /**
     * @Description:更改地址信息
     * @Time:2020/4/30 12:57
     * @Author:pk
     */
    public void getEditAdress(String getIs_default, String getAddress_id) {
        addToRxLife(MainRequest.getEditressindex_1(getIs_default, getAddress_id, new RequestBackListener<List<AddressListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AddressListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getEditAddressDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getEditAddressDataFail(code, msg);
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

    /**
     * @Description:删除地址信息
     * @Time:2020/4/30 12:57
     * @Author:pk
     */
    public void getDeleteAdress (String getAddress_id) {
        addToRxLife(MainRequest.getDeleteAddressindex(getAddress_id, new RequestBackListener<List<AddressListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AddressListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getDeleteAddressDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getDeleteAddressDataFail(code, msg);
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
