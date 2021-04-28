package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.SavingOrderBean;
import com.vinnlook.www.surface.bean.SearchListListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.SavingOrdersView;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class SavingOrdersPresenter extends MvpPresenter<SavingOrdersView> {

    public void getSavingOrdersList(int page, int limit) {

        addToRxLife(MainRequest.getSavingOrdersList(page, limit, new RequestBackListener<SavingOrderBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SavingOrderBean data) {
                if (isAttachView())
                    getBaseView().getSavingOrdersSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSavingOrdersFail(code, msg);
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
