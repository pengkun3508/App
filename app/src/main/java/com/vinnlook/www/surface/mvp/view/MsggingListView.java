package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.MsggingListBean;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public interface MsggingListView extends MvpView {
    void getPushListDataSuccess(int code, MsggingListBean data);

    void getPushListDataFail(int code, String msg);
}
