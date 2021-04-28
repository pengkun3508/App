package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ProblemBean;

/**
 * @Description:
 * @Time:2020/11/6$
 * @Author:pk$
 */
public interface ProblemView extends MvpView {
    void getProblemListSuccess(int code, ProblemBean data);

    void getProblemListFail(int code, String msg);

    void getQuestionDataSuccess(int code, Object data);

    void getQuestionDataFail(int code, String msg);
}
