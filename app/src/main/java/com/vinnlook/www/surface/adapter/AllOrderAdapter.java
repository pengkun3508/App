package com.vinnlook.www.surface.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.surface.activity.LogisticsActivity;
import com.vinnlook.www.surface.activity.PayOrderActivity;

/**
 * @Description: 订单列表
 * @Time:2020/4/2$
 * @Author:pk$
 */
public class AllOrderAdapter extends BaseStateAdapter<AllOrderListBean.ListBean, AllOrderAdapter.AllOrderHolder> {

    int marks;
    Context context;


    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);//进入详情页面

        void onCelearOrderClicked(String order_id, int adapterPosition, String type);//删除订单

        void onCelearOrderClicked_1(String order_id, int adapterPosition, String type);//取消订单

        void onSignInOrderClicked(String order_id, int adapterPosition);//签收订单
    }

    public AllOrderAdapter(Context context, OnItemClickListener mOnItemClickListener, int mark) {
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
        this.marks = mark;
    }

    @Override
    protected AllOrderHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new AllOrderHolder(inflate(parent, R.layout.allorderlist_item_1));
    }


    public class AllOrderHolder extends BaseHolder<AllOrderListBean.ListBean> {
        TextView orderItem1No, orderItem1State, orderItem1TotalNumberMoney, orderItem1ShanChuBtn, orderItem1PayBtn, orderItem1SeeWuliu, orderItem1ShouhuoBtn, orderItem1TuihuanBtn, orderItem1QuxiaoBtn,
                order_no_copy;
        RecyclerView orderlistItem1Recycler;
        LinearLayout order_to_details_btn;
        OrderItemAdapter adapter;

        AllOrderHolder(View itemView) {
            super(itemView);
            orderItem1No = getView(R.id.order_item1_no);//订单号
            orderItem1State = getView(R.id.order_item1_state);//订单状态
            orderItem1TotalNumberMoney = getView(R.id.order_item1_total_number_money);//产品总数，总价格
            orderItem1ShanChuBtn = getView(R.id.order_item1_shanchu_btn);//删除订单
            orderItem1QuxiaoBtn = getView(R.id.order_item1_quxiao_btn);//取消订单
            orderItem1PayBtn = getView(R.id.order_item1_pay_btn);//支付订单
            orderItem1SeeWuliu = getView(R.id.order_item1_see_wuliu);//查看物流
            orderItem1ShouhuoBtn = getView(R.id.order_item1_shouhuo_btn);//确认收货
            orderItem1TuihuanBtn = getView(R.id.order_item1_tuihuan_btn);//退换货
            orderlistItem1Recycler = getView(R.id.orderlist_item1_recycler);
            order_to_details_btn = getView(R.id.order_to_details_btn);
            order_no_copy = getView(R.id.order_no_copy);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        int position = getAdapterPosition();
                        //确保position值有效
                        if (position != RecyclerView.NO_POSITION) {
                            mOnItemClickListener.onItemClicked(view, position);
                        }
                    }
                }
            });

            order_to_details_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //确保position值有效
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemClickListener.onItemClicked(view, position);
                    }
                }
            });


        }

        @Override
        public void bindData(AllOrderListBean.ListBean data) {

            orderlistItem1Recycler.setLayoutManager(new LinearLayoutManager(context));
            adapter = new OrderItemAdapter(context, data.getOrder_id(), data.getStatus());
            orderlistItem1Recycler.setAdapter(adapter);
            adapter.setData(data.getShop_list());


            adapter.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
//                    int position = getAdapterPosition();
                    //确保position值有效
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        mOnItemClickListener.onItemClicked(view, getAdapterPosition());
                    }
                }
            });


            Log.e("AllOrderAdapter", "getStatus===" + data.getStatus());

//            if (marks==0) {//全部
//                orderItem1State.setText("状态值");
//                orderItem1ShanChuBtn.setVisibility(View.VISIBLE);//取消订单
//                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
//                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
//                orderItem1PingjiaBtn.setVisibility(View.GONE);//评价
//                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货
//            } else
            if (data.getStatus() == 1) {//待付款
                orderItem1State.setText("待付款");
                orderItem1QuxiaoBtn.setVisibility(View.VISIBLE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.VISIBLE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.GONE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
//                orderItem1PingjiaBtn.setVisibility(View.GONE);//评价
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            } else if (data.getStatus() == 2) {//待发货
                orderItem1State.setText("待发货");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.VISIBLE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货


            } else if (data.getStatus() == 3) {//待收货
                orderItem1State.setText("待收货");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.VISIBLE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.VISIBLE);//确认收货
//                orderItem1PingjiaBtn.setVisibility(View.GONE);//评价
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            } else if (data.getStatus() == 4) {//待评价
                orderItem1State.setText("待评价");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.VISIBLE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
//                orderItem1PingjiaBtn.setVisibility(View.GONE);//评价
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            } else if (data.getStatus() == 5) {//退换货
                orderItem1State.setText("退换货");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.VISIBLE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
//                orderItem1PingjiaBtn.setVisibility(View.GONE);//评价
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            } else if (data.getStatus() == 6) {
                orderItem1State.setText("已完成");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.GONE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.VISIBLE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            } else if (data.getStatus() == 7) {
                orderItem1State.setText("已取消");
                orderItem1QuxiaoBtn.setVisibility(View.GONE);//取消订单
                orderItem1ShanChuBtn.setVisibility(View.VISIBLE);//删除订单
                orderItem1PayBtn.setVisibility(View.GONE);//支付订单
                orderItem1SeeWuliu.setVisibility(View.GONE);//查看物流
                orderItem1ShouhuoBtn.setVisibility(View.GONE);//确认收货
                orderItem1TuihuanBtn.setVisibility(View.GONE);//退换货

            }
            orderItem1No.setText("订单号：" + data.getOrder_sn());
            orderItem1TotalNumberMoney.setText("共" + data.getNum() + "件产品    实付：" + Html.fromHtml("&yen") + data.getOrder_amount() + "元");

            //复制
            order_no_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(orderItem1No.getText());
                    Toast.makeText(context, "复制成功", Toast.LENGTH_LONG).show();
                }
            });

            //取消订单Btn
            orderItem1QuxiaoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onCelearOrderClicked_1(data.getOrder_id(), getAdapterPosition(), data.getStatus() + "");
                }
            });

            //删除订单Btn
            orderItem1ShanChuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onCelearOrderClicked(data.getOrder_id(), getAdapterPosition(), data.getStatus() + "");
                }
            });

            //查看物流Btn
            orderItem1SeeWuliu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogisticsActivity.startSelf(context, data.getOrder_id());

                }
            });

            //确认签收Btn
            orderItem1ShouhuoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onSignInOrderClicked(data.getOrder_id(), getAdapterPosition());
                }
            });

            //支付订单
            orderItem1PayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String recId = "";
                    String goods_id = "";
                    String product_id = "";
                    String num = "";
                    String real_id = "";
                    String address_id = "";
                    String type = "";
                    String confirmMessage = "";
                    String confirmMessage2 = "";
                    PayOrderActivity.startSelf(context, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage, confirmMessage2, data.getOrder_id(),
                            data.getOrder_amount(), "", "", "", "");

                }
            });

        }
    }
}
