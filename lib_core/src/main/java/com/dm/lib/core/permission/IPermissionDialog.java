package com.dm.lib.core.permission;

/**
 * @author Cuizhen
 * @date 2018/8/28-下午1:34
 */
public interface IPermissionDialog {
    void show(IPermissionDialogCallback callback);
    void dismiss();
}
