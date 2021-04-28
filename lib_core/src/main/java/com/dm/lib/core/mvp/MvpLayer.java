package com.dm.lib.core.mvp;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import per.goweii.anylayer.AnyLayer;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/12/10
 */
public abstract class MvpLayer<P extends MvpPresenter> implements MvpView, AnyLayer.OnVisibleChangeListener {

    protected final Activity mActivity;
    protected AnyLayer mAnyLayer;

    protected P presenter;

    public MvpLayer(@NonNull Activity activity) {
        mActivity = activity;
    }

    /**
     * 获取布局资源文件
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();

    @NonNull
    protected abstract AnyLayer onCreateLayer(@NonNull Activity activity, @LayoutRes int layoutId);

    /**
     * 初始化控件
     */
    protected abstract void initView(@NonNull AnyLayer anyLayer);

    @Override
    public void onShow(AnyLayer anyLayer) {
        presenter =  initPresenter();
        if (presenter != null) {
            presenter.onCreate(this);
        }
    }

    @Override
    public void onDismiss(AnyLayer anyLayer) {
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public void showLoadingDialog() {
    }

    @Override
    public void dismissLoadingDialog() {
    }

    @Override
    public void clearLoading() {
    }

    public void show(){
        if (mAnyLayer == null) {
            mAnyLayer = onCreateLayer(mActivity, getLayoutId());
            mAnyLayer.onVisibleChangeListener(this);
            mAnyLayer.bindData(new AnyLayer.IDataBinder() {
                @Override
                public void bind(AnyLayer anyLayer) {
                    initView(anyLayer);
                }
            });
        }
        mAnyLayer.show();
    }

    public void dismiss(){
        if (mAnyLayer != null) {
            mAnyLayer.dismiss();
        }
    }
}
