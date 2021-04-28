package com.vinnlook.www.http.model;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/11/30
 */
public class SignBean extends BaseBean {
    /**
     * token :
     * ms : 1597131022303
     * time : 1597131022
     * date : 2020-08-11 15:30:22
     * version : {"version":"1.0.1","code":"1","url":"http://admin.vinnlook.com/admin/images/logo.png","img_url":"","must":"3"}
     */

    private String token;
    private String ms;
    private String time;
    private String date;
    private VersionBean version;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

}
