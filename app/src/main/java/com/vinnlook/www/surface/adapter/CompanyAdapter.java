package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.CompanyBean;

/**
 * @Description:
 * @Time:2021/4/28$
 * @Author:pk$
 */
public class CompanyAdapter extends BaseStateAdapter<CompanyBean, CompanyAdapter.CompanyHolder> {
    Context context;

    String hourss;
    String minutess;
    String secondss;

    int size;

    public CompanyAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected CompanyHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CompanyHolder(inflate(parent, R.layout.company_item));
    }

    class CompanyHolder extends BaseHolder<CompanyBean> {
        TextView about_text, phone_tel;


        CompanyHolder(View itemView) {
            super(itemView);
            about_text = itemView.findViewById(R.id.about_text);
            phone_tel = itemView.findViewById(R.id.phone_tel);
        }

        @Override
        public void bindData(CompanyBean data) {
            about_text.setText(data.getName());
            phone_tel.setText(data.getValue());


        }


    }

}
