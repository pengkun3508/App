package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.http.model.VersionBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public interface BrandView extends MvpView {

    void getBrandListSuccess(int code, BrandListBean data);

    void getBrandListFail(int code, String msg);
}
