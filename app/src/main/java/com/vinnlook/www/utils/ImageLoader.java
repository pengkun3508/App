package com.vinnlook.www.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.utils.LogUtils;
import com.dm.lib.utils.Utils;
import com.luck.picture.lib.tools.ScreenUtils;
import com.vinnlook.www.R;


/**
 * 图片加载，方便统一管理
 *
 * @author Cuizhen
 * @date 2018/5/7-下午5:52
 */
public class ImageLoader {

    /**
     * 用户头像
     */
    public static void userIcon(Context context, ImageView imageView, String url) {
        Glide.with(checkContext(context))
                .asBitmap()
                .load(checkUrl(url))
                .apply(new RequestOptions()
                        .error(R.mipmap.icon_heart)
                        .placeholder(R.mipmap.icon_heart)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(imageView);
    }


    /**
     * 加载原图
     */
    public static void image(Context context, ImageView imageView, String url) {
        Glide.with(checkContext(context))
                .load(checkUrl(url))
                .apply(getOptions(true, true))
                .into(imageView);
    }

    /**
     * 加载原图
     */
    public static void image(Context context, ImageView imageView, String url, SimpleCallback<Boolean> callback) {
        Glide.with(checkContext(context))
                .load(checkUrl(url))
                .apply(getOptions(true, true))
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        callback.onResult(false);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        callback.onResult(true);
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 获取原图
     */
    public static void get(Context context, String url, final SimpleCallback<Bitmap> callback) {
        Glide.with(checkContext(context))
                .asBitmap()
                .load(checkUrl(url))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (callback != null) {
                            callback.onResult(resource);
                        }
                    }
                });
    }

    /**
     * 获取原图
     */
    public static Bitmap gets(Context context, String url, final SimpleCallback<Bitmap> callback) {
        Bitmap bitmap = null;
        Glide.with(checkContext(context))
                .asBitmap()
                .load(checkUrl(url))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (callback != null) {
                            callback.onResult(resource);
                            Bitmap bitmap = resource;

                        }
                    }
                });

        return bitmap;
    }


    /**
     * 预加载图片
     */
    public static void preload(Context context, String url) {
        Glide.with(checkContext(context))
                .load(checkUrl(url))
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .preload();
    }


    /**
     * Glide 设置图片适应屏幕
     *
     * @param context
     * @param view
     * @param url
     */
    public static void setGlideim(Context context, final View view, String url) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                int w = resource.getMinimumWidth();
                int h = resource.getMinimumHeight();
                LogUtils.w("手机----------w" + ScreenUtils.getScreenWidth(context), "33-----------w---" + w + "---------h--------" + h);
                view.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(context), (ScreenUtils.getScreenWidth(context) * h) / w));//720*365
                view.setBackground(resource);

            }

        });
    }

    @SuppressLint("CheckResult")
    private static RequestOptions getOptions(boolean holder, boolean cache) {
        RequestOptions options = new RequestOptions();
        if (holder) {
            options.error(R.mipmap.ic_picture_loadfailed_2)
                    .placeholder(R.mipmap.ic_picture_loadfailed_2);
        }
        if (cache) {
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            options.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        return options;
    }

    private static Context checkContext(Context context) {
        if (context != null) {
            return context;
        }
        return Utils.getAppContext();
    }

    private static String checkUrl(String url) {
        if (url == null) {
            return "";
        }
        return url;
    }

}
