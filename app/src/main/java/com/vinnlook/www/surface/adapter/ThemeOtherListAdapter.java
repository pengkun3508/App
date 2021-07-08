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
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class ThemeOtherListAdapter extends BaseStateAdapter<ThemeOtherListBean.ListBean, ThemeOtherListAdapter.ThemeOtherListHolder> {

    Context context;

    public ThemeOtherListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected ThemeOtherListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ThemeOtherListHolder(inflate(parent, R.layout.theme_other_list_item));
    }

    class ThemeOtherListHolder extends BaseHolder<ThemeOtherListBean.ListBean> {
        RoundedImageView theme_other_item_img;

        ThemeOtherListHolder(View itemView) {
            super(itemView);
            theme_other_item_img = itemView.findViewById(R.id.theme_other_item_img);
        }

        @Override
        public void bindData(ThemeOtherListBean.ListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            theme_other_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            theme_other_item_img.setImageMatrix(matrix);
//            recomm_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, theme_other_item_img, data.getImage());

        }
    }
}

