package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ThemeOtherDetailsBean;

/**
 * @Description:
 * @Time:2021/7/7$
 * @Author:pk$
 */
public interface ThemeOtherDetailsView extends MvpView {
    void getThemeOtherDetailsSuccess(int code, ThemeOtherDetailsBean data);

    void getThemeOtherDetailsFail(int code, String msg);
}
