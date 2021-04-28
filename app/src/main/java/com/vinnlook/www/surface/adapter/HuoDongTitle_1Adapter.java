package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HuoDong2Bean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDongTitle_1Adapter extends BaseStateAdapter5<HuoDong2Bean, HuoDongTitle_1Adapter.HuoDongTitleHolder> {
    Context context;

    public HuoDongTitle_1Adapter(Context context) {
        this.context = context;
    }


    @Override
    protected HuoDongTitleHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HuoDongTitleHolder(inflate(parent, R.layout.huodong_title_1_item));
    }

    class HuoDongTitleHolder extends BaseHolder<HuoDong2Bean> {
        TextView title_item_name, title_tiem_num;
        LinearLayout huodong_item_layout;


        HuoDongTitleHolder(View itemView) {
            super(itemView);
            huodong_item_layout = itemView.findViewById(R.id.huodong_item_layout);
            title_item_name = itemView.findViewById(R.id.title_item_name);
            title_tiem_num = itemView.findViewById(R.id.title_tiem_num);
        }

        @Override
        public void bindData(HuoDong2Bean data) {
            title_tiem_num.setText(String.valueOf(getAdapterPosition() + 1));
            title_item_name.setText(data.getName());

            if (data.getMark().equals("1")) {
                huodong_item_layout.setBackgroundResource(R.color.white);
            } else {
                huodong_item_layout.setBackgroundResource(R.color.touming);
            }


        }


    }

}

