package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.utils.UserInfoBean;

public interface MainView extends MvpView {
    void getMainSuccess(int code, UserInfoBean data);

    void getMainFail(int code, String msg);
}
