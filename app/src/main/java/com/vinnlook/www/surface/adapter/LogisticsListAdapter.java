package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.WaybillListBean;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class LogisticsListAdapter extends BaseStateAdapter<WaybillListBean, LogisticsListAdapter.LogisticsListHolder> {

    Context context;

    public LogisticsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected LogisticsListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new LogisticsListHolder(inflate(parent, R.layout.logistics_item_1));
    }


    public class LogisticsListHolder extends BaseHolder<WaybillListBean> {

        TextView item_text;

        LogisticsListHolder(View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.item_text);

        }

        @Override
        public void bindData(WaybillListBean data) {
            item_text.setText(data.getValue());


        }
    }
}
