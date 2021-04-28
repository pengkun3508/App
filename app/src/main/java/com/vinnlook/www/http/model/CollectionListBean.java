package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:收藏列表
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CollectionListBean extends BaseBean {

    /**
     * list : [{"product_price":"108.00","search_attr":"563|564","preferential_price":"0.00","color_id":"563","goods_id":"20","goods_name":"日本Evercolor日抛型 Airy Brown 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":"0","promote_start_date":"0","promote_end_date":"0","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585734281_9456.png"},{"product_price":"155.00","search_attr":"2|3","preferential_price":"0.00","color_id":"2","goods_id":"1","goods_name":"日本Angelcolor bambi series Natural 系列日抛型 Natural  Black 20枚","virtual_sales":"0","suppliers_id":"2","is_promote":"0","promote_start_date":"1586793600","promote_end_date":"1587052800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726474_6911.png"}]
     * count : 2
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
         * search_attr : 563|564
         * preferential_price : 0.00
         * color_id : 563
         * goods_id : 20
         * goods_name : 日本Evercolor日抛型 Airy Brown 10枚
         * virtual_sales : 0
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585734281_9456.png
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

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }
    }
}
