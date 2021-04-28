package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ApplyListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ApplyRefundListView;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class ApplyRefundListPresenter extends MvpPresenter<ApplyRefundListView> {
    public void getApplyList(int page, int limit) {
        addToRxLife(MainRequest.getApplyList(page, limit, new RequestBackListener<ApplyListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ApplyListBean data) {
                if (isAttachView())
                    getBaseView().getApplyListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getApplyListFail(code, msg);
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
