package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.MemberBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/8/18$
 * @Author:pk$
 */
public interface MemberView extends MvpView {

    void getMemberDetailSuccess(int code, MemberBean data);

    void getMemberDetailFail(int code, String msg);
}
