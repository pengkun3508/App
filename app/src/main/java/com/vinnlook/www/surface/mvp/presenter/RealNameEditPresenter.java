package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.RealNameDetailsBean;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.RealNameEditView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNameEditPresenter extends MvpPresenter<RealNameEditView> {

    /**
     * @Description:实名详情
     * @Time:2020/5/9 17:07
     * @Author:pk
     */
    public void getRealNameDetails(String id) {

        addToRxLife(MainRequest.getRealNameDetails(id, new RequestBackListener<RealNameDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, RealNameDetailsBean data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getRealNameDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getRealNameDetailsFail(code, msg);
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

    /**
     * @Description:新增实名
     * @Time:2020/5/9 17:07
     * @Author:pk
     */
    public void getAddRealName(String name, String idcard, String is_default) {
        addToRxLife(MainRequest.getAddRealName(name,idcard,is_default,new RequestBackListener<List<RealNameListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RealNameListBean> data) {
                if (isAttachView())
                    getBaseView().getAddRealNameSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddRealNameFail(code, msg);
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

    /**
     * @Description:编辑实名
     * @Time:2020/5/9 17:07
     * @Author:pk
     */
    public void getRealNameEdit(String name, String idcard, String getIs_default, String getId) {
        addToRxLife(MainRequest.getRealNameEdit(name,idcard,getIs_default,getId,new RequestBackListener<List<RealNameListBean>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<RealNameListBean> data) {
                if (isAttachView())
                    getBaseView().getRealNameEditSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getRealNameEditFail(code, msg);
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
