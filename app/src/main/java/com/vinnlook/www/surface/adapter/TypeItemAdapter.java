package com.vinnlook.www.surface.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.utils.ResUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;

/**
 * @Description:
 * @Time:2020/5/11$
 * @Author:pk$
 */
public class TypeItemAdapter extends BaseStateAdapter<ClassifyTypeBean.SonListBean, TypeItemAdapter.TypeItemHolder> {


    @Override
    protected TypeItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new TypeItemHolder(inflate(parent, R.layout.classify_screen_item));
    }

    public class TypeItemHolder extends BaseHolder<ClassifyTypeBean.SonListBean> {
        RoundTextView tv_title;


        TypeItemHolder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
        }

        @Override
        public void bindData(ClassifyTypeBean.SonListBean data) {
            tv_title.setText(data.getCat_name());
            if (data.getType().equals("2")) {//选择状态
                tv_title.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色

            } else {
                tv_title.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line)); //背景变色
            }

        }
    }
}
