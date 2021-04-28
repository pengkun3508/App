package com.vinnlook.www.http.model;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @author damai
 * @date 2018/6/19
 */
public class VersionBean extends BaseBean {
    /**
     * version : 1.0.0
     * code : 1
     * url : http://api.vinnlook.com/customs/index/index
     * img_url : test
     * must : null
     */

    private String version;
    private String code;
    private String url;
    private String img_url;
    private int must;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setImgUrl(String description) {
        this.img_url = description;
    }

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }
}
