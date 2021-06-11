package com.vinnlook.www.common;

import android.os.Environment;

/**
 * 可变的参数
 * 发布新版本时注意检查一下下面几个参数的配置
 * {@link #HTTP_HOST}
 * {@link #SHOW_WELCOME_BELOW_VERSION_CODE}
 *
 * @author Yanbo
 */
public interface Config {


    /**
     * 是否显示欢迎页
     * 低于这个版本号则显示欢迎页，也就是在这个版本变更的欢迎页图片
     * 如果低于这个版本这表示用户没有看到过这个欢迎页
     */
    int SHOW_WELCOME_BELOW_VERSION_CODE = 300;

    /**
     * 网络请求延时
     */
    int HTTP_TIMEOUT = 5000;

    /**
     * 域名
     */

    /**
     * 正式的
     * http://hszyapi.35g.cn/
     * http://192.168.2.239:20002/
     */
//    String HTTP_HOST = "http://192.168.2.239:20002/";
    String HTTP_HOST = "http://shop.jealook.com/";

//    String HTTP_HOST = "http://v6.vinnlook.com/";


    /**
     * api版本
     */
//    String HTTP_VERSION = "v1/";
//    String HTTP_VERSION = "v2/";
//    String HTTP_VERSION = "v3/";
//    String HTTP_VERSION = "v4/";
//    String HTTP_VERSION = "v5/";
    String HTTP_VERSION = "v6/";
//    String HTTP_VERSION = "v7/";
//    String HTTP_VERSION = "develop/";


    /**
     * 软件版本更新同一版本最大忽略时间
     */
    long APP_UPDATE_IGNORE_TIME_MAX_MILL = 1 * 24 * 60 * 60 * 1000;


    /**
     * 弹窗默认动画时长（毫秒）
     */
    int DIALOG_ANIM_DURATION = 350;

    /**
     * 按钮不可点击时的透明度
     */
    float BUTTON_UN_ENABLE_ALPHA = 0.6f;


    /**
     * 启动页时间
     */
    int START_ACTIVITY_DELAY = 1000;

    /**
     * 列表第一页序号
     */
    int LIST_FIRST_PAGE_INDEX = 1;

    /**
     * 列表一页默认条目数
     */
    int ONE_PAGE_ITEM_MAX_COUNT_DEFAULT = 10;

    /**
     * 是否启用列表惯性滑动到底部时自动加载更多
     */
    boolean REFRESH_AUTO_LOAD_MORE_ENABLE = false;

    /**
     * 下拉刷新和上拉加载是否启用越界回弹
     */
    boolean REFRESH_OVER_SCROLL_BOUNCE_ENABLE = true;
    /**
     * 下拉刷新和上拉加载的超时时长
     */
    int REFRESH_AND_LOAD_MORE_DELAY = HTTP_TIMEOUT;

    /**
     * 下载文件的路径
     */
    String DOWNLOAD_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hszy";


    /****************************************************************************
     ********************    以下为图片压缩相关配置的默认值     **********************
     ********************      **********************     **********************
     ****************************************************************************/

    /**
     * 上传普通图片默认尺寸
     */
    int IMAGE_UPLOAD_SIZE = 1080;

    /**
     * 图片上传文件后缀名（不要更改，应该为合法的图片后缀）
     */
    String IMG_UPLOAD_NAME_EXTENSION = ".jpg";

    /**
     * 图片file压缩缓存目录名
     */
    String IMG_COMPRESS_CACHE_DIR_NAME = "img_compress";

    /****************************************************************************
     ********************    以下为视频播放相关配置的默认值     **********************
     ********************     勿修改，除非你知道改的是什么      **********************
     ****************************************************************************/

    /**
     * 是否在设置界面显示播放设置
     * 开启后默认仍然是不显示的，需要在关于我们界面连击版本号10次才能开启
     * 若果此项为false则连击版本号的开启失效
     */
    boolean GSY_VIDEO_SETTING_SHOW = true;

    /**
     * 自动切换播放模式
     */
    boolean GSY_VIDEO_PLAYER_AUTO = false;

    /**
     * 播放模式
     * 0--Ijk   1--Exo2   2--System
     */
    int GSY_VIDEO_PLAYER_MODE = 0;
    String[] GSY_VIDEO_PLAYER_MODE_NAME = new String[]{
            "Ijk", "Exo2", "System"
    };

    /**
     * 渲染模式
     * 0--Texture   1--Surface   2--GlSurface
     */
    int GSY_VIDEO_PLAYER_TYPE = 0;
    String[] GSY_VIDEO_PLAYER_TYPE_NAME = new String[]{
            "Texture", "Surface", "GLSurface"
    };

    /**
     * 硬解码
     */
    boolean GSY_VIDEO_PLAYER_ENABLE_CODEC = false;

    /**
     * 硬解码渲染优化
     */
    boolean GSY_VIDEO_PLAYER_ENABLE_CODEC_TEXTURE = false;

    /**
     * 视频缓存
     */
    boolean GSY_VIDEO_PLAYER_ENABLE_CACHE = false;

    /**
     * 强制替换链接为HTTPS
     * http的视频在android9.0无法播放，需要开启此项
     */
    boolean GSY_VIDEO_PLAYER_ENABLE_HTTPS = true;

    /**
     * 吐司提示报错信息
     */
    boolean GSY_VIDEO_PLAYER_ENABLE_TOAST = false;

    /**
     * 视频播放触摸滑动快进的比例
     * 默认1。数值越大，滑动的产生的seek越小
     */
    float GSY_VIDEO_PLAYER_SEEK_RATIO = 10;


}