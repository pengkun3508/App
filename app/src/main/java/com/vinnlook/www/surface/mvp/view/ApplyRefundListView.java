package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ApplyListBean;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public interface ApplyRefundListView extends MvpView {
    void getApplyListSuccess(int code, ApplyListBean data);

    void getApplyListFail(int code, String msg);
}
