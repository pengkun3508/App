package com.vinnlook.www.common;

/**
 * 不可变的常量
 *
 * @author Yanbo
 * @date 2018/8/31
 */
public interface Constant {


    /**
     * 保存本地数据公共参数
     */
    String Sharepre_Name = "vinnlook";

    /**
     * 公共请求头的名字
     */
    String PUBLIC_HEADER_TIME_NAME = "JEALOOK-time";
    String PUBLIC_HEADER_SIGN_NAME = "JEALOOK-sign";
    /**
     * 公共请求参数的键值
     */
    String PUBLIC_PARAM_SYSTEM_KEY = "system";
    String PUBLIC_PARAM_SYSTEM_VALUE = "android";
    String PUBLIC_PARAM_VERSION_KEY = "version";
    String PUBLIC_PARAM_USER_ID_KEY = "user_id";
    String PUBLIC_PARAM_USER_DEVICE_KEY = "user_device";
    String PUBLIC_PARAM_JPUSH_DEVICE_KEY = "jpush_device";
    String PUBLIC_PARAM_DEVICE_ID_KEY = "device_id";//设备ID

    String KEY_SIGN_BEAN = "sign_bean";
    /**
     * 把正式参数和公共请求参数合并为一个参数传入后台（为方便以后的双向加密和解密），这个合并后的参数的KEY值为下
     */
    String PUBLIC_PARAM_KEY = "data";

    /**
     * 友盟
     */
    String WECHAT_APP_ID = " ";
    String WECHAT_APP_SECRET = " ";
    String QQ_APP_ID = " ";
    String QQ_APP_SECRET = " ";


    /**
     * 微信
     */

    //恩贝
//    String WECHAT_APPID = "wx15087974bfc54020";
//    String WECHAT_SECRET = "3eb2db47e792e057c60183999f963151";

    //韦恩
    String WECHAT_APPID = "wx6a9c69dcb128c19f";
    String WECHAT_SECRET = "9d32911a3fc5823cc5f9abd2a78d0f59";


    /**
     * 一键登录
     */
    String LONIG_PHONE_NUMBER_KEY = "FvSp+jOEuV7uNKwZ8+Do/pmDDnMM6DLS83fKGfP/bz6WQXYvO2thkxm+/ATB3Fem47+8ap8MUT8vTcEI80hlxkgEOUMoY1FLZPtLAFgRnQLs07su6nAR/hURkdGa3ZD/uL/LUbWyV0jWmXuUr8n7HG8vXaRZbdxj96o2XIz0gfoJReIIu3tE0JFTrjtfm+TvfnAUjIv0vFoxf2txlcHVxIkqGr01ejJcp8HoZ5orkX0gidEVIRlO9igsVE4OKQfaQ82ts3eCSGMuBuNpEPrj+p3NwTsQPF/ZRxBCNfD5VX5ckMjazegcng==";


}
