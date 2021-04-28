package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * @Description:
 * @Time:2020/6/11$
 * @Author:pk$
 */
public interface ModifyPhoneView extends MvpView {
    void getVerificationCodeSuccess(int code, Object data);

    void getVerificationCodeFail(int code, String msg);

    void getModifyPhoneNumberSuccess(int code, UserInfoBean data);

    void getModifyPhoneNumberFail(int code, String msg);
}
