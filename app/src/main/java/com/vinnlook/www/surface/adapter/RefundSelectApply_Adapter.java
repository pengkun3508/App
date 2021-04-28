package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class RefundSelectApply_Adapter extends BaseStateAdapter<RefundInfoBean.GoodsListBean, RefundSelectApply_Adapter.RefundSelectApplyHolder> {

    Context context;

    public RefundSelectApply_Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected RefundSelectApplyHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new RefundSelectApplyHolder(inflate(parent, R.layout.pinpailist_item));
    }

    class RefundSelectApplyHolder extends BaseHolder<RefundInfoBean.GoodsListBean> {

        ImageView tv_img;

        RefundSelectApplyHolder(View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.pinpai_img);
        }

        @Override
        public void bindData(RefundInfoBean.GoodsListBean data) {
            tv_img.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(context).load(data.getBrand_logo()).into(tv_img);
            ImageLoader.image(context, tv_img, data.getImage());

        }


    }
}

