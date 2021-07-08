package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.MoveGuangListBean;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public interface MoveGuangListView extends MvpView {
    void getMoveGuangListSuccess(int code, MoveGuangListBean data);

    void getMoveGuangListFail(int code, String msg);
}
