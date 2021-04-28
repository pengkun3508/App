package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.NotCouponListBean;

/**
 * @Description:优惠券适配器--未领取
 * @Time:2020/4/1 18:17
 * @Author:pk
 */

public class CouponAdapter_1 extends BaseStateAdapter<NotCouponListBean, CouponAdapter_1.CouponHolder> {

    Context context;
    ItemBtnClickListener itemBtnClickListener;


    public CouponAdapter_1(Context context, ItemBtnClickListener itemBtnClickListener) {
        super();
        this.context = context;
        this.itemBtnClickListener = itemBtnClickListener;
    }


    @Override
    protected CouponHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CouponHolder(inflate(parent, R.layout.rv_item_coupon));
    }

    class CouponHolder extends BaseHolder<NotCouponListBean> {

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
        public void bindData(NotCouponListBean data) {
            coupon_btn.setText("领取");
            coupon_name.setText(data.getCoupon_name());
            //1:折扣券；2：满减券
            if (data.getType().equals("2")) {
                coupon_price.setText(Html.fromHtml("&yen") + data.getReduced());
            } else if (data.getType().equals("1")) {
                coupon_price.setText(data.getReduced() + "折");
            }

            //1:时间范围；2：领券当日起；3：领券次日起
            //除了Use_time_type=1显示时间段之外，其余的都显示有效期
            if (data.getUse_time_type().equals("1")) {
                coupon_time.setText(data.getUse_time_start() + " -  \n" + data.getUse_time_end());
            } else {
                coupon_time.setText("有效期：" + data.getUse_time() + "天");
            }



            //领取
            coupon_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemBtnClickListener.onBtnClickListener(data.getId(), coupon_btn, getAdapterPosition());

                }
            });
        }
    }

    public interface ItemBtnClickListener {
        void onBtnClickListener(String type_id, TextView coupon_btn, int pos);

    }

}