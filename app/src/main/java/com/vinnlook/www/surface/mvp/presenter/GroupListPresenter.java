package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.surface.bean.GroupOrderListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.GroupListView;
import com.vinnlook.www.surface.mvp.view.HaiTaoClassView;

/**
 * @Description:
 * @Time:2021/5/10$
 * @Author:pk$
 */
public class GroupListPresenter  extends MvpPresenter<GroupListView> {


    public void getGroupOrderListData(int page, int limit, String type) {
        addToRxLife(MainRequest.getGroupOrderListData(page,limit,type, new RequestBackListener<GroupOrderListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, GroupOrderListBean data) {
                if (isAttachView())
                    getBaseView().getGroupOrderListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getGroupOrderListFail(code, msg);
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
