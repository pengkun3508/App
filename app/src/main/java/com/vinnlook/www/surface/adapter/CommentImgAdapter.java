package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/5/6$
 * @Author:pk$
 */
public class CommentImgAdapter extends BaseStateAdapter<String, CommentImgAdapter.CommentImgHolderr> {
    Context context;

    public CommentImgAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected CommentImgHolderr getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new CommentImgHolderr(inflate(parent, R.layout.details_imag_item));
    }

    class CommentImgHolderr extends BaseHolder<String> {

        RoundedImageView details_img;

        CommentImgHolderr(View itemView) {
            super(itemView);
            details_img = getView(R.id.details_img);
        }

        @Override
        public void bindData(String data) {

            Log.e("评论图片", "==评论图片==" + data);
//            Matrix matrix = new Matrix();           //创建一个单位矩阵
//            matrix.setTranslate(0, 0);          //平移x和y各100单位
//            matrix.preRotate(0);                   //顺时针旋转30度
//            details_img.setScaleType(ImageView.ScaleType.MATRIX);
            details_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, details_img, data);

//            Glide.with(context)
//                    .load(data)
//                    .into(details_img);

        }


    }

}


