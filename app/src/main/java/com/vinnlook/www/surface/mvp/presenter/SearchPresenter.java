package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.surface.bean.SearchListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PurchaseView;
import com.vinnlook.www.surface.mvp.view.SearchView;

import java.util.List;

public class SearchPresenter extends MvpPresenter<SearchView> {

    public void getSearchData() {
        addToRxLife(MainRequest.getSearchData(new RequestBackListener<SearchListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SearchListBean data) {
                if (isAttachView())
                    getBaseView().getSearchDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSearchDataFail(code, msg);
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

    public void getSearchList(String keywords) {
        addToRxLife(MainRequest.getSearchList(keywords,new RequestBackListener<List<String>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<String> data) {
                if (isAttachView())
                    getBaseView().getSearchListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSearchListFail(code, msg);
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
