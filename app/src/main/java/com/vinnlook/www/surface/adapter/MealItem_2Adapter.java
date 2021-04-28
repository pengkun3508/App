package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.SetMealBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/28$
 * @Author:pk$
 */
public class MealItem_2Adapter extends BaseStateAdapter<SetMealBean.ListBean, MealItem_2Adapter.MealItem_2Holder> {

    Context context;

    String discount_price;

    private AddItemCheckClick addItemCheckClick;

    public void setAddItemCheckClick(AddItemCheckClick addItemCheckClick) {
        this.addItemCheckClick = addItemCheckClick;
    }

    public interface AddItemCheckClick {
        void onTypeClickListener(SetMealBean.ListBean data, int adapterPosition);
    }


    public MealItem_2Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected MealItem_2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MealItem_2Holder(inflate(parent, R.layout.set_meal_item2));
    }

    public void setPrice(String discount_pricevs) {
        discount_price = discount_pricevs;
    }

    class MealItem_2Holder extends BaseHolder<SetMealBean.ListBean> {

        TextView meal_2_name, meal_2_type, meal_2_price1, meal_2_price2;
        RoundedImageView meal_2_img;
        LinearLayout meal_2_select_type;

        MealItem_2Holder(View itemView) {
            super(itemView);
            meal_2_img = getView(R.id.meal_2_img);
            meal_2_name = getView(R.id.meal_2_name);
            meal_2_type = getView(R.id.meal_2_type);
            meal_2_select_type = getView(R.id.meal_2_select_type);
            meal_2_price1 = getView(R.id.meal_2_price1);
            meal_2_price2 = getView(R.id.meal_2_price2);
        }

        @Override
        public void bindData(SetMealBean.ListBean data) {
            ImageLoader.image(context, meal_2_img, data.getGoods_thumb());
            meal_2_name.setText(data.getGoods_name());
            meal_2_type.setText(data.getGoods_attr_name());
            meal_2_price1.setText(Html.fromHtml("&yen") + data.getProduct_price());
            meal_2_price2.setText(Html.fromHtml("&yen") + discount_price);

            //选规格
            meal_2_select_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addItemCheckClick.onTypeClickListener(data, getAdapterPosition());

                }
            });


        }


    }
}