package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ArticleDetailsBean;

/**
 * @Description:
 * @Time:2021/7/1$
 * @Author:pk$
 */
public interface ArticleDetailsView extends MvpView {
    void getArticleSuccess(int code, ArticleDetailsBean data);

    void getArticleFail(int code, String msg);

    void getGiveDataSuccess(int code, Object data);

    void getGiveDataFail(int code, String msg);

    void getCollectDataSuccess(int code, Object data);

    void getCollectDataFail(int code, String msg);
}
