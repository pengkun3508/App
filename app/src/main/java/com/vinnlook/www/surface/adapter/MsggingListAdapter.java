package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter7;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.MsggingListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingListAdapter extends BaseStateAdapter7<MsggingListBean.ListBean, MsggingListAdapter.MsggingListHolder> {

    Context context;
    int posion;

    public MsggingListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected MsggingListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MsggingListHolder(inflate(parent, R.layout.msglist_item));
    }

    public void setPosion(int posions) {
        posion = posions;
    }

    class MsggingListHolder extends BaseHolder<MsggingListBean.ListBean> {
        ImageView msg_item_img;
        TextView msg_item_name, msg_item_time, msg_item_content;

        MsggingListHolder(View itemView) {
            super(itemView);
            msg_item_img = itemView.findViewById(R.id.msg_item_img);
            msg_item_name = itemView.findViewById(R.id.msg_item_name);
            msg_item_time = itemView.findViewById(R.id.msg_item_time);
            msg_item_content = itemView.findViewById(R.id.msg_item_content);
        }

        @Override
        public void bindData(MsggingListBean.ListBean data) {

            ImageLoader.image(context, msg_item_img, data.getImage());
            msg_item_name.setText(data.getTitle());
            msg_item_time.setText(data.getPush_time());
            msg_item_content.setText(data.getContent());
        }
    }
}

