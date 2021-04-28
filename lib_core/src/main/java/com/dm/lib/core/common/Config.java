package com.dm.lib.core.common;

/**
 * 可变的参数
 *
 * @author Cuizhen
 */
public interface Config {

    /**
     * 强制退出登录状态的本地广播的Action
     */
    String ACTION_FORCE_OFFLINE = "com.dm.receiver.FORCE_OFFLINE";

    /**
     * 更新APP的本地广播的Action
     */
    String ACTION_APP_UPDATE = "com.dm.receiver.APP_UPDATE";

    /**
     * dialog背景变暗程度
     */
    float DIALOG_BG_DIM_AMOUNT = 0.5F;
}