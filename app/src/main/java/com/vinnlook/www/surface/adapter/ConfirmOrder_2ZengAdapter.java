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
public class ConfirmOrder_2ZengAdapter extends BaseStateAdapter5<ConfirmOrderBean.HtSendListBean, ConfirmOrder_2ZengAdapter.ConfirmOrder_2ZengHolder> {

    Context context;

    PreferClickListener preferClickListener;

    public void setPreferClickListener(PreferClickListener preferClickListener) {
        this.preferClickListener = preferClickListener;
    }

    public ConfirmOrder_2ZengAdapter(Context context) {
        this.context = context;

    }

    @Override
    protected ConfirmOrder_2ZengHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ConfirmOrder_2ZengHolder(inflate(parent, R.layout.confirm_item_zeng));
    }

    class ConfirmOrder_2ZengHolder extends BaseHolder<ConfirmOrderBean.HtSendListBean> {

        RoundedImageView confirm_order_img;
        TextView confirm_order_name, confirm_order_type, confirm_order_number, classify_supi;
        LinearLayout item_zeng_select_type;

        ConfirmOrder_2ZengHolder(View itemView) {
            super(itemView);
            confirm_order_img = getView(R.id.confirm_order_img);
            confirm_order_name = getView(R.id.confirm_order_name);
            confirm_order_type = getView(R.id.confirm_order_type);
            confirm_order_number = getView(R.id.confirm_order_number);
            item_zeng_select_type = getView(R.id.item_zeng_select_type);
            classify_supi = getView(R.id.classify_supi);

        }

        @Override
        public void bindData(ConfirmOrderBean.HtSendListBean data) {
            confirm_order_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, confirm_order_img, data.getGoods_thumb());

            confirm_order_name.setText("\u3000\u3000\u3000" + data.getGoods_name());

            confirm_order_number.setText("x" + data.getNumber());

//            confirm_order_type.setText(data.getGoods_attr_name());
            if (data.getType().equals("1")) {
                confirm_order_type.setText("?????? ?????? ????????????????????????");
            } else if (data.getType().equals("2")) {
                confirm_order_type.setText(data.getGoods_attr_name());
            }
            classify_supi.setText("??????");
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

        void onGoReClickListener(ConfirmOrderBean.HtSendListBean data, String getGoods_id, String getSearch_attr, int position);

    }
}
