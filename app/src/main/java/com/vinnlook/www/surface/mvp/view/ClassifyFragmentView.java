package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.ClassifyBean;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/21$
 * @Author:pk$
 */
public interface ClassifyFragmentView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getClassifySuccess(int code, ClassifyBean data);

    void getClassifyFail(int code, String msg);

    void getClassifyTypeListSuccess(int code, List<ClassifyTypeBean> data,int type);

    void getClassifyTypeListFail(int code, String msg);

    void getSearchListSuccess(int code, List<String> data);

    void getSearchListFail(int code, String msg);
}
