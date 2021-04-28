package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.CommodityListBean;

/**
 * @Description:
 * @Time:2021/1/12$
 * @Author:pk$
 */
public interface ItemFragmentView extends MvpView {
    void getCommodityListSuccess(int code, CommodityListBean data);

    void getCommodityListFail(int code, String msg);
}
