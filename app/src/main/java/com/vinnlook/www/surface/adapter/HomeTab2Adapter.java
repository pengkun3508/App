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
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab2Bean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class HomeTab2Adapter extends BaseStateAdapter<HomeTab2Bean.ListBean, HomeTab2Adapter.HomeTabHolder> {
    Context getContext;


    @Override
    protected HomeTabHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HomeTabHolder(inflate(parent, R.layout.home_tab_2_item_fragment));
    }

    class HomeTabHolder extends BaseHolder<HomeTab2Bean.ListBean> {
        ImageView tab_2_item_img;
        TextView tab_2_item_name;

        HomeTabHolder(View itemView) {
            super(itemView);
            getContext = itemView.getContext();
            tab_2_item_img = getView(R.id.tab_2_item_img);
            tab_2_item_name = getView(R.id.tab_2_item_name);

        }

        @Override
        public void bindData(HomeTab2Bean.ListBean data) {
            tab_2_item_name.setText(data.getBrand_name());

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            tab_2_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            tab_2_item_img.setImageMatrix(matrix);


            ImageLoader.image(getContext, tab_2_item_img, data.getBrand_logo());
//            Glide.with(getContext).load(data.getBrand_logo())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
//                    .into(tab_2_item_img);


        }


    }
}