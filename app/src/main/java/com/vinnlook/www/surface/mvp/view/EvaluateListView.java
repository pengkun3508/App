package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.EvaluateListBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public interface EvaluateListView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getEvaluateListSuccess(int code, EvaluateListBean data);

    void getEvaluateListFail(int code, String msg);
}
