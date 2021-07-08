package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ThemeListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeListAdapter extends BaseStateAdapter<ThemeListBean.ListBean, ThemeListAdapter.ThemeListHolder> {

    Context context;

    public ThemeListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected ThemeListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ThemeListHolder(inflate(parent, R.layout.theme_list_item));
    }

    class ThemeListHolder extends BaseHolder<ThemeListBean.ListBean> {
        RoundedImageView theme_item_img;

        ThemeListHolder(View itemView) {
            super(itemView);
            theme_item_img = itemView.findViewById(R.id.theme_item_img);
        }

        @Override
        public void bindData(ThemeListBean.ListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            theme_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            theme_item_img.setImageMatrix(matrix);
//            recomm_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, theme_item_img, data.getWidth_image());

        }
    }
}

