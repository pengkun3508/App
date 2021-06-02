package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GroupDetailsBean;

/**
 * @Description:
 * @Time:2021/5/11$
 * @Author:pk$
 */
public interface OpenGroupView extends MvpView {
    void getGroupDetailsSuccess(int code, GroupDetailsBean data);

    void getGroupDetailsFail(int code, String msg);
}
