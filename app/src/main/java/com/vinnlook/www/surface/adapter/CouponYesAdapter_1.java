package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.AlreadyCouponListBean;

/**
 * @Description:优惠券适配器--已领取
 * @Time:2020/4/1 18:17
 * @Author:pk
 */

public class CouponYesAdapter_1 extends BaseStateAdapter<AlreadyCouponListBean, CouponYesAdapter_1.CouponHolder> {

    Context context;
    ItemBtnClickListener itemBtnClickListener;


    public CouponYesAdapter_1(Context context, ItemBtnClickListener itemBtnClickListener) {
        super();
        this.context = context;
        this.itemBtnClickListener = itemBtnClickListener;
    }


    @Override
    protected CouponHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CouponHolder(inflate(parent, R.layout.rv_item_coupon));
    }

    class CouponHolder extends BaseHolder<AlreadyCouponListBean> {

        TextView coupon_price;
        TextView coupon_time;
        TextView coupon_btn, coupon_name;
        RelativeLayout item_coupon_bg_layout;

        CouponHolder(View itemView) {
            super(itemView);
            coupon_price = getView(R.id.coupon_price);
            coupon_time = getView(R.id.coupon_time);
            coupon_btn = getView(R.id.coupon_btn);
            coupon_name = getView(R.id.coupon_name);
            item_coupon_bg_layout = getView(R.id.item_coupon_bg_layout);

        }

        @Override
        public void bindData(AlreadyCouponListBean data) {
            coupon_btn.setText("使用");
            coupon_name.setText(data.getCoupon_name());
            coupon_time.setText(data.getUse_time_start() + " -  \n" + data.getUse_time_end());
            //1:折扣券；2：满减券
            if (data.getType().equals("2")) {
                coupon_price.setText(Html.fromHtml("&yen") + data.getReduced());
            } else if (data.getType().equals("1")) {
                coupon_price.setText(data.getReduced() + "折");
            }

            if (data.getIs_use() == 1) {//可以使用优惠券
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_1);
                coupon_btn.setVisibility(View.VISIBLE);
            } else if (data.getIs_use() == 0) {//不可以使用
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_1_1);
                coupon_btn.setVisibility(View.INVISIBLE);
            }

            if (data.getIs_used() == 1) {//使用过
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_1_1);
                coupon_btn.setVisibility(View.INVISIBLE);
            }

            //使用
            coupon_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("bindData","getAdapterPosition===="+getAdapterPosition());
                    itemBtnClickListener.onBtnClickListener_1(data.getId(), view, getAdapterPosition());

                }
            });


        }
    }

    public interface ItemBtnClickListener {
        void onBtnClickListener_1(String type_id, View coupon_btn, int pos);

    }

}