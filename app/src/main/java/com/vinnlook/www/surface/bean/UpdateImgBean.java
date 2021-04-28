package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/27$
 * @Author:pk$
 */
public class UpdateImgBean extends BaseBean {
    /**
     * short_img : /app_img/20200527/20200527093144_88344.png
     * long_img : http://img.vinnlook.com/app_img/20200527/20200527093144_88344.png
     */

    private String short_img;
    private String long_img;

    public String getShort_img() {
        return short_img;
    }

    public void setShort_img(String short_img) {
        this.short_img = short_img;
    }

    public String getLong_img() {
        return long_img;
    }

    public void setLong_img(String long_img) {
        this.long_img = long_img;
    }
}
