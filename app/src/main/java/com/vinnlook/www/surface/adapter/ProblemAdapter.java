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
import com.vinnlook.www.surface.bean.ProblemBean;

/**
 * @Description:
 * @Time:2020/11/6$
 * @Author:pk$
 */
public class ProblemAdapter extends BaseStateAdapter<ProblemBean.ListBean, ProblemAdapter.ProblemHolder> {
    Context context;

    public ProblemAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ProblemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ProblemHolder(inflate(parent, R.layout.problem_item));
    }

    class ProblemHolder extends BaseHolder<ProblemBean.ListBean> {

        TextView wen_wen_text, wen_da_text, wen_count_text;
        LinearLayout wen_count_layout, da_layout;

        ProblemHolder(View itemView) {
            super(itemView);
            wen_wen_text = itemView.findViewById(R.id.wen_wen_text);//问题
            wen_da_text = itemView.findViewById(R.id.wen_da_text);//答案
            wen_count_layout = itemView.findViewById(R.id.wen_count_layout);//全部回答布局
            wen_count_text = itemView.findViewById(R.id.wen_count_text);//全部数量
            da_layout = itemView.findViewById(R.id.da_layout);//答案布局

        }

        @Override
        public void bindData(ProblemBean.ListBean data) {
            wen_wen_text.setText(data.getQuestion());
            wen_da_text.setText(data.getUser_name() + ":" + data.getContent());
            wen_count_text.setText("全部" + data.getAnswer_count() + "个回答 >");

            if (Integer.parseInt(data.getAnswer_count()) == 0) {
                da_layout.setVisibility(View.GONE);
            } else {
                da_layout.setVisibility(View.VISIBLE);
            }

        }


    }
}
