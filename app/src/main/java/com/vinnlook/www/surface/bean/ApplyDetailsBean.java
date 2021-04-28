package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class ApplyDetailsBean extends BaseBean {
    /**
     * address : 陕西省咸阳市其他区西咸新区空港综合保税区
     * receiver_tel : 17730600821
     * receiver_name : VINN美瞳海外专营店
     * price : 28.20
     * create_time : 2021-02-20 16:09:22
     * type : 1
     * status : 2
     * msg :
     * goods_status : 2
     * order_id : 1941
     * id : 557
     * list : [{"id":"557","type":"1","status":"2","msg":"","price":"18.80","goods_status":"2","create_time":"2021-02-20 16:09:22","order_sn":"EC2021022016090471","order_id":"1941","is_all_refund":"0","is_refund_all":"1","rec_id":"2490","goods_name":"Rohto乐敦 日抛 Crystal  Gray 5片","goods_price":"9.90","goods_number":"1","suppliers_id":"2","image":"http://img.jealook.com/backend/20210113/1610518009_3613.png","attr_name":"Crystal  Gray,5片,-5.50"},{"id":"558","type":"1","status":"2","msg":"","price":"9.40","goods_status":"2","create_time":"2021-02-20 16:09:22","order_sn":"EC2021022016090471","order_id":"1941","is_all_refund":"0","is_refund_all":"1","rec_id":"2491","goods_name":"Rohto乐敦 日抛 Larimar Blue 5片","goods_price":"9.90","goods_number":"1","suppliers_id":"2","image":"http://img.jealook.com/backend/20210113/1610518009_5394.png","attr_name":"Larimar Blue,5片,-5.50"}]
     */

    private String address;
    private String receiver_tel;
    private String receiver_name;
    private String price;
    private String create_time;
    private String type;
    private String status;
    private String msg;
    private String goods_status;
    private String order_id;
    private String id;
    private List<ListBean> list;
    private String is_init;

    public String getIs_init() {
        return is_init;
    }

    public void setIs_init(String is_init) {
        this.is_init = is_init;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver_tel() {
        return receiver_tel;
    }

    public void setReceiver_tel(String receiver_tel) {
        this.receiver_tel = receiver_tel;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getGoods_status() {
        return goods_status;
    }

    public void setGoods_status(String goods_status) {
        this.goods_status = goods_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 557
         * type : 1
         * status : 2
         * msg :
         * price : 18.80
         * goods_status : 2
         * create_time : 2021-02-20 16:09:22
         * order_sn : EC2021022016090471
         * order_id : 1941
         * is_all_refund : 0
         * is_refund_all : 1
         * rec_id : 2490
         * goods_name : Rohto乐敦 日抛 Crystal  Gray 5片
         * goods_price : 9.90
         * goods_number : 1
         * suppliers_id : 2
         * image : http://img.jealook.com/backend/20210113/1610518009_3613.png
         * attr_name : Crystal  Gray,5片,-5.50
         */

        private String id;
        private String type;
        private String status;
        private String msg;
        private String price;
        private String goods_status;
        private String create_time;
        private String order_sn;
        private String order_id;
        private String is_all_refund;
        private String is_refund_all;
        private String rec_id;
        private String goods_name;
        private String goods_price;
        private String goods_number;
        private String suppliers_id;
        private String image;
        private String attr_name;
        private String goods_id;
        private String search_attr;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoods_status() {
            return goods_status;
        }

        public void setGoods_status(String goods_status) {
            this.goods_status = goods_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIs_all_refund() {
            return is_all_refund;
        }

        public void setIs_all_refund(String is_all_refund) {
            this.is_all_refund = is_all_refund;
        }

        public String getIs_refund_all() {
            return is_refund_all;
        }

        public void setIs_refund_all(String is_refund_all) {
            this.is_refund_all = is_refund_all;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public String getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(String suppliers_id) {
            this.suppliers_id = suppliers_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }
    }
}
