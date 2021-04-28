package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.utils.DisplayInfoUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.dm.lib.utils.timer.MillisTimer;
import com.m7.imkfsdk.utils.PickUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Config;
import com.vinnlook.www.surface.adapter.WelcomePagerAdapter;
import com.vinnlook.www.surface.mvp.presenter.StartPresenter;
import com.vinnlook.www.surface.mvp.view.StartView;
import com.vinnlook.www.utils.sp.SPStartUtils;

import butterknife.BindView;


/**
 * 启动页 欢迎页
 * <p>
 * 显示逻辑为：
 * 1、判断是否为第一次启动
 * 2、第一次启动先显示欢迎页
 * 3、欢迎页结束后或者不是第一次启动，显示logo动画并开始启动倒计时，时长为{@link Config#START_ACTIVITY_DELAY}
 * <p>
 * 数据预加载：
 * 1、判断登录状态。直接初始化本地登录状态，切换首页显示效果（暂未实现）
 * 2、请求首页第一页的数据，并预加载图片
 *
 * @author Yanbo
 */
public class StartActivity extends BaseActivity<StartPresenter> implements StartView {

    public static final String TAG = StartActivity.class.getSimpleName();

    private static final long WELCOME_ANIM_DURATION = 500;
    private static final int[] IMAGES = {
            R.mipmap.start_img1,
            R.mipmap.start_img2,
            R.mipmap.start_img3,
            R.mipmap.start_img4
    };

    @BindView(R.id.vp_start_welcome)
    ViewPager vpWelcome;
    @BindView(R.id.vp_start_welcome_1)
    RelativeLayout vp_start_welcome_1;

    private MillisTimer mTimer = null;
    public PopupWindow popupwindow;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initWindow() {
        if (App.findActivity(MainActivity.class) != null) {
            finish();
            return;
        }
        super.initWindow();
        StatusBarUtils.setStatusBarMode(this, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected StartPresenter initPresenter() {
        return new StartPresenter();
    }

    @Override
    public void initView() {
        vp_start_welcome_1.setVisibility(View.VISIBLE);
        vpWelcome.setVisibility(View.GONE);

        //处理权限
        handlePermission();


    }

    @Override
    public void loadData() {
        startTimeCount();
    }

    private void initWelcome() {
        vp_start_welcome_1.setVisibility(View.GONE);
        vpWelcome.setVisibility(View.VISIBLE);
        final WelcomePagerAdapter adapter = new WelcomePagerAdapter(getContext());
        vpWelcome.setAdapter(adapter);
        adapter.setImages(IMAGES);
        adapter.setOnImageViewClickListener(new WelcomePagerAdapter.OnImageViewClickListener() {
            @Override
            public void onClick(int position) {
                if (position < IMAGES.length - 1) {
                    vpWelcome.setCurrentItem(position + 1);
                } else {
                    SPStartUtils.instance().setStarted();
                    startMainActivity();
                }
            }
        });
        doWelcomePagerAnimIn();
    }

    private void doWelcomePagerAnimIn() {
        vpWelcome.setVisibility(View.VISIBLE);
        ObjectAnimator animAIn = ObjectAnimator.ofFloat(vpWelcome, "alpha", 1, 1);
        animAIn.setInterpolator(new DecelerateInterpolator());
        animAIn.setDuration(WELCOME_ANIM_DURATION);
        ObjectAnimator animXIn = ObjectAnimator.ofFloat(vpWelcome, "translationX", DisplayInfoUtils.getInstance().getWidthPixels(), 0);
        animXIn.setInterpolator(new DecelerateInterpolator());
        animXIn.setDuration(WELCOME_ANIM_DURATION);
        animAIn.start();
        animXIn.start();
    }

    private void startTimeCount() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new MillisTimer(Config.START_ACTIVITY_DELAY, 1000);
        mTimer.setOnTimerFinishListener(new MillisTimer.OnTimerFinishListener() {
            @Override
            public void onFinish() {
                boolean isStartFirst = SPStartUtils.instance().isFirst();
                if (isStartFirst) {
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView();
                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
//                    startMainActivity();
                } else {
                    startMainActivity();
                }
            }
        });
        mTimer.start();
    }

    private void cancelTimeCount() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void startMainActivity() {
        cancelTimeCount();
        MainActivity.startSelf(getContext());
        StartActivity.this.finish();
        overridePendingTransition(R.anim.activity_zoom_small_in, R.anim.activity_zoom_small_out);
    }

    @Override
    protected void onDestroy() {
        cancelTimeCount();
        super.onDestroy();
    }


    /**
     * 协议-隐私-弹框
     * getFeeMsg1,getMobile1, getFeeMsg2,getMobile2
     */
    public void initmPopupWindowView() {

        TextView text_content, text_content2, text_content3, text_content4, text_content5, cancel_btn, agree_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.privacy_layout, null, false);
        text_content = customView.findViewById(R.id.text_content);//内容
        text_content2 = customView.findViewById(R.id.text_content2);//内容
        text_content3 = customView.findViewById(R.id.text_content3);//内容
        text_content4 = customView.findViewById(R.id.text_content4);//内容
        text_content5 = customView.findViewById(R.id.text_content5);//内容

        cancel_btn = customView.findViewById(R.id.cancel_btn);//暂不同意
        agree_btn = customView.findViewById(R.id.agree_btn);//同意

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
//        customView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    popupwindow = null;
//                }
//                return false;
//            }
//        });

//        text_content.setText("请您务必审慎阅读，充分理解“用户协议”和“隐私政策”各条款，" +
//                "包括但不限于向您提供一键登录，微信登录，支付等服务，我们需要收集您的设备信息，" +
//                "个人信息。您可阅读" +
//                < font color = 'blue' >《用户协议》 </font > +
//                "和<font color='blue'>《隐私政策》</font>" +
//                "了解详细信息。如您同意，请点击“同意”开始接受我们的服务。");

//        SpannableStringBuilder spannable = new SpannableStringBuilder("请您务必审慎阅读，充分理解“用户协议”和“隐私政策”各条款，" +
//                "包括但不限于向您提供一键登录，微信登录，支付等服务，我们需要收集您的设备信息，" +
//                "个人信息。您可阅读" + "《用户协议》和《隐私政策》了解详细信息。如您同意，请点击“同意”开始接受我们的服务。");
        String courseName = "《用户协议》";
        String courseName2 = "《隐私政策》";

        SpannableString courseSpannable = new SpannableString(courseName);
        SpannableString courseSpannable2 = new SpannableString(courseName2);

        ClickableSpan courseSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
//                Toast.makeText(StartActivity.this, "触发点击事件2222!", Toast.LENGTH_SHORT).show();
                WebActivity.startSelf(StartActivity.this, "http://shop.jealook.com/v1/html/article-info?id=119");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.black));
                ds.setUnderlineText(false);
            }


        };
        ClickableSpan lessonSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

