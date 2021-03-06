package com.vinnlook.www.surface.adapter;

import android.content.Context;
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
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class RecommendAdapter_1 extends BaseStateAdapter<HomeGoodsListBean.ListBean, RecommendAdapter_1.RecommendHolder> {

    Context context;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public RecommendAdapter_1(Context context) {
        this.context = context;
    }


    @Override
    protected RecommendHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new RecommendHolder(inflate(parent, R.layout.classifylist_item));
    }

    class RecommendHolder extends BaseHolder<HomeGoodsListBean.ListBean> {

        ImageView classify_img;
        TextView classify_name;
        TextView classify_price, classify_supi;

        RecommendHolder(View itemView) {
            super(itemView);

            classify_img = getView(R.id.classify_img);
            classify_name = getView(R.id.classify_name);
            classify_price = getView(R.id.classify_price);
            classify_supi = getView(R.id.classify_supi);


        }

        @Override
        public void bindData(HomeGoodsListBean.ListBean data) {

            classify_name.setText("\u3000\u3000\u3000" + data.getGoods_name());
//            classify_price.setText(Html.fromHtml("&yen")+ data.getProduct_price());
//            SpannableStringBuilder span = new SpannableStringBuilder(data.getGoods_name());
//            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 4,
//                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            classify_name.setText(span);


            if (data.getSuppliers_id().equals("1")) {//??????
                classify_supi.setText("??????");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//??????
                classify_supi.setText("??????");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }


            Matrix matrix = new Matrix();           //????????????????????????
            matrix.setTranslate(0, 0);          //??????x???y???100??????
            matrix.preRotate(0);                   //???????????????30???
            classify_img.setScaleType(ImageView.ScaleType.MATRIX);
            classify_img.setImageMatrix(matrix);
//            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(classify_img.getContext(), classify_img, data.getGoods_thumb());
//            Glide.with(context).load(data.getGoods_thumb())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
//                    .into(classify_img);


            if (data.getIs_promote().equals("1")) {//??????????????????
                classify_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            } else if (data.getIs_promote().equals("0")) {//??????????????????
                classify_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

        }


    }

}

