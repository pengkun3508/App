package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/6$
 * @Author:pk$
 */
public class ReBangListBean extends BaseBean {

    /**
     * product_id : 11028
     * color_id : 7991
     * product_price : 115.00
     * preferential_price : 75.00
     * search_attr : 7960|7991
     * goods_id : 179
     * is_promote : 0
     * promote_start_date : 1598421953
     * promote_end_date : 1599029258
     * suppliers_id : 2
     * goods_name : MerMer 日抛型 Linen 10枚
     * virtual_sales : 37
     * attr_value : Linen
     * goods_thumb : http://img.jealook.com/backend/20200731/1596175279_6508.png
     */

    private String product_id;
    private String color_id;
    private String product_price;
    private String preferential_price;
    private String search_attr;
    private String goods_id;
    private int is_promote;
    private String promote_start_date;
    private String promote_end_date;
    private String suppliers_id;
    private String goods_name;
    private String virtual_sales;
    private String attr_value;
    private String goods_thumb;
    private String brand_name;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }



    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
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

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public String getSuppliers_id() {
        return suppliers_id;
    }

    public void setSuppliers_id(String suppliers_id) {
        this.suppliers_id = suppliers_id;
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

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }
}
