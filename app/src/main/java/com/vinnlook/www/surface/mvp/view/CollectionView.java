package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.http.model.VersionBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public interface CollectionView extends MvpView {

    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getCollectionListSuccess(int code, CollectionListBean data);

    void getCollectionListFail(int code, String msg);
}

