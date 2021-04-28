package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/7$
 * @Author:pk$
 */
public class ShopCartListBean extends BaseBean {
    /**
     * list : [{"goods_number":"2","rec_id":"606","goods_id":"19","goods_name":"Evercolor日抛型 Chiff on Brown 20枚 Chiff on Brown 20枚","goods_attr":"529|548|555","product_id":"2040","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","goods_attr_id":"529|548|555","number":"3","product_price":"145.00","preferential_price":"125.00","search_attr":"529|555","color_id":"555","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_1322.png","goods_attr_name":"Chiff on Brown,20枚,125度"},{"goods_number":"1","rec_id":"586","goods_id":"19","goods_name":"Evercolor日抛型 Chiff on Brown 20枚 Chiff on Brown 20枚","goods_attr":"529|545|555","product_id":"2060","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","goods_attr_id":"529|545|555","number":"4","product_price":"145.00","preferential_price":"125.00","search_attr":"529|555","color_id":"555","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_1322.png","goods_attr_name":"Chiff on Brown,20枚,750度"}]
     * is_all : 0
     * ht_count : 2
     * zy_count : 1
     */

    private String is_all;
    private String ht_count;
    private String zy_count;
    private List<ListBean> list;

    public String getIs_all() {
        return is_all;
    }

    public void setIs_all(String is_all) {
        this.is_all = is_all;
    }

    public String getHt_count() {
        return ht_count;
    }

    public void setHt_count(String ht_count) {
        this.ht_count = ht_count;
    }

    public String getZy_count() {
        return zy_count;
    }

    public void setZy_count(String zy_count) {
        this.zy_count = zy_count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goods_number : 2
         * rec_id : 606
         * goods_id : 19
         * goods_name : Evercolor日抛型 Chiff on Brown 20枚 Chiff on Brown 20枚
         * goods_attr : 529|548|555
         * product_id : 2040
         * is_check : 0
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 1594051200
         * promote_end_date : 1594656000
         * goods_attr_id : 529|548|555
         * number : 3
         * product_price : 145.00
         * preferential_price : 125.00
         * search_attr : 529|555
         * color_id : 555
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585733834_1322.png
         * goods_attr_name : Chiff on Brown,20枚,125度
         */

        private String goods_number;
        private String rec_id;
        private String goods_id;
        private String goods_name;
        private String goods_attr;
        private String product_id;
        private String is_check;
        private String suppliers_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String goods_attr_id;
        private String number;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String color_id;
        private String goods_thumb;
        private String goods_attr_name;

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
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

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getIs_check() {
            return is_check;
        }

        public void setIs_check(String is_check) {
            this.is_check = is_check;
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

        public String getGoods_attr_id() {
            return goods_attr_id;
        }

        public void setGoods_attr_id(String goods_attr_id) {
            this.goods_attr_id = goods_attr_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(String preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_attr_name() {
            return goods_attr_name;
        }

        public void setGoods_attr_name(String goods_attr_name) {
            this.goods_attr_name = goods_attr_name;
        }
    }
}
