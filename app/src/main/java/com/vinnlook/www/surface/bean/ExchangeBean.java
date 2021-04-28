package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class ExchangeBean extends BaseBean {
    /**
     * points : 900
     * pay_points : 100
     * is_member : 1
     * member_end_time : 1970-01-01
     */

    private String points;
    private String pay_points;
    private int is_member;
    private String member_end_time;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPay_points() {
        return pay_points;
    }

    public void setPay_points(String pay_points) {
        this.pay_points = pay_points;
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
}
