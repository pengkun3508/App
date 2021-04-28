package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.bean.UserInfo;

public interface EditDataView extends MvpView {
    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getUserInfoSuccess(int code, UserInfo data);

    void getUserInfoFail(int code, String msg);

    void uploadPhotosSuccess(int code, UpdateImgBean data);

    void uploadPhotosFailed(int code, String msg);

    void uploadPersonalSuccess(int code, UserInfo data);

    void uploadPersonalFailed(int code, String msg);
}
