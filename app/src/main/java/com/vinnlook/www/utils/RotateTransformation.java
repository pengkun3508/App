package com.vinnlook.www.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;

/**
 * @Description:
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class RotateTransformation extends BitmapTransformation {
    private float rotateRotationAngle = 0f;

    public RotateTransformation(Context context, float rotateRotationAngle) {

        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }


    @NonNull
    public static RequestOptions getRotateOptions(Context context) {
        return RequestOptions.bitmapTransform(new RotateTransformation(context, 90));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

//        Glide.with(this).load(path).apply(getRotateOptions(getContext())).into(imageView);
}
