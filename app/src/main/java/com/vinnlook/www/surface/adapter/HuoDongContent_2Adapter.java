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
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HuoDong2Bean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDongContent_2Adapter extends BaseStateAdapter<HuoDong2Bean.ActiveListBean.GoodsListBean, HuoDongContent_2Adapter.HuoDongContentHolder> {
    Context context;

    public HuoDongContent_2Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected HuoDongContentHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HuoDongContentHolder(inflate(parent, R.layout.huodong_content_2_item));
    }

    class HuoDongContentHolder extends BaseHolder<HuoDong2Bean.ActiveListBean.GoodsListBean> {
        RoundedImageView huodong_content_img;
        TextView huodong_content_name, huo_goods_num, huo_goods_str, huo_goods_fu, huo_goods_price;


        HuoDongContentHolder(View itemView) {
            super(itemView);
            huodong_content_name = itemView.findViewById(R.id.huodong_content_name);
            huodong_content_img = itemView.findViewById(R.id.huodong_content_img);
            huo_goods_num = itemView.findViewById(R.id.huo_goods_num);
            huo_goods_str = itemView.findViewById(R.id.huo_goods_str);
            huo_goods_fu = itemView.findViewById(R.id.huo_goods_fu);
            huo_goods_price = itemView.findViewById(R.id.huo_goods_price);
        }

        @Override
        public void bindData(HuoDong2Bean.ActiveListBean.GoodsListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            huodong_content_img.setScaleType(ImageView.ScaleType.MATRIX);
            huodong_content_img.setImageMatrix(matrix);
//            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, huodong_content_img, data.getGoods_thumb());
//            ImageLoader.image(context, huodong_content_img, "http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210407/20210407185756_40856.jpg");
            huodong_content_name.setText((String) data.getGoods_name());
            huo_goods_num.setText(data.getAttr_name().getNum());
            huo_goods_str.setText(data.getAttr_name().getStr());

            huo_goods_fu.setText(Html.fromHtml("&yen"));
            if (data.getIs_promote() == 0) {
                huo_goods_price.setText(data.getProduct_price());
            } else if (data.getIs_promote() == 1) {
                huo_goods_price.setText(data.getPreferential_price());
            }


        }


    }

}

