package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;

/**
 * @Description:
 * @Time:2020/11/12$
 * @Author:pk$
 */
public interface ApplyRefundView extends MvpView {
    void uploadPhotosSuccess(int code, UpdateImgBean data);

    void uploadPhotosFailed(int code, String msg);

    void getAddRefundApplySuccess(int code, Object data);

    void getAddRefundApplyFail(int code, String msg);

    void getRefundInfoSuccess(int code, RefundInfoBean data);

    void getRefundInfoFail(int code, String msg);
}
