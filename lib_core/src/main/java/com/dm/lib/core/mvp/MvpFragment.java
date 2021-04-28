package com.dm.lib.core.mvp;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;

import com.dm.lib.core.R;
import com.dm.lib.core.dialog.LoadingDialog;
import com.dm.lib.utils.ClickHelper;
import com.dm.lib.utils.DisplayInfoUtils;
import com.dm.lib.utils.ResUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载
 *
 * @author Cuizhen
 * @version v1.0.0
 * @date 2018/3/10-下午12:38
 */
public abstract class MvpFragment<T extends MvpPresenter> extends LazyFragment implements MvpView, View.OnClickListener {
    protected T presenter;

    /**
     * 获取布局资源文件
     *
     * @return int
     */
    @Override
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     *
     * @return P
     */
    protected abstract T initPresenter();

    /**
     * 绑定事件
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 点击事件，可连续点击
     */
    protected boolean onClickEveryTip(final View v){
        return false;
    }

    /**
     * 点击事件，500毫秒第一次
     */
    protected void onClickOnlyFirst(final View v){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onCreate(this);
        }
        initialize();
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void initialize() {
        initView();
        loadData();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    public Fragment getFragment() {
        return this;
    }

    /**
     * 用注解绑定点击事件时，在该方法绑定
     */
    @Override
    public void onClick(final View v) {
        if (!onClickEveryTip(v)) {
            ClickHelper.onlyFirstSameView(v, new ClickHelper.Callback() {
                @Override
                public void onClick(View view) {
                    onClickOnlyFirst(view);
                }
            });
        }
    }
}
