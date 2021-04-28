package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.surface.bean.PublishComment;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.PublishCommentView;
import com.vinnlook.www.utils.ImageCompressUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/15$
 * @Author:pk$
 */
public class PublishCommentPresenter extends MvpPresenter<PublishCommentView> {
    public void getPublicComment(String goods_id, String goods_attr) {

        addToRxLife(MainRequest.getPublicComment(goods_id, goods_attr, new RequestBackListener<PublishComment>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, PublishComment data) {
                if (isAttachView())
                    getBaseView().getPublishCommentSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getPublishCommentFail(code, msg);
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

    public void multigraph(final String imgUrl, String order_id, String rec_id, String trim, String total_score, String describe_score, String logistics_score, String server_score, String is_show) {
        showLoading();
        addToRxLife(MainRequest.multigraph(imgUrl, order_id, rec_id, trim, total_score, describe_score, logistics_score, server_score, is_show, new RequestBackListener<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, Object data) {
                if (isAttachView()) {
                    getBaseView().multigraphSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttachView()) {
                    getBaseView().multigraphFailed(code, msg);
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


    public void postUploadPhotos(String selectList) {
        showLoading();
        File  files=new File(selectList);
        File fileImage = ImageCompressUtils.compress(files);
        addToRxLife(MainRequest.postUploadPhotos(fileImage,new RequestBackListener<UpdateImgBean>() {
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

}
