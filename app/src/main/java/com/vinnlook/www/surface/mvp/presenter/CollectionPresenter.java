package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.CollectionView;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CollectionPresenter extends MvpPresenter<CollectionView> {

    public void getCollectionListData(int page, int limit) {
        addToRxLife(MainRequest.getCollectionListData(page, limit, new RequestBackListener<CollectionListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, CollectionListBean data) {
                if (isAttachView())
                    getBaseView().getCollectionListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView()) {
                        getBaseView().getCollectionListFail(code, msg);

                }
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
