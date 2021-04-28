package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.RealNameDetailsBean;
import com.vinnlook.www.surface.bean.RealNameListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public interface RealNameEditView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getRealNameDetailsSuccess(int code, RealNameDetailsBean data);

    void getRealNameDetailsFail(int code, String msg);

    void getAddRealNameSuccess(int code, List<RealNameListBean> data);

    void getAddRealNameFail(int code, String msg);

    void getRealNameEditSuccess(int code, List<RealNameListBean> data);

    void getRealNameEditFail(int code, String msg);
}
