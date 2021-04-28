package com.vinnlook.www.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.WorkerThread;

import com.dm.lib.utils.CacheUtils;
import com.dm.lib.utils.LogUtils;
import com.dm.lib.utils.Utils;
import com.vinnlook.www.common.Config;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;

/**
 * 图片压缩上传
 *
 * @author Cuizhen
 */
public class ImageCompressUtils {

    private static final String TAG = ImageCompressUtils.class.getSimpleName();

    /**
     * 对一个图片文件进行压缩
     * 一个近似正方形图片压缩后的大小在100KB左右
     *
     * @param file 原始图片文件
     * @return 压缩后的
     */
    public static File compressByCompressHelper(File file) {
        LogUtils.i(TAG, "---------compress size start----------");
        String path = getDirPath();
        LogUtils.i(TAG, "image cache path = " + path);
        LogUtils.i(TAG, "image old size = " + file.length());
        long startTime = System.currentTimeMillis();
        CompressHelper helper = new CompressHelper.Builder(Utils.getAppContext())
                .setQuality(80)
                .setMaxWidth(Integer.MAX_VALUE)
                .setMaxHeight(Integer.MAX_VALUE)
                .setFileName(startTime + "_compress_size")
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .setDestinationDirectoryPath(path)
                .build();
        File compress = helper.compressToFile(file);
        long endTime = System.currentTimeMillis();
        LogUtils.i(TAG, "image new size = " + compress.length());
        LogUtils.i(TAG, "compress size time cast = " + (endTime - startTime) + "ms");
        LogUtils.i(TAG, "----------compress size end-----------");
        return compress;
    }

    /**
     * 对一个图片文件进行压缩
     * 一个近似正方形图片压缩后的大小在100KB左右
     *
     * @param file 原始图片文件
     * @return 压缩后的
     */
    @WorkerThread
    public static File compress(File file) {
        LogUtils.i(TAG, "---------compress start----------");
        long startTime = System.currentTimeMillis();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        int w = options.outWidth;
        int h = options.outHeight;

        float scaleW = (float) w / (float) Config.IMAGE_UPLOAD_SIZE;
        float scaleH = (float) h / (float) Config.IMAGE_UPLOAD_SIZE;
        float scale = Math.min(scaleW, scaleH);

        File fileCompress;
        if (scale > 1) {
            options.inJustDecodeBounds = false;
            options.inSampleSize = (int) scale;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            fileCompress = compress(bitmap);
        } else {
            fileCompress = compressByCompressHelper(file);
        }

        long endTime = System.currentTimeMillis();
        LogUtils.i(TAG, "compress time cast = " + (endTime - startTime) + "ms");
        LogUtils.i(TAG, "----------compress end-----------");
        return fileCompress;
    }

    /**
     * 把一个Bitmap压缩为一个图片文件
     * 1、进行分辨率压缩
     * 2、进行文件大小压缩
     *
     * @param bitmap 原始图片
     * @return 压缩后的临时文件
     */
    @WorkerThread
    public static File compress(Bitmap bitmap) {
        LogUtils.i(TAG, "---------compress pixel start----------");
        long startTime = System.currentTimeMillis();


        Bitmap bitmapCompress = BitmapUtils.compress(bitmap, Config.IMAGE_UPLOAD_SIZE);
        String fileName = startTime + "_compress_pixel" + Config.IMG_UPLOAD_NAME_EXTENSION;
        File compressPixel = BitmapUtils.saveFile(getDirPath(), fileName, bitmapCompress);

        File compressSize = compressByCompressHelper(compressPixel);

        compressPixel.delete();

        long endTime = System.currentTimeMillis();
        LogUtils.i(TAG, "compress pixel time cast = " + (endTime - startTime) + "ms");
        LogUtils.i(TAG, "----------compress pixel end-----------");
        return compressSize;
    }

    /**
     * 删除压缩后的临时文件，应该在网络请求上传完成后调用
     */
    public static void deleteCompressFile() {
        File dir = new File(getDirPath());
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    /**
     * 获取图片压缩缓存文件夹路径
     *
     * @return
     */
    private static String getDirPath() {
        return CacheUtils.getCacheDir() + "/" + Config.IMG_COMPRESS_CACHE_DIR_NAME;
    }
}
