package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.RealNameEditActivity;
import com.vinnlook.www.surface.bean.RealNameListBean;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNameAdapter extends BaseStateAdapter<RealNameListBean, RealNameAdapter.RealNameHolder> {

    Context context;
    ItemBtnClickListener itemBtnClickListener;
    String getIs_default;

    public RealNameAdapter(Context context, ItemBtnClickListener itemBtnClickListener) {
        super();
        this.context = context;
        this.itemBtnClickListener = itemBtnClickListener;
    }

    @Override
    protected RealNameHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new RealNameHolder(inflate(parent, R.layout.rv_item_realname));
    }

    class RealNameHolder extends BaseHolder<RealNameListBean> {
        TextView rename_name, rename_icard_no, tvDelete, tvEdit;
        ImageView ivCheckCircle;//地址选项
        LinearLayout llCheckCircle;//

        RealNameHolder(View itemView) {
            super(itemView);
            rename_name = getView(R.id.rename_name);
            rename_icard_no = getView(R.id.rename_icard_no);
            ivCheckCircle = getView(R.id.iv_check_circle);
            tvDelete = getView(R.id.tv_delete);
            llCheckCircle = getView(R.id.ll_check_circle);
            tvEdit = getView(R.id.tv_edit);
        }

        @Override
        public void bindData(RealNameListBean data) {
            rename_name.setText(data.getName());
            rename_icard_no.setText(data.getId_card());

            getIs_default = data.getIs_default();


            if (data.getIs_default().equals("1")) {//默认
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            } else {//不是默认
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            }

            llCheckCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    pos = getAdapterPosition();
                    Log.e("选择默认", "==getIs_default==" + data.getIs_default());
                    if (getIs_default.equals("1")) {
                        getIs_default = "0";
                    } else {
                        getIs_default = "1";
                    }
                    itemBtnClickListener.onBtnClickListener(getData().get(getAdapterPosition()).getId(), getIs_default);
                }
            });
            //删除地址
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemBtnClickListener.onDeletClickListener(getData().get(getAdapterPosition()).getId(), getAdapterPosition());

                }
            });

            //编辑地址
            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RealNameEditActivity.startSelf(context, getData().get(getAdapterPosition()), data.getId());

                }
            });


        }
    }


    public interface ItemBtnClickListener {
        void onBtnClickListener(String id, String is_default);

        void onDeletClickListener(String id, int pos);
    }
}
