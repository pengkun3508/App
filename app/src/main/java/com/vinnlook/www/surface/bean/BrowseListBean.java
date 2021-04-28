package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:浏览足迹
 * @Time:2020/9/9$
 * @Author:pk$
 */
public class BrowseListBean extends BaseBean {
    /**
     * list : [{"goods_attr":"3601|3630","id":"5392","product_id":"8539","color_id":"3630","product_price":"78.00","preferential_price":"55.00","search_attr":"3601|3630","goods_id":"98","is_promote":0,"promote_start_date":"1595260800","promote_end_date":"1595865600","suppliers_id":"1","goods_name":"Lalish领丽秀 日抛型 高贵靓丽 10枚","attr_value":"高贵靓丽","goods_thumb":"http://img.jealook.com/backend/20200403/1585893466_2459.png"},{"goods_attr":"529|555","id":"5388","product_id":"2038","color_id":"555","product_price":"145.00","preferential_price":"125.00","search_attr":"529|555","goods_id":"19","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"2","goods_name":"Evercolor日抛型 Chiffon Brown 20枚","attr_value":"Chiffon Brown","goods_thumb":"http://img.jealook.com/backend/20200924/1600928126_5659.png"},{"goods_attr":"3498|3527","id":"5369","product_id":"7953","color_id":"3527","product_price":"149.00","preferential_price":"0.00","search_attr":"3498|3527","goods_id":"95","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"Givre绮芙丽 日抛型 杏桃甜棕 30枚","attr_value":"杏桃甜棕","goods_thumb":"http://img.jealook.com/backend/20200403/1585896648_7202.png"},{"goods_attr":"3251|3252","id":"5365","product_id":"7076","color_id":"3251","product_price":"65.00","preferential_price":"50.00","search_attr":"3251|3252","goods_id":"87","is_promote":0,"promote_start_date":"1592323200","promote_end_date":"1592496000","suppliers_id":"1","goods_name":"中国版 Naturali月抛 琉璃雅棕 1枚","attr_value":"琉璃雅棕","goods_thumb":"http://img.jealook.com/backend/20200622/1592808275_9644.png"},{"goods_attr":"7251|7284","id":"5341","product_id":"9532","color_id":"7284","product_price":"99.00","preferential_price":"68.00","search_attr":"7251|7284","goods_id":"166","is_promote":0,"promote_start_date":"1599550938","promote_end_date":"1600099200","suppliers_id":"1","goods_name":"Seed dreaming 以梦 日抛 Aquamarine碧波绿 10枚","attr_value":"Aquamarine碧波绿","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_2068.png"},{"goods_attr":"3313|3338","id":"5321","product_id":"7256","color_id":"3338","product_price":"75.00","preferential_price":"58.00","search_attr":"3313|3338","goods_id":"89","is_promote":0,"promote_start_date":"1597128282","promote_end_date":"1597680000","suppliers_id":"1","goods_name":"中国版 Chouchou日抛 甜橘蜜茶 10枚","attr_value":"甜橘蜜茶","goods_thumb":"http://img.jealook.com/backend/20200603/1591168394_2042.png"},{"goods_attr":"8985|8986","id":"5319","product_id":"11940","color_id":"8985","product_price":"69.00","preferential_price":"0.00","search_attr":"8985|8986","goods_id":"230","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"BeeHeartB 日抛型 101BR 10枚","attr_value":"101BR","goods_thumb":"http://img.jealook.com/backend/20200827/1598510378_7875.png"},{"goods_attr":"3666|3692","id":"5316","product_id":"8747","color_id":"3692","product_price":"88.00","preferential_price":"0.00","search_attr":"3666|3692","goods_id":"100","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"Erouge爱如久 双周抛型 璀璨金棕 6枚","attr_value":"璀璨金棕","goods_thumb":"http://img.jealook.com/backend/20200927/1601186411_5536.png"},{"goods_attr":"3570|13241","id":"5313","product_id":"19491","color_id":"13241","product_price":"78.00","preferential_price":"49.00","search_attr":"3570|13241","goods_id":"97","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"1","goods_name":"Revia蕾美 日抛型 幽靜森林 10枚","attr_value":"幽靜森林","goods_thumb":"http://img.jealook.com/backend/20201016/1602818169_4166.png"},{"goods_attr":"564|588","id":"5295","product_id":"2228","color_id":"588","product_price":"108.00","preferential_price":"88.00","search_attr":"564|588","goods_id":"20","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"2","goods_name":"Evercolor日抛型 Aqua Beige 10枚","attr_value":"Aqua Beige","goods_thumb":"http://img.jealook.com/backend/20200401/1585734282_2780.png"}]
     * count : 28
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
         * goods_attr : 3601|3630
         * id : 5392
         * product_id : 8539
         * color_id : 3630
         * product_price : 78.00
         * preferential_price : 55.00
         * search_attr : 3601|3630
         * goods_id : 98
         * is_promote : 0
         * promote_start_date : 1595260800
         * promote_end_date : 1595865600
         * suppliers_id : 1
         * goods_name : Lalish领丽秀 日抛型 高贵靓丽 10枚
         * attr_value : 高贵靓丽
         * goods_thumb : http://img.jealook.com/backend/20200403/1585893466_2459.png
         */

        private String goods_attr;
        private String id;
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
        private String attr_value;
        private String goods_thumb;

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
