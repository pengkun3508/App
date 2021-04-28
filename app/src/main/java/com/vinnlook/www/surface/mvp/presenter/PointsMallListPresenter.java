package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.PointsMallBean;
import com.vinnlook.www.surface.bean.PointsMallListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PointsMallListView;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallListPresenter  extends MvpPresenter<PointsMallListView> {
    public void getPointsMallList(int page, int limit) {
        addToRxLife(MainRequest.getPointsMallList(page,limit,new RequestBackListener<PointsMallListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, PointsMallListBean data) {
                if (isAttachView())
                    getBaseView().getPointsMallListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPointsMallListFail(code, msg);
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
