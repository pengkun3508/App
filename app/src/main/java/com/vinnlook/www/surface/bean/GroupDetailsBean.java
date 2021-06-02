package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/5/12$
 * @Author:pk$
 */
public class GroupDetailsBean extends BaseBean {
    /**
     * order_id : 16728
     * order_sn : EC2021051211311314580
     * order_status : 1
     * group_status : 0
     * shipping_status : 0
     * pay_status : 2
     * goods_amount : 99.00
     * order_amount : 93.05
     * bonus : 5.95
     * shipping_fee : 0.00
     * is_comment : 0
     * is_customs : 1
     * pay_time : 1620790279
     * group_active_id : 188
     * order_sn_group : EC2021051211311314580PT
     * is_first_group : 1
     * shop_list : [{"product_id":"13499","goods_id":"310","goods_attr":"10105|10106|10108","color_id":"10105","search_attr":"10105|10106","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rich Standard 双周抛 Innocent Brown 6片","is_gift":"0","number":"1","product_price":"99.00","rec_id":"34763","comment_id":"0","refund_status":"0","order_id":"16728","payment":"94.05","shop_status":0,"type":1,"goods_attr_name":"Innocent Brown,6片,-1.25","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20200911/1599808281_4229.png","refund_id":""}]
     * surplus_time : -5682
     * again_invite : 1
     * group_people : 3
     * num : 1
     * suppliers_id : 2
     * group_list : [{"product_price":"105.00","search_attr":"732|733","preferential_price":null,"color_id":"732","product_number":"46","product_id":"3134","goods_id":"25","goods_name":"Flanmy 日抛 Maple Chiffon 10片","virtual_sales":"17","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943049","promote_end_date":"1599634079","brand_name":"Flanmy","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png","attr_name":"Maple Chiffon 10片","group_price":"104","group_people":"3","still_time":280439,"is_post_fee":"0"},{"product_price":"99.00","search_attr":"10105|10106","preferential_price":null,"color_id":"10105","product_number":"16","product_id":"13497","goods_id":"310","goods_name":"Rich Standard 双周抛 Innocent Brown 6片","virtual_sales":"6","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Richstandard","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20200911/1599808281_4229.png","attr_name":"Innocent Brown 6片","group_price":"98","group_people":"3","still_time":280439,"is_post_fee":"0"}]
     */

    private String order_id;
    private String order_sn;
    private String order_status;
    private String group_status;
    private String shipping_status;
    private String pay_status;
    private String goods_amount;
    private String order_amount;
    private String bonus;
    private String shipping_fee;
    private String is_comment;
    private String is_customs;
    private String pay_time;
    private String group_active_id;
    private String order_sn_group;
    private String is_first_group;
    private int surplus_time;
    private int again_invite;
    private String group_people;
    private int num;
    private int suppliers_id;
    private List<ShopListBean> shop_list;
    private List<GroupListBean> group_list;
    private String goods_id;
    private String search_attr;
    private String group_id;
    private String goods_name;
    private String goods_thumb;
    private String share_code;
    private String is_group;

    public String getIs_group() {
        return is_group;
    }

    public void setIs_group(String is_group) {
        this.is_group = is_group;
    }



    public String getShare_code() {
        return share_code;
    }

