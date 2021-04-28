package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:已领取优惠券
 * @Time:2020/7/23$
 * @Author:pk$
 */
public class AlreadyCouponListBean extends BaseBean {
    /**
     * id : 590
     * order_id : 0
     * coupon_name : envie旧版优惠券
     * use_shop_type : 2
     * reduced : 7.2
     * type : 1
     * min_money : 222.00
     * use_time_type : 1
     * discount_id : 20
     * use_time_start : 2021-01-25 17:03:51
     * use_time_end : 2021-03-31 00:00:00
     * is_used : 0
     * is_use : 0
     */

    private String id;
    private String order_id;
    private String coupon_name;
    private String use_shop_type;
    private String reduced;
    private String type;
    private String min_money;
    private String use_time_type;
    private String discount_id;
    private String use_time_start;
    private String use_time_end;
    private int is_used;
    private int is_use;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getUse_shop_type() {
        return use_shop_type;
    }

    public void setUse_shop_type(String use_shop_type) {
        this.use_shop_type = use_shop_type;
    }

    public String getReduced() {
        return reduced;
    }

    public void setReduced(String reduced) {
        this.reduced = reduced;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(String discount_id) {
        this.discount_id = discount_id;
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

    public int getIs_used() {
        return is_used;
    }

    public void setIs_used(int is_used) {
        this.is_used = is_used;
    }

    public int getIs_use() {
        return is_use;
    }

    public void setIs_use(int is_use) {
        this.is_use = is_use;
    }
}
