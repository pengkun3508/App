package com.vinnlook.www.surface.mvp.presenter;

import android.util.Log;

import com.dm.lib.core.mvp.MvpPresenter;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.mvp.model.MainRequest;
import com.vinnlook.www.surface.mvp.view.VideonFragmentView_1;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public class VideonFragmentPresenter_1 extends MvpPresenter<VideonFragmentView_1> {
    public void getShopListData() {
        addToRxLife(MainRequest.getShopListData_1(new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
                if (isAttachView())
                    getBaseView().getShopListData_1Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getShopListDataFail(code, msg);

                if (isAttachView()) {
                    getBaseView().getShopListData_1Fail(code, msg);
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

    //全选
    public void getSelectAllShopping(int type, int isAll, int is_check) {
        addToRxLife(MainRequest.getSelectAllShopping(type, isAll, is_check, new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
                if (isAttachView())
                    getBaseView().getSelectAllShoppingSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getShopListDataFail(code, msg);

                if (isAttachView()) {
                    getBaseView().getSelectAllShoppingFail(code, msg);
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

    //单选
    public void getDanSelectShopping(String rec_id, int isCheck) {
        addToRxLife(MainRequest.getDanSelectShopping(rec_id, isCheck, new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
                if (isAttachView())
                    getBaseView().getDanSelectShoppingSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getShopListDataFail(code, msg);

                if (isAttachView()) {
                    getBaseView().getDanSelectShoppingFail(code, msg);
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

    //修改数量
    public void getNumberData(String num, String rec_id) {
        addToRxLife(MainRequest.getNumberData(num, rec_id, new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
                if (isAttachView())
                    getBaseView().getNumberDataSuccess(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
//                if (isAttachView())
//                    getBaseView().getShopListDataFail(code, msg);

                if (isAttachView()) {
                    getBaseView().getNumberDataFail(code, msg);
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

    public void getModifyType_1(String getRec_id, String num, String product_id) {
        addToRxLife(MainRequest.getModifyType_1(getRec_id, num, product_id, new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
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

    //删除
    public void getDeleteData_1(String getRec_id, String is_all) {
        addToRxLife(MainRequest.getDeleteData_1(getRec_id, is_all, new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
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

    //结算
    public void getConfirmOrderData(String recId, String goods_id, String product_id, String num, String ecpressId, String integralNum, String address_id,
                                    String id, String ht_sendid, String zy_sendid, String proIdSb) {
        addToRxLife(MainRequest.getConfirmOrderData(recId, goods_id, product_id, num, ecpressId, integralNum, address_id, id, ht_sendid, zy_sendid, proIdSb, new RequestBackListener<ConfirmOrderBean>() {
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

    public void getTypeShopData3(String goods_id) {

        addToRxLife(MainRequest.getTypeShopData(goods_id, new RequestBackListener<MoveDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveDataBean data) {
                if (isAttachView())
                    getBaseView().getTypeShop3Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeShop3Fail(code, msg);
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

    public void getRemoveData() {
        addToRxLife(MainRequest.getRemoveData(new RequestBackListener<List<ShopCartListBean_1>>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, List<ShopCartListBean_1> data) {
                if (isAttachView())
                    getBaseView().getShopListData_1Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getShopListData_1Fail(code, msg);
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

    public void getTypeShopData4(String goods_id) {
        addToRxLife(MainRequest.getTypeShopData(goods_id, new RequestBackListener<MoveDataBean>() {
            @Override
            public void onStart() {
//                showLoading();
            }

            @Override
            public void onSuccess(int code, MoveDataBean data) {
                if (isAttachView())
                    getBaseView().getTypeShop4Success(code, data);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e("code", "code===" + code);
                Log.e("msg", "msg===" + msg);
                if (isAttachView())
                    getBaseView().getTypeShop4Fail(code, msg);
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
