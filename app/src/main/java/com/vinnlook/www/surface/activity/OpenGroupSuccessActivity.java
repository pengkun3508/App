package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.OpenGroupPresenter;
import com.vinnlook.www.surface.mvp.view.OpenGroupView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SavePhoto;
import com.vinnlook.www.utils.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:开团成功页面
 * @Time:2021/5/11$
 * @Author:pk$
 */
public class OpenGroupSuccessActivity extends BaseActivity<OpenGroupPresenter> implements OpenGroupView {
    static String orderId;
    public PopupWindow popupwindow;
    public PopupWindow popupwindow1;
    int endTime;
    @BindView(R.id.group_success_hour)
    RoundTextView groupSuccessHour;
    @BindView(R.id.group_success_min)
    RoundTextView groupSuccessMin;
    @BindView(R.id.group_success_seconds)
    RoundTextView groupSuccessSeconds;
    @BindView(R.id.group_success_yaoqing)
    TextView groupSuccessYaoqing;
    @BindView(R.id.group_success_order)
    TextView groupSuccessOrder;
    @BindView(R.id.group_success_home)
    TextView groupSuccessHome;
    @BindView(R.id.wx_py_btn)
    LinearLayout wxPyBtn;
    @BindView(R.id.wx_pyq_btn)
    LinearLayout wxPyqBtn;
    @BindView(R.id.wx_qrcode_btn)
    LinearLayout wxQrcodeBtn;
    @BindView(R.id.copy_btn)
    LinearLayout copyBtn;
    @BindView(R.id.group_success_peo)
    TextView groupSuccessPeo;
    @BindView(R.id.group_success_clear)
    LinearLayout groupSuccessClear;
    @BindView(R.id.group_success_layout)
    LinearLayout groupSuccessLayout;
    @BindView(R.id.group_success_peo2)
    TextView groupSuccessPeo2;
    GroupDetailsBean groupDetailsBean;
    Bitmap bitmaps; //二维码图片;

    SendMessageToWX.Req req;
    WXMediaMessage msg;
    String url;
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            endTime = endTime - 1;
            long days = endTime / (60 * 60 * 24);
            long hours1 = endTime % (60 * 60 * 24) / (60 * 60);
            long minutess1 = endTime % (60 * 60) / 60;
            long secondss1 = endTime % 60;

            String dayss;
            String hourss;
            String minutess;
            String secondss;
            if (days < 10) {
                dayss = "0" + days;
            } else {
                dayss = String.valueOf(days);
            }

            if (hours1 < 10) {
                hourss = "0" + hours1;
            } else {
                hourss = String.valueOf(hours1);
            }

            if (minutess1 < 10) {
                minutess = "0" + minutess1;
            } else {
                minutess = String.valueOf(minutess1);
            }

            if (secondss1 < 10) {
                secondss = "0" + secondss1;
            } else {
                secondss = String.valueOf(secondss1);
            }

//            Log.e("团", "倒计时===hourss===" + hourss);
//            Log.e("团", "倒计时===minutess===" + minutess);
//            Log.e("团", "倒计时===secondss===" + secondss);
            groupSuccessHour.setText(hourss);
            groupSuccessMin.setText(minutess);
            groupSuccessSeconds.setText(secondss);

