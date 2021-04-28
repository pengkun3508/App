package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/4/19$
 * @Author:pk$
 */
public class CertifyListBean extends BaseBean {
    /**
     * name : ICP备案号
     * value : 陕ICP备2020015843号-1
     * url :
     */

    private String name;
    private String value;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
