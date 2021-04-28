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
import com.vinnlook.www.surface.bean.SetMealBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/28$
 * @Author:pk$
 */
public class MealItem_1Adapter extends BaseStateAdapter<SetMealBean.ListBean, MealItem_1Adapter.MealItem_1Holder> {

    Context context;
    int size;
    int posion;

    public MealItem_1Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected MealItem_1Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MealItem_1Holder(inflate(parent, R.layout.set_meal_1_item));
    }


    class MealItem_1Holder extends BaseHolder<SetMealBean.ListBean> {

        TextView item_1_text;
        ImageView item_1_img;

        MealItem_1Holder(View itemView) {
            super(itemView);
            item_1_img = getView(R.id.item_1_img);
            item_1_text = getView(R.id.item_1_text);
        }

        @Override
        public void bindData(SetMealBean.ListBean data) {
            ImageLoader.image(context, item_1_img, data.getGoods_thumb());

            if (getAdapterPosition() == getData().size() - 1) {
                item_1_text.setVisibility(View.GONE);
            } else {
                item_1_text.setVisibility(View.VISIBLE);
            }

        }


    }
}