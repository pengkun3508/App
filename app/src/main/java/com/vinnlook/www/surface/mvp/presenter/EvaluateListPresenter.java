package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.EvaluateListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.EvaluateListView;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class EvaluateListPresenter extends MvpPresenter<EvaluateListView> {
    public void getEvaluateList(int page, int limit, String goods_id) {
        addToRxLife(MainRequest.getEvaluateList(page,limit,goods_id,new RequestBackListener<EvaluateListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, EvaluateListBean data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getEvaluateListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())

                    getBaseView().getEvaluateListFail(code, msg);
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
