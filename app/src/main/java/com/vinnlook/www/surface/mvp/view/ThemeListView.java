package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ThemeListBean;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.surface.bean.WaybillListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public interface ThemeListView extends MvpView {
    void getThemeListSuccess(int code, ThemeListBean data);

    void getThemeListFail(int code, String msg);


}
