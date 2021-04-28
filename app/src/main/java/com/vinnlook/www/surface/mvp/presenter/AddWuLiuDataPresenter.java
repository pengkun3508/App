package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.model.bean.AddressDetailsBean;
import com.vinnlook.www.surface.mvp.view.AddWuLiuDataView;
import com.vinnlook.www.surface.mvp.view.AddressView;
import com.vinnlook.www.utils.ImageCompressUtils;

import java.io.File;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class AddWuLiuDataPresenter  extends MvpPresenter<AddWuLiuDataView> {
    public void postUploadPhotos(String selectList) {
        showLoading();

        File files = new File(selectList);
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

    public void postWaybillData(String order_id, String iD, String waybillId, String waySn, String sImages) {
        addToRxLife(MainRequest.postWaybillData(order_id,iD,waybillId,waySn,sImages, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + data);
                if (isAttachView())
                    getBaseView().getWaybillDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getWaybillDataFail(code, msg);
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
