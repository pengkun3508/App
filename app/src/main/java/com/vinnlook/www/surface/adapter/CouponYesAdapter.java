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
import com.vinnlook.www.surface.bean.NewNotCouponListBean;

/**
 * @Description:优惠券适配器--已领取
 * @Time:2020/4/1 18:17
 * @Author:pk
 */

public class CouponYesAdapter extends BaseStateAdapter<NewNotCouponListBean, CouponYesAdapter.CouponHolder> {

    Context context;
    ItemBtnClickListener itemBtnClickListener;
    String type;


    public CouponYesAdapter(Context context, ItemBtnClickListener itemBtnClickListener) {
        super();
        this.context = context;
        this.itemBtnClickListener = itemBtnClickListener;
    }


    @Override
    protected CouponHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CouponHolder(inflate(parent, R.layout.rv_item_coupon_1));
    }

    public void setType(String types) {
        type = types;
    }

    class CouponHolder extends BaseHolder<NewNotCouponListBean> {

        TextView coupon_price;
        TextView coupon_time;
        TextView coupon_btn, coupon_name, coupon_content;
        RelativeLayout item_coupon_bg_layout;

        CouponHolder(View itemView) {
            super(itemView);
            coupon_price = getView(R.id.coupon_price);
            coupon_time = getView(R.id.coupon_time);
            coupon_btn = getView(R.id.coupon_btn);
            coupon_name = getView(R.id.coupon_name);
            coupon_content = getView(R.id.coupon_content);
            item_coupon_bg_layout = getView(R.id.item_coupon_bg_layout);

        }

        @Override
        public void bindData(NewNotCouponListBean data) {
            coupon_name.setText(data.getCoupon_name());
//            coupon_time.setText(data.getUse_time_start() + " - " + data.getUse_time_end());
//            //1:折扣券；2：满减券
            if (data.getType().equals("2")) {
                coupon_price.setText(Html.fromHtml("&yen") + data.getReduced());
            } else if (data.getType().equals("1")) {
                coupon_price.setText(data.getReduced() + "折");
            }
            coupon_content.setText(data.getContent());

//            coupon_btn.setVisibility(View.GONE);

            coupon_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemBtnClickListener.onBtnClickListener(data.getId(), coupon_btn);

                }
            });

            //1:时间范围；2：领券当日起；3：领券次日起
            //除了Use_time_type=1显示时间段之外，其余的都显示有效期
            if (data.getUse_time_type().equals("1")) {
                coupon_time.setText("有效期：" + data.getUse_time_start() + " 至 " + data.getUse_time_end());
            } else {
                coupon_time.setText("有效期：" + data.getUse_time() + "天");
            }

            if (type.equals("1")) {//待领取
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2);
                coupon_price.setTextColor(context.getResources().getColor(R.color.coupon_text1));
                coupon_btn.setVisibility(View.VISIBLE);
            } else if (type.equals("2")) {//待使用
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2);
                coupon_price.setTextColor(context.getResources().getColor(R.color.coupon_text1));
                coupon_btn.setVisibility(View.GONE);
            } else if (type.equals("3")) {//已使用
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2_1);
                coupon_price.setTextColor(context.getResources().getColor(R.color.coupon_text));
                coupon_btn.setVisibility(View.GONE);
            } else if (type.equals("4")) {//已过期
                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2_1);
                coupon_price.setTextColor(context.getResources().getColor(R.color.coupon_text));
                coupon_btn.setVisibility(View.GONE);
            }


        }
    }

    public interface ItemBtnClickListener {
        void onBtnClickListener(String type_id, TextView coupon_btn);

    }

}