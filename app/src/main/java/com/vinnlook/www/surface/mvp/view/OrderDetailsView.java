package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.http.model.VersionBean;

public interface OrderDetailsView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getOederDetailsSuccess(int code, OrderDetailsBean data);

    void getOederDetailsFail(int code, String msg);

    void getCelearOrderSuccess(int code, AllOrderListBean data);

    void getCelearOrderFail(int code, String msg);

    void getSignInOrderSuccess(int code, SiginOrderBean data);

    void getSignInOrderFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);
}
