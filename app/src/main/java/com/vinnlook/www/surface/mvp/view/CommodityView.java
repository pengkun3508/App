package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.CommodityTitleBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public interface CommodityView  extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getCommodityListSuccess(int code, CommodityListBean data);

    void getCommodityListFail(int code, String msg);

    void getCommodityTitleSuccess(int code, List<CommodityTitleBean> data);

    void getCommodityTitleFail(int code, String msg);
}
