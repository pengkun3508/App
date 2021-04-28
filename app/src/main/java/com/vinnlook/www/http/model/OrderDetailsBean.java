package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class OrderDetailsBean extends BaseBean {

    /**
     * order_id : 283
     * order_sn : 202004301139376716951921
     * real_id : 7
     * address_id : 40
     * confirm_time : 2020-05-08 13:57:16
     * order_status : 1
     * shipping_status : 0
     * pay_status : 0
     * postscript :
     * shipping_fee : 0.00
     * bonus : 0.00
     * goods_amount : 1620.20
     * order_amount : 1620.20
     * status : 0
     * real : {"name":"张浩博","id_card":"4****************1","id":"7"}
     * address : {"address_refer":"澳门特别行政区  澳门半岛","address":"纳帕溪谷","address_name":"澳门人","mobile":"005****1655","address_id":"40"}
     * shop_list : [{"product_id":"20","goods_id":"1","goods_attr":"2|3|5042","color_id":"2","search_attr":"2|3","is_promote":"0","promote_start_date":"1586793600","promote_end_date":"1587052800","suppliers_id":"2","goods_name":"日本Angelcolor bambi series Natural 系列日抛型 Natural  Black 20枚","number":"10","product_price":"155.00","rec_id":"313","goods_attr_name":"Natural  Black,20枚,550度","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726474_6911.png"},{"product_id":"435","goods_id":"6","goods_attr":"134|153|160","color_id":"160","search_attr":"134|160","is_promote":"1","promote_start_date":"1586707200","promote_end_date":"1588262400","suppliers_id":"2","goods_name":"日本Artiral 日抛型 Artiral Brown 10枚","number":"1","product_price":"78.00","rec_id":"314","goods_attr_name":"10枚,550度,Artiral Brown","goods_thumb":"http://img.vinnlook.com/backend/20200401/1585726897_6100.png"}]
     */

    private String order_id;
    private String order_sn;
    private String real_id;
    private String address_id;
    private String confirm_time;
    private String order_status;
    private String shipping_status;
    private String pay_status;
    private String postscript;
    private String shipping_fee;
    private String bonus;
    private String goods_amount;
    private String order_amount;
    private int status;
    private RealBean real;
    private AddressBean address;
    private List<ShopListBean> shop_list;
    private String mrakAll;
    private String waybillName;
    private List<DiscountInfoBean> discount_info;
    private String is_comment;
    private String is_all_refund;
    private String is_refund_all;
    private String is_edit_address;

    public String getIs_edit_address() {
        return is_edit_address;
    }

    public void setIs_edit_address(String is_edit_address) {
        this.is_edit_address = is_edit_address;
    }



    public String getIs_refund_all() {
        return is_refund_all;
    }

    public void setIs_refund_all(String is_refund_all) {
        this.is_refund_all = is_refund_all;
    }



    public String getIs_all_refund() {
        return is_all_refund;
    }

    public void setIs_all_refund(String is_all_refund) {
        this.is_all_refund = is_all_refund;
    }




    public String getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(String is_comment) {
        this.is_comment = is_comment;
    }

    public List<DiscountInfoBean> getDiscount_info() {
        return discount_info;
    }

    public void setDiscount_info(List<DiscountInfoBean> discount_info) {
        this.discount_info = discount_info;
    }


    public String getWaybillName() {
        return waybillName;
    }

    public void setWaybillName(String waybillName) {
        this.waybillName = waybillName;
    }

    public String getMrakAll() {
        return mrakAll;
    }

    public void setMrakAll(String mrakAll) {
        this.mrakAll = mrakAll;
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

    public String getReal_id() {
        return real_id;
    }

    public void setReal_id(String real_id) {
        this.real_id = real_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
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

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RealBean getReal() {
        return real;
    }

    public void setReal(RealBean real) {
        this.real = real;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public List<ShopListBean> getShop_list() {
        return shop_list;
    }

    public void setShop_list(List<ShopListBean> shop_list) {
        this.shop_list = shop_list;
    }

    public static class DiscountInfoBean extends BaseBean {

        /**
         * name : 会员优惠
         * price : 7.25
         */

        private String name;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class RealBean extends BaseBean {
        /**
         * name : 张浩博
         * id_card : 4****************1
         * id : 7
         */

        private String name;
        private String id_card;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class AddressBean extends BaseBean {
        /**
         * address_refer : 澳门特别行政区  澳门半岛
         * address : 纳帕溪谷
         * address_name : 澳门人
         * mobile : 005****1655
         * address_id : 40
         */

        private String address_refer;
        private String address;
        private String address_name;
        private String mobile;
        private String address_id;

        public String getAddress_refer() {
            return address_refer;
        }

        public void setAddress_refer(String address_refer) {
            this.address_refer = address_refer;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }
    }

    public static class ShopListBean extends BaseBean {
        /**
         * product_id : 20
         * goods_id : 1
         * goods_attr : 2|3|5042
         * color_id : 2
         * search_attr : 2|3
         * is_promote : 0
         * promote_start_date : 1586793600
         * promote_end_date : 1587052800
         * suppliers_id : 2
         * goods_name : 日本Angelcolor bambi series Natural 系列日抛型 Natural  Black 20枚
         * number : 10
         * product_price : 155.00
         * rec_id : 313
         * comment_id: null,
         * goods_attr_name : Natural  Black,20枚,550度
         * goods_thumb : http://img.vinnlook.com/backend/20200401/1585726474_6911.png
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
        private String number;
        private String product_price;
        private String rec_id;
        private String comment_id;
        private String goods_attr_name;
        private String goods_thumb;
        private String refund_id;
        private String shop_status;//0--无变化；1--提出退款申请；2--退款成功；3--退款失败；
        private String mark;
        private String type;
        private String payment;
        private String is_gift;

        public String getIs_gift() {
            return is_gift;
        }

        public void setIs_gift(String is_gift) {
            this.is_gift = is_gift;
        }



        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }



        public String getShop_status() {
            return shop_status;
        }

        public void setShop_status(String shop_status) {
            this.shop_status = shop_status;
        }


        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }



        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
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
    }
}
