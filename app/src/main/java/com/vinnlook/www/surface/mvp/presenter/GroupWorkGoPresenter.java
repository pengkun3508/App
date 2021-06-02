package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.EvaluateListView;
import com.vinnlook.www.surface.mvp.view.GroupWorkGoView;

/**
 * @Description:
 * @Time:2021/4/15$
 * @Author:pk$
 */
public class GroupWorkGoPresenter  extends MvpPresenter<GroupWorkGoView> {
    public void getGroupListData(int page, int limit) {

        addToRxLife(MainRequest.getGroupListData(page,limit, new RequestBackListener<GroupListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, GroupListBean data) {
                if (isAttachView())
                    getBaseView().getGroupListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getGroupListFail(code, msg);
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
