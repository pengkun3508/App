package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vinnlook.www.utils.ImageLoader;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @Description:
 * @Time:2020/10/29$
 * @Author:pk$
 */
public class BannerImgAdapter3 extends BannerAdapter<String, BannerImgAdapter3.BannerViewHolder> {

    Context context;
    List<String> gatBannetData;

    public BannerImgAdapter3(Context contexts, List<String> gatBannetDatas) {
        super(gatBannetDatas);
        context = contexts;
        gatBannetData = gatBannetDatas;

    }

//    public BannerImageAdapter(DataBean mDatas) {
//        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
//        super(mDatas);
//    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
//        Glide.with(context).load(data)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
//                .into(holder.imageView);
        ImageLoader.image(context,holder.imageView,data);


    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
