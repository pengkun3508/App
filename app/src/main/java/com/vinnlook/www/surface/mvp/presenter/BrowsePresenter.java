package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.surface.bean.BrowseListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.BrandView;
import com.vinnlook.www.surface.mvp.view.BrowseView;

/**
 * @Description:
 * @Time:2020/9/9$
 * @Author:pk$
 */
public class BrowsePresenter extends MvpPresenter<BrowseView> {
    public void getBrowseListData(int page, int limit) {
        addToRxLife(MainRequest.getBrowseListData(page, limit, new RequestBackListener<BrowseListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, BrowseListBean data) {
                if (isAttachView())
                    getBaseView().getBrowseListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView()) {
                    getBaseView().getBrowseListFail(code, msg);

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
