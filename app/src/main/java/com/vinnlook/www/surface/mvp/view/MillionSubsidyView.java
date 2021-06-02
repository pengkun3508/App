package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.LimitedBean;

/**
 * @Description:
 * @Time:2021/6/2$
 * @Author:pk$
 */
public interface MillionSubsidyView extends MvpView {
    void getLimiteSuccess(int code, LimitedBean data);

    void getLimiteFail(int code, String msg);
}
