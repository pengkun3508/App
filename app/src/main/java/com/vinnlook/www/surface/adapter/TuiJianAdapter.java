package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:分类适配器
 * @Time:2020/3/31$
 * @Author:pk$
 */
public class TuiJianAdapter extends BaseStateAdapter<MoveDataBean.RecommendBean, TuiJianAdapter.TuiJianHolder> {

    Context context;
    TuiJianClickListener tuiJianClickListener;

    public void setTuiJianClickListener(TuiJianClickListener tuiJianClickListener) {
        this.tuiJianClickListener = tuiJianClickListener;
    }

    public TuiJianAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected TuiJianHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new TuiJianHolder(inflate(parent, R.layout.ripaolist_item));
    }

    class TuiJianHolder extends BaseHolder<MoveDataBean.RecommendBean> {

        ImageView paoqi_item_img;
        TextView paoqi_item_name;
        TextView paoqi_item_type;
        TextView paoqi_item_price, classify_supi;
        LinearLayout home_paoqi_add_car;

        TuiJianHolder(View itemView) {
            super(itemView);
            paoqi_item_img = itemView.findViewById(R.id.paoqi_item_img);
            paoqi_item_name = itemView.findViewById(R.id.paoqi_item_name);
            paoqi_item_type = itemView.findViewById(R.id.paoqi_item_type);
            paoqi_item_price = itemView.findViewById(R.id.paoqi_item_price);
            classify_supi = getView(R.id.classify_supi);
            home_paoqi_add_car = getView(R.id.home_paoqi_add_car);

        }


        @Override
        public void bindData(MoveDataBean.RecommendBean data) {

//                paoqi_item_img.setImageDrawable(ResUtils.getDrawable(R.drawable.shapeyuanjiao3));

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            paoqi_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            paoqi_item_img.setImageMatrix(matrix);

//                paoqi_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                Glide.with(context).load(data.getGoods_thumb()).into(paoqi_item_img);
            ImageLoader.image(context, paoqi_item_img, data.getGoods_thumb());
            paoqi_item_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
//                paoqi_item_type.setText(data.getGoods_name());
            paoqi_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            paoqi_item_type.setText(data.getBrand_name());

            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }

            //加入购物车
            home_paoqi_add_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tuiJianClickListener.onGoClickListener(data, data.getGoods_id(), data.getSearch_attr());
                }
            });

        }


    }

    public interface TuiJianClickListener {
        void onGoClickListener(MoveDataBean.RecommendBean data, String getGoods_id, String getSearch_attr);

    }

}



