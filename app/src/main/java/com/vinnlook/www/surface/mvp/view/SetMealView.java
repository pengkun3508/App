package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.SetMealBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/25$
 * @Author:pk$
 */
public interface SetMealView extends MvpView {
    void getMealListDataSuccess(int code, List<SetMealBean> data);

    void getMealListDataFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getAddShoppingCarSuccess(int code, Object data);

    void getAddShoppingCarFail(int code, String msg);
}
