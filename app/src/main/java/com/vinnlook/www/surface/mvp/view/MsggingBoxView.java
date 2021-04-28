package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.MsggingTypeBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/2$
 * @Author:pk$
 */
public interface MsggingBoxView extends MvpView {
    void getTypeListDataSuccess(int code, List<MsggingTypeBean> data);

    void getTypeListDataFail(int code, String msg);
}
