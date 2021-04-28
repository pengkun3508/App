package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.EditDataView;
import com.vinnlook.www.utils.ImageCompressUtils;

import java.io.File;

public class EditDataPresenter extends MvpPresenter<EditDataView> {
    public void getUserInfoData() {
        addToRxLife(MainRequest.getUserInfoData(new RequestBackListener<UserInfo>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, UserInfo data) {
                Log.e("code", "code===" + code);
                Log.e("data", "data===" + data.toString());
                if (isAttachView())
                    getBaseView().getUserInfoSuccess(code, data);

            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getUserInfoFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));
    }

    public void postUploadPhotos(String getPath) {
        showLoading();
        File files = new File(getPath);
        File fileImage = ImageCompressUtils.compress(files);
        addToRxLife(MainRequest.postUploadPhotos(fileImage, new RequestBackListener<UpdateImgBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, UpdateImgBean data) {
                if (isAttachView()) {
                    getBaseView().uploadPhotosSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttachView()) {
                    getBaseView().uploadPhotosFailed(code, msg);
                }
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));

    }

    public void postPersonalInformation(String imgUrl, String nicheng, String sex) {
        addToRxLife(MainRequest.postPersonalInformation(imgUrl,nicheng,sex, new RequestBackListener<UserInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, UserInfo data) {
                if (isAttachView()) {
                    getBaseView().uploadPersonalSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttachView()) {
                    getBaseView().uploadPersonalFailed(code, msg);
                }
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));

    }
}
