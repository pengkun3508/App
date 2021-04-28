package com.vinnlook.www.jpush;

import android.app.Notification;
import android.content.Context;

import com.dm.lib.utils.Utils;
import com.vinnlook.www.R;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2018/10/12
 */
public class JPushHelper {

    public static void customNotification(Context context) {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
        // 通知图标
        builder.statusBarDrawable = R.mipmap.ic_launcher;
        // 设置为自动消失和呼吸灯闪烁
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
        // 设置为铃声、震动、呼吸灯闪烁都要
        builder.notificationDefaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    public static String getId() {
        return JPushInterface.getRegistrationID(Utils.getAppContext());
    }
}
