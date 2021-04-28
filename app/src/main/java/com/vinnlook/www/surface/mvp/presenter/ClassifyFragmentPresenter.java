package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.ProjectApi;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.ClassifyBean;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.ClassifyFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2020/4/21$
 * @Author:pk$
 */
public class ClassifyFragmentPresenter extends MvpPresenter<ClassifyFragmentView> {

    public void getClassifyData(String goods_name, int page, int limit, int type, String sort_key, String sort_value,String str) {
        addToRxLife(MainRequest.getClassifyindex(goods_name,page,limit,type,sort_key,sort_value,str,new RequestBackListener<ClassifyBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ClassifyBean data) {
                if (isAttachView())
                    getBaseView().getClassifySuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getClassifyFail(code, msg);
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


    public void getTypeList(int type) {
        addToRxLife(MainRequest.getTypeList(new RequestBackListener<List<ClassifyTypeBean>>() {
            @Override
            public void onStart() {
                if (type==1) {
//                    showLoading();
                }
            }

            @Override
            public void onSuccess(int code, List<ClassifyTypeBean> data) {
                if (isAttachView())
                    getBaseView().getClassifyTypeListSuccess(code, data,type);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getClassifyTypeListFail(code, msg);
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

    //检索
    public void getSearchList(String toString) {
        addToRxLife(MainRequest.getSearchList(toString,new RequestBackListener<List<String>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<String> data) {
                if (isAttachView())
                    getBaseView().getSearchListSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSearchListFail(code, msg);
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
}
