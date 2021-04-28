package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.CertifyListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/19$
 * @Author:pk$
 */
public interface QualificationsView extends MvpView {
    void getCertifyListSuccess(int code, List<CertifyListBean> data);

    void getCertifyListFail(int code, String msg);
}
