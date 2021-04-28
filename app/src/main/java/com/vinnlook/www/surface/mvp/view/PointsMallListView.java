package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.PointsMallListBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public interface PointsMallListView extends MvpView {
    void getPointsMallListSuccess(int code, PointsMallListBean data);

    void getPointsMallListFail(int code, String msg);
}
