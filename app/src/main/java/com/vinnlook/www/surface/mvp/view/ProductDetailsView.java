package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.ProductDetailsBean;

public interface ProductDetailsView extends MvpView {
    void getProductDetailsSuccess(int code, ProductDetailsBean data);

    void getProductDetailsFail(int code, String msg);
}
