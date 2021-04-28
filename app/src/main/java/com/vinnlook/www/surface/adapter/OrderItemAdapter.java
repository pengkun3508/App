package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.surface.activity.ApplyDetailsActivity;
import com.vinnlook.www.surface.activity.PublishCommentActivity;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class OrderItemAdapter extends BaseStateAdapter<AllOrderListBean.ListBean.ShopListBean, OrderItemAdapter.OrderItemHolder> {

    String marks;

    Context context;
    String order_id;
    int status;

    public OrderItemAdapter(Context contexts, String order_ids, int getStatus) {
        super();
        context = contexts;
        order_id = order_ids;
        status = getStatus;
    }


    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }


    @Override
    protected OrderItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new OrderItemHolder(inflate(parent, R.layout.allorderlist_item));
    }


    public class OrderItemHolder extends BaseHolder<AllOrderListBean.ListBean.ShopListBean> {
        TextView order_item_type, order_item_type_number, order_item1_pingjia_btn, order_item1_static_btn, classify_supi,
                order_item1_yes_pingjia_btn;
        ImageView order_item_image;
        TextView order_item_title;

        OrderItemHolder(View itemView) {
            super(itemView);
            order_item_image = getView(R.id.order_item_image);
            order_item_title = getView(R.id.order_item_title);
            order_item_type = getView(R.id.order_item_type);
            order_item_type_number = getView(R.id.order_item_type_number);
            order_item1_pingjia_btn = getView(R.id.order_item1_pingjia_btn);
            order_item1_static_btn = getView(R.id.order_item1_static_btn);
            classify_supi = itemView.findViewById(R.id.classify_supi);
            order_item1_yes_pingjia_btn = itemView.findViewById(R.id.order_item1_yes_pingjia_btn);
        }

        @Override
        public void bindData(AllOrderListBean.ListBean.ShopListBean data) {
            ImageLoader.image(context, order_item_image, data.getGoods_thumb());
            order_item_title.setText("\u3000\u3000\u3000 " + data.getGoods_name());
            order_item_type.setText(data.getGoods_attr_name());
            order_item_type_number.setText("x" + data.getNumber());
            

            //is_gift：0：不是赠品；1：是赠品
            if (data.getIs_gift().equals("0")) {
                if (data.getSuppliers_id().equals("1")) {//自营
                    classify_supi.setText("自营");
                    classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
                } else if (data.getSuppliers_id().equals("2")) {//海淘
                    classify_supi.setText("海淘");
                    classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
                }
            } else {
                classify_supi.setText("赠品");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_2));


            }


            if (status == 2) {//待发货
                order_item1_pingjia_btn.setVisibility(View.GONE);
                order_item1_yes_pingjia_btn.setVisibility(View.GONE);
                if (data.getShop_status().equals("1")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款中");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款中");
                    }
                } else if (data.getShop_status().equals("2")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款成功");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款成功");
                    }
                } else if (data.getShop_status().equals("3")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退款");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退货退款");
                    }
                } else if (data.getShop_status().equals("4")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("取消退款");
                } else if (data.getShop_status().equals("5")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("同意退货");
                } else if (data.getShop_status().equals("6")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已发货");
                } else if (data.getShop_status().equals("7")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已收货");
                } else {
                    order_item1_static_btn.setVisibility(View.GONE);
                }

            } else if (status == 3) {//待收货
                order_item1_pingjia_btn.setVisibility(View.GONE);
                order_item1_yes_pingjia_btn.setVisibility(View.GONE);
                if (data.getShop_status().equals("1")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款中");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款中");
                    }
                } else if (data.getShop_status().equals("2")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款成功");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款成功");
                    }
                } else if (data.getShop_status().equals("3")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退款");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退货退款");
                    }
                } else if (data.getShop_status().equals("4")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("取消退款");
                } else if (data.getShop_status().equals("5")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("同意退货");
                } else if (data.getShop_status().equals("6")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已发货");
                } else if (data.getShop_status().equals("7")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已收货");
                } else {
                    order_item1_static_btn.setVisibility(View.GONE);
                }

            } else if (status == 4) {//评价状态.
                if (data.getComment_id().equals("0")) {//判断是否评价状态
                    order_item1_pingjia_btn.setVisibility(View.VISIBLE);
                    order_item1_yes_pingjia_btn.setVisibility(View.GONE);
                } else {
                    order_item1_pingjia_btn.setVisibility(View.GONE);
                    order_item1_yes_pingjia_btn.setVisibility(View.VISIBLE);
                }
                if (data.getShop_status().equals("1")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款中");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款中");
                    }
                } else if (data.getShop_status().equals("2")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款成功");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款成功");
                    }
                } else if (data.getShop_status().equals("3")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退款");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退货退款");
                    }
                    ;
                } else if (data.getShop_status().equals("4")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("取消退款");
                } else if (data.getShop_status().equals("5")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("同意退货");
                } else if (data.getShop_status().equals("6")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已发货");
                } else if (data.getShop_status().equals("7")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已收货");
                } else {
                    order_item1_static_btn.setVisibility(View.GONE);
                }
            } else if (status == 5) {//退换货
                order_item1_pingjia_btn.setVisibility(View.GONE);
                order_item1_yes_pingjia_btn.setVisibility(View.GONE);
                if (data.getShop_status().equals("1")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款中");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款中");
                    }
                } else if (data.getShop_status().equals("2")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款成功");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款成功");
                    }

                } else if (data.getShop_status().equals("3")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退款");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退货退款");
                    }
                } else if (data.getShop_status().equals("4")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("取消退款");
                } else if (data.getShop_status().equals("5")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("同意退货");
                } else if (data.getShop_status().equals("6")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已发货");
                } else if (data.getShop_status().equals("7")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已收货");
                } else {
                    order_item1_static_btn.setVisibility(View.GONE);
                }
            } else if (status == 6) {//已完成
                if (data.getComment_id().equals("0")) {//判断是否评价状态
                    order_item1_pingjia_btn.setVisibility(View.VISIBLE);
                    order_item1_yes_pingjia_btn.setVisibility(View.GONE);
                } else {
                    order_item1_pingjia_btn.setVisibility(View.GONE);
                    order_item1_yes_pingjia_btn.setVisibility(View.VISIBLE);
                }
                if (data.getShop_status().equals("1")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款中");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款中");
                    }
                } else if (data.getShop_status().equals("2")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退款成功");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("退货退款成功");
                    }
                } else if (data.getShop_status().equals("3")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退款");
                    } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                        order_item1_static_btn.setText("拒绝退货退款");
                    }
                } else if (data.getShop_status().equals("4")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("取消退款");
                } else if (data.getShop_status().equals("5")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("同意退货");
                } else if (data.getShop_status().equals("6")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已发货");
                } else if (data.getShop_status().equals("7")) {
                    order_item1_static_btn.setVisibility(View.VISIBLE);
                    order_item1_static_btn.setText("已收货");
                } else {
                    order_item1_static_btn.setVisibility(View.GONE);
                }
            } else {
                order_item1_pingjia_btn.setVisibility(View.GONE);
                order_item1_static_btn.setVisibility(View.GONE);
            }


            /**
             * 评价
             */
            order_item1_pingjia_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PublishCommentActivity.startSelf(context, data.getGoods_id(), data.getSearch_attr(), order_id, data.getRec_id());
                }
            });

            //进入退款详情页面
            order_item1_static_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApplyDetailsActivity.startSelf(context, order_id, data.getRefund_id());
                }
            });

        }
    }
}
