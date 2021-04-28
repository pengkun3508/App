package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ApplyRefundView;
import com.vinnlook.www.utils.ImageCompressUtils;

import java.io.File;

/**
 * @Description:
 * @Time:2020/11/12$
 * @Author:pk$
 */
public class ApplyRefundPresenter extends MvpPresenter<ApplyRefundView> {
    //下载数据
    public void getRefundInfo(String order_id, String sb) {
        addToRxLife(MainRequest.getRefundInfo(order_id, sb, new RequestBackListener<RefundInfoBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, RefundInfoBean data) {
                if (isAttachView())
                    getBaseView().getRefundInfoSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getRefundInfoFail(code, msg);
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

    //上传图片
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

    //提交数据
    public void getAddRefundApply(String order_id, String refund_list, String type, String status, String conten, String img,String getIs_all_refund) {
        addToRxLife(MainRequest.getAddRefundApply(order_id, refund_list, type, status, conten, img,getIs_all_refund, new RequestBackListener<Object>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView())
                    getBaseView().getAddRefundApplySuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddRefundApplyFail(code, msg);
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
