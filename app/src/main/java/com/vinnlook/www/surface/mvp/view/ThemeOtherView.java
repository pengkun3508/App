package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public interface ThemeOtherView extends MvpView {
    void getThemeOtherListSuccess(int code, ThemeOtherListBean data);

    void getThemeOtherListFail(int code, String msg);
}
