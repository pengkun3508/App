package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/1/27$
 * @Author:pk$
 */
public class PinpaiListAdapter extends BaseStateAdapter5<HaiTaoClassBean.BrandBean, PinpaiListAdapter.PinpaiListHolder> {

    Context context;

    public PinpaiListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected PinpaiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PinpaiListHolder(inflate(parent, R.layout.pinpailist_item));
    }

    class PinpaiListHolder extends BaseHolder<HaiTaoClassBean.BrandBean> {

        ImageView tv_img;

        PinpaiListHolder(View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.pinpai_img);
        }

        @Override
        public void bindData(HaiTaoClassBean.BrandBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            tv_img.setScaleType(ImageView.ScaleType.MATRIX);
//            Glide.with(context).load(data.getBrand_logo()).into(tv_img);
            ImageLoader.image(context, tv_img, data.getBrand_logo());

        }


    }
}

