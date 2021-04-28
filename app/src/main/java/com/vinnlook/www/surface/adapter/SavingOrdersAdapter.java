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
import com.vinnlook.www.surface.bean.SavingOrderBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class SavingOrdersAdapter extends BaseStateAdapter<SavingOrderBean.ListBean, SavingOrdersAdapter.SavingOrdersHolder> {

    int marks;
    Context context;


    @Override
    protected SavingOrdersHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SavingOrdersHolder(inflate(parent, R.layout.savingorder_item));
    }


    public class SavingOrdersHolder extends BaseHolder<SavingOrderBean.ListBean> {
        TextView saving_item_no, saving_item_time, item_price;


        SavingOrdersHolder(View itemView) {
            super(itemView);
            saving_item_no = getView(R.id.saving_item_no);//订单号
            saving_item_time = getView(R.id.saving_item_time);//时间
            item_price = getView(R.id.item_price);//金额


        }

        @Override
        public void bindData(SavingOrderBean.ListBean data) {
            saving_item_no.setText("订单号:" + data.getOrder_sn());
            saving_item_time.setText(data.getConfirm_time());
            item_price.setText("已省" + Html.fromHtml("&yen") + data.getCoupon_price());

        }
    }
}
