package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class TypeGoodsBean extends BaseBean {

    /**
     * list : [{"product_price":"108.00","search_attr":"564|588","preferential_price":"0.00","color_id":"588","goods_id":"20","goods_name":"日本Evercolor日抛型 Aqua beige 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Evercolor","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585734282_2780.png"},{"product_price":"145.00","search_attr":"529|555","preferential_price":"130.50","color_id":"555","goods_id":"19","goods_name":"日本Ever color日抛型 Chiff on Brown 20枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1586707200","promote_end_date":"1588262400","brand_name":"Evercolor","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_1322.png"},{"product_price":"155.00","search_attr":"2|3","preferential_price":"0.00","color_id":"2","goods_id":"1","goods_name":"日本Angelcolor bambi series Natural 系列日抛型 Natural  Black 20枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1586793600","promote_end_date":"1587052800","brand_name":"Angelcolor bambi series","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726474_6911.png"},{"product_price":"155.00","search_attr":"3|10","preferential_price":"0.00","color_id":"10","goods_id":"1","goods_name":"日本Angelcolor bambi series Natural 系列日抛型 Natural  Brown 20枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1586793600","promote_end_date":"1587052800","brand_name":"Angelcolor bambi series","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585725300_9963.png"},{"product_price":"155.00","search_attr":"3|22","preferential_price":"0.00","color_id":"22","goods_id":"1","goods_name":"日本Angelcolor bambi series Natural 系列日抛型 Natural  Nude 20枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1586793600","promote_end_date":"1587052800","brand_name":"Angelcolor bambi series","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585725300_8942.png"},{"product_price":"125.00","search_attr":"26|27","preferential_price":"0.00","color_id":"26","goods_id":"2","goods_name":"日本Angelcolor bambi series vintage系列日抛型 Vintage Brown 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726597_5109.png"},{"product_price":"125.00","search_attr":"27|52","preferential_price":"0.00","color_id":"52","goods_id":"2","goods_name":"日本Angelcolor bambi series vintage系列日抛型 Vintage Gray 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726597_1699.png"},{"product_price":"125.00","search_attr":"27|54","preferential_price":"0.00","color_id":"54","goods_id":"2","goods_name":"日本Angelcolor bambi series vintage系列日抛型 Vintage Haze 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726597_5678.png"},{"product_price":"125.00","search_attr":"27|55","preferential_price":"0.00","color_id":"55","goods_id":"2","goods_name":"日本Angelcolor bambi series vintage系列日抛型 Vintage Olive 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726597_4453.png"},{"product_price":"225.00","search_attr":"26|5047","preferential_price":"0.00","color_id":"26","goods_id":"2","goods_name":"日本Angelcolor bambi series vintage系列日抛型 Vintage Brown 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726597_5109.png"}]
     * count : 283
     * count_page : 29
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
         * product_price : 108.00
         * search_attr : 564|588
         * preferential_price : 0.00
         * color_id : 588
         * goods_id : 20
         * goods_name : 日本Evercolor日抛型 Aqua beige 10枚
         * virtual_sales : 0
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : Evercolor
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585734282_2780.png
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String brand_name;
        private String goods_thumb;

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

        public String getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(String preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
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

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
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
    }
}
