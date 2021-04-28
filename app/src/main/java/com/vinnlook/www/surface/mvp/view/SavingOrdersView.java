package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.SavingOrderBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public interface SavingOrdersView extends MvpView {
    void getSavingOrdersFail(int code, String msg);

    void getSavingOrdersSuccess(int code, SavingOrderBean data);
}
