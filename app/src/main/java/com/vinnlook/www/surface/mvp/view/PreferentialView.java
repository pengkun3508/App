package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.PreferentialBean;

/**
 * @Description:
 * @Time:2020/12/11$
 * @Author:pk$
 */
public interface PreferentialView extends MvpView {
    void getPreferentialListSuccess(int code, PreferentialBean data);

    void getPreferentialListFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);
}
