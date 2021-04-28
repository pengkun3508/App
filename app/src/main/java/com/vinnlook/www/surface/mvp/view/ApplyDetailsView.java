package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.bean.ApplyDetailsBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/16$
 * @Author:pk$
 */
public interface ApplyDetailsView extends MvpView {
    void getApplyDetailsSuccess(int code, ApplyDetailsBean data);

    void getApplyDetailsFail(int code, String msg);

    void getApplyCancelSuccess(int code, Object data);

    void getApplyCancelFail(int code, String msg);

    void getOederDetailsSuccess(int code, OrderDetailsBean data);

    void getOederDetailsFail(int code, String msg);
}
