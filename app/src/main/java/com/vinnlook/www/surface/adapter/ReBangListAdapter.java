package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:分类适配器
 * @Time:2020/3/31$
 * @Author:pk$
 */
public class ReBangListAdapter extends BaseStateAdapter<ReBangListBean, ReBangListAdapter.ReBangListHolder> {

    Context context;
    ReBangClickListener reBangClickListener;

    public void setReBangClickListener(ReBangClickListener reBangClickListener) {
        this.reBangClickListener = reBangClickListener;
    }

    public ReBangListAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ReBangListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ReBangListHolder(inflate(parent, R.layout.rebanglist_item_1));
    }

    class ReBangListHolder extends BaseHolder<ReBangListBean> {

        RelativeLayout rebang_paihang_img;
        TextView rebang_paihang_text, rebang_name, rebang_number, rebang_price;
        RoundedImageView rebang_img;
        LinearLayout rebang_add_car;


        ReBangListHolder(View itemView) {
            super(itemView);
            rebang_paihang_img = itemView.findViewById(R.id.rebang_paihang_img);
            rebang_paihang_text = itemView.findViewById(R.id.rebang_paihang_text);
            rebang_img = itemView.findViewById(R.id.rebang_img);
            rebang_name = itemView.findViewById(R.id.rebang_name);
            rebang_number = getView(R.id.rebang_number);
            rebang_price = getView(R.id.rebang_price);
            rebang_add_car = getView(R.id.rebang_add_car);


        }


        @Override
        public void bindData(ReBangListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            rebang_img.setScaleType(ImageView.ScaleType.MATRIX);
            rebang_img.setImageMatrix(matrix);
            ImageLoader.image(context, rebang_img, data.getGoods_thumb());


            rebang_name.setText(data.getGoods_name());
            if (data.getIs_promote() == 0) {
                rebang_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote() == 1) {
                rebang_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            }

            rebang_number.setText("已下单" + data.getVirtual_sales() + "件");
            rebang_paihang_text.setText(getAdapterPosition() + 1 + "");
            if (getAdapterPosition() == 0) {
                rebang_paihang_img.setBackground(context.getResources().getDrawable(R.mipmap.move_rebang_list_item_img1));
            } else if (getAdapterPosition() == 1) {
                rebang_paihang_img.setBackground(context.getResources().getDrawable(R.mipmap.move_rebang_list_item_img2));
            } else if (getAdapterPosition() == 2) {
                rebang_paihang_img.setBackground(context.getResources().getDrawable(R.mipmap.move_rebang_list_item_img3));
            } else if (getAdapterPosition() > 2) {
                rebang_paihang_img.setBackground(context.getResources().getDrawable(R.mipmap.move_rebang_list_item_img4));
            }


            //加入购物车
            rebang_add_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("rebang_add_car", "加入购物车00000000000");
                    reBangClickListener.onGoReClickListener(data, data.getGoods_id(), data.getSearch_attr());
                }
            });

        }


    }

    public interface ReBangClickListener {

        void onGoReClickListener(ReBangListBean data, String getGoods_id, String getSearch_attr);

    }

}



