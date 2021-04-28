package com.vinnlook.www.widgat.vercode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.dm.lib.utils.timer.CodeTimer;
import com.vinnlook.www.R;


/**
 * 描述：发送验证码和倒计时的按钮
 *
 * @author Cuizhen
 * @date 2018/9/30
 */
public class VerCodeView extends AppCompatTextView {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_WAITING = 1;
    public static final int STATE_FINISH = 2;

    private int mState;
    private int mDuration;
    private CodeTimer<VerCodeView> mSecondTimer;
    private String mText;
    private int mTextColor;
    private Drawable mBgDrawable;
    private String mWaitingText;
    private int mWaitingTextColor;
    private Drawable mWaitingBgDrawable;
    private String mFinishText;
    private int mFinishTextColor;
    private Drawable mFinishBgDrawable;

    public VerCodeView(Context context) {
        this(context, null);
    }

    public VerCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.VerCodeView);
        mState = array.getInt(R.styleable.VerCodeView_vcv_state, STATE_NORMAL);
        mDuration = array.getInteger(R.styleable.VerCodeView_vcv_duration, 60);
        mText = getText().toString();
        mTextColor = getCurrentTextColor();
        mBgDrawable = getBackground();
        mWaitingText = array.getString(R.styleable.VerCodeView_vcv_waiting_text);
        mWaitingTextColor = array.getColor(R.styleable.VerCodeView_vcv_waiting_text_color, mTextColor);
        mWaitingBgDrawable = array.getDrawable(R.styleable.VerCodeView_vcv_waiting_bg);
        mFinishText = array.getString(R.styleable.VerCodeView_vcv_finish_text);
        mFinishTextColor = array.getColor(R.styleable.VerCodeView_vcv_finish_text_color, mTextColor);
        mFinishBgDrawable = array.getDrawable(R.styleable.VerCodeView_vcv_finish_bg);
        array.recycle();
        initState();
    }

    private void initState() {
        switch (mState) {
            default:
                break;
            case STATE_NORMAL:
                changeToNormal();
                break;
            case STATE_WAITING:
                start();
                break;
            case STATE_FINISH:
                changeToFinish();
                break;
        }
    }

    public void start() {
        start(mDuration);
    }

    public void start(int duration) {
        if (mSecondTimer != null) {
            return;
        }
        mSecondTimer = new CodeTimer<>(duration, this);
        mSecondTimer.setOnTimerListener(new CodeTimer.OnTimerListener<VerCodeView>() {
            @Override
            public void onStart(VerCodeView view, long secondUntilFinished) {
                changeToWaiting();
            }

            @Override
            public void onTick(VerCodeView view, long secondUntilFinished) {
                changeOnTick(secondUntilFinished);
            }

            @Override
            public void onFinish(VerCodeView view) {
                mSecondTimer = null;
                changeToFinish();
            }
        }).start();
    }

    private void changeToNormal() {
        setClickable(true);
        setTextColor(mTextColor);
        setBackgroundDrawable(mBgDrawable);
        setText(mText);
    }

    private void changeToWaiting() {
        setClickable(false);
        setTextColor(mWaitingTextColor);
        if (mWaitingBgDrawable != null) {
            setBackgroundDrawable(mWaitingBgDrawable);
        }
    }

    private void changeOnTick(long secondUntilFinished) {
        setText(String.format(getWaitingText(), secondUntilFinished));
    }

    private void changeToFinish() {
        setClickable(true);
        setTextColor(mFinishTextColor);
        if (mFinishBgDrawable != null) {
            setBackgroundDrawable(mFinishBgDrawable);
        }
        setText(mFinishText);
    }

    private String getWaitingText() {
        if (mWaitingText == null) {
            mWaitingText = "%d";
        } else if (!mWaitingText.contains("%")) {
            mWaitingText = mWaitingText + "%d";
        }
        return mWaitingText;
    }
}