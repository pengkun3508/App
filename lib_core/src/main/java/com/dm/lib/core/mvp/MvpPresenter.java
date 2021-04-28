package com.dm.lib.core.mvp;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;

/**
 * @author Cuizhen
 * @version v1.0.0
 * @date 2018/4/4-下午1:23
 */
public abstract class MvpPresenter<V extends MvpView> {
    protected Context context;
    private V baseView;
    private RxLife rxLife;

    void onCreate(V baseView) {
        this.baseView = baseView;
        context = baseView.getContext();
        rxLife = RxLife.create();
    }

    void onDestroy() {
        baseView = null;
        context = null;
        rxLife.destroy();
        rxLife = null;
    }

    public RxLife getRxLife() {
        return rxLife;
    }

    public void addToRxLife(Disposable disposable) {
        if (rxLife != null) {
            rxLife.add(disposable);
        }
    }

    public V getBaseView() {
        return baseView;
    }

    public boolean isAttachView() {
        return baseView != null;
    }

    public Context getContext() {
        return context;
    }

    public void showLoading() {
        if (baseView != null) {
            baseView.showLoadingDialog();
        }
    }

    public void dismissLoading() {
        if (baseView != null) {
            baseView.dismissLoadingDialog();
        }
    }
}
