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
import com.vinnlook.www.surface.bean.GroupOrderListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/10$
 * @Author:pk$
 */
public class GroupListItemAdapter extends BaseStateAdapter<GroupOrderListBean.ListBean.ShopListBean, GroupListItemAdapter.GroupListItemHolder> {

    Context context;
    GroupListItemAdapter adapter;

    public GroupListItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected GroupListItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GroupListItemHolder(inflate(parent, R.layout.grouplist_item_item));
    }


    public class GroupListItemHolder extends BaseHolder<GroupOrderListBean.ListBean.ShopListBean> {

        ImageView group_item_item_image;
        TextView group_item_item_title, group_item_item_type, group_item_item_number;

        GroupListItemHolder(View itemView) {
            super(itemView);
            group_item_item_image = itemView.findViewById(R.id.group_item_item_image);
            group_item_item_title = itemView.findViewById(R.id.group_item_item_title);
            group_item_item_type = itemView.findViewById(R.id.group_item_item_type);
            group_item_item_number = itemView.findViewById(R.id.group_item_item_number);


        }

        @Override
        public void bindData(GroupOrderListBean.ListBean.ShopListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            group_item_item_image.setScaleType(ImageView.ScaleType.MATRIX);
            group_item_item_image.setImageMatrix(matrix);
            ImageLoader.image(context, group_item_item_image, data.getGoods_thumb());

            group_item_item_title.setText(data.getGoods_name());
            group_item_item_type.setText(data.getGoods_attr_name());
            group_item_item_number.setText("x" + data.getNumber());
        }
    }
}
