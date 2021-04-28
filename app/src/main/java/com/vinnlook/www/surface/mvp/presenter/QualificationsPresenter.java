package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.CertifyListBean;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.QualificationsView;
import com.vinnlook.www.surface.mvp.view.RankFragmentView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/19$
 * @Author:pk$
 */
public class QualificationsPresenter extends MvpPresenter<QualificationsView> {
    public void getCertifyList() {
        addToRxLife(MainRequest.getCertifyList(new RequestBackListener<List<CertifyListBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<CertifyListBean> data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getCertifyListSuccess(code, data);

            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCertifyListFail(code, msg);
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
