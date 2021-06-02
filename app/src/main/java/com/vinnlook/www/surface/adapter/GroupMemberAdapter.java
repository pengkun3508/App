package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/12$
 * @Author:pk$
 */
public class GroupMemberAdapter extends BaseStateAdapter<MoveDataBean.GroupListBean, GroupMemberAdapter.GroupMemberHolder> {

    Context context;
    String hourss;
    String minutess;
    String secondss;

    public GroupMemberAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected GroupMemberHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GroupMemberHolder(inflate(parent, R.layout.group_member_item));
    }

    public class GroupMemberHolder extends BaseHolder<MoveDataBean.GroupListBean> {
        RoundedImageView group_member_item_img1;
        RoundTextView group_member_item_img2;

        GroupMemberHolder(View itemView) {
            super(itemView);
            group_member_item_img1 = itemView.findViewById(R.id.group_member_item_img1);
            group_member_item_img2 = itemView.findViewById(R.id.group_member_item_img2);

        }

        @Override
        public void bindData(MoveDataBean.GroupListBean data) {


            if (!data.getOrder_id().equals("")) {
//                Matrix matrix = new Matrix();           //创建一个单位矩阵
//                matrix.setTranslate(0, 0);          //平移x和y各100单位
//                matrix.preRotate(0);                   //顺时针旋转30度
//                group_member_item_img1.setScaleType(ImageView.ScaleType.MATRIX);
//                group_member_item_img1.setImageMatrix(matrix);
//                ImageLoader.image(context, group_member_item_img1, data.getImg_url());

                group_member_item_img1.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.userIcon(context, group_member_item_img1, data.getImg_url());
            }

            if (getAdapterPosition() == 0) {
                group_member_item_img2.setVisibility(View.VISIBLE);
            } else {
                group_member_item_img2.setVisibility(View.GONE);
            }


        }
    }


}
