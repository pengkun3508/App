package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;

/**
 * @Description:
 * @Time:2020/8/19$
 * @Author:pk$
 */
public interface PayMemberView extends MvpView {
    void getPostPayMemberSuccess(int code, WeCatPayBean data);

    void getPostPayMemberFail(int code, String msg);

    void getPostPayMember_2Success(int code, ALiPayBean data);

    void getPostPayMember_2Fail(int code, String msg);
}
