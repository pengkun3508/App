package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.MsggingListBean;
import com.vinnlook.www.surface.bean.MsggingTypeBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.MsggingBoxView;
import com.vinnlook.www.surface.mvp.view.MsggingListView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingListPresenter  extends MvpPresenter<MsggingListView> {
    public void getPushListData(int page, int limit, String getType) {
        addToRxLife(MainRequest.getPushListData(page,limit,getType,new RequestBackListener<MsggingListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, MsggingListBean data) {
                if (isAttachView())
                    getBaseView().getPushListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPushListDataFail(code, msg);
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
