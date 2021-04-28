package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.ReBangListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/5$
 * @Author:pk$
 */
public interface ReBangListView extends MvpView {
    void getTypeReBangListSuccess(int code, List<ReBangListBean> data);

    void getTypeReBangListFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);
}
