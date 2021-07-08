package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.surface.bean.GuangThemBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.GuangFragmentView;
import com.vinnlook.www.surface.mvp.view.Guang_1FragmentView;

/**
 * @Description:
 * @Time:2021/6/28$
 * @Author:pk$
 */
public class Guang_1FragmentPresenter extends MvpPresenter<Guang_1FragmentView> {
    public void getThemData() {
        addToRxLife(MainRequest.getThemData(new RequestBackListener<GuangThemBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, GuangThemBean data) {
                if (isAttachView())
                    getBaseView().getThemDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code==主题乐园==" + code);
                Log.e("msg", "msg==主题乐园==" + msg);
                if (isAttachView())
                    getBaseView().getThemDataFail(code, msg);
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
