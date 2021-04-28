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
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.surface.activity.AddressAddActivity;

import butterknife.BindView;

public class AddressAdapter extends BaseStateAdapter<AddressListBean, AddressAdapter.AddressHolder> {


    private int pos;
    Context context;
    ItemBtnClickListener itemBtnClickListener;
    String getIs_default;
    String getAddress_id;

    public AddressAdapter(Context context, ItemBtnClickListener itemBtnClickListener) {
        super();

        this.context = context;
        this.itemBtnClickListener = itemBtnClickListener;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    protected AddressHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new AddressHolder(inflate(parent, R.layout.rv_item_address));
    }

    class AddressHolder extends BaseHolder<AddressListBean> {
        @BindView(R.id.tv_name)
        TextView tvName;//姓名
        @BindView(R.id.tv_phone)
        TextView tvPhone;//手机号
        @BindView(R.id.tv_address)
        TextView tvAddress;//地址信息
        @BindView(R.id.iv_check_circle)
        ImageView ivCheckCircle;//地址选项
        @BindView(R.id.tv_edit)
        TextView tvEdit;//编辑地址
        @BindView(R.id.tv_delete)
        TextView tvDelete;//删除地址
        @BindView(R.id.ll_check_circle)
        LinearLayout llCheckCircle;//

        AddressHolder(View itemView) {
            super(itemView);
            tvName = getView(R.id.tv_name);
            tvPhone = getView(R.id.tv_phone);
            tvAddress = getView(R.id.tv_address);
            ivCheckCircle = getView(R.id.iv_check_circle);
            tvDelete = getView(R.id.tv_delete);
            llCheckCircle = getView(R.id.ll_check_circle);
            tvEdit = getView(R.id.tv_edit);
        }

        @Override
        public void bindData(AddressListBean data) {
            tvName.setText(data.getAddress_name());
            tvPhone.setText(data.getMobile());
            tvAddress.setText(data.getAddress());
            getIs_default = data.getIs_default();
            getAddress_id = data.getAddress_id();

//            if (getAdapterPosition() == pos) {
//                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//            } else {
//                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
//            }

            if (getIs_default.equals("1")) {//默认
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            } else {
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            }


            llCheckCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    pos = getAdapterPosition();
                    Log.e("选择默认", "==getIs_default==" + data.getIs_default());
                    if (getIs_default.equals("1")) {
                        getIs_default = "0";
//                        ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));

                    } else {
                        getIs_default = "1";
//                        ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//                        itemBtnClickListener.onBtnClickListener(data.getAddress_id(), "1");
                    }

                    itemBtnClickListener.onBtnClickListener(getData().get(getAdapterPosition()).getAddress_id(), getIs_default);
                    Log.e("选择默认", "==刷新适配器==111===" + getData().get(getAdapterPosition()).getAddress_id());
                    Log.e("选择默认", "==刷新适配器==" + getIs_default);

                }
            });
            //删除地址
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemBtnClickListener.onDeletClickListener(getData().get(getAdapterPosition()).getAddress_id(),getAdapterPosition());

                }
            });

            //编辑地址
            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddressAddActivity.startSelf(context, getData().get(getAdapterPosition()), data.getAddress_id());

                }
            });


        }
    }


    public interface ItemBtnClickListener {
        void onBtnClickListener(String address_id, String s);

        void onDeletClickListener(String address_id,int pos);
    }
}