package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.PreferentialBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/11$
 * @Author:pk$
 */
public class PreferentialAdapter extends BaseStateAdapter<PreferentialBean.ShopListBean, PreferentialAdapter.PreferentialHolder> {
    Context context;


    PreferClickListener preferClickListener;

    public void setPreferClickListener(PreferClickListener preferClickListener) {
        this.preferClickListener = preferClickListener;
    }


    public PreferentialAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected PreferentialHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PreferentialHolder(inflate(parent, R.layout.preferential_item));
    }

    class PreferentialHolder extends BaseHolder<PreferentialBean.ShopListBean> {

        RoundedImageView prefer_img;
        TextView prefer_name, prefer_number, prefer_price;

        LinearLayout prefer_add_car;

        PreferentialHolder(View itemView) {
            super(itemView);
            prefer_img = itemView.findViewById(R.id.prefer_img);
            prefer_name = itemView.findViewById(R.id.prefer_name);
            prefer_number = itemView.findViewById(R.id.prefer_number);
            prefer_price = itemView.findViewById(R.id.prefer_price);
            prefer_add_car = getView(R.id.prefer_add_car);

        }

        @Override
        public void bindData(PreferentialBean.ShopListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            prefer_img.setScaleType(ImageView.ScaleType.MATRIX);
            prefer_img.setImageMatrix(matrix);
            ImageLoader.image(context, prefer_img, data.getGoods_thumb());
            prefer_name.setText(data.getGoods_name());
            prefer_number.setText("已下单" + data.getVirtual_sales() + "件");
            prefer_price.setText(Html.fromHtml("&yen") + data.getProduct_price());

            //加入购物车
            prefer_add_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("rebang_add_car", "加入购物车00000000000");
                    preferClickListener.onGoReClickListener(data, data.getGoods_id(), data.getSearch_attr());
                }
            });
        }
    }

    public interface PreferClickListener {

        void onGoReClickListener(PreferentialBean.ShopListBean data, String getGoods_id, String getSearch_attr);

    }
}

