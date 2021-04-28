package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

/**
 * @Description:
 * @Time:2020/12/24$
 * @Author:pk$
 */
public class OrderCouponAdapter extends BaseStateAdapter5<OrderDetailsBean.DiscountInfoBean, OrderCouponAdapter.OrderCouponHolder> {

    Context context;

    public OrderCouponAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected OrderCouponHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new OrderCouponHolder(inflate(parent, R.layout.order_coupon_item));
    }

    class OrderCouponHolder extends BaseHolder<OrderDetailsBean.DiscountInfoBean> {

        TextView name, price;

        OrderCouponHolder(View itemView) {
            super(itemView);
            name = getView(R.id.name);
            price = getView(R.id.price);

        }

        @Override
        public void bindData(OrderDetailsBean.DiscountInfoBean data) {
            name.setText(data.getName());
            price.setText("-"+Html.fromHtml("&yen") + data.getPrice());
        }
    }

}
