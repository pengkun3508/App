package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.TypeGoodsBean;
import com.vinnlook.www.surface.bean.WaybillListBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.SelectLogisticsView;
import com.vinnlook.www.surface.mvp.view.SetingView;

import java.util.List;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class SelectLogisticsPresenter   extends MvpPresenter<SelectLogisticsView> {
    public void getLogisticsList() {
        addToRxLife(MainRequest.getLogisticsList(new RequestBackListener<List<WaybillListBean>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<WaybillListBean> data) {
                if (isAttachView())
                    getBaseView().getLogisticsListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getLogisticsListFail(code, msg);
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
