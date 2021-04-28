package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.RankingListView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/22$
 * @Author:pk$
 */
public class RankingListPresenter extends MvpPresenter<RankingListView> {
    /**
     * 排行榜title 数据
     */
    public void getRankingTabData() {
        addToRxLife(MainRequest.getRankingTabData(new RequestBackListener<List<RankingTabBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RankingTabBean> data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getRankingTabDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getRankingTabDataFail(code, msg);
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

    public void getTypeReBangList(String posion) {
        addToRxLife(MainRequest.getTypeReBangList(posion, new RequestBackListener<List<ReBangListBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ReBangListBean> data) {
                if (isAttachView())
                    getBaseView().getTypeReBangListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeReBangListFail(code, msg);
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
