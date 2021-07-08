package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.CollectionList2Bean;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.http.model.VersionBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public interface CollectionView extends MvpView {

    void getCollectionListSuccess(int code, CollectionListBean data);

    void getCollectionListFail(int code, String msg);

    void getCollectionList2Success(int code, CollectionList2Bean data);

    void getCollectionList2Fail(int code, String msg);
}

