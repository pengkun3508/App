package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.MsggingTypeBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingBoxAdapter extends BaseStateAdapter<MsggingTypeBean, MsggingBoxAdapter.MsggingBoxHolder> {

    Context context;

    public MsggingBoxAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected MsggingBoxHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MsggingBoxHolder(inflate(parent, R.layout.msgbox_item));
    }



    class MsggingBoxHolder extends BaseHolder<MsggingTypeBean> {
        ImageView msg_item_img;
        TextView msg_item_text1, msg_item_text2, msg_item_line, msg_item_number, msg_item_data;


        MsggingBoxHolder(View itemView) {
            super(itemView);
            msg_item_img = itemView.findViewById(R.id.msg_item_img);
            msg_item_text1 = itemView.findViewById(R.id.msg_item_text1);
            msg_item_text2 = itemView.findViewById(R.id.msg_item_text2);
            msg_item_line = itemView.findViewById(R.id.msg_item_line);
            msg_item_number = itemView.findViewById(R.id.msg_item_number);
            msg_item_data = itemView.findViewById(R.id.msg_item_data);
        }

        @Override
        public void bindData(MsggingTypeBean data) {

            ImageLoader.image(context, msg_item_img, data.getImage());
            msg_item_text1.setText(data.getName());

            if (data.getContent().equals("")) {
                msg_item_text2.setText("暂无内容");
            } else {
                msg_item_text2.setText(data.getContent());
            }
            msg_item_number.setText(data.getUnread_count());
            msg_item_data.setText(data.getPush_time());
            if (Integer.parseInt(data.getUnread_count()) > 0) {
                msg_item_number.setVisibility(View.VISIBLE);
            } else {
                msg_item_number.setVisibility(View.GONE);
            }

            if (getAdapterPosition() == getData().size() - 1) {
                msg_item_line.setVisibility(View.INVISIBLE);
            } else {
                msg_item_line.setVisibility(View.VISIBLE);
            }


        }
    }
}
