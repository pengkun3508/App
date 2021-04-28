package com.dm.lib.core.notification;

import android.content.Context;

import androidx.core.app.NotificationCompat;


/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/11/13
 */
public class AnyNotification {

    private final NotificationCompat.Builder mBuilder;

    public AnyNotification(Context context, String channelId) {
        mBuilder = new NotificationCompat.Builder(context, channelId);
    }

}