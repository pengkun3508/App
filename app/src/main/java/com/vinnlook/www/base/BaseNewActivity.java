package com.vinnlook.www.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.umeng.analytics.MobclickAgent;
import com.vinnlook.www.utils.CacheActivity;

/**
 * @Description:
 * @Time:2020/6/22$
 * @Author:pk$
 */
public abstract class BaseNewActivity extends FragmentActivity implements ActivityPageSetting, View.OnClickListener {
    public View mNightView = null;
    public WindowManager mWindowManager;
    public Context context;


    @SuppressLint("SourceLockedOrientationActivity")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle arg0) {

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        context = this;
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //设置状态栏字体颜色
//        statusBarBlackText(false);

        setContent();
        if (getIntentValue()) {
            setupView();
            initView();
            setOnListener();
            setModel();
            //管理activity
            CacheActivity.addActivity(this);
        }
        initFont();
//		toggleTranslucent();
    }


    public int getScreen(boolean flag) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (flag) {
            return metrics.widthPixels;
        } else {
            return metrics.heightPixels;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void night() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        lp.gravity = Gravity.BOTTOM;// 可以自定义显示的位置
        lp.y = 10;
        if (mNightView == null) {
            mNightView = new TextView(this);
            mNightView.setBackgroundColor(0x80000000);
        }
        try {
            mWindowManager.addView(mNightView, lp);
        } catch (Exception ex) {

        }

    }


    //系统修改字体不影响应用字体被修改
    private void initFont() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1.0f;
        resources.updateConfiguration(configuration, null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheActivity.finishSingleActivity(this);
    }

    /**
     * 空实现
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 获取颜色
     *
     * @param resourceId
     * @return
     */
    protected int getColorRes(int resourceId) {
        return ContextCompat.getColor(context, resourceId);
    }


    public void setEnable(View view, boolean enable) {
        view.setEnabled(enable);
        if (enable) {//控件可用
            view.setAlpha(1f);
        } else {//控件不可用
            view.setAlpha(0.42f);
        }
    }

}
