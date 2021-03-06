package com.vinnlook.www.widgat.vercode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.ColorRes;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.vinnlook.www.R;
import com.vinnlook.www.utils.ScrollExpandTextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述：验证码输入框
 *
 * @author Cuizhen
 * @date 2018/11/9
 */
public class VerCodeEditText  extends AppCompatEditText implements TextWatcher {
    private static final int DEFAULT_CURSOR_DURATION = 400;

    private int mFigures;//需要输入的位数
    private int mVerCodeMargin;//验证码之间的间距
    private int mBottomSelectedColor;//底部选中的颜色
    private int mBottomNormalColor;//未选中的颜色
    private int mBottomInputtedColor;//已输入的颜色
    private float mBottomLineNormalHeight;//底线的高度
    private float mBottomLineSelectedHeight;//底线的高度
    private float mBottomLineInputtedHeight;//底线的高度
    private int mSelectedBackgroundColor;//选中的背景颜色
    private int mCursorWidth;//光标宽度
    private int mCursorColor;//光标颜色
    private int mCursorDuration;//光标闪烁间隔

    private OnVerCodeChangedListener onCodeChangedListener;
    private int mCurrentPosition = 0;
    private int mEachRectLength = 0;//每个矩形的边长
    private Paint mSelectedBackgroundPaint;
    private Paint mNormalBackgroundPaint;
    private Paint mBottomSelectedPaint;
    private Paint mBottomNormalPaint;
    private Paint mBottomInputtedPaint;
    private Paint mCursorPaint;

    // 控制光标闪烁
    private boolean isCursorShowing;
    private TimerTask mCursorTimerTask;
    private Timer mCursorTimer;

    public VerCodeEditText(Context context) {
        this(context, null);
    }

