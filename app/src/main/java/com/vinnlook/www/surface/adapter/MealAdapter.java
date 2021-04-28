package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.SetMealBean;

import static com.mobile.auth.gatewayauth.utils.ReflectionUtils.getActivity;

/**
 * @Description:
 * @Time:2020/12/25$
 * @Author:pk$
 */
public class MealAdapter extends BaseStateAdapter<SetMealBean, MealAdapter.MealHolder> {

    Context context;

    MealItem_1Adapter adapter;
    MealItem_2Adapter adapter2;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(int position);

        void onTypeClickListener(SetMealBean.ListBean data, int position1, int position2);

        void onAddCatClicked(SetMealBean data, int position);
    }

    public void setItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MealAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected MealHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MealHolder(inflate(parent, R.layout.set_meal_item));
    }

    class MealHolder extends BaseHolder<SetMealBean> {

        TextView item_meal_name, item_meal_price, set_meal_add_cat_btn;
        ImageView item_meal_icon;
        RecyclerView item_meal_recycler_1, item_meal_recycler_2;
        RelativeLayout select_meal_btn;
        LinearLayout item_meal_recy2_layout;

        MealHolder(View itemView) {
            super(itemView);
            item_meal_name = getView(R.id.item_meal_name);
            item_meal_price = getView(R.id.item_meal_price);
            item_meal_icon = getView(R.id.item_meal_icon);
            item_meal_recycler_1 = getView(R.id.item_meal_recycler_1);
            item_meal_recycler_2 = getView(R.id.item_meal_recycler_2);
            select_meal_btn = getView(R.id.select_meal_btn);
            set_meal_add_cat_btn = getView(R.id.set_meal_add_cat_btn);
            item_meal_recy2_layout = getView(R.id.item_meal_recy2_layout);
        }

        @Override
        public void bindData(SetMealBean data) {
            item_meal_name.setText(data.getActive_name());
            item_meal_price.setText(Html.fromHtml("&yen") + data.getPrice());

            adapter = new MealItem_1Adapter(context);
            final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
            manager1.setOrientation(GridLayoutManager.HORIZONTAL);
            item_meal_recycler_1.setLayoutManager(manager1);
            item_meal_recycler_1.setNestedScrollingEnabled(false);
            item_meal_recycler_1.setHasFixedSize(true);
            item_meal_recycler_1.setAdapter(adapter);
            adapter.setData(data.getList());

            adapter2 = new MealItem_2Adapter(context);
            final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
            item_meal_recycler_2.setLayoutManager(manager2);
            item_meal_recycler_2.setAdapter(adapter2);
            adapter2.setData(data.getList());
            adapter2.setPrice(data.getDiscount_price());


            if (data.getType().equals("1")) {
                item_meal_recycler_1.setVisibility(View.VISIBLE);
                item_meal_recy2_layout.setVisibility(View.GONE);
            } else if (data.getType().equals("2")) {
                item_meal_recycler_1.setVisibility(View.GONE);
                item_meal_recy2_layout.setVisibility(View.VISIBLE);
            }


            adapter2.setAddItemCheckClick(new MealItem_2Adapter.AddItemCheckClick() {
                @Override
                public void onTypeClickListener(SetMealBean.ListBean data, int adapterPosition) {
                    mOnItemClickListener.onTypeClickListener(data, getAdapterPosition(), adapterPosition);
                }
            });


            //Item下拉，上收
            select_meal_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClicked(getAdapterPosition());
                }
            });

            //加入购物车
            set_meal_add_cat_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onAddCatClicked(data, getAdapterPosition());

                }
            });


        }


    }
}