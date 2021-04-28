package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/23$
 * @Author:pk$
 */
public class DetailsImagAdapter extends RecyclerView.Adapter<DetailsImagAdapter.ViewHolder> {

    Context context;
    List<String> details;
    String mark;

    public DetailsImagAdapter(Context context, List<String> details, String mark) {
        this.context = context;
        this.details = details;
        this.mark = mark;
    }

    @NonNull
    @Override
    public DetailsImagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_imag_item_1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsImagAdapter.ViewHolder viewHolder, int position) {

//        viewHolder.details_img.setScaleType(ImageView.ScaleType.FIT_XY);
//        viewHolder.details_img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
             //设置并应用矩阵

        if (mark.equals("0")) {//详情图片
            viewHolder.details_img.setScaleType(ImageView.ScaleType.MATRIX);

            viewHolder.details_img.setImageMatrix(matrix);
        } else if (mark.equals("1")) {//购物须知
            viewHolder.details_img.setScaleType(ImageView.ScaleType.MATRIX);
            viewHolder.details_img.setImageMatrix(matrix);
        }

        ImageLoader.image(context,viewHolder.details_img,details.get(position));

//        Glide.with(context)
//                .load(details.get(position))
//                .into(viewHolder.details_img);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView details_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details_img = itemView.findViewById(R.id.details_img);
        }
    }
}
