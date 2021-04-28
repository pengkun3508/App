package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public interface VideonFragmentView_1 extends MvpView {
    void getShopListData_1Success(int code, List<ShopCartListBean_1> data);

    void getShopListData_1Fail(int code, String msg);

    void getSelectAllShoppingSuccess(int code, List<ShopCartListBean_1> data);

    void getSelectAllShoppingFail(int code, String msg);

    void getDanSelectShoppingSuccess(int code, List<ShopCartListBean_1> data);

    void getDanSelectShoppingFail(int code, String msg);

    void getNumberDataSuccess(int code, List<ShopCartListBean_1> data);

    void getNumberDataFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getModifyTypeSuccess(int code, List<ShopCartListBean_1> data);

    void getModifyTypeFail(int code, String msg);

    void getDeleteDataSuccess(int code, List<ShopCartListBean_1> data);

    void getDeleteDataFail(int code, String msg);

    void getConfirmOrderSuccess(int code, ConfirmOrderBean data);

    void getConfirmOrderFail(int code, String msg);

    void getTypeShop3Success(int code, MoveDataBean data);

    void getTypeShop3Fail(int code, String msg);

    void getTypeShop4Success(int code, MoveDataBean data);

    void getTypeShop4Fail(int code, String msg);
}
