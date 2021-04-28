package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.RealNameListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public interface RealNameView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getRealNameListSuccess(int code, List<RealNameListBean> data);

    void getRealNameListFail(int code, String msg);

    void getEditRealNameSuccess(int code, List<RealNameListBean> data);

    void getEditRealNameFail(int code, String msg);

    void getDeletRealNameSuccess(int code, List<RealNameListBean> data);

    void getDeletRealNameFail(int code, String msg);
}
