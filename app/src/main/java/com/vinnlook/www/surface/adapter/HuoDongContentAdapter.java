package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HuoDongBean;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/1/5$
 * @Author:pk$
 */
public class HuoDongContentAdapter extends BaseStateAdapter<HuoDongBean.GoodsListBean, HuoDongContentAdapter.HuoDongContentHolder> {
    Context context;
    ReBangClickListener reBangClickListener;

    public void setReBangClickListener(ReBangClickListener reBangClickListener) {
        this.reBangClickListener = reBangClickListener;
    }

    public interface ReBangClickListener {

        void onGoReClickListener(HuoDongBean.GoodsListBean data, String getGoods_id, String getSearch_attr);

    }


    public HuoDongContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected HuoDongContentHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HuoDongContentHolder(inflate(parent, R.layout.huodong_content_item));
    }

    class HuoDongContentHolder extends BaseHolder<HuoDongBean.GoodsListBean> {
        ImageView huodong_tiem_img;
        TextView huodong_tiem_name, huodong_tiem_attr, huodong_tiem_price;
        LinearLayout huodong_tiem_car;


        HuoDongContentHolder(View itemView) {
            super(itemView);
            huodong_tiem_img = itemView.findViewById(R.id.huodong_tiem_img);
            huodong_tiem_name = itemView.findViewById(R.id.huodong_tiem_name);
            huodong_tiem_attr = itemView.findViewById(R.id.huodong_tiem_attr);
            huodong_tiem_price = itemView.findViewById(R.id.huodong_tiem_price);
            huodong_tiem_car = itemView.findViewById(R.id.huodong_tiem_car);
        }

        @Override
        public void bindData(HuoDongBean.GoodsListBean data) {

            Matrix matrix = new Matrix();           //????????????????????????
            matrix.setTranslate(0, 0);          //??????x???y???100??????
            matrix.preRotate(0);                   //???????????????30???
            huodong_tiem_img.setScaleType(ImageView.ScaleType.MATRIX);
            huodong_tiem_img.setImageMatrix(matrix);
//            classify_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(huodong_tiem_img.getContext(), huodong_tiem_img, data.getGoods_thumb());
            huodong_tiem_name.setText(data.getGoods_name());
            huodong_tiem_attr.setText(data.getAttr());

            if (data.getIs_promote().equals("1")) {//??????????????????
                huodong_tiem_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
            } else if (data.getIs_promote().equals("0")) {//??????????????????
                huodong_tiem_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }

            huodong_tiem_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reBangClickListener.onGoReClickListener(data, data.getGoods_id(), data.getSearch_attr());

                }
            });


        }


    }

}
