package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GroupOrderListBean;

/**
 * @Description:
 * @Time:2021/5/10$
 * @Author:pk$
 */
public interface GroupListView extends MvpView {
    void getGroupOrderListSuccess(int code, GroupOrderListBean data);

    void getGroupOrderListFail(int code, String msg);
}
