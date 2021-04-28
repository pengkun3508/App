package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class UserInfo extends BaseBean {

    /**
     * img_url : http://thirdwx.qlogo.cn/mmopen/vi_32/G8T3U4de9iaPb22cxW2Y05ReqwElxianetEn0FoK7CtbiaDSITsjdUEvkyyVfYOIt5Hld2GrUKEKNrP9l0RFJtaxw/132
     * user_name : 星雨
     * sex : 保密
     * mobile :
     * sex_type : 0
     */

    private String img_url;
    private String user_name;
    private String sex;
    private String mobile;
    private int sex_type;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSex_type() {
        return sex_type;
    }

    public void setSex_type(int sex_type) {
        this.sex_type = sex_type;
    }
}
