package com.vinnlook.www.surface.mvp.view;


import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.SignBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public interface HomeFragmentView extends MvpView {

    void getAppUpdateSuccess(int code, SignBean version);

    void getAppUpdateFail(int code, String msg);

    void getHomeDataSuccess(int code, HomeDataBean data);

    void getHomeDataFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);

    void getTypeShopSuccess_1(int code, MoveDataBean data);

    void getTypeShopFail_1(int code, String msg);
}
