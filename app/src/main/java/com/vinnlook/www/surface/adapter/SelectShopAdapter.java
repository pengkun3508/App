package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class SelectShopAdapter extends BaseStateAdapter<OrderDetailsBean.ShopListBean, SelectShopAdapter.SelectShopHolder> {

    Context context;
    SelectListClickListener selectListClickListener;
    List<OrderDetailsBean.ShopListBean> list = new ArrayList<>();

    public void setSelectListClickListener(SelectListClickListener selectListClickListener) {
        this.selectListClickListener = selectListClickListener;
    }


    public SelectShopAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected SelectShopHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SelectShopHolder(inflate(parent, R.layout.select_shop_item));
    }

    public void setList() {
        list = new ArrayList<>();
    }

    class SelectShopHolder extends BaseHolder<OrderDetailsBean.ShopListBean> {
        TextView select_cart_name, select_cart_type, select_cart_number;
        ImageView select_check_circle, select_cart_img;

        RelativeLayout select_check_circle_layout;


        SelectShopHolder(View itemView) {
            super(itemView);
            select_check_circle_layout = getView(R.id.select_check_circle_layout);//选择按钮
            select_check_circle = getView(R.id.select_check_circle);//选择图标
            select_cart_img = getView(R.id.select_cart_img);//图片
            select_cart_name = getView(R.id.select_cart_name);//名称
            select_cart_type = getView(R.id.select_cart_type);//型号
            select_cart_number = getView(R.id.select_cart_number);//数量

        }

        @Override
        public void bindData(OrderDetailsBean.ShopListBean data) {
            ImageLoader.image(context, select_cart_img, data.getGoods_thumb());
            select_cart_type.setText(data.getGoods_attr_name());
            select_cart_name.setText(data.getGoods_name());
            select_cart_number.setText(data.getNumber());

            if (data.getMark().equals("0")) {//未选择
                select_check_circle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            } else if (data.getMark().equals("1")) {//选择状态
                select_check_circle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            }

            select_check_circle_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.getMark().equals("0")) {//未选择
                        list.add(data);
                        if (selectListClickListener != null) {
//                                shoppingCheOnClickAll.OnClick(1, list.size() == getData().size());
                            selectListClickListener.onSelectClickListener(1, getAdapterPosition(), list.size() == getData().size());
                        }
                    } else if (data.getMark().equals("1")) {//已选择状态
                        list.remove(data);
                        if (selectListClickListener != null) {
//                                shoppingCheOnClickAll.OnClick(0, list.size() == getData().size());
                            selectListClickListener.onSelectClickListener(0, getAdapterPosition(), list.size() == getData().size());
                        }
                    }

                    Log.e("单选", "list.size===" + list.size());


                }
            });


        }
    }

    public interface SelectListClickListener {
        void onSelectClickListener(int mark, int position, boolean flag);//选择按钮
    }


}

