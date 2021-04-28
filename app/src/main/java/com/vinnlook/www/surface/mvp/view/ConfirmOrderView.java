package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;

import java.util.List;

public interface ConfirmOrderView extends MvpView {

    void getConfirmOrderSuccess(int code, ConfirmOrderBean data);

    void getConfirmOrderFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getCouponList1Success(int code, List<AlreadyCouponListBean> data);

    void getCouponList1Fail(int code, String msg);
}
