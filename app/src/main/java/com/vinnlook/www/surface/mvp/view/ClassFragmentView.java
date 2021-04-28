package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.mvp.model.bean.ClassBean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public interface ClassFragmentView extends MvpView {
    void getClassListSuccess(int code, List<ClassBean> data);

    void getClassListFail(int code, String msg);

    void addClassListSuccess(int code, BaseBean data);

    void addClassListFail(int code, String msg);
}