    public VerCodeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerCodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));//防止出现下划线
        initPaint();
        initCursorTimer();
        setFocusableInTouchMode(true);
        super.addTextChangedListener(this);
    }

    /**
     * 初始化paint
     */
    private void initPaint() {
        mSelectedBackgroundPaint = new Paint();
        mSelectedBackgroundPaint.setColor(mSelectedBackgroundColor);
        mNormalBackgroundPaint = new Paint();
        mNormalBackgroundPaint.setColor(getColor(android.R.color.transparent));

        mBottomSelectedPaint = new Paint();
        mBottomSelectedPaint.setColor(mBottomSelectedColor);
        mBottomSelectedPaint.setStrokeWidth(mBottomLineSelectedHeight);
        mBottomNormalPaint = new Paint();
        mBottomNormalPaint.setColor(mBottomNormalColor);
        mBottomNormalPaint.setStrokeWidth(mBottomLineNormalHeight);
        mBottomInputtedPaint = new Paint();
        mBottomInputtedPaint.setColor(mBottomInputtedColor);
        mBottomInputtedPaint.setStrokeWidth(mBottomLineInputtedHeight);

        mCursorPaint = new Paint();
        mCursorPaint.setAntiAlias(true);
        mCursorPaint.setColor(mCursorColor);
        mCursorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCursorPaint.setStrokeWidth(mCursorWidth);
    }

    /**
     * 初始化Attrs
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.VerCodeEditText);
        mFigures = ta.getInteger(R.styleable.VerCodeEditText_figures, 4);
        mVerCodeMargin = (int) ta.getDimension(R.styleable.VerCodeEditText_verCodeMargin, 0);
        mBottomNormalColor = ta.getColor(R.styleable.VerCodeEditText_bottomLineNormalColor, getColor(android.R.color.darker_gray));
        mBottomSelectedColor = ta.getColor(R.styleable.VerCodeEditText_bottomLineSelectedColor, getCurrentTextColor());
        mBottomInputtedColor = ta.getColor(R.styleable.VerCodeEditText_bottomLineInputtedColor, getColor(android.R.color.darker_gray));
        mBottomLineNormalHeight = ta.getDimension(R.styleable.VerCodeEditText_bottomLineNormalHeight, dp2px(2));
        mBottomLineSelectedHeight = ta.getDimension(R.styleable.VerCodeEditText_bottomLineSelectedHeight, dp2px(2));
        mBottomLineInputtedHeight = ta.getDimension(R.styleable.VerCodeEditText_bottomLineInputtedHeight, dp2px(2));
        mSelectedBackgroundColor = ta.getColor(R.styleable.VerCodeEditText_selectedBackgroundColor, getColor(android.R.color.darker_gray));
        mCursorWidth = (int) ta.getDimension(R.styleable.VerCodeEditText_cursorWidth, dp2px(1));
        mCursorColor = ta.getColor(R.styleable.VerCodeEditText_cursorColor, getColor(android.R.color.darker_gray));
        mCursorDuration = ta.getInteger(R.styleable.VerCodeEditText_cursorDuration, DEFAULT_CURSOR_DURATION);
        ta.recycle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setLayoutDirection(LAYOUT_DIRECTION_LTR);
        }
    }

    private void initCursorTimer() {
        mCursorTimerTask = new TimerTask() {
            @Override
            public void run() {
                // 通过光标间歇性显示实现闪烁效果
                isCursorShowing = !isCursorShowing;
                postInvalidate();
            }
        };
        mCursorTimer = new Timer();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 启动定时任务，定时刷新实现光标闪烁
        mCursorTimer.scheduleAtFixedRate(mCursorTimerTask, 0, mCursorDuration);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCursorTimer.cancel();
    }

    @Override
    final public void setCursorVisible(boolean visible) {
        super.setCursorVisible(visible);//隐藏光标的显示
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthResult = 0, heightResult = 0;
        //最终的宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widthResult = widthSize;
        } else {
            widthResult = getScreenWidth(getContext());
        }
        //每个矩形形的宽度
        mEachRectLength = (widthResult - (mVerCodeMargin * (mFigures - 1))) / mFigures;
        //最终的高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightResult = heightSize;
        } else {
            heightResult = mEachRectLength;
        }
        setMeasuredDimension(widthResult, heightResult);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            requestFocus();
            setSelection(getText().length());
            showKeyBoard(getContext());
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCurrentPosition = getText().length();
        int width = mEachRectLength - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        for (int i = 0; i < mFigures; i++) {
            canvas.save();
            int start = width * i + i * mVerCodeMargin;
            int end = width + start;
            //画一个矩形
            if (i == mCurrentPosition) {//选中的下一个状态
                canvas.drawRect(start, 0, end, height, mSelectedBackgroundPaint);
            } else {
                canvas.drawRect(start, 0, end, height, mNormalBackgroundPaint);
            }
            canvas.restore();
        }
        //绘制文字
        String value = getText().toString();
        for (int i = 0; i < value.length(); i++) {
            canvas.save();
            int start = width * i + i * mVerCodeMargin;
            float x = start + width / 2;
            TextPaint paint = getPaint();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getCurrentTextColor());
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(String.valueOf(value.charAt(i)), x, baseline, paint);
            canvas.restore();
        }
        //绘制底线
        for (int i = 0; i < mFigures; i++) {
            canvas.save();
            int start = width * i + i * mVerCodeMargin;
            int end = width + start;
            if (i == mCurrentPosition){
                float lineY = height - mBottomLineSelectedHeight / 2;
                canvas.drawLine(start, lineY, end, lineY, mBottomSelectedPaint);
            } else if (i < mCurrentPosition) {
                float lineY = height - mBottomLineInputtedHeight / 2;
                canvas.drawLine(start, lineY, end, lineY, mBottomInputtedPaint);
            } else {
                float lineY = height - mBottomLineNormalHeight / 2;
                canvas.drawLine(start, lineY, end, lineY, mBottomNormalPaint);
            }
            canvas.restore();
        }
        //绘制光标
        if (!isCursorShowing && isCursorVisible() && mCurrentPosition < mFigures && hasFocus()) {
            canvas.save();
            int startX = mCurrentPosition * (width + mVerCodeMargin) + width / 2;
            int startY = height / 4;
            int endX = startX;
            int endY = height - height / 4;
            canvas.drawLine(startX, startY, endX, endY, mCursorPaint);
            canvas.restore();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mCurrentPosition = getText().length();
        postInvalidate();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mCurrentPosition = getText().length();
        postInvalidate();
        if (onCodeChangedListener != null) {
            onCodeChangedListener.onVerCodeChanged(getText(), start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        mCurrentPosition = getText().length();
        postInvalidate();
        if (getText().length() == mFigures) {
            if (onCodeChangedListener != null) {
                onCodeChangedListener.onInputCompleted(getText());
            }
        } else if (getText().length() > mFigures) {
            getText().delete(mFigures, getText().length());
        }
    }

    public void setFigures(int figures) {
        mFigures = figures;
        postInvalidate();
    }

    public void setVerCodeMargin(int margin) {
        mVerCodeMargin = margin;
        postInvalidate();
    }

    public void setBottomSelectedColor(@ColorRes int bottomSelectedColor) {
        mBottomSelectedColor = getColor(bottomSelectedColor);
        postInvalidate();
    }

    public void setBottomNormalColor(@ColorRes int bottomNormalColor) {
        mBottomSelectedColor = getColor(bottomNormalColor);
        postInvalidate();
    }

    public void setSelectedBackgroundColor(@ColorRes int selectedBackground) {
        mSelectedBackgroundColor = getColor(selectedBackground);
        postInvalidate();
    }

    public void setBottomLineSelectedHeight(int bottomLineSelectedHeight) {
        this.mBottomLineSelectedHeight = bottomLineSelectedHeight;
        postInvalidate();
    }

    public void setBottomLineNormalHeight(int bottomLineNormalHeight) {
        this.mBottomLineNormalHeight = bottomLineNormalHeight;
        postInvalidate();
    }

    public void setBottomLineInputtedHeight(int bottomLineInputtedHeight) {
        this.mBottomLineInputtedHeight = bottomLineInputtedHeight;
        postInvalidate();
    }

    public void setOnVerCodeChangedListener(OnVerCodeChangedListener listener) {
        this.onCodeChangedListener = listener;
    }

    /**
     * 返回颜色
     */
    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }

    /**
     * dp转px
     */
    private int  dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * 获取手机屏幕的宽度
     * @param context
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public void showKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 验证码变化时候的监听事件
     */
    public interface OnVerCodeChangedListener {

        /**
         * 当验证码变化的时候
         */
        void onVerCodeChanged(CharSequence s, int start, int before, int count);

        /**
         * 输入完毕后的回调
         */
        void onInputCompleted(CharSequence s);
    }
}
