package com.vinnlook.www.utils.sp;


import com.dm.lib.utils.SPUtils;
import com.vinnlook.www.common.Config;

/**
 * @author Cuizhen
 * @date 2018/6/30-上午10:30
 */
public class SPVideoSettingUtils {

    private static final String KEY_SHOW = "KEY_SHOW";
    private static final String KEY_AUTO = "KEY_AUTO";
    private static final String KEY_MODE = "KEY_MODE";
    private static final String KEY_TYPE = "KEY_TYPE";
    private static final String KEY_CODEC = "KEY_CODEC";
    private static final String KEY_CODEC_TEXTURE = "KEY_CODEC_TEXTURE";
    private static final String KEY_CACHE = "KEY_CACHE";
    private static final String KEY_TOAST = "KEY_TOAST";
    private static final String KEY_HTTPS = "KEY_HTTPS";
    private static final String KEY_SEEK_RATIO = "KEY_SEEK_RATIO";

    private SPUtils sp;

    private SPVideoSettingUtils() {
        sp = SPUtils.newInstance("video_setting");
    }

    public static SPVideoSettingUtils instance() {
        return new SPVideoSettingUtils();
    }

    public boolean isShow() {
        return sp.get(KEY_SHOW, false);
    }

    public void saveShow(boolean show) {
        sp.save(KEY_SHOW, show);
    }

    public boolean isAuto() {
        return sp.get(KEY_AUTO, Config.GSY_VIDEO_PLAYER_AUTO);
    }

    public void saveAuto(boolean mode) {
        sp.save(KEY_AUTO, mode);
    }

    public int getMode() {
        return sp.get(KEY_MODE, Config.GSY_VIDEO_PLAYER_MODE);
    }

    public void saveMode(int mode) {
        sp.save(KEY_MODE, mode);
    }

    public int getType() {
        return sp.get(KEY_TYPE, Config.GSY_VIDEO_PLAYER_TYPE);
    }

    public void saveType(int type) {
        sp.save(KEY_TYPE, type);
    }

    public boolean isCodec(){
        return sp.get(KEY_CODEC, Config.GSY_VIDEO_PLAYER_ENABLE_CODEC);
    }

    public void saveCodec(boolean enable){
        sp.save(KEY_CODEC, enable);
    }

    public boolean isCodecTexture(){
        return sp.get(KEY_CODEC_TEXTURE, Config.GSY_VIDEO_PLAYER_ENABLE_CODEC_TEXTURE);
    }

    public void saveCodecTexture(boolean enable){
        sp.save(KEY_CODEC_TEXTURE, enable);
    }

    public boolean isCache(){
        return sp.get(KEY_CACHE, Config.GSY_VIDEO_PLAYER_ENABLE_CACHE);
    }

    public void saveCache(boolean enable){
        sp.save(KEY_CACHE, enable);
    }

    public boolean isHttps(){
        return sp.get(KEY_HTTPS, Config.GSY_VIDEO_PLAYER_ENABLE_HTTPS);
    }

    public void saveHttps(boolean enable){
        sp.save(KEY_HTTPS, enable);
    }

    public boolean isToast(){
        return sp.get(KEY_TOAST, Config.GSY_VIDEO_PLAYER_ENABLE_TOAST);
    }

    public void saveToast(boolean enable){
        sp.save(KEY_TOAST, enable);
    }

    public float getSeekRatio(){
        return sp.get(KEY_SEEK_RATIO, Config.GSY_VIDEO_PLAYER_SEEK_RATIO);
    }

    public void saveSeekRatio(float ratio){
        sp.save(KEY_SEEK_RATIO, ratio);
    }
}
