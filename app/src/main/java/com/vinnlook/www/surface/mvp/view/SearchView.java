package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.SearchListBean;

import java.util.List;

public interface SearchView extends MvpView {
    void getSearchDataSuccess(int code, SearchListBean data);

    void getSearchDataFail(int code, String msg);

    void getSearchListSuccess(int code, List<String> data);

    void getSearchListFail(int code, String msg);
}
