package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.CommodityTitleBean;

/**
 * @Description:
 * @Time:2021/1/8$
 * @Author:pk$
 */
public class CommodityTitleAdapter extends BaseStateAdapter<CommodityTitleBean, CommodityTitleAdapter.CommodityTitleHolder> {
    Context context;

    String hourss;
    String minutess;
    String secondss;

    int size;

    public CommodityTitleAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(String time) {
//        mHour = time;
//        notifyDataSetChanged();

    }

    @Override
    protected CommodityTitleHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CommodityTitleHolder(inflate(parent, R.layout.commodity_title_item));
    }

    public void setSize(int sizes) {
        size = sizes;
    }

    class CommodityTitleHolder extends BaseHolder<CommodityTitleBean> {
        TextView commodity_title_item;


        CommodityTitleHolder(View itemView) {
            super(itemView);
            commodity_title_item = itemView.findViewById(R.id.commodity_title_item);
        }

        @Override
        public void bindData(CommodityTitleBean data) {
            commodity_title_item.setText(data.getTitle());
            if (data.getColor_mark().equals("1")) {
                commodity_title_item.setBackgroundResource(R.color.them);
//                commodity_title_item.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                commodity_title_item.setBackgroundResource(R.color.black);
            }



        }


    }

}
