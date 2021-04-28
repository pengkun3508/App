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
import com.vinnlook.www.surface.bean.MemberBean;

/**
 * @Description:
 * @Time:2020/10/23$
 * @Author:pk$
 */
public class MemeberPrice_Adapter_1 extends BaseStateAdapter<MemberBean.DiscountBean, MemeberPrice_Adapter_1.MemeberPriceHolder> {

    Context context;
    int defItem;

    public MemeberPrice_Adapter_1(Context context) {
        this.context = context;
    }


    //默认选中第一项
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    @Override
    protected MemeberPriceHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MemeberPriceHolder(inflate(parent, R.layout.memberprice_item_1));
    }


    class MemeberPriceHolder extends BaseHolder<MemberBean.DiscountBean> {

        TextView memebet_qian_fuhao, memeber_item_text1, memeber_item_text2, memebet_qian_zhe;


        MemeberPriceHolder(View itemView) {
            super(itemView);
            memebet_qian_fuhao = itemView.findViewById(R.id.memebet_qian_fuhao);
            memeber_item_text1 = itemView.findViewById(R.id.memeber_item_text1);
            memeber_item_text2 = itemView.findViewById(R.id.memeber_item_text2);
            memebet_qian_zhe = itemView.findViewById(R.id.memebet_qian_zhe);
        }

        @Override
        public void bindData(MemberBean.DiscountBean data) {

            memebet_qian_fuhao.setText(Html.fromHtml("&yen"));

            //折扣
            if (data.getType().equals("1")) {
                memebet_qian_fuhao.setVisibility(View.GONE);
                memeber_item_text1.setText(data.getReduced() + "折");
                memebet_qian_zhe.setVisibility(View.VISIBLE);

                //满减
            } else if (data.getType().equals("2")) {
                memebet_qian_fuhao.setVisibility(View.VISIBLE);
                memeber_item_text1.setText(data.getReduced());
                memebet_qian_zhe.setVisibility(View.GONE);
            }
            memeber_item_text2.setText("满" + data.getMin_money() + "使用");


        }


    }
}
