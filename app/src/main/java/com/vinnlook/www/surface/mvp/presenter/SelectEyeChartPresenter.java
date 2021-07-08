package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.EyeChartDetailsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.SelectEyeChartView;

/**
 * @Description:精选眼图-详情
 * @Time:2021/7/5$
 * @Author:pk$
 */
public class SelectEyeChartPresenter extends MvpPresenter<SelectEyeChartView> {
    public void getEyeChartData(String iD) {
        addToRxLife(MainRequest.getEyeChartData(iD, new RequestBackListener<EyeChartDetailsBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, EyeChartDetailsBean data) {
                if (isAttachView())
                    getBaseView().getEyeChartDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "==精选眼图-详情==code===" + code);
                Log.e("msg", "==精选眼图-详情==msg===" + msg);
                if (isAttachView())
                    getBaseView().getEyeChartDataFail(code, msg);
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

    //点赞
    public void getGiveData(String articleId, int giveType) {
        addToRxLife(MainRequest.getGiveData(articleId, giveType, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getGiveDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "==点赞==code===" + code);
                Log.e("msg", "==点赞==msg===" + msg);
                if (isAttachView())
                    getBaseView().getGiveDataFail(code, msg);
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

    //收藏
    public void getCollectData(String articleId, int collectType) {
        addToRxLife(MainRequest.getCollectData(articleId, collectType, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getCollectDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "==收藏==code===" + code);
                Log.e("msg", "==收藏==msg===" + msg);
                if (isAttachView())
                    getBaseView().getCollectDataFail(code, msg);
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
