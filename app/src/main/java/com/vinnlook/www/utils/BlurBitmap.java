package com.vinnlook.www.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.RequiresApi;

/**
 * @Description:
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class BlurBitmap {
    /**
     * 图片缩放比例
     */
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * 最大的模糊度在0.0到25.0之间
     */
    private static final  float BLUR_RADIUS = 25f;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap bitmap){
        //计算出图片缩小后的宽度
        int width = Math.round(bitmap.getWidth() * BITMAP_SCALE);
        int height = Math.round(bitmap.getHeight() * BITMAP_SCALE);

        //将缩小的图片作为预渲染的图片
        Bitmap inputBitMap = Bitmap.createScaledBitmap(bitmap,width,height,false);
        //创建一张渲染后的输入图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitMap);
        //创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        //创建一个迷糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript  = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //由于RenderScript并没有使用Vm来分配内存，所以需要使用Allocation类来创建和分配内存
        //创建Allocation对象的时候其实内存是空的，需要使用copyTo()将数据填充进来
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitMap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        //设置渲染的模糊程度，25f是最大的模糊度
        blurScript.setRadius(BLUR_RADIUS);
        //设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        //将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        //将数据填充到Allcation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }



    /**
     * 模糊图片的具体方法
     *
     * @param context   上下文对象
     * @param image     需要模糊的图片
     * @return          模糊处理后的图片
     */
    static Bitmap blurs(Context context, Bitmap image) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片。
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片。
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(BLUR_RADIUS);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}

