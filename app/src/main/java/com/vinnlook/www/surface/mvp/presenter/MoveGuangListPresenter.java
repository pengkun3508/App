package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GuangSelectBean;
import com.vinnlook.www.surface.bean.MoveGuangListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.MoveGuangListView;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class MoveGuangListPresenter extends MvpPresenter<MoveGuangListView> {
    public void getMoveGuangList(int page, int limit, String shopId) {
        addToRxLife(MainRequest.getMoveGuangList(page,limit,shopId,new RequestBackListener<MoveGuangListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveGuangListBean data) {
                if (isAttachView())
                    getBaseView().getMoveGuangListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code==Move-guang==" + code);
                Log.e("msg", "msg==Move-guang==" + msg);
                if (isAttachView())
                    getBaseView().getMoveGuangListFail(code, msg);
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
