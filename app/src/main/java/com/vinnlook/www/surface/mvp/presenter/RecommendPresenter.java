package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.surface.bean.HomePublicListBean;
import com.vinnlook.www.surface.bean.TypeGoodsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.RecommendView;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class RecommendPresenter extends MvpPresenter<RecommendView> {

    public void getTypeGoods(int page, int limit, String iD) {
        addToRxLife(MainRequest.getTypeGoods(page, limit, iD, new RequestBackListener<TypeGoodsBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, TypeGoodsBean data) {
                if (isAttachView())
                    getBaseView().getTypeGoodsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeGoodsFail(code, msg);
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

    public void getDataList(int page, int limit, String suppliersId, String isColor, String tossPeriod,String goods_name,String sort_key,String sort_value,String attrId,String getId) {
        addToRxLife(MainRequest.getDataList(page, limit, suppliersId,isColor,tossPeriod,goods_name,sort_key,sort_value,attrId,getId, new RequestBackListener<HomePublicListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, HomePublicListBean data) {
                if (isAttachView())
                    getBaseView().getHomePublicSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getHomePublicFail(code, msg);
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

    //广告商品列表
    public void getGoodsList(int page, int limit, String iD) {

        addToRxLife(MainRequest.getGoodsList(page, limit, iD, new RequestBackListener<HomeGoodsListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, HomeGoodsListBean data) {
                if (isAttachView())
                    getBaseView().getGoodsListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getGoodsListFail(code, msg);
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
