package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CollectionList2Bean;
import com.vinnlook.www.http.model.CollectionListBean;
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
                Log.e("code", "=商品--code=====" + code);
                Log.e("msg", "=商品--code=====" + msg);
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

    public void getCollectionList2Data(int page, int limit, String type) {
        addToRxLife(MainRequest.getCollectionList2Data(page, limit, type, new RequestBackListener<CollectionList2Bean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, CollectionList2Bean data) {
                if (isAttachView())
                    getBaseView().getCollectionList2Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "=文章--code=====" + code);
                Log.e("msg", "=文章--code=====" + msg);
                if (isAttachView()) {
                    getBaseView().getCollectionList2Fail(code, msg);

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
