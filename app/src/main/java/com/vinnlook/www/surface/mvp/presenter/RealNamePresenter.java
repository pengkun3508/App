package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.RealNameView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNamePresenter extends MvpPresenter<RealNameView> {

    public void getRealNameListData() {
        addToRxLife(MainRequest.getRealNameListData(new RequestBackListener<List<RealNameListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RealNameListBean> data) {
                if (isAttachView())
                    getBaseView().getRealNameListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getRealNameListFail(code, msg);
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

    public void getEditRealName(String id, String is_default) {
        addToRxLife(MainRequest.getEditRealName(id, is_default, new RequestBackListener<List<RealNameListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RealNameListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getEditRealNameSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getEditRealNameFail(code, msg);
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

    public void getDeletRealName(String id) {
        addToRxLife(MainRequest.getDeletRealName(id,  new RequestBackListener<List<RealNameListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RealNameListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getDeletRealNameSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getDeletRealNameFail(code, msg);
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
