package com.vinnlook.www.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Description:
 * @Time:2020/5/21$
 * @Author:pk$
 */
public class DeviceUuidFactory {


    protected static final String PREFS_FILE = "device_id";

    protected static final String PREFS_DEVICE_ID = "device_id";

    protected static UUID uuid;

    private static String deviceType = "0";

    private static final String TYPE_ANDROID_ID = "1";

    private static final String TYPE_DEVICE_ID = "2";

    private static final String TYPE_RANDOM_UUID = "3";

    public DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        uuid = UUID.fromString(id);
                    } else {
                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                        try {
                            if (!"9774d56d682e549c".equals(androidId)) {
                                deviceType = TYPE_ANDROID_ID;
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                @SuppressLint("MissingPermission") final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                if (deviceId != null
                                        && !"0123456789abcdef".equals(deviceId.toLowerCase())
                                        && !"000000000000000".equals(deviceId.toLowerCase())) {
                                    deviceType = TYPE_DEVICE_ID;
                                    uuid = UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"));
                                } else {
                                    deviceType = TYPE_RANDOM_UUID;
                                    uuid = UUID.randomUUID();
                                }
                            }
                        } catch (UnsupportedEncodingException e) {
                            deviceType = TYPE_RANDOM_UUID;
                            uuid = UUID.randomUUID();
                        } finally {
                            uuid = UUID.fromString(deviceType + uuid.toString());
                        }

                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
                    }
                }
            }
        }
    }

    public UUID getDeviceUuid() {
        Log.e("DeviceUuidFactory", "------>???????????????ID?????????" + uuid.toString());
        return uuid;
    }
}