package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class ApplyListBean extends BaseBean {
    /**
     * list : [{"id":"22","type":"1","status":"1","order_sn":"EC2020111309351666","order_id":"1354","goods_name":"Femii妃蜜莉 月抛型 傲娇褐绿 1枚","goods_price":"29.00","goods_number":"1","image":"http://img.jealook.com/backend/20200622/1592807337_8571.png","attr_name":"傲娇褐绿,1枚,-1.75"}]
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
         * id : 22
         * type : 1
         * status : 1
         * order_sn : EC2020111309351666
         * order_id : 1354
         * goods_name : Femii妃蜜莉 月抛型 傲娇褐绿 1枚
         * goods_price : 29.00
         * goods_number : 1
         * image : http://img.jealook.com/backend/20200622/1592807337_8571.png
         * attr_name : 傲娇褐绿,1枚,-1.75
         */

        private String id;
        private String type;
        private String status;
        private String order_sn;
        private String order_id;
        private String goods_name;
        private String goods_price;
        private String goods_number;
        private String image;
        private String attr_name;
        private String suppliers_id;
        public String getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(String suppliers_id) {
            this.suppliers_id = suppliers_id;
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
