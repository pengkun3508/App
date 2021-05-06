package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.surface.bean.CompanyBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.CompanyView;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/29$
 * @Author:pk$
 */
public class CompanyPresenter extends MvpPresenter<CompanyView> {
    public void getCompanyListData() {
        addToRxLife(MainRequest.getCompanyListData(new RequestBackListener<List<CompanyBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<CompanyBean> data) {
                if (isAttachView())
                    getBaseView().getCompanyListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getCompanyListFail(code, msg);
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
