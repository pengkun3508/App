package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GroupListBean;

/**
 * @Description:
 * @Time:2021/4/15$
 * @Author:pk$
 */
public interface GroupWorkGoView extends MvpView {
    void getGroupListSuccess(int code, GroupListBean data);

    void getGroupListFail(int code, String msg);
}
