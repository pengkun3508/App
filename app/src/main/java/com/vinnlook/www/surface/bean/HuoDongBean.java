package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/1/5$
 * @Author:pk$
 */
public class HuoDongBean extends BaseBean {

    /**
     * act_name : 限时优惠
     * gift :
     * act_range_ext : 27
     * start_time : 1609828088
     * end_time : 1610121600
     * act_type : 0
     * act_type_ext : 0.00
     * type : 2
     * type_name : 限时优惠
     * details : 限时优惠
     * goods_list : [{"product_id":"3230","color_id":"804","product_price":"199.00","preferential_price":"198.90","search_attr":"787|804","diameter":"14.5mm","base_curve":"8.6mm","water_content":"58%","goods_id":"27","is_promote":"1","promote_start_date":"1609828088","promote_end_date":"1610121600","suppliers_id":"2","goods_name":"Flanmy 日抛  Matcha Tart 30枚","virtual_sales":"0","attr_value":"Matcha Tart","goods_thumb":"http://img.jealook.com/backend/20201228/1609150396_5934.png","attr":"直径：14.5mm | 基弧：8.6mm | 含水量：58%","goods_price":"198.90"},{"product_id":"3207","color_id":"786","product_price":"205.00","preferential_price":"204.90","search_attr":"786|787","diameter":"","base_curve":"","water_content":"","goods_id":"27","is_promote":"1","promote_start_date":"1609828088","promote_end_date":"1610121600","suppliers_id":"2","goods_name":"Flanmy 日抛  Maple Chiffon 30枚","virtual_sales":"0","attr_value":"Maple Chiffon","goods_thumb":"http://img.jealook.com/backend/20201228/1609150396_5391.png","attr":"直径： | 基弧： | 含水量：","goods_price":"204.90"}]
     */

    private String act_name;
    private String gift;
    private String act_range_ext;
    private String start_time;
    private String end_time;
    private String act_type;
    private String act_type_ext;
    private String type;
    private String type_name;
    private String details;
    private List<GoodsListBean> goods_list;
    private String color_mark;//自己写的颜色标示；1--为已选中；2--为未选中

    public String getColor_mark() {
        return color_mark;
    }

    public void setColor_mark(String color_mark) {
        this.color_mark = color_mark;
    }


    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getAct_range_ext() {
        return act_range_ext;
    }

    public void setAct_range_ext(String act_range_ext) {
        this.act_range_ext = act_range_ext;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getAct_type() {
        return act_type;
    }

    public void setAct_type(String act_type) {
        this.act_type = act_type;
    }

    public String getAct_type_ext() {
        return act_type_ext;
    }

    public void setAct_type_ext(String act_type_ext) {
        this.act_type_ext = act_type_ext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * product_id : 3230
         * color_id : 804
         * product_price : 199.00
         * preferential_price : 198.90
         * search_attr : 787|804
         * diameter : 14.5mm
         * base_curve : 8.6mm
         * water_content : 58%
         * goods_id : 27
         * is_promote : 1
         * promote_start_date : 1609828088
         * promote_end_date : 1610121600
         * suppliers_id : 2
         * goods_name : Flanmy 日抛  Matcha Tart 30枚
         * virtual_sales : 0
         * attr_value : Matcha Tart
         * goods_thumb : http://img.jealook.com/backend/20201228/1609150396_5934.png
         * attr : 直径：14.5mm | 基弧：8.6mm | 含水量：58%
         * goods_price : 198.90
         */

        private String product_id;
        private String color_id;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String diameter;
        private String base_curve;
        private String water_content;
        private String goods_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String virtual_sales;
        private String attr_value;
        private String goods_thumb;
        private String attr;
        private String goods_price;

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

        public String getDiameter() {
            return diameter;
        }

        public void setDiameter(String diameter) {
            this.diameter = diameter;
        }

        public String getBase_curve() {
            return base_curve;
        }

        public void setBase_curve(String base_curve) {
            this.base_curve = base_curve;
        }

        public String getWater_content() {
            return water_content;
        }

        public void setWater_content(String water_content) {
            this.water_content = water_content;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }
    }
}
