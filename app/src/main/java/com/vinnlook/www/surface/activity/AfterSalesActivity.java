package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.AfterSalesPresenter;
import com.vinnlook.www.surface.mvp.view.AfterSalesView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:选择售后
 * @Time:2021/2/3$
 * @Author:pk$
 */
public class AfterSalesActivity extends BaseActivity<AfterSalesPresenter> implements AfterSalesView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.after_tuikuan)
    RelativeLayout afterTuikuan;
    @BindView(R.id.after_tuihuo_tuikuan)
    RelativeLayout afterTuihuoTuikuan;

    static OrderDetailsBean orderDetailsBean;
    static String getIs_refund_all;
    static String getStatus;

    public static void startSelf(Context context, OrderDetailsBean orderDetailsBeans, String getIs_refund_alls, String getStatuss) {
        Intent intent = new Intent(context, AfterSalesActivity.class);
        context.startActivity(intent);
        orderDetailsBean = orderDetailsBeans;
        getIs_refund_all = getIs_refund_alls;
        getStatus = getStatuss;
        Log.e("选择退款方式===", "getStatus=====" + getStatus);
        Log.e("选择退款方式===", "getShop_list.size=====" + orderDetailsBean.getShop_list().size());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sales;
    }

    @Override
    protected AfterSalesPresenter initPresenter() {
        return new AfterSalesPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        if (getStatus.equals("2")) {//待发货状态，隐藏退货退款
            afterTuihuoTuikuan.setVisibility(View.GONE);
        } else {
            afterTuihuoTuikuan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.after_tuikuan, R.id.after_tuihuo_tuikuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.after_tuikuan://仅退款
//                AfterSalesShopActivity.startSelf(this, orderDetailsBean, "1", getIs_refund_all);

//                ApplyRefundSelectActivity_1.startSelf(this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id(), "1", getIs_all_refund);


                if (getIs_refund_all.equals("1")) {//必须整单退款（仅退款）
                    //直接跳到退款页面，传入仅退款参数--整单
                    ApplyRefundSelectActivity_1.startSelf(AfterSalesActivity.this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id(), "1", getIs_refund_all, getStatus);
                } else if (getIs_refund_all.equals("0")) {//可以单个退款（仅退款）
                    //直接跳入选择商品页面--可以选择商品
                    AfterSalesShopActivity.startSelf(this, orderDetailsBean, "1", getIs_refund_all, getStatus);
                }


                break;
            case R.id.after_tuihuo_tuikuan://退款退货
//                ApplyRefundSelectActivity_2.startSelf(this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id(), "2", getIs_all_refund);
//                AfterSalesShopActivity.startSelf(this, orderDetailsBean, "2", getIs_refund_all);


                if (getIs_refund_all.equals("1")) {//必须整单退款（仅退款）
                    //直接跳到退款页面，传入仅退款参数--整单
                    ApplyRefundSelectActivity_1.startSelf(AfterSalesActivity.this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id(), "2", getIs_refund_all, getStatus);
                } else if (getIs_refund_all.equals("0")) {//可以单个退款（仅退款）
                    //直接跳入选择商品页面--可以选择商品
                    AfterSalesShopActivity.startSelf(this, orderDetailsBean, "2", getIs_refund_all, getStatus);
                }


                break;
        }
    }
}
