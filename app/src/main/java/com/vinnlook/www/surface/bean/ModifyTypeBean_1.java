package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public class ModifyTypeBean_1 extends BaseBean {
    /**
     * list : [{"goods_number":"2","rec_id":"1199","goods_id":"392","goods_attr":"13716|13759|13790","product_id":"20434","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"Juicy Drop 日抛","goods_attr_id":"13716|13759|13790","number":"140","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13790","color_id":"13790","goods_thumb":"http://img.jealook.com/backend/20201111/1605089590_8770.png","goods_attr_name":"mint cream,10枚,-4.50"},{"goods_number":"3","rec_id":"1198","goods_id":"392","goods_attr":"13716|13764|13789","product_id":"20408","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"Juicy Drop 日抛","goods_attr_id":"13716|13764|13789","number":"40","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13789","color_id":"13789","goods_thumb":"http://img.jealook.com/backend/20201111/1605089590_4473.png","goods_attr_name":"honey dew,10枚,-5.75"},{"goods_number":"5","rec_id":"1197","goods_id":"392","goods_attr":"13716|13752|13789","product_id":"20397","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"Juicy Drop 日抛","goods_attr_id":"13716|13752|13789","number":"51","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13789","color_id":"13789","goods_thumb":"http://img.jealook.com/backend/20201111/1605089590_4473.png","goods_attr_name":"honey dew,10枚,-2.75"},{"goods_number":"16","rec_id":"1196","goods_id":"18","goods_attr":"493|501|505","product_id":"1801","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598422096","promote_end_date":"1599029227","goods_name":"eRouge双周抛型","goods_attr_id":"493|501|505","number":"13","product_price":"145.00","preferential_price":"125.00","search_attr":"493|505","color_id":"505","goods_thumb":"http://img.jealook.com/backend/20200928/1601257074_3179.png","goods_attr_name":"Clarity Brown,6枚,-5.00"},{"goods_number":"8","rec_id":"1195","goods_id":"392","goods_attr":"13716|13785|13789","product_id":"20389","is_check":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"Juicy Drop 日抛","goods_attr_id":"13716|13785|13789","number":"8","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13789","color_id":"13789","goods_thumb":"http://img.jealook.com/backend/20201111/1605089590_4473.png","goods_attr_name":"honey dew,10枚,-0.75"}]
     * is_all : 0
     */

    private int is_all;
    private List<ListBean> list;

    public int getIs_all() {
        return is_all;
    }

    public void setIs_all(int is_all) {
        this.is_all = is_all;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goods_number : 2
         * rec_id : 1199
         * goods_id : 392
         * goods_attr : 13716|13759|13790
         * product_id : 20434
         * is_check : 0
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * goods_name : Juicy Drop 日抛
         * goods_attr_id : 13716|13759|13790
         * number : 140
         * product_price : 115.00
         * preferential_price : 0.00
         * search_attr : 13716|13790
         * color_id : 13790
         * goods_thumb : http://img.jealook.com/backend/20201111/1605089590_8770.png
         * goods_attr_name : mint cream,10枚,-4.50
         */

        private String goods_number;
        private String rec_id;
        private String goods_id;
        private String goods_attr;
        private String product_id;
        private String is_check;
        private String suppliers_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String goods_name;
        private String goods_attr_id;
        private String number;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String color_id;
        private String goods_thumb;
        private String goods_attr_name;

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getIs_check() {
            return is_check;
        }

        public void setIs_check(String is_check) {
            this.is_check = is_check;
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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_attr_id() {
            return goods_attr_id;
        }

        public void setGoods_attr_id(String goods_attr_id) {
            this.goods_attr_id = goods_attr_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
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
