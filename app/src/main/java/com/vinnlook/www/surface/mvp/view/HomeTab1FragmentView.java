package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.HomeTab1Bean;

/**
 * @Description:
 * @Time:2021/3/26$
 * @Author:pk$
 */
public interface HomeTab1FragmentView extends MvpView {
    void getHomeTab1DataSuccess(int code, HomeTab1Bean data);

    void getHomeTab1DataFail(int code, String msg);

    void getAppUpdateSuccess(int code, SignBean data);

    void getAppUpdateFail(int code, String msg);
}
