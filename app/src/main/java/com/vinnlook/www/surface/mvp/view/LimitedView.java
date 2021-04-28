package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.http.model.VersionBean;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public interface LimitedView extends MvpView {
    void getLimiteSuccess(int code, LimitedBean limibean);

    void getLimiteFail(int code, String msg);

}
