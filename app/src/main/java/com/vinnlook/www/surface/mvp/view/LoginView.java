package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.utils.UserInfoBean;

public interface LoginView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getVerificationCodeSuccess(int code, Object data);

    void getVerificationCodeFail(int code, String msg);

    void getCheckCodeSuccess(int code, UserInfoBean data);

    void getCheckCodeFail(int code, String msg);
}
