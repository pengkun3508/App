package com.vinnlook.www.utils;

import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.SPUtils;
import com.vinnlook.www.common.Config;

/**
 * 软件更新针对版本进行忽略
 * 可配置一次点击时的忽略时长
 * {@link Config#APP_UPDATE_IGNORE_TIME_MAX_MILL}
 *
 * @author damai
 * @date 2018/6/30-上午10:30
 */
public class SPAppUpdateUtils {

    private static final String KEY_VERSION = "ignore_version";
    private static final String KEY_TIME = "ignore_time";
    private static final String KEY_TIME_1 = "guanggao_time";

    private SPUtils sp;
    private final int mCurrentVersionCode;

    private SPAppUpdateUtils() {
        sp = SPUtils.newInstance("app_update_ignore");
        mCurrentVersionCode = AppInfoUtils.getVersionCode();
    }

    public static SPAppUpdateUtils instance() {
        return new SPAppUpdateUtils();
    }

    public boolean isNew(int versionCodeNew) {
        return mCurrentVersionCode < versionCodeNew;
    }

    public boolean isShouldUpdate(int versionCodeNew) {
        int versionCodeCurrent = AppInfoUtils.getVersionCode();// 获取本地软件版本号code
        if (versionCodeNew <= versionCodeCurrent) {
            return false;
        }
        int versionCodeIgnore = sp.get(KEY_VERSION, 0);
        if (versionCodeIgnore > versionCodeNew) {
            return false;
        }
        if (versionCodeIgnore < versionCodeNew) {
            return true;
        }
        long timeOld = sp.get(KEY_TIME, 0L);
        long timeNew = System.currentTimeMillis();
        return timeNew - timeOld > Config.APP_UPDATE_IGNORE_TIME_MAX_MILL;
    }

    public boolean isShouldUpdate1() {
        long timeOld = sp.get(KEY_TIME, 0L);
        long timeNew = System.currentTimeMillis();
        return timeNew - timeOld > Config.APP_UPDATE_IGNORE_TIME_MAX_MILL;
    }

    public void setIgnore(int versionCode) {
        sp.save(KEY_VERSION, versionCode).save(KEY_TIME, System.currentTimeMillis());
    }


    public void setIgnore1() {
        sp.save(KEY_TIME_1, System.currentTimeMillis());
    }
    public boolean isShouldUpdate1_1() {
        long timeOld = sp.get(KEY_TIME_1, 0L);
        long timeNew = System.currentTimeMillis();
        return timeNew - timeOld > Config.APP_UPDATE_IGNORE_TIME_MAX_MILL;
    }

    public void clearIgnore() {
        sp.clear();
    }
}
