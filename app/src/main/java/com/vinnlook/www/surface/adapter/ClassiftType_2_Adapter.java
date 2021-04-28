package com.vinnlook.www.surface.adapter;

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
 * @Time:2020/10/15$
 * @Author:pk$
 */
public class ClassiftType_2_Adapter extends BaseStateAdapter<ClassifyTypeBean.SonListBean, ClassiftType_2_Adapter.ClassiftType2Holder> {
    @Override
    protected ClassiftType2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ClassiftType2Holder(inflate(parent, R.layout.classifytype_item_2));
    }

    public void setId(String cat_id) {
    }

    class ClassiftType2Holder extends BaseHolder<ClassifyTypeBean.SonListBean> {

        RoundTextView tv_title;

        ClassiftType2Holder(View itemView) {
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

