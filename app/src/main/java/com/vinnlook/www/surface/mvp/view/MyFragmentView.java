package com.vinnlook.www.surface.mvp.view;


import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public interface MyFragmentView extends MvpView {

    void getPersonalInformationSuccess(int code, PersonalInformationBean data);

    void getPersonalInformationFail(int code, String msg);

    void getMobileLoginSuccess(int code, UserInfoBean data);

    void getMobileLoginFail(int code, String msg);

    void getCheckCodeSuccess(int code, UserInfoBean data);

    void getCheckCodeFail(int code, String msg);
}
