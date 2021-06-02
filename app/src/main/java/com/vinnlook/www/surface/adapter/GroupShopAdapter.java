package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/12$
 * @Author:pk$
 */
public class GroupShopAdapter extends BaseStateAdapter<GroupDetailsBean.ShopListBean, GroupShopAdapter.GroupShopHolder> {

    Context context;
    public GroupShopAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected GroupShopHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GroupShopHolder(inflate(parent, R.layout.groupshop_item));
    }
    public class GroupShopHolder extends BaseHolder<GroupDetailsBean.ShopListBean> {
        ImageView group_item_shop_image;
        TextView group_item_shop_title, group_item_shop_type, group_item_shop_number;

        GroupShopHolder(View itemView) {
            super(itemView);
            group_item_shop_image = itemView.findViewById(R.id.group_item_shop_image);
            group_item_shop_title = itemView.findViewById(R.id.group_item_shop_title);
            group_item_shop_type = itemView.findViewById(R.id.group_item_shop_type);
            group_item_shop_number = itemView.findViewById(R.id.group_item_shop_number);

        }

        @Override
        public void bindData(GroupDetailsBean.ShopListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            group_item_shop_image.setScaleType(ImageView.ScaleType.MATRIX);
            group_item_shop_image.setImageMatrix(matrix);
            ImageLoader.image(context, group_item_shop_image, data.getGoods_thumb());

            group_item_shop_title.setText(data.getGoods_name());
            group_item_shop_type.setText(data.getGoods_attr_name());
            group_item_shop_number.setText("x" + data.getNumber());

        }
    }


}
