package com.vinnlook.www.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dm.lib.utils.LogUtils;
import com.vinnlook.www.base.App;


/**
 * 描述：定位
 *
 * @author Cuizhen
 * @date 2018/9/13
 */
public class LocationUtils {

    private final OnLocationListener mOnLocationListener;

    private static final int SUCCESS = 0;
    private static final int FAIL = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    break;
                case SUCCESS:
                    AMapLocation amapLocation = (AMapLocation) msg.obj;
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    amapLocation.getFloor();//获取当前室内定位的楼层
                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    mOnLocationListener.onLocationSuccess(amapLocation);
                    break;
                case FAIL:
                    String info = (String) msg.obj;
                    mOnLocationListener.onLocationFail(info);
                    break;
            }
        }
    };

    //声明定位回调监听器
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            LogUtils.i("LocationUtils", "onLocationChanged");
            if (amapLocation != null) {
                LogUtils.i("LocationUtils", "amapLocation != null");
                if (amapLocation.getErrorCode() == 0) {
                    LogUtils.i("LocationUtils", "amapLocation.getErrorCode() == 0");
                    Message msg = handler.obtainMessage();
                    msg.what = SUCCESS;
                    msg.obj = amapLocation;
                    handler.sendMessage(msg);
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = FAIL;
                    msg.obj = amapLocation.getErrorInfo();
                    handler.sendMessage(msg);
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    LogUtils.e("LocationUtils", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    private LocationUtils(OnLocationListener onLocationListener) {
        mOnLocationListener = onLocationListener;
        AMapLocationClient locationClient = new AMapLocationClient(App.getAppContext());
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        locationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        locationOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        locationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        locationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        locationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        locationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        locationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        locationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        locationClient.setLocationListener(mLocationListener);
        locationOption.setMockEnable(true);
        locationClient.setLocationOption(locationOption);
        locationClient.startLocation();
        LogUtils.i("LocationUtils", "startLocation");
    }

    public static LocationUtils start(@NonNull OnLocationListener onLocationListener) {
        return new LocationUtils(onLocationListener);
    }

    public interface OnLocationListener {
        void onLocationSuccess(AMapLocation amapLocation);

        void onLocationFail(String msg);
    }

}
