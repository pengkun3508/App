package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:品牌商品列表
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class BrandDetailsBean extends BaseBean {
    /**
     * list : [{"product_price":"115.00","search_attr":"13329|13401","preferential_price":null,"color_id":"13401","product_number":"864","product_id":"20024","goods_id":"387","goods_name":"MerMer Light 系列 日抛 Light Lime 10片","virtual_sales":"162","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_5069.png","attr_name":"Light Lime 10片","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"13329|13402","preferential_price":null,"color_id":"13402","product_number":"883","product_id":"20055","goods_id":"387","goods_name":"MerMer Light 系列 日抛 Light Peach 10片","virtual_sales":"162","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_3120.png","attr_name":"Light Peach 10片","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"13329|13403","preferential_price":null,"color_id":"13403","product_number":"500","product_id":"20086","goods_id":"387","goods_name":"MerMer Light 系列 日抛 Light Cyan 10片","virtual_sales":"162","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_9557.png","attr_name":"Light Cyan 10片","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"13329|13404","preferential_price":null,"color_id":"13404","product_number":"817","product_id":"20117","goods_id":"387","goods_name":"MerMer Light 系列 日抛 Light Gray 10片","virtual_sales":"162","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_2122.png","attr_name":"Light Gray 10片","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"13329|13405","preferential_price":null,"color_id":"13405","product_number":"1239","product_id":"20179","goods_id":"387","goods_name":"MerMer Light 系列 日抛 Light Navy（小千代） 10片","virtual_sales":"162","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_9894.png","attr_name":"Light Navy（小千代） 10片","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"5551|5552","preferential_price":null,"color_id":"5551","product_number":"47","product_id":"9244","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Sea Green 10片","virtual_sales":"59","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20210107/1610002858_8512.png","attr_name":"Sea Green 10片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"5552|13150","preferential_price":null,"color_id":"13150","product_number":"47","product_id":"36554","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Salmon 10片","virtual_sales":"59","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20210107/1610002858_4550.png","attr_name":"Salmon 10片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"5552|13151","preferential_price":null,"color_id":"13151","product_number":"24","product_id":"36598","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Smoky Grege 10片","virtual_sales":"59","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20210107/1610002858_4074.png","attr_name":"Smoky Grege 10片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"5552|20341","preferential_price":null,"color_id":"20341","product_number":"14","product_id":"36627","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Soda Blue 10片","virtual_sales":"59","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20210107/1610002858_4675.png","attr_name":"Soda Blue 10片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":"买二赠一"},{"product_price":"115.00","search_attr":"5552|20342","preferential_price":null,"color_id":"20342","product_number":"13","product_id":"36490","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Moss Green 10片","virtual_sales":"59","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_thumb":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20210107/1610002858_8111.png","attr_name":"Moss Green 10片","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":"买二赠一"}]
     * count : 20
     * count_page : 2
     * info : {"brand_banner":"http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com","brand_desc":""}
     */

    private String count;
    private int count_page;
    private InfoBean info;
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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * brand_banner : http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com
         * brand_desc :
         */

        private String brand_banner;
        private String brand_desc;

        public String getBrand_banner() {
            return brand_banner;
        }

        public void setBrand_banner(String brand_banner) {
            this.brand_banner = brand_banner;
        }

        public String getBrand_desc() {
            return brand_desc;
        }

        public void setBrand_desc(String brand_desc) {
            this.brand_desc = brand_desc;
        }
    }

    public static class ListBean {
        /**
         * product_price : 115.00
         * search_attr : 13329|13401
         * preferential_price : null
         * color_id : 13401
         * product_number : 864
         * product_id : 20024
         * goods_id : 387
         * goods_name : MerMer Light 系列 日抛 Light Lime 10片
         * virtual_sales : 162
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : MerMer
         * diameter : 14.0mm
         * base_curve : 8.7mm
         * water_content : 55%
         * goods_thumb : http://vinnlook.oss-cn-zhangjiakou.aliyuncs.com/backend/20201021/1603259751_5069.png
         * attr_name : Light Lime 10片
         * attr : 直径：14.0mm | 基弧：8.7mm | 含水量：55%
         * active_name : 买二赠一
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
        private int is_promote;
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

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
        }
    }
}
