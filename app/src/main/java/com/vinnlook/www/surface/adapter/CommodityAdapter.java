package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
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
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CommodityAdapter extends BaseStateAdapter<CommodityListBean.ListBean, CommodityAdapter.CommodityHolder> {


    Context context;

    public CommodityAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected CommodityHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
//        return new CommodityHolder(inflate(parent, R.layout.classifylist_item));
        return new CommodityHolder(inflate(parent, R.layout.commodity_item));
    }

    class CommodityHolder extends BaseHolder<CommodityListBean.ListBean> {


        RoundedImageView Commodity_tiem_img;
        TextView Commodity_tiem_name;
        TextView Commodity_tiem_price, Commodity_supi, Commodity_tiem_attr, Commodity_activity_name,xianshi_yuanjia;

        CommodityHolder(View itemView) {
            super(itemView);

            Commodity_tiem_img = getView(R.id.Commodity_tiem_img);
            Commodity_tiem_name = getView(R.id.Commodity_tiem_name);
            Commodity_tiem_price = getView(R.id.Commodity_tiem_price);
            Commodity_supi = getView(R.id.Commodity_supi);
            Commodity_tiem_attr = getView(R.id.Commodity_tiem_attr);
            Commodity_activity_name = getView(R.id.Commodity_activity_name);
            xianshi_yuanjia=getView(R.id.xianshi_yuanjia);

        }

        @Override
        public void bindData(CommodityListBean.ListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            Commodity_tiem_img.setScaleType(ImageView.ScaleType.MATRIX);
            Commodity_tiem_img.setImageMatrix(matrix);
//            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, Commodity_tiem_img, data.getGoods_thumb());


            if (data.getSuppliers_id().equals("1")) {//自营
                Commodity_supi.setText("自营");
                Commodity_supi.setBackgroundDrawable(Commodity_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                Commodity_supi.setText("海淘");
                Commodity_supi.setBackgroundDrawable(Commodity_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }

            Commodity_tiem_name.setText("\u3000\u3000\u3000" + " " + data.getGoods_name());
            Commodity_tiem_attr.setText(data.getAttr());


//
//            paoqi_item_type.setText(data.getBrand_name());
//            paoqi_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());

            if (data.getIs_promote().equals("1")) {//显示限时页面
                xianshi_yuanjia.setVisibility(View.VISIBLE);
                Commodity_tiem_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
                xianshi_yuanjia.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote().equals("0")) {//显示普通页面
                xianshi_yuanjia.setVisibility(View.GONE);
                Commodity_tiem_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

            xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

            if (data.getActive_name().equals("")) {
                Commodity_activity_name.setVisibility(View.GONE);
            } else {
                Commodity_activity_name.setText(data.getActive_name());
                Commodity_activity_name.setVisibility(View.VISIBLE);
            }


//            if (data.getIs_promote().equals("1")) {//显示限时页面
//                Commodity_tiem_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
//            } else if (data.getIs_promote().equals("0")) {//显示普通页面
//                Commodity_tiem_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
//            }

        }


    }
}
