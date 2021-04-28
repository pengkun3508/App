package com.vinnlook.www.surface.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

import java.util.HashMap;

/**
 * @Description:
 * @Time:2020/10/16$
 * @Author:pk$
 */
public class ClassiftType_3_Adapter extends BaseStateAdapter5<HashMap<String, String>, ClassiftType_3_Adapter.ClassiftType3Holder> {


    private OnTitleItemClickListener mOnItemClickListener;

    public interface OnTitleItemClickListener {
        void onItemClicked(View view, int position);//进入详情页面
    }

    public void setTitleItemClickListener(OnTitleItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    protected ClassiftType3Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ClassiftType3Holder(inflate(parent, R.layout.classifytype_item_3));
    }

    class ClassiftType3Holder extends BaseHolder<HashMap<String, String>> {

        RoundTextView tv_title;
        ImageView clear_sele;

        ClassiftType3Holder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
            clear_sele = getView(R.id.clear_sele);

        }

        @Override
        public void bindData(HashMap<String, String> data) {
            tv_title.setText(data.get("name"));
            clear_sele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClicked(view, getAdapterPosition());

                }
            });
        }
    }
}

