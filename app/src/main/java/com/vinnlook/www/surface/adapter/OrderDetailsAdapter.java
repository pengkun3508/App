package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.activity.ApplyDetailsActivity;
import com.vinnlook.www.surface.activity.PublishCommentActivity;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class OrderDetailsAdapter extends BaseStateAdapter<OrderDetailsBean.ShopListBean, OrderDetailsAdapter.OrderDetailsHolder> {

    Context context;
    String order_id;
    int getStatus;
    String getIs_all_refund;//1==不可以退款；0==可以退款
    String getIs_refund_all;//0:不是整单退：1：必须整单退
    OrderDetailsClickListener orderDetailsClickListener;

    public void setOnTuiKuaiClickListener(OrderDetailsClickListener orderDetailsClickListener) {
        this.orderDetailsClickListener = orderDetailsClickListener;
    }

    public interface OrderDetailsClickListener {

        void onGoClickListener(OrderDetailsBean.ShopListBean data, String order_id, String getIs_refund_all, int getStatus);

        void onAddShopCarListener(String goods_id, String product_id,String  number);
    }


    public OrderDetailsAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected OrderDetailsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new OrderDetailsHolder(inflate(parent, R.layout.order_details_item));
    }


    public void setOrder_Id(String order_ids, int getStatuss, String getIs_all_refunds, String getIs_refund_alls) {
        order_id = order_ids;
        getStatus = getStatuss;
        getIs_all_refund = getIs_all_refunds;
        getIs_refund_all = getIs_refund_alls;
    }

    class OrderDetailsHolder extends BaseHolder<OrderDetailsBean.ShopListBean> {

        ImageView order_item_image;
        TextView classify_supi, order_item_type, order_item_number, order_item_price, order_pingjia_btn,
                order_tuikuan_btn, order_status_text, order_yes_pingjia_btn, confirm_item_price_1;

        TextView order_item_name, order_add_shop_btn;

        OrderDetailsHolder(View itemView) {
            super(itemView);
            order_item_image = getView(R.id.order_item_image);
            order_item_name = getView(R.id.order_item_name);
            order_item_type = getView(R.id.order_item_type);
            order_item_number = getView(R.id.order_item_number);
            order_item_price = getView(R.id.order_item_price);
            order_pingjia_btn = getView(R.id.order_pingjia_btn);
            order_tuikuan_btn = getView(R.id.order_tuikuan_btn);
            order_status_text = getView(R.id.order_status_text);
            classify_supi = getView(R.id.classify_supi);
            order_yes_pingjia_btn = getView(R.id.order_yes_pingjia_btn);
            confirm_item_price_1 = getView(R.id.confirm_item_price_1);
            order_add_shop_btn = getView(R.id.order_add_shop_btn);


        }

        @Override
        public void bindData(OrderDetailsBean.ShopListBean data) {
            ImageLoader.image(context, order_item_image, data.getGoods_thumb());
            order_item_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
            order_item_type.setText(data.getGoods_attr_name());
            order_item_number.setText("x" + data.getNumber());

            Log.e("bindData", "==getStatus==" + getStatus);

            if (data.getPayment().equals("")) {
                order_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
                confirm_item_price_1.setVisibility(View.GONE);
                order_item_price.setVisibility(View.VISIBLE);
            } else {//实际支付金额
                if (data.getPayment().equals(data.getProduct_price())) {
                    order_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
                    confirm_item_price_1.setVisibility(View.GONE);
                    order_item_price.setVisibility(View.VISIBLE);
                } else {
                    if (data.getProduct_price().equals("")) {
                        order_item_price.setText(Html.fromHtml("&yen") + data.getPayment());
                        confirm_item_price_1.setVisibility(View.GONE);
                        order_item_price.setVisibility(View.VISIBLE);
                        Log.e("测试", "1111111111");

                    } else {
                        Log.e("测试", "222222222");
                        order_item_price.setText(Html.fromHtml("&yen") + data.getPayment());
                        confirm_item_price_1.setText(Html.fromHtml("&yen") + data.getProduct_price());
                        confirm_item_price_1.setVisibility(View.VISIBLE);
                        order_item_price.setVisibility(View.VISIBLE);
                    }
                }
            }

            confirm_item_price_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


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


//            if (getStatus == 1) {//待付款状态
//                order_pingjia_btn.setVisibility(View.GONE);
//                if (getIs_all_refund.equals("1")) {
//                    order_tuikuan_btn.setVisibility(View.GONE);
//                } else {
//                    if (data.getShop_status().equals("0")) {//无变化
//                        order_tuikuan_btn.setVisibility(View.GONE);
//                        order_status_text.setVisibility(View.GONE);
//                    } else if (data.getShop_status().equals("1")) {//退款申请中
//                        order_tuikuan_btn.setVisibility(View.GONE);
//                        order_status_text.setVisibility(View.VISIBLE);
//                        order_status_text.setText("退款中");
//                    } else if (data.getShop_status().equals("2")) {//退款成功
//                        order_tuikuan_btn.setVisibility(View.GONE);
//                        order_status_text.setVisibility(View.VISIBLE);
//                        order_status_text.setText("退款成功");
//                    } else if (data.getShop_status().equals("3")) {//退款失败
//                        order_tuikuan_btn.setVisibility(View.VISIBLE);
//                        order_status_text.setVisibility(View.GONE);
//                    }
//                }
//            } else
            if (getStatus == 2) {//待发货状态
                order_add_shop_btn.setVisibility(View.GONE);
//                order_pingjia_btn.setVisibility(View.GONE);
//                order_yes_pingjia_btn.setVisibility(View.GONE);
                if (getIs_all_refund.equals("1")) {//1==不可以退款；0==可以退款
                    order_tuikuan_btn.setVisibility(View.GONE);
                } else {
                    if (getIs_refund_all.equals("1")) {//0:不是整单退：1：必须整单退
                        order_tuikuan_btn.setVisibility(View.GONE);
                        if (data.getShop_status().equals("0")) {//无变化
                            order_status_text.setVisibility(View.GONE);
                        } else if (data.getShop_status().equals("1")) {//退款中
                            order_status_text.setVisibility(View.VISIBLE);
                            if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退款中");
                            } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退货退款中");
                            }
                        } else if (data.getShop_status().equals("2")) {//退款成功
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("退款成功");
                        } else if (data.getShop_status().equals("3")) {//拒绝退款
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("拒绝退款");
                        } else if (data.getShop_status().equals("4")) {//取消退款
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("取消退款");
                        } else if (data.getShop_status().equals("5")) {//同意退货
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("同意退货");
                        } else if (data.getShop_status().equals("6")) {//已发货
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("已发货");
                        } else if (data.getShop_status().equals("7")) {//已收货
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("已收货");
                        }
                    } else {
                        if (data.getShop_status().equals("0")) {//无变化
                            order_tuikuan_btn.setVisibility(View.VISIBLE);
                            order_status_text.setVisibility(View.GONE);
                        } else if (data.getShop_status().equals("1")) {//退款中
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退款中");
                            } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退货退款中");
                            }
                        } else if (data.getShop_status().equals("2")) {//退款成功
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退款成功");
                            } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("退货退款成功");
                            }
                        } else if (data.getShop_status().equals("3")) {//拒绝退款
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("拒绝退款");
                            } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                                order_status_text.setText("拒绝退货退款");
                            }
                        } else if (data.getShop_status().equals("4")) {//取消退款
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("取消退款");
                        } else if (data.getShop_status().equals("5")) {//同意退货
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("同意退货");
                        } else if (data.getShop_status().equals("6")) {//已发货
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("已发货");
                        } else if (data.getShop_status().equals("7")) {//已收货
                            order_tuikuan_btn.setVisibility(View.GONE);
                            order_status_text.setVisibility(View.VISIBLE);
                            order_status_text.setText("已收货");
                        }
                    }
                }
            } else if (getStatus == 3) {//待收货状态
                order_add_shop_btn.setVisibility(View.VISIBLE);
//                order_pingjia_btn.setVisibility(View.GONE);
//                order_yes_pingjia_btn.setVisibility(View.GONE);
                if (getIs_all_refund.equals("1")) {
                    order_tuikuan_btn.setVisibility(View.GONE);
                } else {
//                    order_tuikuan_btn.setVisibility(View.VISIBLE);
                    if (data.getShop_status().equals("0")) {//无变化
                        order_tuikuan_btn.setVisibility(View.VISIBLE);
                        order_status_text.setVisibility(View.GONE);
                    } else if (data.getShop_status().equals("1")) {//提出退款申请
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款中");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款中");
                        }
                    } else if (data.getShop_status().equals("2")) {//退款成功
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款成功");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款成功");
                        }
                    } else if (data.getShop_status().equals("3")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退款");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退货退款");
                        }
                    } else if (data.getShop_status().equals("4")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("取消退款");
                    } else if (data.getShop_status().equals("5")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("同意退货");
                    } else if (data.getShop_status().equals("6")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已发货");
                    } else if (data.getShop_status().equals("7")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已收货");
                    }
                }
            } else if (getStatus == 4) {//评价状态
                order_add_shop_btn.setVisibility(View.VISIBLE);
                if (data.getComment_id().equals("0")) {//判断是否评价状态
//                    order_add_shop_btn.setVisibility(View.VISIBLE);
//                    order_pingjia_btn.setVisibility(View.VISIBLE);
//                    order_yes_pingjia_btn.setVisibility(View.GONE);
                } else {
//                    order_pingjia_btn.setVisibility(View.GONE);
//                    order_yes_pingjia_btn.setVisibility(View.VISIBLE);
                }

                if (getIs_all_refund.equals("1")) {
                    order_tuikuan_btn.setVisibility(View.GONE);
                } else {
//                    order_tuikuan_btn.setVisibility(View.VISIBLE);
                    if (data.getShop_status().equals("0")) {//无变化
                        order_tuikuan_btn.setVisibility(View.VISIBLE);
                        order_status_text.setVisibility(View.GONE);
                    } else if (data.getShop_status().equals("1")) {//退款中
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款中");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款中");
                        }
                    } else if (data.getShop_status().equals("2")) {//退款成功
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款成功");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款成功");
                        }
                    } else if (data.getShop_status().equals("3")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("拒绝退款");
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退款");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退货退款");
                        }
                    } else if (data.getShop_status().equals("4")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("取消退款");
                    } else if (data.getShop_status().equals("5")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("同意退货");
                    } else if (data.getShop_status().equals("6")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已发货");
                    } else if (data.getShop_status().equals("7")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已收货");
                    }
                }

            } else if (getStatus == 5) {//退换货
                order_add_shop_btn.setVisibility(View.GONE);
                order_tuikuan_btn.setVisibility(View.GONE);
//                order_pingjia_btn.setVisibility(View.GONE);
                if (getIs_all_refund.equals("1")) {
                    order_tuikuan_btn.setVisibility(View.GONE);
                } else {
//                    order_tuikuan_btn.setVisibility(View.VISIBLE);
                    if (data.getShop_status().equals("0")) {//无变化
                        order_tuikuan_btn.setVisibility(View.VISIBLE);
                        order_status_text.setVisibility(View.GONE);
                    } else if (data.getShop_status().equals("1")) {//提出退款申请
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款中");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款中");
                        }
                    } else if (data.getShop_status().equals("2")) {//退款成功
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款成功");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款成功");
                        }
                    } else if (data.getShop_status().equals("3")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退款");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退货退款");
                        }
                    } else if (data.getShop_status().equals("4")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("取消退款");
                    } else if (data.getShop_status().equals("5")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("同意退货");
                    } else if (data.getShop_status().equals("6")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已发货");
                    } else if (data.getShop_status().equals("7")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已收货");
                    }
                }

            } else if (getStatus == 6) {//已完成
                order_add_shop_btn.setVisibility(View.VISIBLE);
                if (data.getComment_id().equals("0")) {//判断是否评价状态
//                    order_pingjia_btn.setVisibility(View.VISIBLE);
//                    order_yes_pingjia_btn.setVisibility(View.GONE);
                } else {
//                    order_pingjia_btn.setVisibility(View.GONE);
//                    order_yes_pingjia_btn.setVisibility(View.VISIBLE);
                }
                if (getIs_all_refund.equals("1")) {
                    order_tuikuan_btn.setVisibility(View.GONE);
                } else {
//                    order_tuikuan_btn.setVisibility(View.VISIBLE);
                    if (data.getShop_status().equals("0")) {//无变化
                        order_tuikuan_btn.setVisibility(View.VISIBLE);
                        order_status_text.setVisibility(View.GONE);
                    } else if (data.getShop_status().equals("1")) {//提出退款申请
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款中");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款中");
                        }
                    } else if (data.getShop_status().equals("2")) {//退款成功
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退款成功");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("退货退款成功");
                        }
                    } else if (data.getShop_status().equals("3")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        if (data.getType().equals("1")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退款");
                        } else if (data.getType().equals("2")) {//订单详情：type,1:仅退款；2：退货退款
                            order_status_text.setText("拒绝退货退款");
                        }
                    } else if (data.getShop_status().equals("4")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("取消退款");
                    } else if (data.getShop_status().equals("5")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("同意退货");
                    } else if (data.getShop_status().equals("6")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已发货");
                    } else if (data.getShop_status().equals("7")) {//退款失败
                        order_tuikuan_btn.setVisibility(View.GONE);
                        order_status_text.setVisibility(View.VISIBLE);
                        order_status_text.setText("已收货");
                    }
                }
            } else {
//                order_pingjia_btn.setVisibility(View.GONE);
                order_tuikuan_btn.setVisibility(View.GONE);
                order_add_shop_btn.setVisibility(View.GONE);
            }

            //再次加入购物车
            order_add_shop_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    orderDetailsClickListener.onAddShopCarListener(data.getGoods_id(), data.getProduct_id(), "1");
                }
            });


            /**
             * 评价
             */
            order_pingjia_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("OrderDetailsAdapter", "点击评价===");
                    PublishCommentActivity.startSelf(context, data.getGoods_id(), data.getSearch_attr(), order_id, data.getRec_id());


                }
            });

            /**
             * 申请退款
             */
            order_tuikuan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("OrderDetailsAdapter", "点击申请退款===");
////                    if (getStatus == 2) {//待发货状态下，直接跳入退款页面--默认为未收到货物
//                        ApplyRefundActivity_1.startSelf(context, data, order_id, "1", getIs_refund_all, getStatus);//types==1仅退款，types==2退款退货
////                    } else {//其他状态下，跳入选择仅退款或者退货退款
//                    ApplyRefundActivity.startSelf(context, data, order_id, getIs_refund_all, getStatus);
////                    }
                    orderDetailsClickListener.onGoClickListener(data, order_id, getIs_refund_all, getStatus);


                }
            });

            /**
             * 点击状态--进入退款详情页面
             */
            order_status_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApplyDetailsActivity.startSelf(context, order_id, data.getRefund_id());
                }
            });

        }


    }
}