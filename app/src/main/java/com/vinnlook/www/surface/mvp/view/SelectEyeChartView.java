package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.EyeChartDetailsBean;

/**
 * @Description:
 * @Time:2021/7/5$
 * @Author:pk$
 */
public interface SelectEyeChartView extends MvpView {
    void getEyeChartDataSuccess(int code, EyeChartDetailsBean data);

    void getEyeChartDataFail(int code, String msg);

    void getGiveDataSuccess(int code, Object data);

    void getGiveDataFail(int code, String msg);

    void getCollectDataSuccess(int code, Object data);

    void getCollectDataFail(int code, String msg);
}
