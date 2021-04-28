package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;

/**
 * @Description:
 * @Time:2021/1/27$
 * @Author:pk$
 */
public interface HaiTaoClassView extends MvpView {
    void getHaiListDataSuccess(int code, HaiTaoClassBean data);

    void getHaiListDataFail(int code, String msg);

    void getTypeShopSuccess_1(int code, MoveDataBean data);

    void getTypeShopFail_1(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);
}
