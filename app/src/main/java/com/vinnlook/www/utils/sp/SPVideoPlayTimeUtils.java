package com.vinnlook.www.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.dm.lib.utils.Utils;


/**
 * 描述：保存视频播放时间的位置
 *
 * @author Yanbo
 * @date 18/11/3
 */
public class SPVideoPlayTimeUtils {


    private final String KEY_URL ;

    private SharedPreferences sp;

    private SPVideoPlayTimeUtils(String url) {
        KEY_URL=url;
        sp = Utils.getAppContext().getSharedPreferences("video_play_time", Context.MODE_PRIVATE);
    }

    public static SPVideoPlayTimeUtils instance(String url) {
        return new SPVideoPlayTimeUtils(url);
    }

    public int getcurrentPosition() {
        return sp.getInt(KEY_URL, 0);
    }

    public void setcurrentPosition(int currentPosition) {
        sp.edit().putInt(KEY_URL, currentPosition).apply();
//        sp.save(KEY_URL, currentPosition);
    }
}
