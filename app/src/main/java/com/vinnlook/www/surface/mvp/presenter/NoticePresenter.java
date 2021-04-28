package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.NoticeListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.NoticeView;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class NoticePresenter extends MvpPresenter<NoticeView> {
    //获取消息列表
    public void getMessageList(int page, int limit) {
        addToRxLife(MainRequest.getMessageList(page, limit, new RequestBackListener<NoticeListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, NoticeListBean data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getMessageListSuccess(code, data);

            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getMessageListFail(code, msg);
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
