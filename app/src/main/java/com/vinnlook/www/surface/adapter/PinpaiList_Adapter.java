package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.utils.ImageLoader;


/**
 * @Description:
 * @Time:2020/3/30$
 * @Author:pk$
 */
public class PinpaiList_Adapter extends BaseStateAdapter<HomeDataBean.BrandBean, PinpaiList_Adapter.PinpaiListHolder> {

    Context context;

    public PinpaiList_Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected PinpaiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PinpaiListHolder(inflate(parent, R.layout.pinpailist_item));
    }

    class PinpaiListHolder extends BaseHolder<HomeDataBean.BrandBean> {

        ImageView tv_img;

        PinpaiListHolder(View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.pinpai_img);
        }

        @Override
        public void bindData(HomeDataBean.BrandBean data) {
            tv_img.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(context).load(data.getBrand_logo()).into(tv_img);
            ImageLoader.image(context,tv_img,data.getBrand_logo());
            Log.e("PinpaiList_Adapter", "===getBrand_logo===" + data.getBrand_logo());

        }


    }
}

