package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/26$
 * @Author:pk$
 */
public class ProductImageAdapter extends BaseStateAdapter<String, ProductImageAdapter.ProductImageHolder> {

    Context getContext;
    Context context;

    public ProductImageAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ProductImageHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ProductImageHolder(inflate(parent, R.layout.product_image_item));
    }


    class ProductImageHolder extends BaseHolder<String> {
        ImageView img_img;

        ProductImageHolder(View itemView) {
            super(itemView);
            getContext = itemView.getContext();
            img_img = getView(R.id.img_img);
        }

        @Override
        public void bindData(String data) {
            Log.e("ProductImageAdapter","data==image==="+data);
            ImageLoader.image(context, img_img, data);

        }
    }

}
