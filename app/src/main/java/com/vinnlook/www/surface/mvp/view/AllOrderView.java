package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.http.model.VersionBean;

/**
 * @Description:
 * @Time:2020/4/2$
 * @Author:pk$
 */
public interface AllOrderView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getOrderListSuccess(int code, AllOrderListBean data);

    void getOrderListFail(int code, String msg);

    void getCelearOrderSuccess(int code, AllOrderListBean data);

    void getCelearOrderFail(int code, String msg);

    void getSignInOrderSuccess(int code, SiginOrderBean data);

    void getSignInOrderFail(int code, String msg);

    void getCelearOrderSuccess_1(int code, AllOrderListBean data);

    void getCelearOrderFail_1(int code, String msg);
}
