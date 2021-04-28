package com.vinnlook.www.base;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.dm.lib.core.common.BaseApp;
import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.Utils;
import com.dm.lib.utils.https.HttpsCompat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.http.RxHttpRequestSetting;
import com.vinnlook.www.jpush.JPushHelper;
import com.vinnlook.www.utils.PreferenceHelper;
import com.vinnlook.www.utils.SharedPreferencesUtil;
import com.vinnlook.www.utils.UMInit;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import per.goweii.rxhttp.core.RxHttp;


/**
 * 描述：
 *
 * @author Yanbo
 * @date 2018/9/27
 */
public class App extends BaseApp {
    private static final String TAG = "Init";
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static String getDeviceId;
    SharedPreferencesUtil sharedPreferencesUtil;
    public static App applicationContext;

    public static IWXAPI api;

    static {
//        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉可以刷新";
//        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
//        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
//        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
//        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
//        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
//        ClassicsHeader.REFRESH_HEADER_SECONDARY = "释放进入二楼";
//        ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm";
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsHeader(context);
            }
        });
//        ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";
//        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";
//        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新...";
//        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载...";
//        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成";
//        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败";
//        ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static App getInstance() {
        // TODO Auto-generated method stub
        return applicationContext;
    }

    public static IWXAPI getwxApi() {
        // TODO Auto-generated method stub
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;

        Utils.init(this);
        initJPush();
        UMInit.init(this);
        initRxHttp();
        initCloudChannel(this);
        sharedPreferencesUtil = new SharedPreferencesUtil(this);
//        startServer();
//        initMobileAnalyticsSDK();//阿里移动数据分析

        api = WXAPIFactory.createWXAPI(this, null);
        //将应用的appid注册到微信
        api.registerApp(Constant.WECHAT_APPID);

        startServer();

    }

    /**
     * 阿里移动数据分析
     */
    private void initMobileAnalyticsSDK() {
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志，线上版本建议关闭
        manService.getMANAnalytics().turnOnDebug();
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作(如需关闭crash report，建议在init方法调用前关闭crash),详见文档5.4
        manService.getMANAnalytics().turnOffCrashReporter();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可；1.1.6版本及之后的版本，请在init方法之前调用此方法设置channel.
//        manService.getMANAnalytics().setChannel("某渠道");
        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化，若您采用上述 2.3中"统一接入的方式"，则使用当前init方法即可。
        manService.getMANAnalytics().init(this, getApplicationContext());
        // 通过此接口关闭页面自动打点功能，详见文档4.2
        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定
        // 若在上述两个地方均没有设置appversion，上报的字段默认为null
        manService.getMANAnalytics().setAppVersion(AppInfoUtils.getVersionName());

    }


    /**
     * 进行长连接
     */
    private void startServer() {
        Log.e("进行长连接", "===启动长连接=====");
//        Intent intent = new Intent(this, BackService.class);
//        startService(intent);
    }

    private void initRxHttp() {
        RxHttp.init(getAppContext());
        RxHttp.initRequest(new RxHttpRequestSetting());
        HttpsCompat.enableTls12ForHttpsURLConnection();
    }


    private void initJPush() {
        JPushInterface.init(this);
        JPushHelper.customNotification(this);
        JPushInterface.setDebugMode(true);
        JPushHelper.getId();
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(this);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {

                Log.e("TAG", "==初始化云推送通道==Success===getDeviceId===" + pushService.getDeviceId());

                Log.e("TAG", "==初始化云推送通道==Success===response===" + response);

                getDeviceId = pushService.getDeviceId();
                createNotificationChannel();
                PreferenceHelper.write(applicationContext, Constant.Sharepre_Name, Constant.PUBLIC_PARAM_USER_DEVICE_KEY, getDeviceId);


            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e("TAG", "==初始化云推送通道==Failed==" + errorCode + "\n==errorMessage===" + errorMessage);
                registerPush();
            }
        });
//            MiPushRegister.register(applicationContext, "XIAOMI_ID", "XIAOMI_KEY"); // 初始化小米辅助推送
//        HuaWeiRegister.register(this); // 接入华为辅助推送
//        VivoRegister.register(applicationContext);
//        OppoRegister.register(applicationContext, "OPPO_KEY", "OPPO_SECRET");
//        MeizuRegister.register(applicationContext, "MEIZU_ID", "MEIZU_KEY");
//        GcmRegister.register(applicationContext, "send_id", "application_id"); // 接入FCM/GCM初始化推送
    }

    /**
     * 建立推送通道
     */
    public void registerPush() {
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {

                Log.e("TAG", "==初始化云推送通道==Success=222==getDeviceId===" + pushService.getDeviceId());

                Log.e("TAG", "==初始化云推送通道==Success=222==response===" + response);

                getDeviceId = pushService.getDeviceId();

                createNotificationChannel();
                PreferenceHelper.write(applicationContext, Constant.Sharepre_Name, Constant.PUBLIC_PARAM_USER_DEVICE_KEY, getDeviceId);

            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e("TAG", "==初始化云推送通道=2222===Failed==" + errorCode + "\n=222====errorMessage===" + errorMessage);
                initCloudChannel(applicationContext);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = "1";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            // 桌面Launcher的消息角标
            mChannel.canShowBadge();
            // 设置显示桌面Launcher的消息角标
            mChannel.setShowBadge(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);


        }
    }

    public SharedPreferencesUtil getSharedPreferencesUtil() {
        return sharedPreferencesUtil;
    }

    public void setSharedPreferencesUtil(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }


}


