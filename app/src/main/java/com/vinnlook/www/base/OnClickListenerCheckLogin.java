package com.vinnlook.www.base;

import android.content.Context;
import android.view.View;

import com.dm.lib.utils.ClickHelper;
import com.vinnlook.www.utils.UserUtils;


/**
 * @author Yanbo
 * @date 2018/5/7-下午4:40
 */
public abstract class OnClickListenerCheckLogin implements View.OnClickListener {

    private final Context context;

    public OnClickListenerCheckLogin(Context context) {
        this.context = context;
    }

    @Override
    public final void onClick(final View v) {
        ClickHelper.onlyFirstSameView(v, new ClickHelper.Callback() {
            @Override
            public void onClick(View view) {
                if (UserUtils.getInstance().doIfLogin(context)) {
                    onClickCheckLogin(view);
                }
            }
        });
    }

    public abstract void onClickCheckLogin(View v);
}
