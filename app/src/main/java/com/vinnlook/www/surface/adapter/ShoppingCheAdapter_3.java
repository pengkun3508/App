package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/28$
 * @Author:pk$
 */
public class ShoppingCheAdapter_3 extends BaseStateAdapter5<ShopCartListBean_1.ListBean.ListBeanX, ShoppingCheAdapter_3.ShoppingCheHolder> {

    Context context;
    String short_flag;
    int typeId;

    SelectListClickListener selectListClickListener;

    public void setAdapter3ClickListener(SelectListClickListener selectListClickListener) {
        this.selectListClickListener = selectListClickListener;
    }

    public interface SelectListClickListener {
        void onAdapter3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int posion);//选择按钮
    }

    public ShoppingCheAdapter_3(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ShoppingCheHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ShoppingCheHolder(inflate(parent, R.layout.rv_item_shopping_che_3));
    }

    public void setFlag(String short_flags, int typeIds) {
        short_flag = short_flags;
        typeId = typeIds;
    }

    class ShoppingCheHolder extends BaseHolder<ShopCartListBean_1.ListBean.ListBeanX> {
        ImageView shop_cart_item_img_2;
        TextView shopping_item_label_2, shop_cart_item_name_2, shop_cart_item_type_2, shop_cart_item_price_2, shixiao_tips_text,see_details_btn;
        RelativeLayout shop_cart_item_type_layout_2;


        ShoppingCheHolder(View itemView) {
            super(itemView);
            shop_cart_item_img_2 = getView(R.id.shop_cart_item_img_2);//图片
            shopping_item_label_2 = getView(R.id.shopping_item_label_2);//活动标符
            shop_cart_item_name_2 = getView(R.id.shop_cart_item_name_2);//name
            shop_cart_item_type_layout_2 = getView(R.id.shop_cart_item_type_layout_2);//选择规格按钮
            shop_cart_item_type_2 = getView(R.id.shop_cart_item_type_2);//规格
            shop_cart_item_price_2 = getView(R.id.shop_cart_item_price_2);//价格
            shixiao_tips_text = getView(R.id.shixiao_tips_text);
            see_details_btn=getView(R.id.see_details_btn);

        }

        @Override
        public void bindData(ShopCartListBean_1.ListBean.ListBeanX data) {
            ImageLoader.image(context, shop_cart_item_img_2, data.getGoods_thumb());
            shop_cart_item_type_2.setText(data.getGoods_attr_name());
            shop_cart_item_name_2.setText(data.getGoods_name());
            shop_cart_item_price_2.setText(Html.fromHtml("&yen") + data.getProduct_price());
            shopping_item_label_2.setText(short_flag);

            //判断是否是失效产品
            if (typeId == 3) {//失效产品
                shopping_item_label_2.setVisibility(View.GONE);
                shop_cart_item_type_2.setTextColor(shop_cart_item_name_2.getResources().getColor(R.color.button_normal));
                shop_cart_item_name_2.setTextColor(shop_cart_item_name_2.getResources().getColor(R.color.button_normal));
                shop_cart_item_price_2.setTextColor(shop_cart_item_price_2.getResources().getColor(R.color.button_normal));
                shixiao_tips_text.setVisibility(View.VISIBLE);
                shop_cart_item_price_2.setVisibility(View.GONE);
                see_details_btn.setVisibility(View.VISIBLE);

            } else {
                shixiao_tips_text.setVisibility(View.GONE);
                see_details_btn.setVisibility(View.GONE);
                shop_cart_item_type_layout_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectListClickListener.onAdapter3ClickListener(data, getAdapterPosition());

                    }
                });
            }


        }
    }


}

