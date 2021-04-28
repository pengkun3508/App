package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.SearchListListBean;

/**
 * @Description:
 * @Time:2020/5/25$
 * @Author:pk$
 */
public interface SearchListView extends MvpView {
    void getSearchListDataSuccess(int code, SearchListListBean data);

    void getSearchListDataFail(int code, String msg);
}
