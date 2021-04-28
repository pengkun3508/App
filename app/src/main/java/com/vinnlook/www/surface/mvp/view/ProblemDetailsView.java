package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ProblemDetailsBean;

/**
 * @Description:
 * @Time:2020/11/11$
 * @Author:pk$
 */
public interface ProblemDetailsView extends MvpView {
    void getProblemDetailsSuccess(int code, ProblemDetailsBean data);

    void getProblemDetailsFail(int code, String msg);

    void getAnswerDataSuccess(int code, Object data);

    void getAnswerDataFail(int code, String msg);

    void getEditPraiseSuccess(int code, Object data);

    void getEditPraiseFail(int code, String msg);
}
