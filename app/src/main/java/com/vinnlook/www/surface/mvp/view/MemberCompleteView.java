package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.MemberBean;

/**
 * @Description:
 * @Time:2020/8/19$
 * @Author:pk$
 */
public interface MemberCompleteView extends MvpView {
    void getMemberDetailSuccess(int code, MemberBean data);

    void getMemberDetailFail(int code, String msg);
}
