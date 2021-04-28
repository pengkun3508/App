package com.dm.lib.utils;

/**
 * 描述：缓存工具类
 *
 * @author ANyu
 * @date 2019\5\23 0023
 */
public class SPUtilCache {

    private SPUtils sp;
    private static final String KEY = "accountCenterData";

    private SPUtilCache() {
        sp = SPUtils.newInstance("Cache");
    }

    public static SPUtilCache instance() {
        return new SPUtilCache();
    }

    public String getCache() {
        return sp.get(KEY, "");
    }

    public void setCache(String bean) {
        sp.save(KEY, bean);
    }
}
