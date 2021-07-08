package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/7$
 * @Author:pk$
 */
public class OtherDetails1Adapter extends BaseStateAdapter5<String, OtherDetails1Adapter.OtherDetails1Holder> {

    Context context;
    int type;

    public OtherDetails1Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected OtherDetails1Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new OtherDetails1Holder(inflate(parent, R.layout.other_details_item1));
    }

    public void setType(int type) {
        this.type = type;
    }


    class OtherDetails1Holder extends BaseHolder<String> {
        RoundedImageView img;

        OtherDetails1Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);


        }

        @Override
        public void bindData(String data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            img.setScaleType(ImageView.ScaleType.MATRIX);
            img.setImageMatrix(matrix);
            ImageLoader.image(context, img, data);


        }
    }
}

