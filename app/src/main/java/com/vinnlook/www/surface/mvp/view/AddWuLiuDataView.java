package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.UpdateImgBean;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public interface AddWuLiuDataView extends MvpView {
    void uploadPhotosSuccess(int code, UpdateImgBean data);

    void uploadPhotosFailed(int code, String msg);

    void getWaybillDataSuccess(int code, Object data);

    void getWaybillDataFail(int code, String msg);
}
