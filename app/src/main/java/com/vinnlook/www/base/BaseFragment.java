package com.vinnlook.www.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;

import com.dm.lib.core.dialog.LoadingDialog;
import com.dm.lib.core.mvp.MvpFragment;
import com.dm.lib.core.mvp.MvpPresenter;
import com.dm.lib.utils.DisplayInfoUtils;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.utils.UserUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载
 *
 * @author Yanbo
 * @version v1.0.0
 * @date 2018/3/10-下午12:38
 */
public abstract class BaseFragment<Presenter extends MvpPresenter> extends MvpFragment<Presenter> {
    private static final long LOADING_MIN_SHOW_TIME = 500L;
    boolean showLoading = true;
    private Unbinder unbinder;
    private long loadingStartTime = 0L;
    private Runnable loadingRunnable = null;
    private LoadingDialog loadingDialog = null;

    protected int getStatusViewColor() {
        return ResUtils.getColor(R.color.view_status_bar);
    }

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

    @Override
    protected void initialize() {
        if (null != getRootView()) {
            unbinder = ButterKnife.bind(this, getRootView());
        }
        initStatusBar();
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
        if (null == getRootView()) {
            return;
        }
        View statusBar = getRootView().findViewById(id);
        if (statusBar == null) {
            return;
        }
        ViewGroup.LayoutParams params = statusBar.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = DisplayInfoUtils.getInstance().getStatusBarHeight();
        statusBar.setBackgroundColor(getStatusViewColor());
        statusBar.setLayoutParams(params);
        statusBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        if (null != getRootView() && null != unbinder) {
            unbinder.unbind();
        }
        clearLoading();
        super.onDestroyView();
    }

    private View getLoadingBar() {
        if (null == getRootView()) {
            return null;
        }
        int id = getLoadingBarId();
        if (id == -1) {
            return null;
        }
        if (0 == id) {
            id = R.id.loading_bar;
        }
        return getRootView().findViewById(id);
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

    public boolean onClickWithoutLogin(View v) {
        return false;
    }

    public void onClickCheckLogin(View v) {
    }

    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
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
        dismissLoadingDialog();
    }

    public void finish(){
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
