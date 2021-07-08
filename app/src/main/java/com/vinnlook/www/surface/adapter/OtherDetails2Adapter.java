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
import com.vinnlook.www.surface.bean.ThemeOtherDetailsBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/7$
 * @Author:pk$
 */
public class OtherDetails2Adapter extends BaseStateAdapter5<ThemeOtherDetailsBean.ListBean, OtherDetails2Adapter.OtherDetails2Holder> {

    Context context;
    int type;

    public OtherDetails2Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected OtherDetails2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new OtherDetails2Holder(inflate(parent, R.layout.other_details_item2));
    }

    public void setType(int type) {
        this.type = type;
    }


    class OtherDetails2Holder extends BaseHolder<ThemeOtherDetailsBean.ListBean> {
        RoundedImageView img;

        OtherDetails2Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);


        }

        @Override
        public void bindData(ThemeOtherDetailsBean.ListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            img.setScaleType(ImageView.ScaleType.MATRIX);
            img.setImageMatrix(matrix);
            ImageLoader.image(context, img, data.getImage());


        }
    }
}

