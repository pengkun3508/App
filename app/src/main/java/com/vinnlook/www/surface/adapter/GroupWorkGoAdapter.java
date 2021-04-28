package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

/**
 * @Description:
 * @Time:2021/4/23$
 * @Author:pk$
 */
public class GroupWorkGoAdapter extends BaseStateAdapter5<HomeTab1Bean.DiscountBean.ListBeanXX, GroupWorkGoAdapter.DiscountHolder> {

    Context context;

    public GroupWorkGoAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    @Override
    protected DiscountHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new DiscountHolder(inflate(parent, R.layout.group_go_item));
    }


    class DiscountHolder extends BaseHolder<HomeTab1Bean.DiscountBean.ListBeanXX> {


        DiscountHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void bindData(HomeTab1Bean.DiscountBean.ListBeanXX data) {


        }


    }
}