//                Toast.makeText(StartActivity.this, "触发点击事件1111!", Toast.LENGTH_SHORT).show();
                WebActivity.startSelf(StartActivity.this, "http://shop.jealook.com/v1/html/article-info?id=117");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.black));
                ds.setUnderlineText(false);
            }

        };
        courseSpannable.setSpan(courseSpan, 0, courseName.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        courseSpannable2.setSpan(lessonSpan, 0, courseName2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        text_content.append("感谢使用Vinnlook App，根据相关法律规定，为了保护您的隐私与信息安全，请充分阅读");
        text_content.append(courseSpannable);
        text_content.append("与");
        text_content.append(courseSpannable2);
        text_content.append("及以下约定：");
        text_content.setMovementMethod(LinkMovementMethod.getInstance());
        text_content.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明

        text_content2.setText("1.获取手机账号权限，用于系统账号登录及管理；");
        text_content3.setText("2.获取手机信息权限，用于获取设备IMEI标识以提供服务；");
        text_content4.setText("3.获取手机存储权限，用于提供缓存服务。");
        text_content5.setText("点击“同意”即表示您已阅读并同意我们的《用户协议》与《隐私协议》以及约定，如果不同意，将退出软件");

        //同意
        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
//                    initWelcome();
                    SPStartUtils.instance().setStarted();
                    startMainActivity();
                }
            }
        });
        //暂不使用
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    /**
     * 文件写入权限 （初始化需要写入文件，点击在线客服按钮之前需打开文件写入权限）
     * 读取设备 ID 权限 （初始化需要获取用户的设备 ID）
     */
    private void handlePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (PickUtils.PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                PickUtils.PermissionUtils.requestPermissions(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PickUtils.PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(StartActivity.this, com.m7.imkfsdk.R.string.notpermession, Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getActivity(), "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
