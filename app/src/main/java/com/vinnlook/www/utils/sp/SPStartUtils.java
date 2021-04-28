package com.vinnlook.www.utils.sp;

import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.SPUtils;
import com.vinnlook.www.common.Config;


/**
 * APP初次启动
 * @author Yanbo
 * @date 2018/6/30-上午10:30
 */
public class SPStartUtils {

    private static final String KEY_VERSION = "version";

    private SPUtils sp;

    private SPStartUtils() {
        sp = SPUtils.newInstance("start_first");
    }

    public static SPStartUtils instance() {
        return new SPStartUtils();
    }

    public boolean isFirst() {
        boolean isFirst;
        int lastVersion = sp.get(KEY_VERSION, 0);
        int currentVersion = AppInfoUtils.getVersionCode();
        if (lastVersion != currentVersion) {
            isFirst = lastVersion < Config.SHOW_WELCOME_BELOW_VERSION_CODE;
        } else {
            isFirst = false;
        }
        return isFirst;
    }

    public void setStarted() {
        sp.save(KEY_VERSION, AppInfoUtils.getVersionCode());
    }
}
