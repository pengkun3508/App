package com.vinnlook.www.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vinnlook.www.event.ActivityStartEvent;
import com.vinnlook.www.eventbas.GlobalMsgEvent;
import com.vinnlook.www.utils.CacheActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Description:
 * @Time:2020/4/20$
 * @Author:pk$
 */
public class BaseActivity_1 extends AppCompatActivity {

    //    MProgressDialog mMProgressDialog;
    protected static final String EXTRA_BUNDLE = "bundle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CacheActivity.addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = this.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = this.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
        super.onCreate(savedInstanceState);

    }

//    /**
//     * 显示进度对话框
//     *
//     * @param msg 对话框的文案信息
//     */
//    public void showProgressDialog(String msg) {
//        if (mMProgressDialog == null) {
//            MProgressDialog.showProgress(this);
//
////            mMProgressDialog = new MProgressDialog();
////
////
////                    .isCanceledOnTouchOutside(false)
////                    .setCornerRadius(20)
////                    .setProgressWidth(3)
////                    .setStrokeWidth(2)
////                    .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
////                        @Override
////                        public void dismiss() {
////                            //关闭监听
////                        }
////                    })
////                    .build();
////
////            mMProgressDialog.show(msg);
//        }
//    }


//    /**
//     * 隐藏正在显示的进度对话框
//     */
//    public void dismissProgressDialog() {
////        if (mMProgressDialog != null ) {
//        MProgressDialog.dismissProgress();
////            mMProgressDialog.dismiss();
////        }
//    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
//        EventBus.getDefault().register(this);
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if(!EventBus.getDefault().isRegistered(this)){//加上判断
//            EventBus.getDefault().unregister(this);
//        }
        EventBus.getDefault().unregister(this);
//        MobclickAgent.onPause(this);
    }

    /**
     * 接收到EventBus发送的事件，处理UI消息提示的显示
     *
     * @param event 包含了提示信息和显示方式的EventBus事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGlobalMsg(GlobalMsgEvent event) {
//        dismissProgressDialog();
        if (event.getDisplayType() == GlobalMsgEvent.DisplayType.Toast) {
            Toast.makeText(this, "" + event.getMsg(), Toast.LENGTH_SHORT).show();
        } else if (event.getDisplayType() == GlobalMsgEvent.DisplayType.Dialog) {
            if (event.isCloseDialog()) {
//                dismissAlertDialog();
            } else if (event.getOnPositiveClick() != null) {
//                showAlertDialog(event.getMsg(), event.getOnPositiveClick(), event.isCancelable());
            } else {
//                showAlertDialog(event.getMsg());
            }
        } else if (event.getDisplayType() == GlobalMsgEvent.DisplayType.ProgressDialog) {
            if (event.isCloseDialog()) {
//                FitnsApp.getInstance().dismissProgressBar();
//                dismissProgressDialog();
            } else {
//                showProgressDialog(event.getMsg());
//                FitnsApp.getInstance().showProgressBar();
            }
        }

    }

    /**
     * 接收到EventBus事件处理Activity页面跳转
     *
     * @param event 包含了页面跳转参数的EventBus事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityStartEvent(ActivityStartEvent event) {
        Log.i("TAG", "class:" + event.getTargetActivityCls());
        if (event == null) {
            //do nothing
            return;
        }
        //启动新活动
        if (event.getTargetActivityCls() != null) {
            Intent intent = new Intent(this, event.getTargetActivityCls());
//            if (FitnsApp.getInstance().isShouldRecordActivity()) {
//                AppManager.getAppManager().addActivity(event.getActivity());
//            }
            if (event.getIntentFlags() > 0) {
                intent.setFlags(event.getIntentFlags());
            }
            if (event.getBundle() != null) {
                intent.putExtra(EXTRA_BUNDLE, event.getBundle());
            }
            if (event.getRequestCode() > 0) {
                startActivityForResult(intent, event.getRequestCode());
            } else {
                startActivity(intent);
            }
        }
        //关闭当前活动
        if (event.isFinishCurrentActivity()) {
            if (event.getResult() == RESULT_OK) {
                setResult(RESULT_OK);
            }
            finish();
        }
    }

    //这个方法给eventbus初始化找到公用的回调方法，不然报错
    @Subscribe
    public void onEvent(String event) {
    }

//    /**
//     * 登出的接收
//     *
//     * @param logoutEvent
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void logOut(LogoutEvent logoutEvent) {
//
//        if (logoutEvent.getNeedLogout() ) {
//
//            android.util.Log.e("app下线通知", "logOut---" );
//
//            FitnsApp.getInstance().clearToken();
//            FitnsApp.getInstance().clearUserId();
//            FitnsApp.getInstance().clearUserPhone();
//
//            new ActivityStartEvent()
//                    .setFinishCurrentActivity(true)
//                    .setTargetActivityCls(LoginActivity.class)
//                    .send();
//
//        }
//    }

}
