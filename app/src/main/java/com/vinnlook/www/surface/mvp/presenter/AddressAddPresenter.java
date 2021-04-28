package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.AddressJsonBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.model.bean.AddressDetailsBean;
import com.vinnlook.www.surface.mvp.view.AddressAddView;

import java.util.List;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019-12-24
 */
public class AddressAddPresenter extends MvpPresenter<AddressAddView> {

    /**
     * @Description:下载地址详情
     * @Time:2020/4/30 12:57
     * @Author:pk
     */
    public void getAddressDetails(String getAddress_id) {
        addToRxLife(MainRequest.getAddressDetails(getAddress_id, new RequestBackListener<AddressDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, AddressDetailsBean data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getAddressDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddressDetailsFail(code, msg);
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
    public void getEditAdress(String pId, String cId, String dId, String name, String phone, String address, String getIs_default, String getAddress_id) {
        addToRxLife(MainRequest.getEditressindex(pId, cId, dId, name, phone, address, getIs_default, getAddress_id, new RequestBackListener<List<AddressListBean>>() {
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
     * @Description:新增地址信息
     * @Time:2020/4/30 12:57
     * @Author:pk
     */
    public void getAddAdress(String pId, String cId, String dId, String name, String phone, String address, String getIs_default ) {
        addToRxLife(MainRequest.getAddAddressindex(pId, cId, dId, name, phone, address, getIs_default, new RequestBackListener<List<AddressListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AddressListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getAddAddressDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddAddressDataFail(code, msg);
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


    public void getAddressJson() {

        addToRxLife(MainRequest.getAddressJson( new RequestBackListener<List<AddressJsonBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<AddressJsonBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getAddressJsonSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddressJsonFail(code, msg);
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
