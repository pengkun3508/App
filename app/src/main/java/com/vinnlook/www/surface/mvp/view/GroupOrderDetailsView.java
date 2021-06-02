package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.surface.bean.GroupListBean;

/**
 * @Description:
 * @Time:2021/5/11$
 * @Author:pk$
 */
public interface GroupOrderDetailsView extends MvpView {
    void getGroupDetailsSuccess(int code, GroupDetailsBean data);

    void getGroupDetailsFail(int code, String msg);
}
