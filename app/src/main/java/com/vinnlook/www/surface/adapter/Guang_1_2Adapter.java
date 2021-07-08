package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GuangThemBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/6/28$
 * @Author:pk$
 */
public class Guang_1_2Adapter extends BaseStateAdapter5<GuangThemBean.ItemBean.ListBeanX, Guang_1_2Adapter.Guang_1_2Holder> {

    Context context;
    int type;

    public Guang_1_2Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected Guang_1_2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Guang_1_2Holder(inflate(parent, R.layout.guang_1_2item));
    }

    public void setType(int type) {
        this.type = type;
    }


    class Guang_1_2Holder extends BaseHolder<GuangThemBean.ItemBean.ListBeanX> {
        ImageView img;

        Guang_1_2Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);


        }

        @Override
        public void bindData(GuangThemBean.ItemBean.ListBeanX data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            img.setScaleType(ImageView.ScaleType.MATRIX);
            img.setImageMatrix(matrix);
//            ImageLoader.image(context, img, data.getImage());
            Log.e("主题乐园","===type==="+type);
            if (type == 1) {
                ImageLoader.image(context, img, data.getHeight_image());
            } else {
                ImageLoader.image(context, img, data.getImage());
            }

        }
    }
}

