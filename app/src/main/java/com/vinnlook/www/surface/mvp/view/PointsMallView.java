package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ExchangeBean;
import com.vinnlook.www.surface.bean.PointsMallBean;
import com.vinnlook.www.surface.bean.SavingOrderBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public interface PointsMallView extends MvpView {
    void getPointsMallSuccess(int code, PointsMallBean data);

    void getPointsMallFail(int code, String msg);

    void getExchangeSuccess(int code, ExchangeBean data);

    void getExchangeFail(int code, String msg);
}
