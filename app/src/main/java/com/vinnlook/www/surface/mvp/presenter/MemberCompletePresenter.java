package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.MemberCompleteView;
import com.vinnlook.www.surface.mvp.view.MemberView;

/**
 * @Description:
 * @Time:2020/8/19$
 * @Author:pk$
 */
public class MemberCompletePresenter extends MvpPresenter<MemberCompleteView> {
    public void getMemberDetailData() {
        addToRxLife(MainRequest.getMemberDetailData(new RequestBackListener<MemberBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, MemberBean data) {
                if (isAttachView())
                    getBaseView().getMemberDetailSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getMemberDetailFail(code, msg);
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
