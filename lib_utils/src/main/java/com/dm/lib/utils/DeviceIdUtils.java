package com.dm.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * 获取设备号
 *
 * @author Cuizhen
 * @date 2018/6/30-上午10:30
 */
public class DeviceIdUtils {

    private static final String KEY_ID = "id";
    private static final String SP_NAME = "device_id";

    @SuppressLint("HardwareIds")
    public static String getId() {
        synchronized (DeviceIdUtils.class) {
            SharedPreferences sp = Utils.getAppContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            String deviceId = sp.getString(KEY_ID, null);
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = Settings.Secure.getString(Utils.getAppContext().getContentResolver(), "android_id");
                if (TextUtils.isEmpty(deviceId)) {
                    SecureRandom random = new SecureRandom();
                    deviceId = (new BigInteger(64, random)).toString(16);
                }
                sp.edit().putString(KEY_ID, deviceId).apply();
            }
            return deviceId;
        }
    }
}
