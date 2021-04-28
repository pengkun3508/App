package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.HuoDongBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/31$
 * @Author:pk$
 */
public interface HuoDongZoneView extends MvpView {
    void getHuoDongListSuccess(int code, List<HuoDongBean> data);

    void getHuoDongListFail(int code, String msg);

    void getTypeShopSuccess(int code, MoveDataBean data);

    void getTypeShopFail(int code, String msg);

    void getAddShopCarSuccess(int code, Object data);

    void getAddShopCarFail(int code, String msg);
}
