package com.vinnlook.www.utils.sp;

import com.dm.lib.utils.SPUtils;

/**
 * 描述：设置APP字体放大的倍数
 *
 * @author Yanbo
 * @date 2019/3/6
 */
public class SPTextSizeUtils {

    private static final String KEY_START = "text_size";

    private SPUtils sp;

    private SPTextSizeUtils() {
        sp = SPUtils.newInstance("start_first");
    }

    public static SPTextSizeUtils instance() {
        return new SPTextSizeUtils();
    }

    public float getTextSize() {
        return  sp.get(KEY_START, 1f);
    }

    public void setTextSize(float textSize) {
        sp.save(KEY_START, textSize);
    }
}
