package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.MsggingTypeBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.MsggingBoxView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/2$
 * @Author:pk$
 */
public class MsggingBoxPresenter extends MvpPresenter<MsggingBoxView> {
    public void getTypeListData() {
        addToRxLife(MainRequest.getTypeListData(new RequestBackListener<List<MsggingTypeBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<MsggingTypeBean> data) {
                if (isAttachView())
                    getBaseView().getTypeListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeListDataFail(code, msg);
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

    public void getClearUnread() {
        addToRxLife(MainRequest.getClearUnread(new RequestBackListener<List<MsggingTypeBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<MsggingTypeBean> data) {
                if (isAttachView())
                    getBaseView().getTypeListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeListDataFail(code, msg);
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
