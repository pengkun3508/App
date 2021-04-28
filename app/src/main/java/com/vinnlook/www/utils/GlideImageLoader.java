//package com.vinnlook.www.utils;
//
//import android.content.Context;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
//import com.vinnlook.www.widgat.ImageLoaderEx;
//import com.makeramen.roundedimageview.RoundedImageView;
//
//public class GlideImageLoader extends ImageLoaderEx {
//
//    @Override
//    public void displayImage(Context context, Object path, RoundedImageView imageView) {
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
////        Matrix matrix = new Matrix();           //创建一个单位矩阵
////        matrix.setTranslate(0, 0);          //平移x和y各100单位
////        matrix.preRotate(0);                   //顺时针旋转30度
////        imageView.setScaleType(ImageView.ScaleType.MATRIX);
////        imageView.setImageMatrix(matrix);
//
//
//        imageView.setPadding(0, 0, 0, 0);
//        imageView.setCornerRadius(15);
//        //Glide 加载图片简单用法
//        Glide.with(context).load(path)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(imageView);
//
////        ImageLoader.image(context, imageView, path.toString());
//
//
////        DrawableRequestBuilder<String> thumbnailRequest = Glide.with( context ).load( url );
////        GlideApp.with(context.getApplicationContext())
////                .load(path)
////                //设置等待时的图片
//////                .placeholder(R.drawable.placeholder_banner)
////                //设置加载失败后的图片显示
//////                .error(R.drawable.placeholder_banner)
////                .fitCenter()
////                //默认淡入淡出动画
////                .transition(withCrossFade())
////                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
////                .skipMemoryCache(false)
////                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
////                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                //设置图片加载的优先级
////                .priority(Priority.HIGH)
////                .thumbnail( thumbnailRequest )
////                .into(imageView);
//
//
//    }
//}
