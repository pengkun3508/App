package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/10/15$
 * @Author:pk$
 */
public class HomePublicListBean extends BaseBean {

    /**
     * list : [{"product_price":"88.00","search_attr":"3666|3692","preferential_price":"0.00","color_id":"3692","goods_id":"100","goods_name":"Erouge爱如久 双周抛型 璀璨金棕 6枚","virtual_sales":"2","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200927/1601186411_5536.png"},{"product_price":"88.00","search_attr":"3699|3726","preferential_price":"59.00","color_id":"3726","goods_id":"101","goods_name":"Jill stuart吉尔斯图亚特 日抛型 宝石靓蓝 10枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"1597128337","promote_end_date":"1597680000","brand_name":"Jill Stuart","goods_thumb":"http://img.jealook.com/backend/20200403/1585893062_2190.png"},{"product_price":"55.00","search_attr":"12903|12960","preferential_price":"0.00","color_id":"12960","goods_id":"382","goods_name":"Elebelle 日抛 Half brown 10枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"elebelle","goods_thumb":"http://img.jealook.com/backend/20200929/1601371230_5324.png"},{"product_price":"88.00","search_attr":"3666|3695","preferential_price":"0.00","color_id":"3695","goods_id":"100","goods_name":"Erouge爱如久 双周抛型 闪耀柔棕 6枚","virtual_sales":"2","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200927/1601186411_3490.png"},{"product_price":"149.00","search_attr":"3498|3527","preferential_price":"0.00","color_id":"3527","goods_id":"95","goods_name":"Givre绮芙丽 日抛型 杏桃甜棕 30枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Givre","goods_thumb":"http://img.jealook.com/backend/20200403/1585896648_7202.png"},{"product_price":"85.00","search_attr":"3282|3283","preferential_price":"0.00","color_id":"3282","goods_id":"88","goods_name":"中国版 Naturali日抛 Charming Brown 10枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Naturali","goods_thumb":"http://img.jealook.com/backend/20200415/1586940085_8339.png"},{"product_price":"29.00","search_attr":"3434|3463","preferential_price":"0.00","color_id":"3463","goods_id":"93","goods_name":"Femii妃蜜莉 月抛型 傲娇褐绿 1枚","virtual_sales":"11","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Femii","goods_thumb":"http://img.jealook.com/backend/20200622/1592807337_8571.png"},{"product_price":"99.00","search_attr":"7251|7281","preferential_price":"68.00","color_id":"7281","goods_id":"166","goods_name":"Seed dreaming 以梦 日抛 Cloudtide云汐褐 10枚","virtual_sales":"9","suppliers_id":"1","is_promote":0,"promote_start_date":"1599550938","promote_end_date":"1600099200","brand_name":"以梦seed","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_8961.png"},{"product_price":"108.00","search_attr":"3402|3429","preferential_price":"0.00","color_id":"3429","goods_id":"92","goods_name":"Femii妃蜜莉 月抛型 拿铁咖棕 6枚","virtual_sales":"1","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Femii","goods_thumb":"http://img.jealook.com/backend/20200622/1592807542_6007.png"},{"product_price":"75.00","search_attr":"3313|3337","preferential_price":"58.00","color_id":"3337","goods_id":"89","goods_name":"中国版 Chouchou日抛 水蜜桃牛奶 10枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"1597128282","promote_end_date":"1597680000","brand_name":"Chouchou","goods_thumb":"http://img.jealook.com/backend/20200603/1591168394_3968.png"}]
     * count : 70
     * count_page : 7
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
         * product_price : 88.00
         * search_attr : 3666|3692
         * preferential_price : 0.00
         * color_id : 3692
         * goods_id : 100
         * goods_name : Erouge爱如久 双周抛型 璀璨金棕 6枚
         * virtual_sales : 2
         * suppliers_id : 1
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : eRouge
         * goods_thumb : http://img.jealook.com/backend/20200927/1601186411_5536.png
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

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }
    }
}
