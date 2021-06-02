package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GroupListBean;

/**
 * @Description:
 * @Time:2021/4/8$
 * @Author:pk$
 */
public interface HomeTab4FragmentView extends MvpView {
    void getGroupListSuccess(int code, GroupListBean data);

    void getGroupListFail(int code, String msg);
}
