package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description: 自营适配器
 * @Time:2020/11/26$
 * @Author:pk$
 */
public class ConfirmOrder_1Adapter extends BaseStateAdapter<ConfirmOrderBean.ZyShopListBean.ShopListBean, ConfirmOrder_1Adapter.ConfirmOrderHolder> {

    Context context;

    public ConfirmOrder_1Adapter(Context context) {
        this.context = context;

    }

    @Override
    protected ConfirmOrderHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ConfirmOrderHolder(inflate(parent, R.layout.confirm_item_order));
    }

    class ConfirmOrderHolder extends BaseHolder<ConfirmOrderBean.ZyShopListBean.ShopListBean> {

        RoundedImageView confirm_order_img;
        TextView confirm_order_name, confirm_order_type, confirm_order_number, confirm_order_price, confirm_order_price_1, activity_name_text, classify_supi;

        ConfirmOrderHolder(View itemView) {
            super(itemView);
            confirm_order_img = getView(R.id.confirm_order_img);
            confirm_order_name = getView(R.id.confirm_order_name);
            confirm_order_type = getView(R.id.confirm_order_type);
            confirm_order_number = getView(R.id.confirm_order_number);
            confirm_order_price = getView(R.id.confirm_order_price);
            confirm_order_price_1 = getView(R.id.confirm_order_price_1);
            activity_name_text = getView(R.id.activity_name_text);
            classify_supi = getView(R.id.classify_supi);

        }

        @Override
        public void bindData(ConfirmOrderBean.ZyShopListBean.ShopListBean data) {
            confirm_order_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            Glide.with(context).load(data.getGoods_thumb())
//                    .into(confirm_order_img);
            ImageLoader.image(context, confirm_order_img, data.getGoods_thumb());

            confirm_order_name.setText("\u3000\u3000\u3000 " +data.getGoods_name());
            confirm_order_type.setText(data.getGoods_attr_name());
            confirm_order_number.setText("x" + data.getNumber());
//            confirm_order_price.setText(Html.fromHtml("&yen") + data.getProduct_price());

            if (data.getIs_promote().equals("1")) {//显示限时页面
                activity_name_text.setText(data.getActive_name());
                activity_name_text.setVisibility(View.VISIBLE);
                confirm_order_price_1.setVisibility(View.VISIBLE);
                confirm_order_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
                confirm_order_price_1.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote().equals("0")) {//显示普通页面
                confirm_order_price_1.setVisibility(View.GONE);
                confirm_order_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
                if (!data.getActive_name().equals("") && data.getActive_name() != null) {
                    activity_name_text.setText(data.getActive_name());
                    activity_name_text.setVisibility(View.VISIBLE);
                } else {
                    activity_name_text.setVisibility(View.GONE);
                }
            }
            classify_supi.setText("自营");
            classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));

            confirm_order_price_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
    }
}
