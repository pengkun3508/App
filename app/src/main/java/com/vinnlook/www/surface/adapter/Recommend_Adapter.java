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
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/4/6$
 * @Author:pk$
 */
public class Recommend_Adapter extends BaseStateAdapter<HomeTab1Bean.RecommendBean.ListBeanXXXX, Recommend_Adapter.PaoQiListHolder> {

    Context context;


    public Recommend_Adapter(Context context) {
        this.context = context;

    }

    @Override
    protected PaoQiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PaoQiListHolder(inflate(parent, R.layout.recommend_item));
    }


    class PaoQiListHolder extends BaseHolder<HomeTab1Bean.RecommendBean.ListBeanXXXX> {

        ImageView recomm_item_img;
        TextView recomm_item_brand, recomm_item_name;

        PaoQiListHolder(View itemView) {
            super(itemView);
            recomm_item_img = itemView.findViewById(R.id.recomm_item_img);
            recomm_item_brand = itemView.findViewById(R.id.recomm_item_brand);
            recomm_item_name = itemView.findViewById(R.id.recomm_item_name);
        }

        @Override
        public void bindData(HomeTab1Bean.RecommendBean.ListBeanXXXX data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            recomm_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            recomm_item_img.setImageMatrix(matrix);
//            recomm_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, recomm_item_img, data.getGoods_thumb());

            recomm_item_brand.setText(data.getBrand_name());
            recomm_item_name.setText(data.getGoods_name());


        }
    }
}

