package com.vinnlook.www.surface.adapter;

import android.graphics.Color;
import android.graphics.Matrix;
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
import com.vinnlook.www.surface.bean.ClassifyBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:分类适配器
 * @Time:2020/3/31$
 * @Author:pk$
 */
public class ClassifyList_Adapter extends BaseStateAdapter<ClassifyBean.ListBean, ClassifyList_Adapter.ClassifyLisHolder> {
    @Override
    protected ClassifyLisHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ClassifyLisHolder(inflate(parent, R.layout.classifylist_item));
    }

    class ClassifyLisHolder extends BaseHolder<ClassifyBean.ListBean> {

        ImageView classify_img;
        TextView classify_name;
        TextView classify_price, classify_supi;

        ClassifyLisHolder(View itemView) {
            super(itemView);
            classify_img = getView(R.id.classify_img);
            classify_name = getView(R.id.classify_name);
            classify_price = getView(R.id.classify_price);
            classify_supi = getView(R.id.classify_supi);

        }


        @Override
        public void bindData(ClassifyBean.ListBean data) {
            classify_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
//            classify_price.setText(Html.fromHtml("&yen")+ data.getProduct_price());
            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            classify_img.setScaleType(ImageView.ScaleType.MATRIX);
            classify_img.setImageMatrix(matrix);
            ImageLoader.image(classify_img.getContext(), classify_img, data.getGoods_thumb());


            if (data.getIs_promote().equals("1")) {//显示限时页面
                classify_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            } else if (data.getIs_promote().equals("0")) {//显示普通页面
                classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

        }
    }
}



