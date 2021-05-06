package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.CompanyBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/29$
 * @Author:pk$
 */
public interface CompanyView extends MvpView {
    void getCompanyListSuccess(int code, List<CompanyBean> data);

    void getCompanyListFail(int code, String msg);
}
