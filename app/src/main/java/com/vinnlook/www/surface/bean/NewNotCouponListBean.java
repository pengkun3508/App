package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/2/19$
 * @Author:pk$
 */
public class NewNotCouponListBean extends BaseBean {
    /**
     * id : 20
     * coupon_name : envie旧版优惠券
     * type : 1
     * reduced : 7.2
     * min_money : 222.00
     * use_time_type : 1
     * use_time : 0
     * use_time_start : 2021-01-25 17:03:51
     * use_time_end : 2021-03-31 00:00:00
     * astrict_count : 0
     * is_member : 0
     * use_shop_type : 2
     * content :
     * receive_count : -1
     */

    private String id;
    private String coupon_name;
    private String type;
    private String reduced;
    private String min_money;
    private String use_time_type;
    private String use_time;
    private String use_time_start;
    private String use_time_end;
    private String astrict_count;
    private String is_member;
    private String use_shop_type;
    private String content;
    private String receive_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReduced() {
        return reduced;
    }

    public void setReduced(String reduced) {
        this.reduced = reduced;
    }

    public String getMin_money() {
        return min_money;
    }

    public void setMin_money(String min_money) {
        this.min_money = min_money;
    }

    public String getUse_time_type() {
        return use_time_type;
    }

    public void setUse_time_type(String use_time_type) {
        this.use_time_type = use_time_type;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public String getUse_time_start() {
        return use_time_start;
    }

    public void setUse_time_start(String use_time_start) {
        this.use_time_start = use_time_start;
    }

    public String getUse_time_end() {
        return use_time_end;
    }

    public void setUse_time_end(String use_time_end) {
        this.use_time_end = use_time_end;
    }

    public String getAstrict_count() {
        return astrict_count;
    }

    public void setAstrict_count(String astrict_count) {
        this.astrict_count = astrict_count;
    }

    public String getIs_member() {
        return is_member;
    }

    public void setIs_member(String is_member) {
        this.is_member = is_member;
    }

    public String getUse_shop_type() {
        return use_shop_type;
    }

    public void setUse_shop_type(String use_shop_type) {
        this.use_shop_type = use_shop_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceive_count() {
        return receive_count;
    }

    public void setReceive_count(String receive_count) {
        this.receive_count = receive_count;
    }
}