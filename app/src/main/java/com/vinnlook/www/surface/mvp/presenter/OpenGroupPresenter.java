package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.OpenGroupView;

/**
 * @Description:
 * @Time:2021/5/11$
 * @Author:pk$
 */
public class OpenGroupPresenter extends MvpPresenter<OpenGroupView> {
    public void getGroupInfoData(String orderId) {

        addToRxLife(MainRequest.getGroupInfoData(orderId, new RequestBackListener<GroupDetailsBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, GroupDetailsBean data) {
                if (isAttachView())
                    getBaseView().getGroupDetailsSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getGroupDetailsFail(code, msg);
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
