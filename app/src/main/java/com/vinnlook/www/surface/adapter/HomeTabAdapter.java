package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ProductDetailsBean;

public class HomeTabAdapter extends BaseStateAdapter<ProductDetailsBean.ProductBean, HomeTabAdapter.HomeTabHolder> {
    Context getContext;

    @Override
    protected HomeTabHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HomeTabHolder(inflate(parent, R.layout.classifylist_item));
    }

    class HomeTabHolder extends BaseHolder<ProductDetailsBean.ProductBean> {
        ImageView classify_img;
        TextView classify_name;
        TextView classify_price, classify_supi;

        HomeTabHolder(View itemView) {
            super(itemView);
            getContext = itemView.getContext();
            classify_img = getView(R.id.classify_img);
            classify_name = getView(R.id.classify_name);
            classify_price = getView(R.id.classify_price);
            classify_supi = getView(R.id.classify_supi);

        }

        @Override
        public void bindData(ProductDetailsBean.ProductBean data) {

            classify_name.setText("\u3000\u3000\u3000" + data.getGoods_name());
            classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());

            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }
            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext).load(data.getGoods_thumb())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                    .into(classify_img);

        }


    }
}