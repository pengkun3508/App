package com.dm.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * 一些系统的Intent跳转
 *
 * @author Cuizhen
 * @date 2018/8/5-下午5:38
 */
public class IntentUtils {

    /**
     * 调用系统拨号界面
     *
     * @param context
     * @param phoneNum
     */
    public static void dialingPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 应用设置
     * @param context
     */
    public static void goToSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }
}
