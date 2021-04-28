package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.OrderLogisticsBean;
import com.vinnlook.www.surface.bean.WayBillLogisticsBean;
import com.vinnlook.www.utils.UserInfoBean;

public interface LogisticsView extends MvpView {
    void getOrderLogisticsSuccess(int code, OrderLogisticsBean data);

    void getOrderLogisticsFail(int code, String msg);

    void getWayBillLogisticsSuccess(int code, WayBillLogisticsBean data);

    void getWayBillLogisticsFail(int code, String msg);
}
