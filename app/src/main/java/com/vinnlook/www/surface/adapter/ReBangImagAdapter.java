package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:详情适配器
 * @Time:2020/5/6$
 * @Author:pk$
 */
public class ReBangImagAdapter extends BaseStateAdapter5<String, ReBangImagAdapter.ReBangImagHolder> {

    Context context;
    CommentImgAdapter commentAdapter;
    List<String> getImage;
    List<LocalMedia> selectList;

    public ReBangImagAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ReBangImagHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ReBangImagHolder(inflate(parent, R.layout.move_rebang_item_1));
    }

    class ReBangImagHolder extends BaseHolder<String> {

        ImageView details_img, details_img_number;

        ReBangImagHolder(View itemView) {
            super(itemView);
            details_img = itemView.findViewById(R.id.details_img);
            details_img_number = itemView.findViewById(R.id.details_img_number);


        }

        @Override
        public void bindData(String data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            details_img.setScaleType(ImageView.ScaleType.MATRIX);
            details_img.setImageMatrix(matrix);
            ImageLoader.image(context, details_img, data);
            if (getAdapterPosition() == 0) {
                details_img_number.setBackground(details_img_number.getResources().getDrawable(R.mipmap.move_img_1));
            } else if (getAdapterPosition() == 1) {
                details_img_number.setBackground(details_img_number.getResources().getDrawable(R.mipmap.move_img_2));
            } else if (getAdapterPosition() == 2) {
                details_img_number.setBackground(details_img_number.getResources().getDrawable(R.mipmap.move_img_3));
            }

        }


    }
}

