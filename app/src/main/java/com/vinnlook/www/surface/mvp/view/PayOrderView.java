package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;

/**
 * @Description:
 * @Time:2020/5/14$
 * @Author:pk$
 */
public interface PayOrderView extends MvpView {
    void getPostSubmitOrderSuccess(int code, WeCatPayBean data);

    void getPostSubmitOrderFail(int code, String msg);

    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getPostALiSubmitOrderSuccess(int code, ALiPayBean data);

    void getPostALiSubmitOrderFail(int code, String msg);
}
