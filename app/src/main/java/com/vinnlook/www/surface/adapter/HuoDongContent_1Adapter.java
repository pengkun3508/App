package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.bean.HuoDong2Bean;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDongContent_1Adapter extends BaseStateAdapter<HuoDong2Bean.ActiveListBean, HuoDongContent_1Adapter.HuoDongContentHolder> {
    Context context;

    public HuoDongContent_1Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected HuoDongContentHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HuoDongContentHolder(inflate(parent, R.layout.huodong_content_1_item));
    }

    class HuoDongContentHolder extends BaseHolder<HuoDong2Bean.ActiveListBean> {
        RoundTextView title_item2_name;
        RecyclerView content_item2_recycler;
        HuoDongContent_2Adapter adapter2;


        HuoDongContentHolder(View itemView) {
            super(itemView);
            title_item2_name = itemView.findViewById(R.id.title_item2_name);
            content_item2_recycler = itemView.findViewById(R.id.content_item2_recycler);
        }

        @Override
        public void bindData(HuoDong2Bean.ActiveListBean data) {
            title_item2_name.setText(data.getAct_name());

            content_item2_recycler.setLayoutManager(new GridLayoutManager(context, 2));
            adapter2 = new HuoDongContent_2Adapter(context);
            content_item2_recycler.setAdapter(adapter2);
            adapter2.setData(data.getGoods_list());

            adapter2.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    MoveAbooutActivity_3.startSelf((Activity) context, adapter2.getData().get(position).getGoods_id(), data.getGoods_list().get(position).getSearch_attr());
                }
            });
        }
    }
}

