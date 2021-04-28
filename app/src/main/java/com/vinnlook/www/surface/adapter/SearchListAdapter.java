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
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.SearchListListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/25$
 * @Author:pk$
 */

public class SearchListAdapter extends BaseStateAdapter<SearchListListBean.ListBean, SearchListAdapter.SearchListHolder> {

    Context context;

    public SearchListAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected SearchListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SearchListHolder(inflate(parent, R.layout.searchlist_item));
    }

    class SearchListHolder extends BaseHolder<SearchListListBean.ListBean> {
        ImageView classify_img;
        TextView classify_name, classify_attr;
        TextView classify_price, classify_supi, classify_avtivity_name, xianshi_yuanjia;

        public SearchListHolder(View itemView) {
            super(itemView);
            classify_img = getView(R.id.classify_img);
            classify_name = getView(R.id.classify_name);
            classify_price = getView(R.id.classify_price);
            classify_supi = getView(R.id.classify_supi);
            classify_attr = getView(R.id.classify_attr);
            classify_avtivity_name = getView(R.id.classify_avtivity_name);
            xianshi_yuanjia = getView(R.id.xianshi_yuanjia);
        }

        @Override
        public void bindData(SearchListListBean.ListBean data) {

            classify_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
//            classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
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
//            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(classify_img.getContext(), classify_img, data.getGoods_thumb());


            if (data.getActive_name().equals("")) {
                classify_avtivity_name.setVisibility(View.GONE);
            } else {
                classify_avtivity_name.setText(data.getActive_name());
                classify_avtivity_name.setVisibility(View.VISIBLE);
            }


            if (data.getIs_promote().equals("1")) {//显示限时页面
                xianshi_yuanjia.setVisibility(View.VISIBLE);
                classify_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
                xianshi_yuanjia.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote().equals("0")) {//显示普通页面
                xianshi_yuanjia.setVisibility(View.GONE);
                classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

            xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

//            if (data.getIs_promote().equals("1")) {//显示限时页面
//                classify_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
//            } else if (data.getIs_promote().equals("0")) {//显示普通页面
//                classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
//            }

            classify_attr.setText(data.getAttr());


        }
    }
}
