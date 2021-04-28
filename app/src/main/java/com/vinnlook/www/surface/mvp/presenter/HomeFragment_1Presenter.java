package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.QrCodeImgBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.HomeFragmentView;
import com.vinnlook.www.surface.mvp.view.HomeFragment_1View;
import com.vinnlook.www.utils.ImageCompressUtils;

import java.io.File;

/**
 * @Description:
 * @Time:2021/3/26$
 * @Author:pk$
 */
public class HomeFragment_1Presenter extends MvpPresenter<HomeFragment_1View> {
    public void getAppUpdate() {
        addToRxLife(MainRequest.getAppUpdate(new RequestBackListener<SignBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, SignBean data) {
                if (isAttachView())
                    getBaseView().getAppUpdateSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAppUpdateFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFinish() {
//                dismissLoading();
            }
        }));
    }

    public void postUploadPhotos(String getPath) {
        showLoading();
        File files = new File(getPath);
        File fileImage = ImageCompressUtils.compress(files);
        addToRxLife(MainRequest.postUploadQrcode(fileImage, new RequestBackListener<QrCodeImgBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, QrCodeImgBean data) {
                if (isAttachView()) {
                    Log.e("二维码", "==code==="+code);
                    Log.e("二维码", "==data==="+data);
                    getBaseView().uploadPhotosSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttachView()) {
                    Log.e("二维码", "==code==="+code);
                    Log.e("二维码", "==msg==="+msg);
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
}
