package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.adapter.SelectShopAdapter;
import com.vinnlook.www.surface.mvp.presenter.AfterSalesPresenter;
import com.vinnlook.www.surface.mvp.view.AfterSalesView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:售后选择商品
 * @Time:2021/2/3$
 * @Author:pk$
 */
public class AfterSalesShopActivity extends BaseActivity<AfterSalesPresenter> implements AfterSalesView {

    static OrderDetailsBean orderDetailsBean;
    static String type;
    static String getIs_refund_all;
    static String getStatus;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.select_recycler)
    RecyclerView selectRecycler;
    @BindView(R.id.select_img)
    ImageView selectImg;
    @BindView(R.id.select_all_btn)
    LinearLayout selectAllBtn;
    @BindView(R.id.select_que_btn)
    RoundTextView selectQueBtn;

    List<OrderDetailsBean.ShopListBean> listBean = new ArrayList<>();
    SelectShopAdapter selectShopAdapter;

    public static void startSelf(Context context, OrderDetailsBean orderDetailsBeans, String types, String getIs_refund_alls, String getStatuss) {
        Intent intent = new Intent(context, AfterSalesShopActivity.class);
        intent.putExtra("bean", orderDetailsBeans);
        context.startActivity(intent);
        orderDetailsBean = orderDetailsBeans;
        type = types;
        getIs_refund_all = getIs_refund_alls;
        getStatus = getStatuss;
        orderDetailsBean.setMrakAll("0");
        Log.e("退款选择商品===", "getStatus===" + getStatus);
        Log.e("退款选择商品===", "getShop_list.size==111===" + orderDetailsBean.getShop_list().size());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sales_shop;
    }

    @Override
    protected AfterSalesPresenter initPresenter() {
        return new AfterSalesPresenter();
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        if (type.equals("1")) {
            actionBar.getTvTitle().setText("仅退款 售后");
        } else if (type.equals("2")) {
            actionBar.getTvTitle().setText("退货退款 售后");
        }

        OrderDetailsBean orderBean = (OrderDetailsBean) getIntent().getSerializableExtra("bean");
        Log.e("退款选择商品===", "getShop_list.size==222===" + orderBean.getShop_list().size());
        orderDetailsBean = orderBean;
        selectShopAdapter = new SelectShopAdapter(this);
        selectRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        if (orderDetailsBean.getMrakAll().equals("0")) {//未全选
            selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
        } else if (orderDetailsBean.getMrakAll().equals("1")) {//已全选
            selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
        }

        for (int i = 0; i < orderDetailsBean.getShop_list().size(); i++) {
            orderDetailsBean.getShop_list().get(i).setMark("0");//未选择状态
        }
//        for (int i = 0; i < orderDetailsBean.getShop_list().size(); i++) {
//            if (!orderDetailsBean.getShop_list().get(i).getShop_status().equals("0")) {
//                orderDetailsBean.getShop_list().remove(i);
//            }
//        }
        Log.e("退款选择商品===", "orderDetailsBean.size==333===" + orderDetailsBean.getShop_list().size());

        selectShopAdapter.setData(orderDetailsBean.getShop_list());
        selectRecycler.setAdapter(selectShopAdapter);
        //单个选择
        selectShopAdapter.setSelectListClickListener(new SelectShopAdapter.SelectListClickListener() {
            @Override
            public void onSelectClickListener(int mark, int position, boolean flag) {
                if (flag) {
                    if (mark == 0) {
                        orderDetailsBean.setMrakAll("0");
                        selectShopAdapter.getData().get(position).setMark("0");
                        selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                    } else {
                        orderDetailsBean.setMrakAll("1");
                        selectShopAdapter.getData().get(position).setMark("1");
                        selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));

                    }

                } else {
                    orderDetailsBean.setMrakAll("0");
                    selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                    if (mark == 0) {
                        selectShopAdapter.getData().get(position).setMark("0");
                    } else {
                        selectShopAdapter.getData().get(position).setMark("1");
                    }
                }
                selectShopAdapter.notifyDataSetChanged();


            }

        });
        //全选
        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderDetailsBean.getMrakAll().equals("0")) {
                    orderDetailsBean.setMrakAll("1");
                    selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                    for (int i = 0; i < selectShopAdapter.getData().size(); i++) {
                        selectShopAdapter.getData().get(i).setMark("1");
                    }
                } else {
                    orderDetailsBean.setMrakAll("0");
                    selectImg.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                    for (int i = 0; i < selectShopAdapter.getData().size(); i++) {
                        selectShopAdapter.getData().get(i).setMark("0");
                    }
                }
                selectShopAdapter.notifyDataSetChanged();
            }
        });

        //申请
        selectQueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.再判断是否海淘商品
                //2.先判断是否已发货--未发货不能单个退款，必须全部退款；--已发货后就可以单个退款
                if (orderDetailsBean.getMrakAll().equals("0")) {//未全选的状态
                    listBean = new ArrayList<>();
                    for (int i = 0; i < selectShopAdapter.getData().size(); i++) {
                        if (selectShopAdapter.getData().get(i).getMark().equals("1")) {
                            listBean.add(selectShopAdapter.getData().get(i));
                        }
                    }
                    Log.e("申请", "===listBean" + listBean);
                    Log.e("申请", "===type" + type);
                    if (listBean.size() > 1) {//批量商品页面
                        if (type.equals("1")) {
                            ApplyRefundSelectActivity_1.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        } else if (type.equals("2")) {
                            ApplyRefundSelectActivity_2.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        }
                    } else if (listBean.size() == 1) {//单个商品页面
                        if (type.equals("1")) {//仅退款
                            ApplyRefundActivity_1.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        } else if (type.equals("2")) {//退货退款
                            ApplyRefundActivity_2.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        }

                    } else {
                        Toast.makeText(AfterSalesShopActivity.this, "请至少选择一个产品", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    listBean = new ArrayList<>();
                    for (int i = 0; i < selectShopAdapter.getData().size(); i++) {
                        if (selectShopAdapter.getData().get(i).getMark().equals("1")) {
                            listBean.add(selectShopAdapter.getData().get(i));
                        }
                    }
//                    ApplyRefundSelectActivity.startSelf(OrderDetailsActivity.this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id());
                    if (listBean.size() == 1){
                        if (type.equals("1")) {//仅退款
                            ApplyRefundActivity_1.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        } else if (type.equals("2")) {//退货退款
                            ApplyRefundActivity_2.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        }
                    }else{
                        if (type.equals("1")) {//仅退款
                            ApplyRefundSelectActivity_1.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        } else if (type.equals("2")) {//退货退款
                            ApplyRefundSelectActivity_2.startSelf(AfterSalesShopActivity.this, listBean, orderDetailsBean.getOrder_id(), type, getIs_refund_all, getStatus);
                        }
                    }


                }


            }
        });

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


}
