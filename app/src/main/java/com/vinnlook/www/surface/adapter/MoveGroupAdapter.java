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
import com.vinnlook.www.surface.bean.GroupDetailsListBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/8$
 * @Author:pk$
 */
public class MoveGroupAdapter extends BaseStateAdapter<GroupDetailsListBean, MoveGroupAdapter.MoveGroupHolder> {

    Context context;

    private OnItemClickListener mOnItemClickListener;

    public MoveGroupAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    protected MoveGroupHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new MoveGroupHolder(inflate(parent, R.layout.move_group_item));
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MoveGroupHolder extends BaseHolder<GroupDetailsListBean> {

        ImageView move_group_item_img, move_group_item_jian;
        TextView move_group_item_text;

        MoveGroupHolder(View itemView) {
            super(itemView);
            move_group_item_img = itemView.findViewById(R.id.move_group_item_img);
            move_group_item_text = itemView.findViewById(R.id.move_group_item_text);
            move_group_item_jian = itemView.findViewById(R.id.move_group_item_jian);
        }


        @Override
        public void bindData(GroupDetailsListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            move_group_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            move_group_item_img.setImageMatrix(matrix);

//            ImageLoader.image(context, move_group_item_img, data.get("img"));
//            move_group_item_text.setText(data.get("name") + "  " + data.get("num") + "件");

            ImageLoader.image(context, move_group_item_img, data.getImg());
            move_group_item_text.setText(data.getName() + "  " + data.getNum() + "件");


            move_group_item_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }
    }

}



