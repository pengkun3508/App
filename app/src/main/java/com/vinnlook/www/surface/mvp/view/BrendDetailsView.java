package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.BrandDetailsBean;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public interface BrendDetailsView extends MvpView {
    void getBrandDetailsListSuccess(int code, BrandDetailsBean data);

    void getBrandDetailsListFail(int code, String msg);
}
