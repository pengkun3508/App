package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.surface.bean.HomePublicListBean;
import com.vinnlook.www.surface.bean.TypeGoodsBean;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public interface RecommendView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getTypeGoodsSuccess(int code, TypeGoodsBean data);

    void getTypeGoodsFail(int code, String msg);

    void getHomePublicSuccess(int code, HomePublicListBean data);

    void getHomePublicFail(int code, String msg);

    void getGoodsListSuccess(int code, HomeGoodsListBean data);

    void getGoodsListFail(int code, String msg);
}
