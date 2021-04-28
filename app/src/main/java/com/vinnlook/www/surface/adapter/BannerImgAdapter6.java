package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.RotateTransformation;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/4/12$
 * @Author:pk$
 */
public class BannerImgAdapter6 extends BannerAdapter<HomeTab1Bean.HeadBannerBean, BannerImgAdapter6.BannerViewHolder> {

    Context context;
    List<HomeTab1Bean.HeadBannerBean> gatData2;


    public BannerImgAdapter6(Context contexts, List<HomeTab1Bean.HeadBannerBean> gatData2s) {
        super(gatData2s);
        context = contexts;
        gatData2 = gatData2s;

    }

//    public BannerImageAdapter(DataBean mDatas) {
//        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
//        super(mDatas);
//    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
//        ImageView imageView = new ImageView(parent.getContext());
//        //注意，必须设置为match_parent，这个是viewpager2强制要求的
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
////        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        Matrix matrix = new Matrix();           //创建一个单位矩阵
//        matrix.setTranslate(0, 0);          //平移x和y各100单位
//        matrix.preRotate(0);                   //顺时针旋转30度
//        imageView.setScaleType(ImageView.ScaleType.MATRIX);
//        imageView.setImageMatrix(matrix);
//        return new BannerViewHolder(imageView);
        return new BannerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_image_layout_6, parent, false));
    }

    @Override
    public void onBindView(BannerViewHolder holder, HomeTab1Bean.HeadBannerBean data, int position, int size) {
//        Glide.with(context).load(data)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(holder.imageView);
//        holder.banner_1.setStartPosition(1);
//        BannerImgAdapter7 bannerAdapter = new BannerImgAdapter7(context, gatBannetData());
//        holder.banner_1.setAdapter(bannerAdapter);
//        holder.banner_1.setUserInputEnabled(false);
//        holder.banner_1.start();
//
//        holder.banner_2.setStartPosition(1);
//        BannerImgAdapter7 bannerAdapter2 = new BannerImgAdapter7(context, gatBannetData());
//        holder.banner_2.setAdapter(bannerAdapter2);
//        holder.banner_2.setUserInputEnabled(false);
//        holder.banner_2.start();
        ImageLoader.image(context, holder.banner_imgs, data.getPhoto());

    }


    public static RequestOptions getRotateOpions(Context context) {
        return RequestOptions.bitmapTransform(new RotateTransformation(context, 180));
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView banner_imgs;
        Banner banner_1, banner_2;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_imgs = itemView.findViewById(R.id.banner_imgs);
//            banner_1 = itemView.findViewById(R.id.banner_1);
//            banner_2 = itemView.findViewById(R.id.banner_2);

//            banner_1.post(new Runnable() {
//                @Override
//                public void run() {
//                    banner_1.getWidth();
//                    double f = Float.valueOf(banner_1.getWidth() + "") / (350 / 175);
//                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(banner_1.getWidth(), (int) f);
//                    banner_1.setLayoutParams(layoutParams);
//                }
//            });


        }
    }

    public List<String> gatBannetData() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < gatData2.size(); i++) {
            for (int j = 0; j < gatData2.get(i).getChild_list().size(); j++) {
                strings.add(gatData2.get(i).getChild_list().get(j).getPhoto());
            }
        }
        return strings;
    }


}
