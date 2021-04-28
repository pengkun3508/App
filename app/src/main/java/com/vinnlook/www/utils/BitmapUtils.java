package com.vinnlook.www.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dm.lib.utils.CacheUtils;
import com.dm.lib.utils.LogUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Bitmap 的帮助类
 *
 * @author Cuizhen
 * @date 2017/12/28
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public final class BitmapUtils {

    private static final String TAG = BitmapUtils.class.getSimpleName();

    private BitmapUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static int[] getSize(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int[] size = new int[2];
        size[0] = options.outWidth;
        size[1] = options.outHeight;
        return size;
    }

    /**
     * 对一个Bitmap进行分辨率的压缩
     * 如需要压缩到1080*1080，意思是取短边等比缩小到1080
     * 例：
     * 原图2048*20480 --> 压缩后1080*10800
     * 原图480*3000 --> 压缩后480*3000
     * 原图6000*1620 --> 压缩后4000*1080
     *
     * @param bitmap 原始图片
     * @return 压缩后的
     */
    public static Bitmap compress(Bitmap bitmap, int size) {
        LogUtils.i(TAG, "---------compress start----------");
        long startTime = System.currentTimeMillis();
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        LogUtils.i(TAG, "bitmap old size = " + w + " * " + h);
        float scaleW = (float) w / (float) size;
        float scaleH = (float) h / (float) size;
        float scale = Math.min(scaleW, scaleH);
        Bitmap compress = bitmap;
        if (scale > 1) {
            compress = Bitmap.createScaledBitmap(compress, (int) (w / scale), (int) (h / scale), true);
        }
        long endTime = System.currentTimeMillis();
        LogUtils.i(TAG, "bitmap new size = " + compress.getWidth() + " * " + compress.getHeight());
        LogUtils.i(TAG, "bitmap time cast = " + (endTime - startTime) + "ms");
        LogUtils.i(TAG, "----------compress end-----------");
        return compress;
    }

    /**
     * 保存图片到本地缓存文件夹
     *
     * @param dirName  位于缓存文件夹下的文件夹名
     * @param fileName 文件名+后缀名
     * @param bitmap   图片
     * @return 保存后的文件
     */
    public static File saveCacheFile(@NonNull String dirName, @NonNull String fileName, Bitmap bitmap) {
        String path = CacheUtils.getCacheDir() + "/" + dirName + "/";
        return saveFile(path, fileName, bitmap);
    }

    /**
     * 保存图片到本地
     *
     * @param path     文件夹名
     * @param fileName 文件名+后缀名
     * @param bitmap   图片
     * @return 保存后的文件
     */
    public static File saveFile(@NonNull String path, @NonNull String fileName, @NonNull Bitmap bitmap) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = null;
        BufferedOutputStream bos = null;
        try {
            file = new File(dir, fileName);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 删除本地缓存文件夹的图片
     *
     * @param dirName  缓存文件夹下的文件夹名
     * @param fileName 文件名+后缀名（为null则清空文件夹）
     */
    public static void deleteCacheFile(@NonNull String dirName, @Nullable String fileName) {
        String path = CacheUtils.getCacheDir() + "/" + dirName + "/";
        deleteFile(path, fileName);
    }

    /**
     * 删除本地缓存文件夹的图片
     *
     * @param path     文件夹路径
     * @param fileName 文件名+后缀名（为null则清空文件夹）
     */
    public static void deleteFile(@NonNull String path, @Nullable String fileName) {
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        if (fileName == null) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        } else {
            File file = new File(dir, fileName);
            file.delete();
        }
    }
}
