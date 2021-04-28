package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.CommodityTitleBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.CommodityView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CommodityPresenter extends MvpPresenter<CommodityView> {
    public void getCommodityList(int page, int limit, String getBrand_id,String getFlood,String getPieces,String type) {
        addToRxLife(MainRequest.getCommodityList(page, limit, getBrand_id,getFlood,getPieces,type, new RequestBackListener<CommodityListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, CommodityListBean data) {
                if (isAttachView())
                    getBaseView().getCommodityListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCommodityListFail(code, msg);
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

    public void getCommodityTitle(String getBrand_id,String actId,String type) {
        addToRxLife(MainRequest.getCommodityTitle(getBrand_id,actId,type, new RequestBackListener<List<CommodityTitleBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<CommodityTitleBean> data) {
                if (isAttachView())
                    getBaseView().getCommodityTitleSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCommodityTitleFail(code, msg);
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
