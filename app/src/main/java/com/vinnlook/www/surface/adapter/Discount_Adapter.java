package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class Discount_Adapter extends BaseStateAdapter5<HomeTab1Bean.DiscountBean.ListBeanXX, Discount_Adapter.DiscountHolder> {

    Context context;
    private static final int ITEM_FOOT = 4;
    int pos;
    int posin;

    public Discount_Adapter(Context context) {
        super();
        this.context = context;
    }

    public void setPostion(int poss) {
        posin = poss;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("holderType","position==="+position);

        return position;
    }

    @Override
    protected DiscountHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        Log.e("holderType","holderType==="+holderType);
//        if (holderType == getData().size() + 1) {
//            View view = LayoutInflater.from(context).inflate(R.layout.discount_item_1, parent, false);
//            return new DiscountHolder(view);
//        }
        return new DiscountHolder(inflate(parent, R.layout.discount_item));
    }


    class DiscountHolder extends BaseHolder<HomeTab1Bean.DiscountBean.ListBeanXX> {
        ImageView discount_item_img;
        TextView discount_item_name, discount_item_price, discount_item_go;
        AutoSplitTextView discount_item_brend;


        DiscountHolder(View itemView) {
            super(itemView);
            discount_item_img = itemView.findViewById(R.id.discount_item_img);
            discount_item_brend = itemView.findViewById(R.id.discount_item_brend);
            discount_item_name = itemView.findViewById(R.id.discount_item_name);
            discount_item_price = itemView.findViewById(R.id.discount_item_price);
            discount_item_go = itemView.findViewById(R.id.discount_item_go);
        }

        @Override
        public void bindData(HomeTab1Bean.DiscountBean.ListBeanXX data) {
            discount_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, discount_item_img, data.getGoods_thumb());

            discount_item_brend.setText(data.getBrand_name());
            discount_item_name.setText(data.getGoods_name());
            if (data.getIs_promote() == 0) {
                discount_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote() == 1) {
                discount_item_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            }


        }


    }
}

