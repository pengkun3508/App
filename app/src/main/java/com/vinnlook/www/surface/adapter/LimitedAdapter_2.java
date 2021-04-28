package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.utils.ImageLoader;

import java.math.BigDecimal;

/**
 * @Description:
 * @Time:2021/4/8$
 * @Author:pk$
 */
public class LimitedAdapter_2 extends BaseStateAdapter<LimitedBean.ListBean, LimitedAdapter_2.LimitedHolder> {
    Context context;

    String hourss;
    String minutess;
    String secondss;

    public LimitedAdapter_2(Context context) {
        this.context = context;
    }

    public void setDatas(String time) {
//        mHour = time;
//        notifyDataSetChanged();

    }

    @Override
    protected LimitedHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new LimitedHolder(inflate(parent, R.layout.xianshilist_item_3));
    }

    class LimitedHolder extends BaseHolder<LimitedBean.ListBean> {

        RoundedImageView xianshi_img;
        TextView xianshi_name, rebang_number, xianshi_price_fuhao, xianshi_jiage, xianshi_yuanjia, home_add_xianshi_car;
        RoundTextView xianshi_item_jian;


        LimitedHolder(View itemView) {
            super(itemView);
            xianshi_img = itemView.findViewById(R.id.xianshi_img);
            xianshi_name = itemView.findViewById(R.id.xianshi_name);
            rebang_number = itemView.findViewById(R.id.rebang_number);
            xianshi_price_fuhao = itemView.findViewById(R.id.xianshi_price_fuhao);
            xianshi_jiage = itemView.findViewById(R.id.xianshi_jiage);
            xianshi_yuanjia = itemView.findViewById(R.id.xianshi_yuanjia);
            home_add_xianshi_car = itemView.findViewById(R.id.home_add_xianshi_car);
            xianshi_item_jian = itemView.findViewById(R.id.xianshi_item_jian);
        }

        @Override
        public void bindData(LimitedBean.ListBean data) {
            ImageLoader.image(context, xianshi_img, data.getGoods_thumb());
            xianshi_name.setText(data.getGoods_name());
            rebang_number.setText("已下单" + data.getVirtual_sales() + "件");
            xianshi_price_fuhao.setText(Html.fromHtml("&yen"));

            if (data.getIs_promote() == 0) {
                xianshi_jiage.setText(data.getProduct_price());
                xianshi_yuanjia.setVisibility(View.GONE);
            } else {
                xianshi_yuanjia.setVisibility(View.VISIBLE);
                xianshi_jiage.setText(data.getPreferential_price());
                xianshi_yuanjia.setText(Html.fromHtml("&yen") + data.getProduct_price());
                xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                BigDecimal bg1 = new BigDecimal(data.getProduct_price());
                double f1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                BigDecimal bg2 = new BigDecimal(data.getPreferential_price());
                double f2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double jianPrice = f1 - f2;

            }
            xianshi_item_jian.setText(data.getShort_flag());


//            home_add_xianshi_car.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });


        }


    }

}
