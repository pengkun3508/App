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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ApplyDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/2/1$
 * @Author:pk$
 */
public class ApplyDetailsAdapter extends BaseStateAdapter<ApplyDetailsBean.ListBean, ApplyDetailsAdapter.ApplyDetailsHolder> {

    Context context;

    public ApplyDetailsAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ApplyDetailsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ApplyDetailsHolder(inflate(parent, R.layout.apply_item_details));
    }

    class ApplyDetailsHolder extends BaseHolder<ApplyDetailsBean.ListBean> {

        RoundedImageView applyDetailsImg;
        TextView classify_supi;
        TextView applyDetailsType;
        LinearLayout shouhouRelayou;
        TextView applyDetailsNum;
        //        TextView applyDetailsPrice;
//        TextView applyDetailsShouhou;
//        TextView applyDetailsStatus;
//        TextView applyDetailsTime;
        TextView applyDetailsName;


        ApplyDetailsHolder(View itemView) {
            super(itemView);
            applyDetailsImg = itemView.findViewById(R.id.apply_details_img);
            applyDetailsName = itemView.findViewById(R.id.apply_details_name);
            applyDetailsType = itemView.findViewById(R.id.apply_details_type);
            shouhouRelayou = itemView.findViewById(R.id.shouhou_relayou);
            applyDetailsNum = itemView.findViewById(R.id.apply_details_num);
//            applyDetailsPrice = itemView.findViewById(R.id.apply_details_price);
//            applyDetailsShouhou = itemView.findViewById(R.id.apply_details_shouhou);
//            applyDetailsStatus = itemView.findViewById(R.id.apply_details_status);
//            applyDetailsTime = itemView.findViewById(R.id.apply_details_time);
            classify_supi = itemView.findViewById(R.id.classify_supi);

        }

        @Override
        public void bindData(ApplyDetailsBean.ListBean data) {

            Matrix matrix = new Matrix();           //????????????????????????
            matrix.setTranslate(0, 0);          //??????x???y???100??????
            matrix.preRotate(0);                   //???????????????30???
            applyDetailsImg.setScaleType(ImageView.ScaleType.MATRIX);
            applyDetailsImg.setImageMatrix(matrix);
            ImageLoader.image(context, applyDetailsImg, data.getImage());
            applyDetailsName.setText("\u3000\u3000\u3000 " +data.getGoods_name());
            applyDetailsType.setText(data.getAttr_name());
            applyDetailsNum.setText("x" + data.getGoods_number());
//            applyDetailsPrice.setText(Html.fromHtml("&yen") + data.getPrice());
//
//            applyDetailsTime.setText(data.getCreate_time());

            if (data.getSuppliers_id().equals("1")) {//??????
                classify_supi.setText("??????");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//??????
                classify_supi.setText("??????");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }


//            if (data.getType().equals("1")) {
//                applyDetailsShouhou.setText("?????????");
//            } else if (data.getType().equals("2")) {
//                applyDetailsShouhou.setText("????????????");
//            }
//            if (data.getGoods_status().equals("1")) {
//                applyDetailsStatus.setText("????????????");
//            } else if (data.getGoods_status().equals("2")) {
//                applyDetailsStatus.setText("????????????");
//            }


        }
    }

}