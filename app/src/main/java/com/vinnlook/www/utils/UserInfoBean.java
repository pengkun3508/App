package com.vinnlook.www.utils;

import java.io.Serializable;

/**
     * @Description:用户信息
     * @Time:2020/5/9 14:07
     * @Author:pk
     */
public class UserInfoBean implements Serializable {
    /**
     * user_id : 340
     * user_name : A-余情未了😂
     * img_url : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKMuRmicBAI2YvBeftn3WtU4RMQTrJ1VB9S4dVjbFz1W1QVOG9YdZ6G0ibhiaHRYWw1XTZVHkss088Pg/132
     * sex :
     * is_member : 0
     * member_end_time : 1970-01-01
     * mobile :
     */

    private String user_id;
    private String user_name;
    private String img_url;
    private String sex;
    private int is_member;
    private String member_end_time;
    private String mobile;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIs_member() {
        return is_member;
    }

    public void setIs_member(int is_member) {
        this.is_member = is_member;
    }

    public String getMember_end_time() {
        return member_end_time;
    }

    public void setMember_end_time(String member_end_time) {
        this.member_end_time = member_end_time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
