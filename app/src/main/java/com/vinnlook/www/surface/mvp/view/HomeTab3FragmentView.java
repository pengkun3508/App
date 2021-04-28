package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.LimitedBean;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public interface HomeTab3FragmentView extends MvpView {
    void getLimiteSuccess(int code, LimitedBean data);

    void getLimiteFail(int code, String msg);
}
