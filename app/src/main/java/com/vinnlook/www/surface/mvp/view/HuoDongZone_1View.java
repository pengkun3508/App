package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.HuoDong2Bean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public interface HuoDongZone_1View extends MvpView {

    void getActivityListSuccess(int code, List<HuoDong2Bean> data);

    void getActivityListFail(int code, String msg);
}
