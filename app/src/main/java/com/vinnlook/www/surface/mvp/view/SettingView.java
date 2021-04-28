package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.UserInfo;

/**
 * @Description:
 * @Time:2021/3/2$
 * @Author:pk$
 */
public interface SettingView extends MvpView {
    void getBindingWechatSuccess(int code, Object data);

    void getBindingWechatFail(int code, String msg);

    void getUserInfoSuccess(int code, UserInfo data);

    void getUserInfoFail(int code, String msg);
}