    public void setShare_code(String share_code) {
        this.share_code = share_code;
    }



    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }



    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getSearch_attr() {
        return search_attr;
    }

    public void setSearch_attr(String search_attr) {
        this.search_attr = search_attr;
    }



    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(String goods_amount) {
        this.goods_amount = goods_amount;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(String is_comment) {
        this.is_comment = is_comment;
    }

    public String getIs_customs() {
        return is_customs;
    }

    public void setIs_customs(String is_customs) {
        this.is_customs = is_customs;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getGroup_active_id() {
        return group_active_id;
    }

    public void setGroup_active_id(String group_active_id) {
        this.group_active_id = group_active_id;
    }

    public String getOrder_sn_group() {
        return order_sn_group;
    }

    public void setOrder_sn_group(String order_sn_group) {
        this.order_sn_group = order_sn_group;
    }

    public String getIs_first_group() {
        return is_first_group;
    }

    public void setIs_first_group(String is_first_group) {
        this.is_first_group = is_first_group;
    }

    public int getSurplus_time() {
        return surplus_time;
    }

    public void setSurplus_time(int surplus_time) {
        this.surplus_time = surplus_time;
    }

    public int getAgain_invite() {
        return again_invite;
    }

    public void setAgain_invite(int again_invite) {
        this.again_invite = again_invite;
    }

    public String getGroup_people() {
        return group_people;
    }

    public void setGroup_people(String group_people) {
        this.group_people = group_people;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSuppliers_id() {
        return suppliers_id;
    }

    public void setSuppliers_id(int suppliers_id) {
        this.suppliers_id = suppliers_id;
    }

    public List<ShopListBean> getShop_list() {
        return shop_list;
    }

    public void setShop_list(List<ShopListBean> shop_list) {
        this.shop_list = shop_list;
    }

    public List<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    public static class ShopListBean {
        /**
         * product_id : 13499
         * goods_id : 310
         * goods_attr : 10105|10106|10108
         * color_id : 10105
         * search_attr : 10105|10106
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * suppliers_id : 2
         * goods_name : Rich Standard 双周抛 Innocent Brown 6片
         * is_gift : 0
         * number : 1
         * product_price : 99.00
         * rec_id : 34763
         * comment_id : 0
         * refund_status : 0
         * order_id : 16728
         * payment : 94.05
         * shop_status : 0
         * type : 1
         * goods_attr_name : Innocent Brown,6片,-1.25
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20200911/1599808281_4229.png
         * refund_id :
         */

        private String product_id;
        private String goods_id;
        private String goods_attr;
        private String color_id;
        private String search_attr;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String is_gift;
        private String number;
        private String product_price;
        private String rec_id;
        private String comment_id;
        private String refund_status;
        private String order_id;
        private String payment;
        private int shop_status;
        private int type;
        private String goods_attr_name;
        private String goods_thumb;
        private String refund_id;

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

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
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

        public String getIs_gift() {
            return is_gift;
        }

        public void setIs_gift(String is_gift) {
            this.is_gift = is_gift;
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

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(String refund_status) {
            this.refund_status = refund_status;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getShop_status() {
            return shop_status;
        }

        public void setShop_status(int shop_status) {
            this.shop_status = shop_status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGoods_attr_name() {
            return goods_attr_name;
        }

        public void setGoods_attr_name(String goods_attr_name) {
            this.goods_attr_name = goods_attr_name;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }
    }

    public static class GroupListBean {
        /**
         * product_price : 105.00
         * search_attr : 732|733
         * preferential_price : null
         * color_id : 732
         * product_number : 46
         * product_id : 3134
         * goods_id : 25
         * goods_name : Flanmy 日抛 Maple Chiffon 10片
         * virtual_sales : 17
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 1598943049
         * promote_end_date : 1599634079
         * brand_name : Flanmy
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png
         * attr_name : Maple Chiffon 10片
         * group_price : 104
         * group_people : 3
         * still_time : 280439
         * is_post_fee : 0
         */

        private String product_price;
        private String search_attr;
        private Object preferential_price;
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
        private String goods_thumb;
        private String attr_name;
        private String group_price;
        private String group_people;
        private int still_time;
        private String is_post_fee;

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

        public Object getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(Object preferential_price) {
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

        public String getGroup_price() {
            return group_price;
        }

        public void setGroup_price(String group_price) {
            this.group_price = group_price;
        }

        public String getGroup_people() {
            return group_people;
        }

        public void setGroup_people(String group_people) {
            this.group_people = group_people;
        }

        public int getStill_time() {
            return still_time;
        }

        public void setStill_time(int still_time) {
            this.still_time = still_time;
        }

        public String getIs_post_fee() {
            return is_post_fee;
        }

        public void setIs_post_fee(String is_post_fee) {
            this.is_post_fee = is_post_fee;
        }
    }
}
