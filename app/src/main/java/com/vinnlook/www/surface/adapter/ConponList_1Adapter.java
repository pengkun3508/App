package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;

/**
 * @Description:
 * @Time:2020/12/16$
 * @Author:pk$
 */
public class ConponList_1Adapter extends BaseStateAdapter<ConfirmOrderBean.DiscountPriceListBean, ConponList_1Adapter.ConponListHolder> {

    Context context;

    public ConponList_1Adapter(Context contexts) {
        context = contexts;
    }

    @Override
    protected ConponListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ConponListHolder(inflate(parent, R.layout.conpon_list_item));
    }

    class ConponListHolder extends BaseHolder<ConfirmOrderBean.DiscountPriceListBean> {

        TextView conpon_text1;
        TextView conpon_text2;


        ConponListHolder(View itemView) {
            super(itemView);
            conpon_text1 = getView(R.id.conpon_text1);//名称
            conpon_text2 = getView(R.id.conpon_text2);//价格
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void bindData(ConfirmOrderBean.DiscountPriceListBean data) {
            conpon_text1.setText(data.getName());
            conpon_text2.setText("-" + Html.fromHtml("&yen") + String.valueOf(data.getPrice()));


        }

    }

}
