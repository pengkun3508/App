package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.UpdateImgBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/3$
 * @Author:pk$
 */
public interface ProblemFeedbackView extends MvpView {
    void getFeedBackSuccess(int code, Object data);

    void getFeedBackFail(int code, String msg);

    void uploadPhotosSuccess(int code, UpdateImgBean data);

    void uploadPhotosFailed(int code, String msg);
}
