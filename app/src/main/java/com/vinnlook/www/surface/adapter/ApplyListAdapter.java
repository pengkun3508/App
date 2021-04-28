package com.vinnlook.www.surface.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ApplyListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class ApplyListAdapter extends BaseStateAdapter<ApplyListBean.ListBean, ApplyListAdapter.ApplyListHolder> {

    Context context;
    ApplyListClickListener applyListClickListener;

    public void setApplyListClickListener(ApplyListClickListener applyListClickListener) {
        this.applyListClickListener = applyListClickListener;
    }


    public ApplyListAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ApplyListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ApplyListHolder(inflate(parent, R.layout.apply_list_item));
    }

    class ApplyListHolder extends BaseHolder<ApplyListBean.ListBean> {
        TextView apply_item_sn, order_no_copy, apply_item_type, apply_item_number, apply_item_money,
                apply_item_kefu_btn, apply_item_see_btn, item_stats_1, item_stats_2, classify_supi;
        RoundedImageView apply_item_image;

        TextView apply_item_name;


        ApplyListHolder(View itemView) {
            super(itemView);
            apply_item_sn = getView(R.id.apply_item_sn);//订单号
            order_no_copy = getView(R.id.order_no_copy);//复制
            apply_item_image = getView(R.id.apply_item_image);//图片
            apply_item_name = getView(R.id.apply_item_name);//产品名
            apply_item_type = getView(R.id.apply_item_type);//产品型号
            apply_item_number = getView(R.id.apply_item_number);//数量
            apply_item_money = getView(R.id.apply_item_money);//价格
            apply_item_kefu_btn = getView(R.id.apply_item_kefu_btn);//联系客服
            apply_item_see_btn = getView(R.id.apply_item_see_btn);//查看详情
            item_stats_1 = getView(R.id.item_stats_1);//状态1
            item_stats_2 = getView(R.id.item_stats_2);//状态2
            classify_supi = getView(R.id.classify_supi);

        }

        @Override
        public void bindData(ApplyListBean.ListBean data) {
            apply_item_sn.setText("订单号：" + data.getOrder_sn());
            ImageLoader.image(context, apply_item_image, data.getImage());
            apply_item_type.setText(data.getAttr_name());
            apply_item_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
            apply_item_number.setText("x" + data.getGoods_number());
            apply_item_money.setText(Html.fromHtml("&yen") + data.getGoods_price());


            if (data.getSuppliers_id().equals("1")) {//自营
                classify_supi.setText("自营");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (data.getSuppliers_id().equals("2")) {//海淘
                classify_supi.setText("海淘");
                classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }


            if (data.getType().equals("1")) {
                item_stats_1.setText("仅退款");
            } else if (data.getType().equals("2")) {
                item_stats_1.setText("退货退款");
            }


            if (data.getStatus().equals("1")) {
                item_stats_2.setText("退款中");
            } else if (data.getStatus().equals("2")) {
                item_stats_2.setText("退款成功");
            } else if (data.getStatus().equals("3")) {
                item_stats_2.setText("拒绝退款");
            } else if (data.getStatus().equals("4")) {
                item_stats_2.setText("取消退款");
            } else if (data.getStatus().equals("5")) {
                item_stats_2.setText("同意退货");
            } else if (data.getStatus().equals("6")) {
                item_stats_2.setText("已发货");
            } else if (data.getStatus().equals("7")) {
                item_stats_2.setText("已收货");
            }
            //客服
            apply_item_kefu_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    applyListClickListener.onApplyKeFuClickListener(getAdapterPosition());
                }
            });
            //查看详情
            apply_item_see_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    applyListClickListener.onApplySeeDetailsClickListener(getAdapterPosition());
                }
            });
            //复制订单号
            order_no_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(apply_item_sn.getText().toString());
                    Toast.makeText(context, "复制成功", Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    public interface ApplyListClickListener {
        void onApplyKeFuClickListener(int posion);//联系客服

        void onApplySeeDetailsClickListener(int posion);//联系客服

    }


}

