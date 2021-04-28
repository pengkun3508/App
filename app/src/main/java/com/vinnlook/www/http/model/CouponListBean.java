package com.vinnlook.www.http.model;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CouponListBean extends BaseBean {
    /**
     * type_id : 18
     * type_money : 20.00
     * send_type : 1
     * min_amount : 0.00
     * use_start_date : 2020-05-29
     * use_end_date : 2020-06-29
     * min_goods_amount : 100.00
     * type_name : test-1
     * is_pastdue : 1
     * is_has : 1
     * is_use : 0
     */

    private String type_id;
    private String type_money;
    private String send_type;
    private String min_amount;
    private String use_start_date;
    private String use_end_date;
    private String min_goods_amount;
    private String type_name;
    private int is_pastdue;
    private int is_has;
    private int is_used;
    private int is_able;

    public int getIs_able() {
        return is_able;
    }

    public void setIs_able(int is_able) {
        this.is_able = is_able;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_money() {
        return type_money;
    }

    public void setType_money(String type_money) {
        this.type_money = type_money;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public String getUse_start_date() {
        return use_start_date;
    }

    public void setUse_start_date(String use_start_date) {
        this.use_start_date = use_start_date;
    }

    public String getUse_end_date() {
        return use_end_date;
    }

    public void setUse_end_date(String use_end_date) {
        this.use_end_date = use_end_date;
    }

    public String getMin_goods_amount() {
        return min_goods_amount;
    }

    public void setMin_goods_amount(String min_goods_amount) {
        this.min_goods_amount = min_goods_amount;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getIs_pastdue() {
        return is_pastdue;
    }

    public void setIs_pastdue(int is_pastdue) {
        this.is_pastdue = is_pastdue;
    }

    public int getIs_has() {
        return is_has;
    }

    public void setIs_has(int is_has) {
        this.is_has = is_has;
    }

    public int getIs_used() {
        return is_used;
    }

    public void setIs_used(int is_used) {
        this.is_used = is_used;
    }
}
