package com.dm.lib.utils;

import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.TextView;

/**
 * 复制到剪贴板
 *
 * @author Cuizhen
 * @date 2018/8/28-下午6:58
 */
public class CopyUtils {

    public static void copyText(@NonNull String text) {
        ClipboardManager clipboardManager = (ClipboardManager) Utils.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(text);
    }

    public static void copyText(@NonNull TextView textView) {
        copyText(textView.getText().toString());
    }
}
