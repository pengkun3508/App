package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/2$
 * @Author:pk$
 */
public class HomeGoodsListBean extends BaseBean {
    /**
     * list : [{"product_id":"19491","color_id":"13241","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13241","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 幽靜森林 10枚","attr_value":"幽靜森林","goods_thumb":"http://img.jealook.com/backend/20201016/1602818169_4166.png"},{"product_id":"19467","color_id":"13240","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13240","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 暮光森林 10枚","attr_value":"暮光森林","goods_thumb":"http://img.jealook.com/backend/20201016/1602818169_6113.png"},{"product_id":"19443","color_id":"13239","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13239","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 橘光森林 10枚","attr_value":"橘光森林","goods_thumb":"http://img.jealook.com/backend/20201016/1602818169_1567.png"},{"product_id":"19419","color_id":"13238","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13238","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 Sheer salble豹纹深棕 10枚","attr_value":"Sheer salble豹纹深棕","goods_thumb":"http://img.jealook.com/backend/20201016/1602817093_3947.png"},{"product_id":"19395","color_id":"13237","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13237","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 Mist iris迷雾深灰 10枚","attr_value":"Mist iris迷雾深灰","goods_thumb":"http://img.jealook.com/backend/20201016/1602816432_9449.png"},{"product_id":"19371","color_id":"13236","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13236","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 Pale mirage梦幻迷褐 10枚","attr_value":"Pale mirage梦幻迷褐","goods_thumb":"http://img.jealook.com/backend/20201016/1602816432_6333.png"},{"product_id":"19347","color_id":"13235","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13235","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 Nostalgia洛亚丽棕 10枚","attr_value":"Nostalgia洛亚丽棕","goods_thumb":"http://img.jealook.com/backend/20201016/1602816432_8273.png"},{"product_id":"16579","color_id":"11862","product_price":"105.00","preferential_price":"95.00","search_attr":"1825|11862","goods_id":"64","is_promote":0,"promote_start_date":"1599550794","promote_end_date":"1600099200","suppliers_id":"2","goods_name":"Revia日抛型 Melty Bare 10枚","attr_value":"Melty Bare","goods_thumb":"http://img.jealook.com/backend/20201019/1603091103_4089.png"},{"product_id":"16557","color_id":"11861","product_price":"105.00","preferential_price":"95.00","search_attr":"1825|11861","goods_id":"64","is_promote":0,"promote_start_date":"1599550794","promote_end_date":"1600099200","suppliers_id":"2","goods_name":"Revia日抛型 Luster Gem 10枚","attr_value":"Luster Gem","goods_thumb":"http://img.jealook.com/backend/20201019/1603091103_9722.png"},{"product_id":"11374","color_id":"8719","product_price":"105.00","preferential_price":"95.00","search_attr":"1825|8719","goods_id":"64","is_promote":0,"promote_start_date":"1599550794","promote_end_date":"1600099200","suppliers_id":"2","goods_name":"Revia日抛型 Private01 10枚","attr_value":"Private01","goods_thumb":"http://img.jealook.com/backend/20200927/1601191996_8623.png"}]
     * count : 14
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
         * product_id : 19491
         * color_id : 13241
         * product_price : 78.00
         * preferential_price : 49.00
         * search_attr : 3570|13241
         * goods_id : 97
         * is_promote : 0
         * promote_start_date : 1594051200
         * promote_end_date : 1594656000
         * suppliers_id : 1
         * goods_name : Revia蕾美 日抛型 幽靜森林 10枚
         * attr_value : 幽靜森林
         * goods_thumb : http://img.jealook.com/backend/20201016/1602818169_4166.png
         */

        private String product_id;
        private String color_id;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String goods_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String attr_value;
        private String goods_thumb;

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
}
