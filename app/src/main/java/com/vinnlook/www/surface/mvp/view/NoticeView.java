package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.NoticeListBean;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public interface NoticeView extends MvpView {
    void getMessageListSuccess(int code, NoticeListBean data);

    void getMessageListFail(int code, String msg);
}
