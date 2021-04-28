package com.vinnlook.www.surface;

import com.luck.picture.lib.entity.LocalMedia;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 18/9/10
 */
public class PictureSelectorBean {

   private LocalMedia localMedia;

   private String imagUrl;

   private int upDataType=0;

    public LocalMedia getLocalMedia() {
        return localMedia;
    }

    public void setLocalMedia(LocalMedia localMedia) {
        this.localMedia = localMedia;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public int getUpDataType() {
        return upDataType;
    }

    public void setUpDataType(int upDataType) {
        this.upDataType = upDataType;
    }
}
