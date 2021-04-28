package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.RotateTransformation;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/2$
 * @Author:pk$
 */
public class BannerImgAdapter4 extends BannerAdapter<String, BannerImgAdapter4.BannerViewHolder> {

    Context context;
    List<String> gatBannetData;


    public BannerImgAdapter4(Context contexts, List<String> gatBannetDatas) {
        super(gatBannetDatas);
        context = contexts;
        gatBannetData = gatBannetDatas;
        setDatas(gatBannetData);

    }

//    public BannerImageAdapter(DataBean mDatas) {
//        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
//        super(mDatas);
//    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
//        parent.inf
//        ImageView imageView = new ImageView(parent.getContext());
//        //注意，必须设置为match_parent，这个是viewpager2强制要求的
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        return new BannerViewHolder(imageView);

        return new BannerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_image_layout, parent, false));
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
//        Glide.with(context).load(data)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(holder.imageView);
        ImageLoader.image(context,holder.banner_imgs,data);


//        holder.banner_imgs.setImageBitmap(data);

    }


    public static RequestOptions getRotateOpions(Context context) {
        return RequestOptions.bitmapTransform(new RotateTransformation(context, 180));
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView banner_imgs;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_imgs = itemView.findViewById(R.id.banner_imgs);
        }
    }


}
