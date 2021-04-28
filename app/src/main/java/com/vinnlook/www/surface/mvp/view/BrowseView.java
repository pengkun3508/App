package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.BrowseListBean;

/**
 * @Description:
 * @Time:2020/9/9$
 * @Author:pk$
 */
public interface BrowseView extends MvpView {
    void getBrowseListSuccess(int code, BrowseListBean data);

    void getBrowseListFail(int code, String msg);
}
