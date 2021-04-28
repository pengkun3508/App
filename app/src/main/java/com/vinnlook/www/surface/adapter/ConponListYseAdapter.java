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
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter6;

/**
 * @Description:
 * @Time:2021/2/18$
 * @Author:pk$
 */
public class ConponListYseAdapter extends BaseStateAdapter6<AlreadyCouponListBean, ConponListYseAdapter.CouponHolder> {

    Context context;
    CouponYesAdapter_1.ItemBtnClickListener itemBtnClickListener;
    String bonus_id;


    public ConponListYseAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected CouponHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CouponHolder(inflate(parent, R.layout.rv_item_coupon_1));
    }

    public void setId(String bonus_ids) {
        bonus_id = bonus_ids;
    }

    class CouponHolder extends BaseHolder<AlreadyCouponListBean> {

        TextView coupon_price;
        TextView coupon_time;
        TextView coupon_btn, coupon_name;
        RelativeLayout item_coupon_bg_layout;
        ImageView coupon_select_btn;

        CouponHolder(View itemView) {
            super(itemView);
            coupon_price = getView(R.id.coupon_price);
            coupon_time = getView(R.id.coupon_time);
            coupon_btn = getView(R.id.coupon_btn);
            coupon_name = getView(R.id.coupon_name);
            item_coupon_bg_layout = getView(R.id.item_coupon_bg_layout);
            coupon_select_btn = getView(R.id.coupon_select_btn);

        }

        @Override
        public void bindData(AlreadyCouponListBean data) {
            coupon_btn.setText("使用");
            coupon_name.setText(data.getCoupon_name());
            coupon_time.setText("有限期："+data.getUse_time_start() + "至" + data.getUse_time_end());
            //1:折扣券；2：满减券
            if (data.getType().equals("2")) {
                coupon_price.setText(Html.fromHtml("&yen") + data.getReduced());
            } else if (data.getType().equals("1")) {
                coupon_price.setText(data.getReduced() + "折");
            }

            item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2);
            coupon_btn.setVisibility(View.GONE);
            if (bonus_id.equals(data.getId())) {
                coupon_select_btn.setVisibility(View.VISIBLE);
            } else {
                coupon_select_btn.setVisibility(View.GONE);
            }


//            if (data.getIs_use() == 1) {//可以使用优惠券
//                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2);
//                coupon_btn.setVisibility(View.VISIBLE);
//            } else if (data.getIs_use() == 0) {//不可以使用
//                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2_1);
//                coupon_btn.setVisibility(View.GONE);
//            }
//
//            if (data.getIs_used() == 1) {//使用过
//                item_coupon_bg_layout.setBackgroundResource(R.mipmap.coupon_bg_2_1);
//                coupon_btn.setVisibility(View.GONE);
//            }

//            //使用
//            coupon_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.e("bindData", "getAdapterPosition====" + getAdapterPosition());
//                    itemBtnClickListener.onBtnClickListener_1(data.getId(), view, getAdapterPosition());
//
//                }
//            });


        }
    }

    public interface ItemBtnClickListener {
        void onBtnClickListener_1(String type_id, View coupon_btn, int pos);

    }

}