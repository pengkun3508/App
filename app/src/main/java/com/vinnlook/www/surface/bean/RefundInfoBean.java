package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class RefundInfoBean extends BaseBean {
    /**
     * goods_list : [{"rec_id":"1525","goods_name":"Femii妃蜜莉 月抛型 风尚烟灰 1枚","goods_id":"93","goods_price":35.67,"goods_number":"1","image":"http://img.jealook.com/backend/20200622/1592807337_6291.png","goods_attr_name":"风尚烟灰,1枚,-1.50"},{"rec_id":"1526","goods_name":"Femii妃蜜莉 月抛型 瑰丽彩霞灰 1枚","goods_id":"93","goods_price":35.67,"goods_number":"1","image":"http://img.jealook.com/backend/20200924/1600919532_8849.png","goods_attr_name":"瑰丽彩霞灰,1枚,-3.50"},{"rec_id":"1527","goods_name":"Femii妃蜜莉 月抛型 金光日丽棕 1枚","goods_id":"93","goods_price":35.67,"goods_number":"1","image":"http://img.jealook.com/backend/20200924/1600919532_9823.png","goods_attr_name":"金光日丽棕,1枚,-1.50"}]
     * price : 107.01
     */

    private String price;
    private List<GoodsListBean> goods_list;
    private String post_price;

    public String getPost_price() {
        return post_price;
    }

    public void setPost_price(String post_price) {
        this.post_price = post_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * rec_id : 1525
         * goods_name : Femii妃蜜莉 月抛型 风尚烟灰 1枚
         * goods_id : 93
         * goods_price : 35.67
         * goods_number : 1
         * image : http://img.jealook.com/backend/20200622/1592807337_6291.png
         * goods_attr_name : 风尚烟灰,1枚,-1.50
         */

        private String rec_id;
        private String goods_name;
        private String goods_id;
        private String goods_price;
        private String goods_number;
        private String image;
        private String goods_attr_name;
        private String new_goods_price;


        public String getNew_goods_price() {
            return new_goods_price;
        }

        public void setNew_goods_price(String new_goods_price) {
            this.new_goods_price = new_goods_price;
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

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getGoods_attr_name() {
            return goods_attr_name;
        }

        public void setGoods_attr_name(String goods_attr_name) {
            this.goods_attr_name = goods_attr_name;
        }
    }
}
