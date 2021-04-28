package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HomeTab1FragmentView;

/**
 * @Description:
 * @Time:2021/3/26$
 * @Author:pk$
 */
public class HomeTab1FragmentPresenter  extends MvpPresenter<HomeTab1FragmentView> {
    public void getHomeTab1Data() {
        addToRxLife(MainRequest.getHomeTab1Data(new RequestBackListener<HomeTab1Bean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, HomeTab1Bean data) {
                if (isAttachView())
                    getBaseView().getHomeTab1DataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getHomeTab1DataFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("getHomeData","===Throwable=="+e);

            }

            @Override
            public void onFinish() {
//                dismissLoading();
            }
        }));
    }

    public void getAppUpdate() {
        addToRxLife(MainRequest.getAppUpdate( new RequestBackListener<SignBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SignBean data) {
                if (isAttachView())
                    getBaseView().getAppUpdateSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAppUpdateFail(code, msg);
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
