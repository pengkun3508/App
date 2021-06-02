package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.GroupQiTaAdapter;
import com.vinnlook.www.surface.adapter.GroupShopAdapter;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.GroupOrderDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.GroupOrderDetailsView;
import com.vinnlook.www.utils.CacheActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:拼团订单详情
 * @Time:2021/5/11$
 * @Author:pk$
 */
public class GroupOrderDetailsActivity extends BaseActivity<GroupOrderDetailsPresenter> implements GroupOrderDetailsView {

    static String orderId;
    @BindView(R.id.group_details_back)
    ImageView groupDetailsBack;
    @BindView(R.id.group_details_layout_yes)
    LinearLayout groupDetailsLayoutYes;
    @BindView(R.id.group_details_layout_no)
    LinearLayout groupDetailsLayoutNo;
    @BindView(R.id.group_details_recycler_1)
    RecyclerView groupDetailsRecycler1;
    @BindView(R.id.group_details_price)
    TextView groupDetailsPrice;
    @BindView(R.id.group_details_btn1)
    LinearLayout groupDetailsBtn1;
    @BindView(R.id.group_details_btn2)
    TextView groupDetailsBtn2;
    @BindView(R.id.group_details_btn3)
    TextView groupDetailsBtn3;
    @BindView(R.id.group_details_recycler_2)
    RecyclerView groupDetailsRecycler2;
    @BindView(R.id.group_details_btn0)
    LinearLayout groupDetailsBtn0;

    GroupDetailsBean groupDetailsBean;
    GroupShopAdapter adapter1;
    GroupQiTaAdapter adapter2;
    int dataTime;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < adapter2.getData().size(); i++) {
                dataTime = Integer.valueOf(adapter2.getData().get(i).getStill_time());
                dataTime = dataTime - 1;
                adapter2.getData().get(i).setStill_time(dataTime);

            }
            //设置倒计时
            adapter2.setData(adapter2.getData());
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dataTime <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

    public static void startSelf(Context context, String orderIds) {
        Intent intent = new Intent(context, GroupOrderDetailsActivity.class);
        context.startActivity(intent);
        orderId = orderIds;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_order_details;
    }

    @Override
    protected GroupOrderDetailsPresenter initPresenter() {
        return new GroupOrderDetailsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);

        //购买商品适配器
        adapter1 = new GroupShopAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        groupDetailsRecycler1.setLayoutManager(manager1);
        groupDetailsRecycler1.setNestedScrollingEnabled(false);
        groupDetailsRecycler1.setHasFixedSize(true);
        groupDetailsRecycler1.setAdapter(adapter1);
        adapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                OrderDetailsActivity.startSelf(GroupOrderDetailsActivity.this, adapter1.getData().get(position).getOrder_id());

                MoveAbooutActivity_3.startSelf(GroupOrderDetailsActivity.this, adapter1.getData().get(position).getGoods_id(),
                        adapter1.getData().get(position).getSearch_attr());

            }
        });

        //其他商品适配器
        adapter2 = new GroupQiTaAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
        groupDetailsRecycler2.setLayoutManager(manager2);
        groupDetailsRecycler2.setNestedScrollingEnabled(false);
        groupDetailsRecycler2.setHasFixedSize(true);
        groupDetailsRecycler2.setAdapter(adapter2);
        adapter2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_4.startSelf(GroupOrderDetailsActivity.this, adapter2.getData().get(position).getGoods_id(), adapter2.getData().get(position).getSearch_attr(), "","");
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getGroupInfoData(orderId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.group_details_back, R.id.group_details_btn1, R.id.group_details_btn2, R.id.group_details_btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_details_back://返回
                finish();
                break;
            case R.id.group_details_btn1://再开新团--进入商品详情页面
            case R.id.group_details_btn3://再尝试一次--进入商品详情页面
                if (groupDetailsBean.getIs_group().equals("0")) {
                    Toast.makeText(this, "该商品活动已结束", Toast.LENGTH_SHORT).show();
                } else if (groupDetailsBean.getIs_group().equals("1")) {
                    MoveAbooutActivity_4.startSelf(this, groupDetailsBean.getGoods_id(), groupDetailsBean.getSearch_attr(), "","");
                }
                break;
            case R.id.group_details_btn2://查看订单
                OrderDetailsActivity.startSelf(this, groupDetailsBean.getOrder_id());
                break;
        }
    }

    @Override
    public void getGroupDetailsSuccess(int code, GroupDetailsBean data) {

        groupDetailsBean = data;
        if (data.getGroup_status().equals("1")) {//成功
            groupDetailsLayoutYes.setVisibility(View.VISIBLE);
            groupDetailsLayoutNo.setVisibility(View.GONE);
            groupDetailsBtn3.setVisibility(View.GONE);
            groupDetailsBtn0.setVisibility(View.VISIBLE);
        } else if (data.getGroup_status().equals("2")) {//失败
            groupDetailsLayoutYes.setVisibility(View.GONE);
            groupDetailsLayoutNo.setVisibility(View.VISIBLE);
            groupDetailsBtn3.setVisibility(View.VISIBLE);
            groupDetailsBtn0.setVisibility(View.GONE);
        }
        //实付金额
        groupDetailsPrice.setText(Html.fromHtml("&yen") + data.getOrder_amount());


        //购买商品适配器赋值
        adapter1.setData(data.getShop_list());
        //其他商品适配器赋值
        adapter2.setData(data.getGroup_list());

        //        计算秒杀倒计时---ms
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    @Override
    public void getGroupDetailsFail(int code, String msg) {

    }
}
