package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/15$
 * @Author:pk$
 */
public class ConfirmOrder_1ZengAdapter extends BaseStateAdapter5<ConfirmOrderBean.ZySendListBean, ConfirmOrder_1ZengAdapter.ConfirmOrder_1ZengHolder> {

    Context context;

    PreferClickListener preferClickListener;

    public void setPreferClickListener(PreferClickListener preferClickListener) {
        this.preferClickListener = preferClickListener;
    }

    public ConfirmOrder_1ZengAdapter(Context context) {
        this.context = context;

    }

    @Override
    protected ConfirmOrder_1ZengHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ConfirmOrder_1ZengHolder(inflate(parent, R.layout.confirm_item_zeng));
    }

    class ConfirmOrder_1ZengHolder extends BaseHolder<ConfirmOrderBean.ZySendListBean> {

        RoundedImageView confirm_order_img;
        TextView confirm_order_name, confirm_order_type, confirm_order_number, classify_supi;
        LinearLayout item_zeng_select_type;

        ConfirmOrder_1ZengHolder(View itemView) {
            super(itemView);
            confirm_order_img = getView(R.id.confirm_order_img);
            confirm_order_name = getView(R.id.confirm_order_name);
            confirm_order_type = getView(R.id.confirm_order_type);
            confirm_order_number = getView(R.id.confirm_order_number);
            item_zeng_select_type = getView(R.id.item_zeng_select_type);
            classify_supi = getView(R.id.classify_supi);

        }

        @Override
        public void bindData(ConfirmOrderBean.ZySendListBean data) {
            confirm_order_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, confirm_order_img, data.getGoods_thumb());


            confirm_order_name.setText("\u3000\u3000\u3000" +data.getGoods_name());
            confirm_order_number.setText("x" + data.getNumber());

//            confirm_order_type.setText(data.getGoods_attr_name());
            if (data.getType().equals("1")) {
                confirm_order_type.setText("选择 颜色 规格（片数） 度数");
            } else if (data.getType().equals("2")) {
                confirm_order_type.setText(data.getGoods_attr_name());
            }

            classify_supi.setText("赠品");
            classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_2));


            item_zeng_select_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferClickListener.onGoReClickListener(data, data.getGoods_id(), data.getSearch_attr(), getAdapterPosition());
                }
            });

        }
    }

    public interface PreferClickListener {

        void onGoReClickListener(ConfirmOrderBean.ZySendListBean data, String getGoods_id, String getSearch_attr, int position);

    }
}
