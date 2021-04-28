package com.vinnlook.www.widgat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.vinnlook.www.R;
import com.vinnlook.www.surface.mvp.model.bean.HomeBean;

import java.util.List;

/**
 * 描述：
 *
 * @author ANyu
 * @date 2019\6\6 0006
 */
public class MarqueeTextView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int TOAST_FLAT = 0x0001;
    public static final String TEXT_OVER = "";
    public static int TextIndex = 0;

    private Handler activityHandler;
    private final String TAG = "CustomScrollBar";
    Context context;
    private Message message;


    private SurfaceHolder holder;
    private Paint paint = null;// 画笔
    /*  private boolean bStop = false; // 停止滚动*/

    /*private boolean clickEnable = false; // 可以点击*/
    private boolean direction = true; // direction:true,右向左移动；false，左向右移动
    private int speed = 3; // 滚动速度
    private String text = "";// 文本内容f
    private float textSize = 30f; // 字号
    private int textColor = Color.BLACK; // 文字颜色
    private int times = Integer.MAX_VALUE; // 滚动次数
    private List<HomeBean.NewbannerBean> newbanner;
    private int viewWidth = 0;// 控件的长度
    private int viewHeight = 0; // 控件的高度
    private float textWidth = 0f;// 水平滚动时的文本长度
    /* private float textHeight = 0f; // 垂直滚动时的文本高度*/

    private float textX = 0f;// 文字的横坐标
    private float textY = 0f;// 文字的纵坐标
    private float viewWidth_plus_textLength = 0.0f;// 显示总长度
    private Handler mHandler;

    /* private ScheduledExecutorService scheduledExecutorService; // 执行滚动线程*/

    public MarqueeTextView(Context context) {
        super(context);
    }

    /**
     * customscrollbar类的构造方法
     *
     * @param context
     * @param attrs
     */
    public MarqueeTextView(Context context, AttributeSet attrs) {
        //---------1
        super(context, attrs);
        this.context = context;
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();

        //获取布局文件中的值
        @SuppressLint("Recycle")
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeText);
        direction = arr.getBoolean(R.styleable.MarqueeText_direction, direction);
        speed = arr.getInteger(R.styleable.MarqueeText_speed, speed);
        text = arr.getString(R.styleable.MarqueeText_text);
        textColor = arr.getColor(R.styleable.MarqueeText_textColor, textColor);
        textSize = arr.getDimension(R.styleable.MarqueeText_textSize, textSize);
        times = arr.getInteger(R.styleable.MarqueeText_times, times);

        paint.setColor(textColor);
        //settextsize方法可以设置字体大小
        paint.setTextSize(textSize);

        /*
         * 下面两行代码配合draw()方法中的canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
         * 将画布填充为透明
         */
        setZOrderOnTop(true);

        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        setFocusable(true); // 设置焦点
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //----------2
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);//得到控件的宽度
        //得到控件的高度
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
//        viewHeight = getMeasuredHeight();

        textWidth = paint.measureText(text);// 获取text的长度
        //字幕+控件的总长度
        viewWidth_plus_textLength = viewWidth + textWidth;
        //y轴的坐标
        //getpaddingtop指文字到控件上边界的距离，getpaddingbottom指文字到控件下边界的距离
//            textY = (viewHeight - getFontHeight(textSize)) / 2 + getPaddingTop() - getPaddingBottom();
        textY = getFontHeight(textSize + 20);
    }

    @SuppressLint({"ShowToast", "ClickableViewAccessibility"})
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_DOWN:
//                message = activityHandler.obtainMessage();
//                message.what = TOAST_FLAT;
//                message.sendToTarget();
                mHandler.post(toastRunnable);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        //----------4
//        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //-----------3
        //获取主线程的handler,将scrollthread放进主线程处理
        mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(ScrollThread);
        setZOrderOnTop(true);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        //结束scrollthread的递归
        mHandler.removeCallbacks(ScrollThread);
        Log.d(TAG, "surfaceDestroyed: ");
    }

    /**
     * 获取当前字体的字的实际高度
     *
     * @param fontSize
     * @return
     */
    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        //获取fontmetrics实例
        Paint.FontMetrics fm = paint.getFontMetrics();
        //descent:字的当前绘制顶线，ascent:字的当前绘制顶线
        //返回当前绘制底线-当前绘制顶线的值，得到的即为字的实际高度
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    /**
     * 设置字幕的滚动次数
     * @param times
     */
/*    private void setTimes(int times) {
        if (times <= 0) {
            this.times = Integer.MAX_VALUE;
        } else {
            this.times = times;
            time = times;
        }
    }*/

    /**
     * 设置滚动字幕的字符串
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 设置字幕的滚动速度
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        if (speed > 10 || speed < 0) {
            throw new IllegalArgumentException("Speed was invalid integer, it must between 0 and 10");
        } else {
            this.speed = speed;
        }
    }

    /**
     * 设置字幕文字的字体大小
     *
     * @param font
     */
    public void setFont(float font) {
        this.textSize = font;
        this.paint.setTextSize(font);
    }

    /**
     * 设置字幕文字的颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        this.paint.setColor(color);
    }

    /**
     * 设置文字滚动的方向
     * true:右向左移动，false:左向右移动
     *
     * @param direction
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * 滚动字幕绘制方法
     * x,y传入的是文字的左下角坐标
     *
     * @param X
     * @param Y
     */
    private synchronized void draw(float X, float Y) {
        Canvas canvas = holder.lockCanvas();//获取画布
        // 通过清屏把画布填充为透明
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawText(text, X, Y, paint);
        holder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
    }

    Runnable toastRunnable = new Runnable() {
        @Override
        public void run() {
//            Toast.makeText(context, "This is a click toast!", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 绘制滚动字幕的线程
     */
    Runnable ScrollThread = new Runnable() {
        @Override
        public void run() {
//            Log.d(TAG, "run: thread is running");
            //判断字的宽度是否大于控件的宽度，如果大于则开始滚动，如果小于，则静态显示在控件的中间
//            if(textWidth>viewWidth){
//                if (direction) {
            //绘制字幕，传入参数分别为x轴，y轴在控件中的坐标
            draw(viewWidth - textX, textY);
            //计算下一次绘制的x轴坐标
            textX += speed;// 速度设置：1-10
            //当x轴坐标大于控件最大长度时，将x轴坐标清零，滚动总次数减一
            if (textX > viewWidth_plus_textLength) {
                TextIndex++;
                textX = 0;
            }
               /* }else{
                    //从左向右移动
                    draw(textX,textY);
                    textX+=speed;
                    if(textX>viewWidth){
                        textX = -textWidth;
                    }*/
       /*         }
            }else {
                //文字的宽度小于母控件的宽度时，静态显示文字
                textX = (viewWidth-textWidth)/2;
                draw(textX, textY);
            }*/
            //递归调用scrollthread线程
            mHandler.postDelayed(ScrollThread, 5);
        }
    };

    public void setHandler(Handler handler) {
        activityHandler = handler;
    }
}
