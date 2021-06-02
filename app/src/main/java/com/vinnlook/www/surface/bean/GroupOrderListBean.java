package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/5/11$
 * @Author:pk$
 */
public class GroupOrderListBean extends BaseBean {
    /**
     * list : [{"order_id":"16723","order_sn":"EC2021051112291814218","order_status":"1","shipping_status":"0","pay_status":"2","goods_amount":"105.00","order_amount":"98.75","shipping_fee":"0.00","is_comment":"0","is_customs":"1","pay_time":"1620707366","group_active_id":"189","order_sn_group":"","is_first_group":"0","surplus_time":-4371,"content":"人团，仅剩-1人完成完成拼团","shop_list":[{"product_id":"3137","goods_id":"25","goods_attr":"732|733|736","color_id":"732","search_attr":"732|733","is_promote":"0","promote_start_date":"1598943049","promote_end_date":"1599634079","suppliers_id":"2","goods_name":"Flanmy 日抛 Maple Chiffon 10片","is_gift":"0","number":"1","product_price":"105.00","rec_id":"34757","comment_id":"0","refund_status":"0","order_id":"16723","payment":"99.75","shop_status":0,"type":1,"goods_attr_name":"Maple Chiffon,10片,-1.50","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png","refund_id":""}],"num":1,"suppliers_id":2}]
     * count : 1
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
         * order_id : 16723
         * order_sn : EC2021051112291814218
         * order_status : 1
         * shipping_status : 0
         * pay_status : 2
         * goods_amount : 105.00
         * order_amount : 98.75
         * shipping_fee : 0.00
         * is_comment : 0
         * is_customs : 1
         * pay_time : 1620707366
         * group_active_id : 189
         * order_sn_group :
         * is_first_group : 0
         * surplus_time : -4371
         * content : 人团，仅剩-1人完成完成拼团
         * shop_list : [{"product_id":"3137","goods_id":"25","goods_attr":"732|733|736","color_id":"732","search_attr":"732|733","is_promote":"0","promote_start_date":"1598943049","promote_end_date":"1599634079","suppliers_id":"2","goods_name":"Flanmy 日抛 Maple Chiffon 10片","is_gift":"0","number":"1","product_price":"105.00","rec_id":"34757","comment_id":"0","refund_status":"0","order_id":"16723","payment":"99.75","shop_status":0,"type":1,"goods_attr_name":"Maple Chiffon,10片,-1.50","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png","refund_id":""}]
         * num : 1
         * suppliers_id : 2
         */

        private String order_id;
        private String order_sn;
        private String order_status;
        private String shipping_status;
        private String pay_status;
        private String goods_amount;
        private String order_amount;
        private String shipping_fee;
        private String is_comment;
        private String is_customs;
        private String pay_time;
        private String group_active_id;
        private String order_sn_group;
        private String is_first_group;
        private long surplus_time;
        private String group_status;
        private String content;
        private int num;
        private int suppliers_id;
        private List<ShopListBean> shop_list;
        private String time;
        private long countTime;
        private String hour;
        private String minute;
        private String second;
        private String goods_id;
        private String search_attr;
        private String group_id;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
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

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }



        public long getCountTime() {
            return countTime;
        }

        public void setCountTime(long countTime) {
            this.countTime = countTime;
        }



        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }



        public String getGroup_status() {
            return group_status;
        }

        public void setGroup_status(String group_status) {
            this.group_status = group_status;
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

        public long getSurplus_time() {
            return surplus_time;
        }

        public void setSurplus_time(long surplus_time) {
            this.surplus_time = surplus_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public static class ShopListBean {
            /**
             * product_id : 3137
             * goods_id : 25
             * goods_attr : 732|733|736
             * color_id : 732
             * search_attr : 732|733
             * is_promote : 0
             * promote_start_date : 1598943049
             * promote_end_date : 1599634079
             * suppliers_id : 2
             * goods_name : Flanmy 日抛 Maple Chiffon 10片
             * is_gift : 0
             * number : 1
             * product_price : 105.00
             * rec_id : 34757
             * comment_id : 0
             * refund_status : 0
             * order_id : 16723
             * payment : 99.75
             * shop_status : 0
             * type : 1
             * goods_attr_name : Maple Chiffon,10片,-1.50
             * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610359448_7393.png
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
    }
}
