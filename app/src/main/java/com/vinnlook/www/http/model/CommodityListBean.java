package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CommodityListBean extends BaseBean {
    /**
     * list : [{"product_price":"209.00","search_attr":"13150|20340","preferential_price":"0.00","color_id":"13150","product_number":"23","product_id":"23496","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 Salmon 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4550.png","attr_name":"Salmon 30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"},{"product_price":"209.00","search_attr":"13151|20340","preferential_price":"0.00","color_id":"13151","product_number":"18","product_id":"23668","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 Smoky Grege 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4074.png","attr_name":"Smoky Grege 30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"},{"product_price":"209.00","search_attr":"5551|20340","preferential_price":"0.00","color_id":"5551","product_number":"25","product_id":"23568","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 Sea Green 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8512.png","attr_name":"Sea Green 30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"},{"product_price":"209.00","search_attr":"5581|20340","preferential_price":"0.00","color_id":"5581","product_number":"25","product_id":"23604","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 Slate Gray 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_9970.png","attr_name":"Slate Gray 30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"},{"product_price":"209.00","search_attr":"5582|20340","preferential_price":"0.00","color_id":"5582","product_number":"22","product_id":"23532","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 Sea Blue 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_7915.png","attr_name":"Sea Blue 30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"},{"product_price":"209.00","search_attr":"5584|20340","preferential_price":"0.00","color_id":"5584","product_number":"23","product_id":"23432","goods_id":"141","goods_name":"「西安保税仓发货」Mermer mix 合作款 日抛 10片 \\/ 30片 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1591781400","promote_end_date":"1591878600","brand_name":"Mermer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_1570.png","attr_name":"30片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%"}]
     * count : 6
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
         * product_price : 209.00
         * search_attr : 13150|20340
         * preferential_price : 0.00
         * color_id : 13150
         * product_number : 23
         * product_id : 23496
         * goods_id : 141
         * goods_name : 「西安保税仓发货」Mermer mix 合作款 日抛 10片 \/ 30片 Salmon 30片
         * virtual_sales : 1
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 1591781400
         * promote_end_date : 1591878600
         * brand_name : Mermer
         * diameter : 14.2mm
         * base_curve : 8.6mm
         * water_content : 38.5%
         * goods_thumb : http://img.jealook.com/backend/20210107/1610002858_4550.png
         * attr_name : Salmon 30片
         * attr : 直径：14.2mm | 基弧：8.6mm | 含水量：38.5%
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String product_number;
        private String product_id;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String brand_name;
        private String diameter;
        private String base_curve;
        private String water_content;
        private String goods_thumb;
        private String attr_name;
        private String attr;
        private String active_name;

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
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

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }
    }
}
