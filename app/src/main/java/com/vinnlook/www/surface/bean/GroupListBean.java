package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/5/8$
 * @Author:pk$
 */
public class GroupListBean extends BaseBean {
    /**
     * list : [{"product_price":"105.00","search_attr":"732|733","preferential_price":null,"color_id":"732","product_number":"46","product_id":"3134","goods_id":"25","goods_name":"Flanmy 日抛 Maple Chiffon 10片","virtual_sales":"16","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943049","promote_end_date":"1599634079","brand_name":"Flanmy","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png","attr_name":"Maple Chiffon 10片","group_price":"104","group_people":"3","still_time":34857,"is_post_fee":"0"},{"product_price":"99.00","search_attr":"10105|10106","preferential_price":null,"color_id":"10105","product_number":"16","product_id":"13497","goods_id":"310","goods_name":"Rich Standard 双周抛 Innocent Brown 6片","virtual_sales":"4","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Richstandard","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20200911/1599808281_4229.png","attr_name":"Innocent Brown 6片","group_price":"98","group_people":"3","still_time":34857,"is_post_fee":"0"}]
     * count : 2
     * count_page : 1
     */

    private String count;
    private int count_page;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getCount_page() {
        return count_page;
    }

    public void setCount_page(int count_page) {
        this.count_page = count_page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * product_price : 105.00
         * search_attr : 732|733
         * preferential_price : null
         * color_id : 732
         * product_number : 46
         * product_id : 3134
         * goods_id : 25
         * goods_name : Flanmy 日抛 Maple Chiffon 10片
         * virtual_sales : 16
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 1598943049
         * promote_end_date : 1599634079
         * brand_name : Flanmy
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png
         * attr_name : Maple Chiffon 10片
         * group_price : 104
         * group_people : 3
         * still_time : 34857
         * is_post_fee : 0
         */

        private String product_price;
        private String search_attr;
        private Object preferential_price;
        private String color_id;
        private String product_number;
        private String product_id;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String brand_name;
        private String goods_thumb;
        private String attr_name;
        private String group_price;
        private String group_people;
        private int still_time;
        private String is_post_fee;
        private long countTime;
        private String day;
        private String hour;
        private String minute;

        public long getCountTime() {
            return countTime;
        }

        public void setCountTime(long countTime) {
            this.countTime = countTime;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }



        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
        }

        public Object getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(Object preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getProduct_number() {
            return product_number;
        }

        public void setProduct_number(String product_number) {
            this.product_number = product_number;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getVirtual_sales() {
            return virtual_sales;
        }

        public void setVirtual_sales(String virtual_sales) {
            this.virtual_sales = virtual_sales;
        }

        public String getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(String suppliers_id) {
            this.suppliers_id = suppliers_id;
        }

        public int getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(int is_promote) {
            this.is_promote = is_promote;
        }

        public String getPromote_start_date() {
            return promote_start_date;
        }

        public void setPromote_start_date(String promote_start_date) {
            this.promote_start_date = promote_start_date;
        }

        public String getPromote_end_date() {
            return promote_end_date;
        }

        public void setPromote_end_date(String promote_end_date) {
            this.promote_end_date = promote_end_date;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public String getGroup_price() {
            return group_price;
        }

        public void setGroup_price(String group_price) {
            this.group_price = group_price;
        }

        public String getGroup_people() {
            return group_people;
        }

        public void setGroup_people(String group_people) {
            this.group_people = group_people;
        }

        public int getStill_time() {
            return still_time;
        }

        public void setStill_time(int still_time) {
            this.still_time = still_time;
        }

        public String getIs_post_fee() {
            return is_post_fee;
        }

        public void setIs_post_fee(String is_post_fee) {
            this.is_post_fee = is_post_fee;
        }
    }
}
