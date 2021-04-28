package com.vinnlook.www.utils;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * @author Cuizhen
 * @date 2018/8/28-下午6:39
 */
public class UMInit {

    /**
     * <meta-data android:name="UMENG_APPKEY" android:value="5a682a2e8f4a9d671100000f" />
     * <meta-data android:name="UMENG_CHANNEL" android:value="${UMENG_CHANNEL_VALUE}" />
     */
    public static void init(Context context) {
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.onEvent(context, "start_app");
        UMConfigure.setLogEnabled(false);// 设置输出运行时日志
    }

    /**
     * 注意：
     * 确保在所有的Activity中都调用 MobclickAgent.onResume() 和onPause()方法，
     * 这两个调用不会阻塞应用程序的主线程，也不会影响应用程序的性能。
     * 注意：如果您的Activity之间有继承或者控制关系，请不要同时在父和子Activity中重复添加nPause和nResume方法，
     * 否则会造成重复统计，导致启动次数异常增高。(例如：使用TabHost、TabActivity、ActivityGroup时)。
     * 当应用在后台运行超过30秒（默认）再回到前台，将被认为是两次独立的Session(启动)，
     * 例如：用户回到home，或进入其他程序，经过一段时间后再返回之前的应用。即被认为是两个独立的Session。
     */
    public static void onResume(Activity context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(Activity context) {
        MobclickAgent.onPause(context);
    }

    public static void onPageStart(Context context) {
        if (context != null) {
            MobclickAgent.onPageStart(context.getClass().getName());
        }
    }

    public static void onPageEnd(Context context) {
        if (context != null) {
            MobclickAgent.onPageEnd(context.getClass().getName());
        }
    }
}