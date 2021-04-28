package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HuoDongBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

/**
 * @Description:
 * @Time:2021/1/5$
 * @Author:pk$
 */
public class HuoDongTitleAdapter extends BaseStateAdapter5<HuoDongBean, HuoDongTitleAdapter.HuoDongTitleHolder> {
    Context context;

    String hourss;
    String minutess;
    String secondss;

    public HuoDongTitleAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(String time) {
//        mHour = time;
//        notifyDataSetChanged();

    }

    @Override
    protected HuoDongTitleHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HuoDongTitleHolder(inflate(parent, R.layout.huodong_title_item));
    }

    class HuoDongTitleHolder extends BaseHolder<HuoDongBean> {
        TextView huodong_title_item;


        HuoDongTitleHolder(View itemView) {
            super(itemView);
            huodong_title_item = itemView.findViewById(R.id.huodong_title_item);
        }

        @Override
        public void bindData(HuoDongBean data) {
            huodong_title_item.setText(data.getAct_name());
            if (data.getColor_mark().equals("1")) {
                huodong_title_item.setBackgroundResource(R.mipmap.huodongtitle_bg2);
                huodong_title_item.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                huodong_title_item.setBackgroundResource(R.mipmap.huodongtitle_bg1);
                huodong_title_item.setTextColor(context.getResources().getColor(R.color.black));
            }


        }


    }

}
