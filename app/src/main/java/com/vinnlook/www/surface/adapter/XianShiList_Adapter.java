package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.utils.ImageLoader;


/**
 * @Description:
 * @Time:2020/3/30$
 * @Author:pk$ List<HomeDataBean.DiscountBean.ListBeanXX>
 */
public class XianShiList_Adapter extends BaseStateAdapter<HomeDataBean.DiscountBean.ListBeanXX, XianShiList_Adapter.XianShiListHolder> {


    private static Context context;
    private static long mHour;
    private static long mMin;
    private static long mSecond;
    private static String mHours;

    String mMins;
    String mSeconds;

    XianShiGoShoppingClickListener xianShiGoShoppingClickListener;

    public void setXianShiGoShoppingClickListener(XianShiGoShoppingClickListener xianShiGoShoppingClickListener) {
        this.xianShiGoShoppingClickListener = xianShiGoShoppingClickListener;
    }


    public XianShiList_Adapter(Context contexts) {
        context = contexts;
    }

    public static void setDatas(String time) {
        mHours = time;

    }

    @Override
    protected XianShiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new XianShiListHolder(inflate(parent, R.layout.xianshilist_item));
    }

    class XianShiListHolder extends BaseHolder<HomeDataBean.DiscountBean.ListBeanXX> {

        TextView xianshi_name;
        ImageView xianshi_img;
        TextView xianshi_jiage;
        TextView xianshi_yuanjia;
        TextView xianshi_hour;
        TextView xianshi_min;
        TextView xianshi_second, classify_supi, home_add_xianshi_car;

        XianShiListHolder(View itemView) {
            super(itemView);
            xianshi_name = getView(R.id.xianshi_name);
            xianshi_img = getView(R.id.xianshi_img);
            xianshi_jiage = getView(R.id.xianshi_jiage);
            xianshi_yuanjia = getView(R.id.xianshi_yuanjia);
            xianshi_hour = getView(R.id.xianshi_hour);
            xianshi_min = getView(R.id.xianshi_min);
            xianshi_second = getView(R.id.xianshi_second);
            classify_supi = getView(R.id.classify_supi);
            home_add_xianshi_car = getView(R.id.home_add_xianshi_car);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void bindData(HomeDataBean.DiscountBean.ListBeanXX data) {


            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            xianshi_img.setScaleType(ImageView.ScaleType.MATRIX);
            xianshi_img.setImageMatrix(matrix);
            ImageLoader.image(context, xianshi_img, data.getGoods_thumb());

            xianshi_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
//            Glide.with(context).load(data.getGoods_thumb())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                    .into(xianshi_img);
            xianshi_jiage.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            xianshi_yuanjia.setText(Html.fromHtml("&yen") + data.getProduct_price());
            xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

            xianshi_hour.setText(mHours + "");

            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }


            //抢购，加入购物车
            home_add_xianshi_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    xianShiGoShoppingClickListener.onGoClickListener(data, data.getGoods_id(), data.getSearch_attr());
                }
            });


        }
    }

    public interface XianShiGoShoppingClickListener {
        void onGoClickListener(HomeDataBean.DiscountBean.ListBeanXX data, String getGoods_id,String getSearch_attr);

    }
}
