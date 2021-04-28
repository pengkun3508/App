package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:限时列表
 * @Time:2020/4/27$
 * @Author:pk$
 */
public class LimitedBean extends BaseBean {

    /**
     * list : [{"goods_id":"6","sort_order":"100","is_promote":1,"goods_name":"日本Artiral 日抛型 Artiral  Black 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"70.20","product_price":"78.00","product_id":"390","search_attr":"133|134","color_id":"133","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726897_8513.png","surplus_time":560719},{"goods_id":"6","sort_order":"100","is_promote":1,"goods_name":"日本Artiral 日抛型 Artiral Brown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"70.20","product_price":"78.00","product_id":"416","search_attr":"134|160","color_id":"160","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726897_6100.png","surplus_time":560719},{"goods_id":"6","sort_order":"100","is_promote":1,"goods_name":"日本Artiral 日抛型 Artiral ochre 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"70.20","product_price":"78.00","product_id":"442","search_attr":"134|161","color_id":"161","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726897_3366.png","surplus_time":560719},{"goods_id":"10","sort_order":"100","is_promote":1,"goods_name":"日本Bijou日抛型 Brown 0 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"44.10","product_price":"49.00","product_id":"727","search_attr":"253|5064","color_id":"5064","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200415/1586945459_8169.png","surplus_time":560719},{"goods_id":"10","sort_order":"100","is_promote":1,"goods_name":"日本Bijou日抛型 Brown 1 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"44.10","product_price":"49.00","product_id":"753","search_attr":"253|5065","color_id":"5065","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200415/1586945459_6200.png","surplus_time":560719},{"goods_id":"10","sort_order":"100","is_promote":1,"goods_name":"日本Bijou日抛型 Brown 5 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"44.10","product_price":"49.00","product_id":"779","search_attr":"253|5066","color_id":"5066","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200415/1586945459_4098.png","surplus_time":560719},{"goods_id":"15","sort_order":"100","is_promote":1,"goods_name":"日本Dorb月抛型 Black 3枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"139.50","product_price":"155.00","product_id":"1236","search_attr":"391|392","color_id":"391","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729119_8936.png","surplus_time":560719},{"goods_id":"15","sort_order":"100","is_promote":1,"goods_name":"日本Dorb月抛型 Brown 3枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"139.50","product_price":"155.00","product_id":"1260","search_attr":"392|416","color_id":"416","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729119_7028.png","surplus_time":560719},{"goods_id":"15","sort_order":"100","is_promote":1,"goods_name":"日本Dorb月抛型 Choco 3枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"139.50","product_price":"155.00","product_id":"1284","search_attr":"392|417","color_id":"417","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729119_4440.png","surplus_time":560719},{"goods_id":"15","sort_order":"100","is_promote":1,"goods_name":"日本Dorb月抛型 Olive 3枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"139.50","product_price":"155.00","product_id":"1308","search_attr":"392|418","color_id":"418","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729119_9693.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Chameau Brown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1332","search_attr":"420|421","color_id":"420","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729250_9533.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 OliveBrown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1362","search_attr":"421|443","color_id":"443","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729250_7595.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Classic   Umberp 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1392","search_attr":"421|446","color_id":"446","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729250_3319.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Coralcheek 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1419","search_attr":"421|451","color_id":"451","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729250_2317.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Plum Black 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1477","search_attr":"421|453","color_id":"453","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729250_8578.png","surplus_time":560719},{"goods_id":"16","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Shade Brown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"1503","search_attr":"421|5071","color_id":"5071","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200414/1586856800_9326.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Chameau Brown 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1531","search_attr":"456|457","color_id":"456","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_8065.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Champnage gray 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1561","search_attr":"457|469","color_id":"469","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_3733.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Classic   Umberp 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1590","search_attr":"457|481","color_id":"481","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_1961.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Coralcheek 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1616","search_attr":"457|487","color_id":"487","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_2766.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 OliveBrown 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1645","search_attr":"457|488","color_id":"488","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_9142.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Plum Black 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1674","search_attr":"457|489","color_id":"489","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585729832_2953.png","surplus_time":560719},{"goods_id":"17","sort_order":"100","is_promote":1,"goods_name":"日本Envie日抛型 Shade Brown 30枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"220.50","product_price":"245.00","product_id":"1704","search_attr":"457|5076","color_id":"5076","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200414/1586856828_2276.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Antiquebeige 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"1966","search_attr":"528|529","color_id":"528","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_7964.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Apricot Brown 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"1990","search_attr":"529|547","color_id":"547","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730512_8349.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Champange Brown 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2014","search_attr":"529|553","color_id":"553","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730512_6351.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Chiff on Brown 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2038","search_attr":"529|555","color_id":"555","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_1322.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Classic  cheek 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2062","search_attr":"529|556","color_id":"556","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730512_5683.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Natural  Black 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2086","search_attr":"529|557","color_id":"557","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730512_8168.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Natural  Brown 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2109","search_attr":"529|558","color_id":"558","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730512_7291.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Natural mocha 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2133","search_attr":"529|559","color_id":"559","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585730513_8304.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Silhouetteduo 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2157","search_attr":"529|560","color_id":"560","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733834_8260.png","surplus_time":560719},{"goods_id":"19","sort_order":"100","is_promote":1,"goods_name":"日本Ever color日抛型 Urbannoir 20枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"130.50","product_price":"145.00","product_id":"2181","search_attr":"529|561","color_id":"561","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585733835_1174.png","surplus_time":560719},{"goods_id":"48","sort_order":"100","is_promote":1,"goods_name":"日本Nadeshiko color日抛型 Blue 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"88.20","product_price":"98.00","product_id":"5115","search_attr":"1368|1369","color_id":"1368","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200402/1585793496_5601.png","surplus_time":560719},{"goods_id":"48","sort_order":"100","is_promote":1,"goods_name":"日本Nadeshiko color日抛型 Brown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"88.20","product_price":"98.00","product_id":"5141","search_attr":"1369|1395","color_id":"1395","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200402/1585793496_5039.png","surplus_time":560719},{"goods_id":"48","sort_order":"100","is_promote":1,"goods_name":"日本Nadeshiko color日抛型 Green 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"88.20","product_price":"98.00","product_id":"5167","search_attr":"1369|1396","color_id":"1396","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200402/1585793496_3333.png","surplus_time":560719},{"goods_id":"48","sort_order":"100","is_promote":1,"goods_name":"日本Nadeshiko color日抛型 Pink 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"88.20","product_price":"98.00","product_id":"5193","search_attr":"1369|1397","color_id":"1397","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200402/1585793496_6257.png","surplus_time":560719},{"goods_id":"49","sort_order":"100","is_promote":1,"goods_name":"日本Naturali 日抛型 Charming Brown 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"5219","search_attr":"1399|1400","color_id":"1399","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200415/1586942082_7288.png","surplus_time":560719},{"goods_id":"49","sort_order":"100","is_promote":1,"goods_name":"日本Naturali 日抛型 Charming  hazel 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"97.20","product_price":"108.00","product_id":"5245","search_attr":"1400|5154","color_id":"5154","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200415/1586942082_4970.png","surplus_time":560719},{"goods_id":"141","sort_order":"100","is_promote":1,"goods_name":"Mermer By Richstandard 日抛  Sea green 10枚","virtual_sales":"0","suppliers_id":"2","preferential_price":"103.50","product_price":"115.00","product_id":"9244","search_attr":"5551|5552","color_id":"5551","promote_start_date":"1586707200","promote_end_date":"1590940800","goods_thumb":"http://img.vinnlook.com/backend/20200423/1587617942_3333.png","surplus_time":560719}]
     * count : 46
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
         * goods_id : 6
         * sort_order : 100
         * is_promote : 1
         * goods_name : 日本Artiral 日抛型 Artiral  Black 10枚
         * virtual_sales : 0
         * suppliers_id : 2
         * preferential_price : 70.20
         * product_price : 78.00
         * product_id : 390
         * search_attr : 133|134
         * color_id : 133
         * promote_start_date : 1586707200
         * promote_end_date : 1590940800
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585726897_8513.png
         * surplus_time : 560719
         */

        private String goods_id;
        private String sort_order;
        private int is_promote;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private String preferential_price;
        private String product_price;
        private String product_id;
        private String search_attr;
        private String color_id;
        private String promote_start_date;
        private String promote_end_date;
        private String goods_thumb;
        private int surplus_time;
        private String short_flag;

        public String getShort_flag() {
            return short_flag;
        }

        public void setShort_flag(String short_flag) {
            this.short_flag = short_flag;
        }



        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public int getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(int is_promote) {
            this.is_promote = is_promote;
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

        public String getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(String preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
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

        public int getSurplus_time() {
            return surplus_time;
        }

        public void setSurplus_time(int surplus_time) {
            this.surplus_time = surplus_time;
        }
    }
}
