package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.surface.bean.SearchListListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.SearchListView;

/**
 * @Description:
 * @Time:2020/5/25$
 * @Author:pk$
 */
public class SearchListPresenter extends MvpPresenter<SearchListView> {

    public void getSearchListData(int page, int limit, String keyword,String sort_key,String sort_value) {

        addToRxLife(MainRequest.getSearchListData(page, limit, keyword,sort_key,sort_value, new RequestBackListener<SearchListListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SearchListListBean data) {
                if (isAttachView())
                    getBaseView().getSearchListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSearchListDataFail(code, msg);
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
