package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.WaybillListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public interface SelectLogisticsView extends MvpView {
    void getLogisticsListSuccess(int code, List<WaybillListBean> data);

    void getLogisticsListFail(int code, String msg);
}
