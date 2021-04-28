package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.bean.HomeTab2Bean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HomeTab1FragmentView;
import com.vinnlook.www.surface.mvp.view.HomeTab2FragmentView;

/**
 * @Description:
 * @Time:2021/3/31$
 * @Author:pk$
 */
public class HomeTab2FragmentPresenter  extends MvpPresenter<HomeTab2FragmentView> {
    public void getBrendList() {
        addToRxLife(MainRequest.getBrendList(new RequestBackListener<HomeTab2Bean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, HomeTab2Bean data) {
                if (isAttachView())
                    getBaseView().getHomeTab2DataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getHomeTab2DataFail(code, msg);
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


}
