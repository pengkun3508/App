package com.vinnlook.www.surface.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * 长连接服务器
 */
public class BackService extends Service {


    private static final long HEART_BEAT_RATE = 15 * 1000;//每隔15秒进行一次
    private static String WEBSOCKET_HOST_AND_PORT;//可替换为自己的主机名和端口号

    private WebSocket mWebSocket;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("进入==长连接服务器", "=进入==长连接服务器=====");
        WEBSOCKET_HOST_AND_PORT = "https://shop.jealook.com";

        new InitSocketThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class InitSocketThread extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                initSocket();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 初始化socket
        private void initSocket() throws UnknownHostException, IOException {

            OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
            Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
            client.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                    super.onOpen(webSocket, response);
                    mWebSocket = webSocket;
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {//接收消息的回调
                    super.onMessage(webSocket, text);
                    //收到服务器端传过来的消息text
                    Log.e("长连接服务器", "===onMessage111===text==" + text);

//                    sendMsg();//发送消息
                }

                @Override
                public void onMessage(WebSocket webSocket, ByteString bytes) {
                    super.onMessage(webSocket, bytes);
                    Log.e("长连接服务器", "===onMessage222===bytes==" + bytes);
                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    super.onClosing(webSocket, code, reason);
                    Log.e("长连接服务器", "===onClosing==onClosing===reason==" + reason);
                }

                @Override
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    super.onClosed(webSocket, code, reason);

                    Log.e("长连接服务器", "===onClosed===主动关闭==" + webSocket + "==" + code + "==" + reason);
                }


                @Override
                public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                    super.onFailure(webSocket, t, response);

                    Log.e("长连接连接失败的回调", "===onFailure===response==" + "webSocket==" + webSocket + "\n" + "Throwable===" + t + "\n" + "Response===" + response);
                }
            });
            client.dispatcher().executorService().shutdown();
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
            Log.e("长连接服务器", "===长连接服务器===开启心跳检测==");
        }


        private long sendTime = 0L;
        // 发送心跳包
        private Handler mHandler = new Handler();
        private Runnable heartBeatRunnable = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                    Log.e("长连接处于连接状态", "===发送消息====");
                    TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    @SuppressLint("MissingPermission")
                    String szImei = TelephonyMgr.getDeviceId();

                    Log.e("长连接获取Token值", "===szImei====");


                    boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                    Log.e("长连接已断开", "===isSuccess===="+isSuccess);
                    if (!isSuccess) {//长连接已断开
                        mHandler.removeCallbacks(heartBeatRunnable);
                        mWebSocket.cancel();//取消掉以前的长连接
                        Log.e("长连接已断开", "===长连接已断开====");
                        new InitSocketThread().start();//创建一个新的连接
                    } else {//长连接处于连接状态
                        Log.e("长连接处于连接状态", "===长连接处于连接状态====");
                    }

                    boolean isSuccesss = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态

                    if (!isSuccess) {//长连接已断开
                        mHandler.removeCallbacks(heartBeatRunnable);
                        mWebSocket.cancel();//取消掉以前的长连接
                        Log.e("长连接已断开", "===长连接已断开====");
                        new InitSocketThread().start();//创建一个新的连接
                    } else {//长连接处于连接状态
                        Log.e("长连接处于连接状态", "===长连接处于连接状态====");
                    }

                    sendTime = System.currentTimeMillis();
                }
                mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
            }
        };


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
    }


//    /**
//     * 发送消息
//     *
//     * @param
//     */
//    public void sendMsg() {
//        JSONObject objects = new JSONObject();
//        try {
//            objects.put("sessionId", MyApplication.getInstance().getSharedPreferencesUtil().getString(Constants.SOCKET_SESSIONID, ""));
//            objects.put("userUuid", MyApplication.getInstance().getSharedPreferencesUtil().getString(Constants.USERUUID, ""));
//            objects.put("userType", MyApplication.getInstance().getSharedPreferencesUtil().getString(Constants.MARK, ""));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.e("长连接处于连接状态", "===看看发送几次====" + String.valueOf(objects));
//        mWebSocket.send(String.valueOf(objects));
//
//    }


}

