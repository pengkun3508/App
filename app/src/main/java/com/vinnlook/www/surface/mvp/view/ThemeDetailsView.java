package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ThemeDetailsBean;
import com.vinnlook.www.surface.bean.ThemeListBean;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public interface ThemeDetailsView extends MvpView {

    void getThemeDetailsSuccess(int code, ThemeDetailsBean data);

    void getThemeDetailsFail(int code, String msg);
}
