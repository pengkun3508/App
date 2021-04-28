package com.dm.lib.core.mvp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dm.lib.utils.ClickHelper;

import org.greenrobot.eventbus.EventBus;

import per.goweii.statusbarcompat.StatusBarCompat;


/**
 * @author Cuizhen
 * @version v1.0.0
 * @date 2018/4/4-下午1:23
 */
public abstract class MvpActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView, View.OnClickListener {

    public P presenter;

    /**
     * 获取布局资源文件
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定数据
     */
    protected abstract void loadData();

    /**
     * 再次进入当前界面时的新数据，即onRestart中获取的 Bundle
     */
    protected void regainBundle(@NonNull Bundle bundle) {
    }

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 点击事件，500毫秒第一次
     */
    protected void onClickOnlyFirst(final View v) {
    }

    protected void initWindow() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
//        StatusBarCompat.transparent(this);
        StatusBarCompat.setIconMode(this, false);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(getLayoutId());
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onCreate(this);
        }
        initialize();
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }



    }

    protected void initialize() {
        initView();
        loadData();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    protected Activity getActivity() {
        return this;
    }

    /**
     * 用注解绑定点击事件时，在该方法绑定
     */
    @Override
    public void onClick(final View v) {
        ClickHelper.onlyFirstSameView(v, new ClickHelper.Callback() {
            @Override
            public void onClick(View view) {
                onClickOnlyFirst(view);
            }
        });
    }
}
