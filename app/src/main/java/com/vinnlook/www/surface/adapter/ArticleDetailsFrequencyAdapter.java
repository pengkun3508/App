package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ArticleDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class ArticleDetailsFrequencyAdapter extends BaseStateAdapter<ArticleDetailsBean.ListBean, ArticleDetailsFrequencyAdapter.ArticleDetailsFrequencyHolder> {

    Context context;
    ApplyListAdapter.ApplyListClickListener applyListClickListener;

    public ArticleDetailsFrequencyAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ArticleDetailsFrequencyHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ArticleDetailsFrequencyHolder(inflate(parent, R.layout.article_frequency_item));
    }

    class ArticleDetailsFrequencyHolder extends BaseHolder<ArticleDetailsBean.ListBean> {
        RoundedImageView frequency_item_img;
        TextView frequency_item_text;

        ArticleDetailsFrequencyHolder(View itemView) {
            super(itemView);
            frequency_item_img = getView(R.id.frequency_item_img);
            frequency_item_text = getView(R.id.frequency_item_text);
        }

        @Override
        public void bindData(ArticleDetailsBean.ListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            frequency_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            frequency_item_img.setImageMatrix(matrix);
            ImageLoader.image(context, frequency_item_img, data.getImage());
            frequency_item_text.setText(data.getIssues());


        }
    }
}

