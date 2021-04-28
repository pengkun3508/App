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
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class LimitedAdapter extends BaseStateAdapter<LimitedBean.ListBean, LimitedAdapter.LimitedHolder> {
    Context context;

    String hourss;
    String minutess;
    String secondss;

    public LimitedAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(String time) {
//        mHour = time;
//        notifyDataSetChanged();

    }

    @Override
    protected LimitedHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new LimitedHolder(inflate(parent, R.layout.xianshilist_item_1));
    }

    class LimitedHolder extends BaseHolder<LimitedBean.ListBean> {
        ImageView xianshi_img;
        TextView xianshi_name;
        TextView xianshi_jiage;
        TextView xianshi_yuanjia;
        TextView xianshi_hour;
        TextView xianshi_min;
        TextView xianshi_second;
        TextView classify_supi;


        LimitedHolder(View itemView) {
            super(itemView);
            xianshi_name = itemView.findViewById(R.id.xianshi_name);
            xianshi_img = itemView.findViewById(R.id.xianshi_img);
            xianshi_jiage = itemView.findViewById(R.id.xianshi_jiage);
            xianshi_yuanjia = itemView.findViewById(R.id.xianshi_yuanjia);
            xianshi_hour = itemView.findViewById(R.id.xianshi_hour);
            xianshi_min = itemView.findViewById(R.id.xianshi_min);
            xianshi_second = itemView.findViewById(R.id.xianshi_second);
            classify_supi = itemView.findViewById(R.id.classify_supi);
        }

        @Override
        public void bindData(LimitedBean.ListBean data) {
//            xianshi_img.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(context).load(data.getGoods_thumb())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                    .into(xianshi_img);
            ImageLoader.image(context, xianshi_img, data.getGoods_thumb());
//            Log.e("PinpaiList_Adapter", "===getBrand_logo===" + data.getBrand_logo());
            xianshi_name.setText(data.getGoods_name());
            xianshi_jiage.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            xianshi_yuanjia.setText(Html.fromHtml("&yen") + data.getProduct_price());
            xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            xianshi_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());


            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }


            long hours = data.getSurplus_time() / (60 * 60);
            long minutes = (data.getSurplus_time() / 60) % 60;
            long seconds = data.getSurplus_time() % 60;
            hourss = String.valueOf(hours);
            minutess = String.valueOf(minutes);
            secondss = String.valueOf(seconds);

            if (hours < 10) {
                hourss = "0" + hours;
            }
            if (minutes < 10) {
                minutess = "0" + minutes;
            }
            if (seconds < 10) {
                secondss = "0" + seconds;
            }
//            Log.e("倒计时--列表", "seconds====" + secondss);

            xianshi_hour.setText(hourss + ":" + minutess + ":" + secondss);
        }


    }

}
