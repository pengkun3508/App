package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallBean extends BaseBean {

    /**
     * points : 0
     * pay_points : 0
     * discount : [{"id":"6","coupon_name":"积分兑换-1","point":"100","type":"2","reduced":"1","min_money":"1000.00","use_time_type":"2","use_time":"30","use_time_start":"0","use_time_end":"0","astrict_count":"0","use_shop_type":"1","content":""},{"id":"7","coupon_name":"积分兑换-2","point":"1000","type":"1","reduced":"9.8","min_money":"10000.00","use_time_type":"2","use_time":"10","use_time_start":"0","use_time_end":"0","astrict_count":"0","use_shop_type":"1","content":""}]
     */

    private String points;
    private String pay_points;
    private List<DiscountBean> discount;

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

    public List<DiscountBean> getDiscount() {
        return discount;
    }

    public void setDiscount(List<DiscountBean> discount) {
        this.discount = discount;
    }

    public static class DiscountBean {
        /**
         * id : 6
         * coupon_name : 积分兑换-1
         * point : 100
         * type : 2
         * reduced : 1
         * min_money : 1000.00
         * use_time_type : 2
         * use_time : 30
         * use_time_start : 0
         * use_time_end : 0
         * astrict_count : 0
         * use_shop_type : 1
         * content :
         */

        private String id;
        private String coupon_name;
        private String point;
        private String type;
        private String reduced;
        private String min_money;
        private String use_time_type;
        private String use_time;
        private String use_time_start;
        private String use_time_end;
        private String astrict_count;
        private String use_shop_type;
        private String content;

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

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
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
    }
}
