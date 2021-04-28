package com.dm.lib.utils.timer;

import android.os.CountDownTimer;

import com.dm.lib.utils.LogUtils;

/**
 * 描述：毫秒倒计时
 *
 * @author Cuizhen
 * @date 2018/9/20
 */
public abstract class BaseTimer extends CountDownTimer {

    private boolean mStart = false;
    private long millisUntilFinished;

    public BaseTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture + 499, countDownInterval);
    }

    @Override
    public final void onTick(long millisUntilFinished) {
        if (!mStart) {
            mStart = true;
            onTimerStart(millisUntilFinished);
        }
        this.millisUntilFinished=millisUntilFinished;
        onTimerTick(millisUntilFinished);
    }

    public boolean isStart() {
        return mStart;
    }

    public void finish(){
        onFinish();
        cancel();
    }

    @Override
    public final void onFinish() {
        if (millisUntilFinished>1499){
            onTimerFinish(millisUntilFinished);
        }else {
            onTimerFinish();
        }

    }

    protected abstract void onTimerStart(long millisUntilFinished);

    protected abstract void onTimerTick(long millisUntilFinished);

    protected abstract void onTimerFinish();
    protected abstract void onTimerFinish(long millisUntilFinished);
}

