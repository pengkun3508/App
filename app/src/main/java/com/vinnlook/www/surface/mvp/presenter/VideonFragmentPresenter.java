package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.ShopCartListBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.ModifyTypeBean;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.VideonFragmentView;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/7
 */
public class VideonFragmentPresenter extends MvpPresenter<VideonFragmentView> {

    //下载购物车列表
    public void getShopListData(int type) {
        addToRxLife(MainRequest.getShopListData(type, new RequestBackListener<ShopCartListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ShopCartListBean data) {
                if (isAttachView())
                    getBaseView().getShopListDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getShopListDataFail(code, msg);

                if (isAttachView()) {
                    getBaseView().getShopListDataFail(code, msg);
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
//                dismissLoading();
            }
        }));
    }

    //购物车数量加减
    public void getAddAndReduce(int mark, String number, String getRec_id) {
        addToRxLife(MainRequest.getAddAndReduce(mark, number, getRec_id, new RequestBackListener<ShopCartListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ShopCartListBean data) {
                if (isAttachView())
                    getBaseView().getAddAndReduceSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getAddAndReduceFail(code, msg);
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

    //删除数据
    public void getDeleteData(String getRec_id, String is_all) {

        addToRxLife(MainRequest.getDeleteData(getRec_id, is_all, new RequestBackListener<ShopCartListBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ShopCartListBean data) {
                if (isAttachView())
                    getBaseView().getDeleteDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getDeleteDataFail(code, msg);
            }

            @Override
            public void onNoNet() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", "e===" + e);
            }

            @Override
            public void onFinish() {
                dismissLoading();
            }
        }));

    }

    //下载商品类型
    public void getTypeShopData(String goods_id) {

        addToRxLife(MainRequest.getTypeShopData(goods_id, new RequestBackListener<MoveDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveDataBean data) {
                if (isAttachView())
                    getBaseView().getTypeShopSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeShopFail(code, msg);
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

    public void getModifyType(int mark, String getRec_id, String num, String product_id) {
        addToRxLife(MainRequest.getModifyType(mark, getRec_id, num, product_id, new RequestBackListener<ModifyTypeBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ModifyTypeBean data) {
                if (isAttachView())
                    getBaseView().getModifyTypeSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getModifyTypeFail(code, msg);
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

    //全选
    public void getSelectShopping(int mark, String rec_id, int is_check, int is_all) {
        addToRxLife(MainRequest.getSelectShopping(mark, rec_id, is_check, is_all, new RequestBackListener<ShopCartListBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, ShopCartListBean data) {
                if (isAttachView())
                    getBaseView().getSelectShoppingSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getSelectShoppingFail(code, msg);
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


    //结算
    public void getConfirmOrderData(String recId, String goods_id, String product_id, String num, String ecpressId, String integralNum, String address_id,
                                    String id, String ht_sendid, String zy_sendid, String proIdSb,String group_info,String group_id) {

        addToRxLife(MainRequest.getConfirmOrderData(recId, goods_id, product_id, num, ecpressId, integralNum, address_id, id, ht_sendid, zy_sendid, proIdSb,group_info,group_id, new RequestBackListener<ConfirmOrderBean>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, ConfirmOrderBean data) {
                if (isAttachView())
                    getBaseView().getConfirmOrderSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getConfirmOrderFail(code, msg);
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
