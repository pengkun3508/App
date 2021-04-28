package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/10/23$
 * @Author:pk$
 */
public class MemeberPrice_Adapter_2 extends BaseStateAdapter<MemberBean.ShopBean, MemeberPrice_Adapter_2.MemeberPriceHolder> {

    Context context;
    int defItem;

    public MemeberPrice_Adapter_2(Context context) {
        this.context = context;
    }


    //默认选中第一项
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    @Override
    protected MemeberPriceHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MemeberPriceHolder(inflate(parent, R.layout.ripaolist_item_1));
    }


    class MemeberPriceHolder extends BaseHolder<MemberBean.ShopBean> {

        ImageView paoqi_item_img;
        TextView paoqi_item_name;
        TextView classify_supi;


        MemeberPriceHolder(View itemView) {
            super(itemView);
            paoqi_item_img = itemView.findViewById(R.id.paoqi_item_img);
            paoqi_item_name = itemView.findViewById(R.id.paoqi_item_name);
            classify_supi = getView(R.id.classify_supi);
        }

        @Override
        public void bindData(MemberBean.ShopBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            paoqi_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            paoqi_item_img.setImageMatrix(matrix);

            ImageLoader.image(context, paoqi_item_img, data.getGoods_thumb());

            paoqi_item_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());

            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }

        }


    }
}
