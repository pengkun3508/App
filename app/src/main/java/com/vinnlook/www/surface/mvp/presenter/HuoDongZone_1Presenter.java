package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.HuoDong2Bean;
import com.vinnlook.www.surface.bean.HuoDongBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;
import com.vinnlook.www.surface.mvp.view.HuoDongZone_1View;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDongZone_1Presenter extends MvpPresenter<HuoDongZone_1View> {
    public void getActivityList() {
        addToRxLife(MainRequest.getActivityList(new RequestBackListener<List<HuoDong2Bean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<HuoDong2Bean> data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getActivityListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getActivityListFail(code, msg);
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
