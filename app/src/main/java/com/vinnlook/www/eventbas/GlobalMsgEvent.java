package com.vinnlook.www.eventbas;

import android.view.View;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * Created by apple on 2018/4/10.
 */

public class GlobalMsgEvent extends BaseEvent {
    public enum DisplayType {
        Toast, Dialog, ProgressDialog
    }

    /**
     * 消息显示类型：Toast或者Dialog，默认Toast
     */
    private DisplayType displayType = DisplayType.Toast;
    /**
     * 消息文案
     */
    private String msg;
    /**
     * 是否关闭Dialog，默认为false为显示Dialog
     */
    private boolean closeDialog;

    /**
     * dialog相关属性
     */
    private boolean cancelable; // dialog是否可以取消显示
    private View.OnClickListener onPositiveClick; // positive点击

    public String getMsg() {
        return msg;
    }

    public GlobalMsgEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public GlobalMsgEvent setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
        return this;
    }

    public boolean isCloseDialog() {
        return closeDialog;
    }

    public GlobalMsgEvent setCloseDialog(boolean closeDialog) {
        this.closeDialog = closeDialog;
        return this;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public GlobalMsgEvent setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public View.OnClickListener getOnPositiveClick() {
        return onPositiveClick;
    }

    public GlobalMsgEvent setOnPositiveClick(View.OnClickListener onPositiveClick) {
        this.onPositiveClick = onPositiveClick;
        return this;
    }
}
