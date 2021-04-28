package com.vinnlook.www.base;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.dm.lib.core.common.Config;
import com.dm.lib.core.dialog.LoadingDialog;
import com.dm.lib.core.dialog.TipDialog;
import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.core.mvp.MvpActivity;
import com.dm.lib.core.mvp.MvpPresenter;
import com.dm.lib.core.receiver.ForceOfflineReceiver;
import com.dm.lib.utils.DisplayInfoUtils;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.receiver.AppUpdateReceiver;
import com.vinnlook.www.surface.activity.LoginActivity;
import com.vinnlook.www.surface.dialog.UpdateDialog;
import com.vinnlook.www.utils.UMInit;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.utils.sp.SPTextSizeUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Yanbo
 * @version v1.0.0
 * @date 2018/4/4-下午1:23
 */
public abstract class BaseActivity<P extends MvpPresenter> extends MvpActivity<P> {

    private static final long LOADING_MIN_SHOW_TIME = 500L;
    private Unbinder unbinder;
    private long loadingStartTime = 0L;
    private Runnable loadingRunnable = null;
    private LoadingDialog loadingDialog = null;
    private ForceOfflineReceiver forceOfflineReceiver = null;
    private AppUpdateReceiver appUpdateReceiver = null;
    private LocalBroadcastManager localBroadcastManager = null;
    private TipDialog forceOfflineDialog = null;
    private UpdateDialog updateDialog = null;
    @SuppressLint("HandlerLeak")
    private Handler forceOfflineHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            synchronized (getActivity()) {
                if (forceOfflineDialog == null) {
                    Bundle bundle = message.getData();
                    int code = bundle.getInt(ForceOfflineReceiver.KEY_CODE);
                    String msg = bundle.getString(ForceOfflineReceiver.KEY_MSG);
                    forceOfflineDialog = TipDialog.with(getActivity())
                            .cancelable(false)
                            .title(null)
                            .yesText(R.string.dialog_btn_login)
                            .yesTextColor(getResources().getColor(R.color.them))
                            .singleYesBtn()
                            .message(msg)
                            .onDismissListener(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    forceOfflineDialog = null;
                                }
                            })
                            .onYes(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    LoginActivity.startSelf(getContext());
                                    UserUtils.getInstance().logout();

                                }
                            });
                    forceOfflineDialog.show();
                }
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler appUpdateHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            synchronized (getActivity()) {
                //更新版本
                if (updateDialog == null) {
                    Bundle bundle = message.getData();
                    VersionBean versionBean = (VersionBean) bundle.getSerializable(AppUpdateReceiver.KEY_APP_UPDATE);
                    updateDialog = UpdateDialog.with(getActivity(), versionBean)
                            .setOnDismissListener(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    updateDialog = null;
                                }
                            });
                    updateDialog.show();
                }
            }
        }
    };

    @ColorInt
    protected int getStatusViewColor() {
        return ResUtils.getColor(R.color.view_status_bar);
    }

    /**
     * 状态栏View的id
     */
    @IdRes
    protected int getStatusBarId() {
        return 0;
    }

    /**
     * 加载中view的id
     */
    @IdRes
    protected int getLoadingBarId() {
        return 0;
    }

    /**
     * 必须登录的点击事件
     * 如果已经登录直接执行，没有登录时跳转登录界面
     */
    public void onClickCheckLogin(View v) {
    }

    /**
     * 不需要登录的点击事件
     */
    public boolean onClickWithoutLogin(View v) {
        return false;
    }

    @Override
    protected void initialize() {
        unbinder = ButterKnife.bind(this);
        initFontScale();
        initStatusBar();

        ButterKnife.bind(this);


        super.initialize();
    }

    private void initStatusBar() {
        int id = getStatusBarId();
        if (id == -1) {
            return;
        }
        if (0 == id) {
            id = R.id.view_status_bar;
        }
        View view = findViewById(id);
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = DisplayInfoUtils.getInstance().getStatusBarHeight();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);
        view.setBackgroundColor(getStatusViewColor());
        view.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        UMInit.onPageStart(this);
        UMInit.onResume(this);

        //阿里移动数据分析
        MANService manService = MANServiceProvider.getService();
        manService.getMANPageHitHelper().pageAppear(this);


        if (localBroadcastManager == null) {
            localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        }

        forceOfflineReceiver = new ForceOfflineReceiver(forceOfflineHandler);
        IntentFilter intentFilterForceOffline = new IntentFilter();
        intentFilterForceOffline.addAction(Config.ACTION_FORCE_OFFLINE);
        localBroadcastManager.registerReceiver(forceOfflineReceiver, intentFilterForceOffline);

        appUpdateReceiver = new AppUpdateReceiver(appUpdateHandler);
        IntentFilter intentFilterAppUpdate = new IntentFilter();
        intentFilterAppUpdate.addAction(Config.ACTION_APP_UPDATE);
        localBroadcastManager.registerReceiver(appUpdateReceiver, intentFilterAppUpdate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UMInit.onPageEnd(this);
        UMInit.onPause(this);
        //阿里移动数据分析
        MANService manService = MANServiceProvider.getService();
        manService.getMANPageHitHelper().pageDisAppear(this);

        if (localBroadcastManager != null && forceOfflineReceiver != null) {
            localBroadcastManager.unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
        if (localBroadcastManager != null && appUpdateReceiver != null) {
            localBroadcastManager.unregisterReceiver(appUpdateReceiver);
            appUpdateReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        if (!isFinishing() && null != unbinder) {
            unbinder.unbind();
        }
        clearLoading();
        super.onDestroy();
    }

    private View getLoadingBar() {
        int id = getLoadingBarId();
        if (id == -1) {
            return null;
        }
        if (0 == id) {
            id = R.id.loading_bar;
        }
        return findViewById(id);
    }

    /**
     * 用注解绑定点击事件时，在该方法绑定
     *
     * @param v
     */
    @Override
    protected void onClickOnlyFirst(final View v) {
        if (!onClickWithoutLogin(v)) {
            if (UserUtils.getInstance().doIfLogin(getContext())) {
                onClickCheckLogin(v);
            }
        }
    }

    public void showLoadingBar() {
        final View view = getLoadingBar();
        if (view != null) {
            if (loadingRunnable != null) {
                view.removeCallbacks(loadingRunnable);
            }
            view.setVisibility(View.VISIBLE);
            loadingStartTime = System.currentTimeMillis();
        }
    }

    public void dismissLoadingBar() {
        final View view = getLoadingBar();
        if (view != null) {
            long loadingEndTime = System.currentTimeMillis();
            long loadingTime = loadingEndTime - loadingStartTime;
            long delay = LOADING_MIN_SHOW_TIME - loadingTime;
            delay = delay < 0 ? 0 : delay;
            if (loadingRunnable == null) {
                loadingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                };
            }
            view.postDelayed(loadingRunnable, delay);
        }
    }

    @Override
    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.with(getContext());
        }
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void clearLoading() {
        final View view = getLoadingBar();
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 修改APP字体大小，按当前字体的大小放大或缩小倍数，
     * 修改后要重启所有Activity才能生效
     */
    protected void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = SPTextSizeUtils.instance().getTextSize();
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }


}
