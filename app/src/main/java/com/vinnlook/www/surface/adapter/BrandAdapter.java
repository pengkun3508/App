package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:品牌列表适配器
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class BrandAdapter extends BaseStateAdapter<BrandListBean.ListBean, BrandAdapter.BrandHolder> {


    Context context;

    public BrandAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected BrandHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new BrandHolder(inflate(parent, R.layout.brand_list_item));
    }

    class BrandHolder extends BaseHolder<BrandListBean.ListBean> {


        ImageView pinpai_img;

        BrandHolder(View itemView) {
            super(itemView);

            pinpai_img = getView(R.id.pinpai_img);

        }

        @Override
        public void bindData(BrandListBean.ListBean data) {
            pinpai_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, pinpai_img, data.getBrand_logo());
        }


    }
}
