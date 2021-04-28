package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/12/25$
 * @Author:pk$
 */
public class SetMealBean extends BaseBean {

    /**
     * act_id : 15
     * active_name : test-1
     * price : 1000
     * discount_price : ￥10元
     * list : [{"product_price":"125.00","search_attr":"26|27","preferential_price":"0.00","color_id":"26","product_number":"14","product_id":"79","goods_attr":"25|26|27","goods_id":"2","goods_name":"Angelcolor bambi series vintage系列日抛型 Vintage Brown 10枚 -0.00","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20200401/1585726597_5109.png","goods_attr_name":"Vintage Brown 10枚 -0.00"},{"product_price":"105.00","search_attr":"89|8758","preferential_price":"0.00","color_id":"8758","product_number":"11","product_id":"14618","goods_attr":"87|89|8758","goods_id":"4","goods_name":"Angelcolor bambi series日抛型 Cream Pink 10枚 -0.00","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594656000","promote_end_date":"1595260800","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20200821/1597999575_4777.png","goods_attr_name":"Cream Pink 10枚 -0.00"}]
     */

    private String act_id;
    private String active_name;
    private String price;
    private String discount_price;
    private List<ListBean> list;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

    public String getActive_name() {
        return active_name;
    }

    public void setActive_name(String active_name) {
        this.active_name = active_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * product_price : 125.00
         * search_attr : 26|27
         * preferential_price : 0.00
         * color_id : 26
         * product_number : 14
         * product_id : 79
         * goods_attr : 25|26|27
         * goods_id : 2
         * goods_name : Angelcolor bambi series vintage系列日抛型 Vintage Brown 10枚 -0.00
         * virtual_sales : 2
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : Angelcolor bambi seriesvintage
         * goods_thumb : http://img.jealook.com/backend/20200401/1585726597_5109.png
         * goods_attr_name : Vintage Brown 10枚 -0.00
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String product_number;
        private String product_id;
        private String goods_attr;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String brand_name;
        private String goods_thumb;
        private String goods_attr_name;

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

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
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

        public String getGoods_attr_name() {
            return goods_attr_name;
        }

        public void setGoods_attr_name(String goods_attr_name) {
            this.goods_attr_name = goods_attr_name;
        }
    }
}
