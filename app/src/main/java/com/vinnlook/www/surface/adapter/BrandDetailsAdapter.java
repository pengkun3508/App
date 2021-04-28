package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.BrandDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class BrandDetailsAdapter extends BaseStateAdapter<BrandDetailsBean.ListBean, BrandDetailsAdapter.BrandDetailsHolder> {
    Context context;

    public BrandDetailsAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected BrandDetailsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new BrandDetailsHolder(inflate(parent, R.layout.brand_details_item));
    }

    class BrandDetailsHolder extends BaseHolder<BrandDetailsBean.ListBean> {

        ImageView brend_details_img;
        TextView brend_details_name;
        TextView brend_details_price;

        BrandDetailsHolder(View itemView) {
            super(itemView);
            brend_details_img = getView(R.id.brend_details_img);
            brend_details_name = getView(R.id.brend_details_name);
            brend_details_price = getView(R.id.brend_details_price);

        }

        @Override
        public void bindData(BrandDetailsBean.ListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            brend_details_img.setScaleType(ImageView.ScaleType.MATRIX);
            brend_details_img.setImageMatrix(matrix);
            ImageLoader.image(brend_details_img.getContext(), brend_details_img, data.getGoods_thumb());

            if (data.getIs_promote() == 1) {//显示限时页面
                brend_details_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            } else if (data.getIs_promote() == 0) {//显示普通页面
                brend_details_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

            brend_details_name.setText(data.getGoods_name());

        }


    }
}
