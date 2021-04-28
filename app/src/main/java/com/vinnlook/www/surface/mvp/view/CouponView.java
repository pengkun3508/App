package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.CouponListBean;
import com.vinnlook.www.http.model.NotCouponListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.NewNotCouponListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public interface CouponView extends MvpView {
    void getCouponListSuccess(int code, List<NotCouponListBean> data);

    void getCouponListFail(int code, String msg);

    void getCollectCouponsSuccess(int code, Object data);

    void getCollectCouponsFail(int code, String msg);

    void getConfirmOrderSuccess(int code, ConfirmOrderBean data);

    void getConfirmOrderFail(int code, String msg);

    void getCouponList1Success(int code, List<AlreadyCouponListBean> data);

    void getCouponList1Fail(int code, String msg);

    void getNewCouponListSuccess(int code, List<NewNotCouponListBean> data);

    void getNewCouponListFail(int code, String msg);
}
