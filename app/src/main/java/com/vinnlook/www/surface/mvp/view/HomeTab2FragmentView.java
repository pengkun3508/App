package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.HomeTab2Bean;

/**
 * @Description:
 * @Time:2021/3/31$
 * @Author:pk$
 */
public interface HomeTab2FragmentView extends MvpView {
    void getHomeTab2DataSuccess(int code, HomeTab2Bean data);

    void getHomeTab2DataFail(int code, String msg);
}
