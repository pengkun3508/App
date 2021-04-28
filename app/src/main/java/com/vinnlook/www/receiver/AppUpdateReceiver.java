package com.vinnlook.www.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dm.lib.core.common.BaseApp;
import com.dm.lib.core.common.Config;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.http.model.VersionBean;

import java.io.Serializable;


/**
 * @author Cuizhen
 * @version v1.0.0
 * @date 2018/4/4-上午9:11
 */
public class AppUpdateReceiver extends BroadcastReceiver {

    public static final String KEY_APP_UPDATE = "KEY_APP_UPDATE";
    public static final String KEY_BUNDLE = "KEY_BUNDLE";

    private final Handler appUpdateHandler;

    public AppUpdateReceiver(Handler appUpdateHandler) {
        this.appUpdateHandler = appUpdateHandler;
    }


    public static void send(VersionBean versionBean) {
        Intent intent = new Intent(Config.ACTION_APP_UPDATE);
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(KEY_APP_UPDATE, (Serializable) versionBean);
        intent.putExtra(KEY_BUNDLE, bundle);
        LocalBroadcastManager.getInstance(BaseApp.getAppContext()).sendBroadcast(intent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        Message message = appUpdateHandler.obtainMessage();
        message.setData(intent.getBundleExtra(KEY_BUNDLE));
        appUpdateHandler.sendMessage(message);
    }
}