            handler1.removeMessages(1);
            handler1.sendEmptyMessageDelayed(1, 1000);
            if (endTime <= 0) {
                handler1.removeCallbacksAndMessages(null);
            }
        }
    };

    public static void startSelf(Context context, String orderIds) {
        Intent intent = new Intent(context, OpenGroupSuccessActivity.class);
        context.startActivity(intent);
        orderId = orderIds;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    @Override
    protected OpenGroupPresenter initPresenter() {
        return new OpenGroupPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_group;
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);


    }

    @Override
    protected void loadData() {
        presenter.getGroupInfoData(orderId);

    }

    @OnClick({R.id.group_success_yaoqing, R.id.group_success_order, R.id.group_success_home, R.id.wx_py_btn, R.id.wx_pyq_btn, R.id.wx_qrcode_btn, R.id.copy_btn, R.id.group_success_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_success_yaoqing://邀请好友
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    if (groupDetailsBean != null) {
                        initmPopupWindowView();
                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                }
                break;
            case R.id.group_success_order://查看订单
                CacheActivity.finishActivity();
                GroupListActivity.startSelf(this, 0);
                break;
            case R.id.group_success_home://回到首页
                new MainHomeActivityEvent("1").post();
                CacheActivity.finishActivity();
                break;
            case R.id.wx_py_btn://微信好友
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    //构造一个Req
//                    req = new SendMessageToWX.Req();
//                    req.transaction = buildTransaction("webpage");
//                    req.message = msg;
                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
                break;
            case R.id.wx_pyq_btn://朋友圈
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    //构造一个Req
//                    req = new SendMessageToWX.Req();
//                    req.transaction = buildTransaction("webpage");
//                    req.message = msg;
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
                break;
            case R.id.wx_qrcode_btn://二维码
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        return;
                    } else {
                        initmPopupWindowView1(bitmaps);
                        popupwindow1.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
                break;
            case R.id.copy_btn://复制
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(OpenGroupSuccessActivity.this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.group_success_clear://关闭
                groupSuccessLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //下载数据成功
    @Override
    public void getGroupDetailsSuccess(int code, GroupDetailsBean data) {

        groupDetailsBean = data;

        groupSuccessPeo.setText(String.valueOf(data.getAgain_invite()));
        groupSuccessPeo2.setText(String.valueOf(data.getAgain_invite()) + "人");

        //计算秒杀倒计时---ms
        endTime = Integer.valueOf(data.getSurplus_time());
        handler1.sendEmptyMessageDelayed(1, 1000);

        StringBuilder sb = new StringBuilder();
        String[] asdas = groupDetailsBean.getSearch_attr().split("\\|");
        sb.append(asdas[0] + "_");
        sb.append(asdas[1]);
        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接
        url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接

//        webpage.webpageUrl = "http://h5.jealook.com/test/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接
//        url = "http://h5.jealook.com/test/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接

        WXMediaMessage msg = new WXMediaMessage(webpage);
//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                msg.title = "拼团";
                msg.description = groupDetailsBean.getGoods_name();
                Bitmap thumbBmp = null;
                try {
                    thumbBmp = BitmapFactory.decodeStream(new URL(groupDetailsBean.getGoods_thumb()).openStream());

                    //二维码图片
                    bitmaps = BitmapFactory.decodeStream(new URL(groupDetailsBean.getShare_code()).openStream());
                    Log.e("分享二维码", "bitmaps====" + bitmaps);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //商品图片
                Bitmap thumbBmps = Bitmap.createScaledBitmap(thumbBmp, 150, 150, true);
                thumbBmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmps, true);

            }
        }).start();

        //构造一个Req
        req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


    }

    @Override
    public void getGroupDetailsFail(int code, String msg) {

    }

    //分享
    private void initmPopupWindowView() {
        LinearLayout wx_py_btn, wx_pyq_btn, copy_btn, wx_qrcode_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_layout, null, false);
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//好友
        wx_pyq_btn = customView.findViewById(R.id.wx_pyq_btn);//朋友圈
        share_cancel_btn = customView.findViewById(R.id.share_cancel_btn);//取消
        copy_btn = customView.findViewById(R.id.copy_btn);//复制链接
        wx_qrcode_btn = customView.findViewById(R.id.wx_qrcode_btn);//分享二维码

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        StringBuilder sb = new StringBuilder();
        String[] asdas = groupDetailsBean.getSearch_attr().split("\\|");
        sb.append(asdas[0] + "_");
        sb.append(asdas[1]);
        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接
        url = "http://h5.vinnlook.com/detail-share/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接

