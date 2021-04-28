package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.QrCodeImgBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;

/**
 * @Description:
 * @Time:2021/3/26$
 * @Author:pk$
 */
public interface HomeFragment_1View extends MvpView {
    void getAppUpdateSuccess(int code, SignBean data);

    void getAppUpdateFail(int code, String msg);

    void uploadPhotosSuccess(int code, QrCodeImgBean data);

    void uploadPhotosFailed(int code, String msg);
}
