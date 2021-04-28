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

import java.util.HashMap;

/**
 * @Description:
 * @Time:2020/10/30$
 * @Author:pk$
 */
public class MemeberQuanTtileAdapter extends BaseStateAdapter<HashMap, MemeberQuanTtileAdapter.MemeberQuanTtileHolder> {

    Context context;
    int defItem;
    int index;

    View itemViews;

    public MemeberQuanTtileAdapter(Context context) {
        this.context = context;
    }


    //默认选中第一项
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    @Override
    protected MemeberQuanTtileHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MemeberQuanTtileHolder(inflate(parent, R.layout.memeber_title_item));
    }

    public void setPosion(int indexs) {
        index = indexs;
    }

    public View getItemView() {
        return itemViews.findViewById(R.id.item_layouts);
    }


    class MemeberQuanTtileHolder extends BaseHolder<HashMap> {

        ImageView img1, img2;
        TextView text1;


        MemeberQuanTtileHolder(View itemView) {
            super(itemView);
            itemViews = itemView;
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            text1 = getView(R.id.text1);
        }

        @Override
        public void bindData(HashMap data) {
            img1.setBackgroundResource(Integer.parseInt(data.get("img1").toString()));
            img2.setBackgroundResource(Integer.parseInt(data.get("img2").toString()));
            text1.setText(data.get("name").toString());

            if (index == getAdapterPosition()) {
                img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                text1.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);
                text1.setTextColor(context.getResources().getColor(R.color.black));
            }
        }


    }

}
