package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.surface.bean.ReBangListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/12/22$
 * @Author:pk$
 */
public interface RankingListView extends MvpView {
    void getRankingTabDataSuccess(int code, List<RankingTabBean> data);

    void getRankingTabDataFail(int code, String msg);

    void getTypeReBangListSuccess(int code, List<ReBangListBean> data);

    void getTypeReBangListFail(int code, String msg);
}
