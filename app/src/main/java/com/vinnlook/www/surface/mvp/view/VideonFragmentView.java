package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.ShopCartListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.ModifyTypeBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public interface VideonFragmentView extends MvpView {
    void getShopListDataSuccess(int code, ShopCartListBean data);

    void getShopListDataFail(int code, String msg);

    void getAddAndReduceSuccess(int code, ShopCartListBean data);

    void getAddAndReduceFail(int code, String msg);

    void getDeleteDataSuccess(int code, ShopCartListBean data);

    void getDeleteDataFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getModifyTypeSuccess(int code, ModifyTypeBean data);

    void getModifyTypeFail(int code, String msg);

    void getSelectShoppingSuccess(int code, ShopCartListBean data);

    void getSelectShoppingFail(int code, String msg);

    void getConfirmOrderSuccess(int code, ConfirmOrderBean data);

    void getConfirmOrderFail(int code, String msg);
}
