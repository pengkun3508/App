package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GuangSelectBean;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public interface Guang_2FragmentView extends MvpView {
    void getSelectDataSuccess(int code, GuangSelectBean data);

    void getSelectDataFail(int code, String msg);
}
