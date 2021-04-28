package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;

/**
 * @Description:
 * @Time:2020/6/11$
 * @Author:pk$
 */
public interface VerifyPhoneView extends MvpView {
    void getVerificationCodeSuccess(int code, Object data);

    void getVerificationCodeFail(int code, String msg);

    void getVerifyPhoneNumberSuccess(int code, Object data);

    void getVerifyPhoneNumberFail(int code, String msg);
}
