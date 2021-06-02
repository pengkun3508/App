package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.utils.UserInfoBean;

public interface MoveAboutView extends MvpView {


    void getMoveDataSuccess(int code, MoveDataBean data);

    void getMoveDataFail(int code, String msg);


    void getCollectionShopSuccess(int code, Object data);

    void getCollectionShopFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);

    void getMobileLoginSuccess(int code, UserInfoBean data);

    void getMobileLoginFail(int code, String msg);

    void getConfirmOrderSuccess(int code, ConfirmOrderBean data);

    void getConfirmOrderFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getTypeShop4Success(int code, MoveDataBean data);

    void getTypeShop4Fail(int code, String msg);

    void getTypeShop5Success(int code, MoveDataBean data);

    void getTypeShop5Fail(int code, String msg);

    void getTypeShop6Success(int code, MoveDataBean data);

    void getTypeShop6Fail(int code, String msg);
}
