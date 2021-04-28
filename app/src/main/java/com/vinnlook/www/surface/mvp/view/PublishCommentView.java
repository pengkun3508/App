package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.surface.bean.PublishComment;
import com.vinnlook.www.surface.bean.UpdateImgBean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/15$
 * @Author:pk$
 */
public interface PublishCommentView extends MvpView {
    void getPublishCommentSuccess(int code, PublishComment data);

    void getPublishCommentFail(int code, String msg);

    void multigraphSuccess(int code, Object data);

    void multigraphFailed(int code, String msg);

    void uploadPhotosSuccess(int code, UpdateImgBean data);

    void uploadPhotosFailed(int code, String msg);
}