//        webpage.webpageUrl = "http://h5.jealook.com/test/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接
//        url = "http://h5.jealook.com/test/index.html?good_id=" + groupDetailsBean.getGoods_id() + "&search_attr=" + sb.toString() + "&channel=" + Build.MANUFACTURER + "&group_id=" + groupDetailsBean.getGroup_id() + "&is_group=1";//分享商品链接

        msg = new WXMediaMessage(webpage);
//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                msg.title = "拼团";
                msg.description = groupDetailsBean.getGoods_name();
                Bitmap thumbBmp = null;
                try {
                    thumbBmp = BitmapFactory.decodeStream(new URL(groupDetailsBean.getGoods_thumb()).openStream());

                    //二维码图片
                    bitmaps = BitmapFactory.decodeStream(new URL(groupDetailsBean.getShare_code()).openStream());
                    Log.e("分享二维码", "bitmaps====" + bitmaps);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //商品图片
                Bitmap thumbBmps = Bitmap.createScaledBitmap(thumbBmp, 150, 150, true);
                thumbBmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmps, true);

            }
        }).start();

        //构造一个Req
        req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;


        //微信好友
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
            }
        });
        //微信朋友圈
        wx_pyq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }
                }
            }
        });
        //分享二维码
        wx_qrcode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        return;
                    } else {
                        initmPopupWindowView1(bitmaps);
                        popupwindow1.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                    if (popupwindow != null && popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        popupwindow = null;
                    }

                }


            }
        });

        //复制
        copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(url);
                Toast.makeText(OpenGroupSuccessActivity.this, "复制成功", Toast.LENGTH_LONG).show();
            }
        });
        //取消
        share_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
    }

    //分享二维码
    private void initmPopupWindowView1(Bitmap coreBmps) {
        ImageView core_img;
        LinearLayout wx_hy_btn, wx_py_btn, save_core_btn;

        TextView share_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.share_2_layout, null, false);
        core_img = customView.findViewById(R.id.core_img);//二维码
        wx_hy_btn = customView.findViewById(R.id.wx_hy_btn);//好友
        wx_py_btn = customView.findViewById(R.id.wx_py_btn);//朋友圈
        save_core_btn = customView.findViewById(R.id.save_core_btn);//保存到相册

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow1 = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow1 != null && popupwindow1.isShowing()) {
                    popupwindow1.dismiss();
                    popupwindow1 = null;
                }
                return false;
            }
        });
        Log.e("分享二维码", "coreBmps====" + coreBmps);
        ImageLoader.image(this, core_img, groupDetailsBean.getShare_code());


        //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(coreBmps);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(coreBmps, 500, 500, true);
        msg.thumbData = bmpToByteArray(coreBmps, true);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;


        //微信好友
        wx_hy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {

                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //朋友圈
        wx_py_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getwxApi().isWXAppInstalled()) {
                    Toast.makeText(getActivity(), "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享通道
                    req.userOpenId = UserUtils.getInstance().getUserId();//分享人的ID
                    App.getwxApi().sendReq(req);

                    if (popupwindow1 != null && popupwindow1.isShowing()) {
                        popupwindow1.dismiss();
                        popupwindow1 = null;
                    }
                }
            }
        });

        //保存到相册
        save_core_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        "android.permission.READ_EXTERNAL_STORAGE",
                        "android.permission.WRITE_EXTERNAL_STORAGE"};
                //检测是否有写的权限
                int permission = ContextCompat.checkSelfPermission(OpenGroupSuccessActivity.this,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(OpenGroupSuccessActivity.this, PERMISSIONS, 1);
                }
                try {
                    //创建savephoto类保存图片
                    SavePhoto savePhoto = new SavePhoto(OpenGroupSuccessActivity.this);
                    savePhoto.SaveBitmapFromView(core_img);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (popupwindow1 != null && popupwindow1.isShowing()) {
                    popupwindow1.dismiss();
                    popupwindow1 = null;
                }
            }
        });


    }


}
