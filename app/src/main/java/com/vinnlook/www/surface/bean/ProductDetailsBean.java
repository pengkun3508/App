package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:活动详情
 * @Time:2020/5/26$
 * @Author:pk$
 */
public class ProductDetailsBean extends BaseBean {
    /**
     * title : test-活动
     * content : ["http://admin.vinnlook.com/ueditor/php/upload/image/20200526/1590462936932708.png","http://admin.vinnlook.com/ueditor/php/upload/image/20200526/1590462936657944.png"]
     * product : [{"product_price":"95.00","search_attr":"1017|1018","preferential_price":"0.00","color_id":"1017","goods_id":"35","goods_name":"日本Lilmoon月抛型 Cream beige 1枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733266_7458.png"},{"product_price":"95.00","search_attr":"1018|1043","preferential_price":"0.00","color_id":"1043","goods_id":"35","goods_name":"日本Lilmoon月抛型 Cream grege 1枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733266_7347.png"}]
     */

    private String title;
    private List<String> content;
    private List<ProductBean> product;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * product_price : 95.00
         * search_attr : 1017|1018
         * preferential_price : 0.00
         * color_id : 1017
         * goods_id : 35
         * goods_name : 日本Lilmoon月抛型 Cream beige 1枚
         * virtual_sales : 0
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : Lilmoon
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585733266_7458.png
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private int is_promote;
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
    }
}
