package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.utils.ImageLoader;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/12$
 * @Author:pk$
 */
public class BannerImgAdapter7 extends BannerAdapter<String, BannerImgAdapter7.BannerViewHolder> {

    Context context;
    List<String> gatBannetData;


    public BannerImgAdapter7(Context contexts, List<String> gatBannetDatas) {
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
        return new BannerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_image_layout_7, parent, false));
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
        ImageLoader.image(context, holder.banner_imgs, data);

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView banner_imgs;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_imgs = itemView.findViewById(R.id.banner_imgs_7);


        }
    }


}
