package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GuangThemBean;

/**
 * @Description:
 * @Time:2021/6/28$
 * @Author:pk$
 */
public interface Guang_1FragmentView extends MvpView {
    void getThemDataSuccess(int code, GuangThemBean data);

    void getThemDataFail(int code, String msg);
}
