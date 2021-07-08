package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.ArticleDetailsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ArticleDetailsView;

/**
 * @Description:主题-文章详情-1
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class ArticleDetailsPresenter extends MvpPresenter<ArticleDetailsView> {
    public void getArticleData(String iD) {
        addToRxLife(MainRequest.getArticleData(iD, new RequestBackListener<ArticleDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ArticleDetailsBean data) {
                if (isAttachView())
                    getBaseView().getArticleSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getArticleFail(code, msg);
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

    //点赞
    public void getGiveData(String id, int type) {
        addToRxLife(MainRequest.getGiveData(id, type, new RequestBackListener<Object>() {
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
    public void getCollectData(String id, int collectType) {
        addToRxLife(MainRequest.getCollectData(id, collectType, new RequestBackListener<Object>() {
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
