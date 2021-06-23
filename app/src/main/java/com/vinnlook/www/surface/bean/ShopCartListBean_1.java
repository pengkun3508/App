package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public class ShopCartListBean_1 extends BaseBean {
    /**
     * list : [{"goods_number":"1","rec_id":"498","goods_id":"101","goods_attr":"3699|3707|3726","product_id":"8934","is_check":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"1597128337","promote_end_date":"1597680000","goods_name":"Jill stuart吉尔斯图亚特 日抛型","goods_attr_id":"3699|3707|3726","number":"4","product_price":"88.00","preferential_price":"59.00","search_attr":"3699|3726","color_id":"3726","goods_thumb":"http://img.jealook.com/backend/20200403/1585893062_2190.png","goods_attr_name":"宝石靓蓝,10枚,-2.75"},{"goods_number":"6","rec_id":"464","goods_id":"86","goods_attr":"3223|3231|3249","product_id":"7058","is_check":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"中国版 Naturali日抛","goods_attr_id":"3223|3231|3249","number":"3","product_price":"245.00","preferential_price":"0.00","search_attr":"3223|3249","color_id":"3249","goods_thumb":"http://img.jealook.com/backend/20200424/1587713337_2602.png","goods_attr_name":"Charming Hazel,30枚,-2.75"},{"goods_number":"8","rec_id":"463","goods_id":"86","goods_attr":"3223|3225|3249","product_id":"7052","is_check":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"中国版 Naturali日抛","goods_attr_id":"3223|3225|3249","number":"15","product_price":"245.00","preferential_price":"0.00","search_attr":"3223|3249","color_id":"3249","goods_thumb":"http://img.jealook.com/backend/20200424/1587713337_2602.png","goods_attr_name":"Charming Hazel,30枚,-1.25"},{"goods_number":"5","rec_id":"455","goods_id":"86","goods_attr":"3223|3226|3249","product_id":"7053","is_check":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","goods_name":"中国版 Naturali日抛","goods_attr_id":"3223|3226|3249","number":"0","product_price":"245.00","preferential_price":"0.00","search_attr":"3223|3249","color_id":"3249","goods_thumb":"http://img.jealook.com/backend/20200424/1587713337_2602.png","goods_attr_name":"Charming Hazel,30枚,-1.50"},{"goods_number":"1","rec_id":"438","goods_id":"166","goods_attr":"7250|7251|7267","product_id":"9534","is_check":"1","suppliers_id":"1","is_promote":0,"promote_start_date":"1599550938","promote_end_date":"1600099200","goods_name":"Seed dreaming 以梦 日抛","goods_attr_id":"7250|7251|7267","number":"10","product_price":"99.00","preferential_price":"68.00","search_attr":"7251|7284","color_id":"7284","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_2068.png","goods_attr_name":"Aquamarine碧波绿,10枚,-1.25"}]
     * is_all : 0
     * title : 自营仓
     * waybill : 部分地区满60包邮
     * id : 1
     */

    private int is_all;
    private String title;
    private String waybill;
    private String image;
    private int id;
    private List<ListBean> list;
    private DiscountBean discount;

    public DiscountBean getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountBean discount) {
        this.discount = discount;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIs_all() {
        return is_all;
    }

    public void setIs_all(int is_all) {
        this.is_all = is_all;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
         * goods_number : 1
         * rec_id : 498
         * goods_id : 101
         * goods_attr : 3699|3707|3726
         * product_id : 8934
         * is_check : 0
         * suppliers_id : 1
         * is_promote : 0
         * promote_start_date : 1597128337
         * promote_end_date : 1597680000
         * goods_name : Jill stuart吉尔斯图亚特 日抛型
         * goods_attr_id : 3699|3707|3726
         * number : 4
         * product_price : 88.00
         * preferential_price : 59.00
         * search_attr : 3699|3726
         * color_id : 3726
         * goods_thumb : http://img.jealook.com/backend/20200403/1585893062_2190.png
         * goods_attr_name : 宝石靓蓝,10枚,-2.75
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
        private String active_id;
        private String short_flag;
        private String active_type;
        private List<ListBeanX> list;
        private String long_flag;

        public String getActive_type() {
            return active_type;
        }

        public void setActive_type(String active_type) {
            this.active_type = active_type;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public String getActive_id() {
            return active_id;
        }

        public void setActive_id(String active_id) {
            this.active_id = active_id;
        }

        public String getShort_flag() {
            return short_flag;
        }

        public void setShort_flag(String short_flag) {
            this.short_flag = short_flag;
        }

        public String getLong_flag() {
            return long_flag;
        }

        public void setLong_flag(String long_flag) {
            this.long_flag = long_flag;
        }


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

        public static class ListBeanX {

            /**
             * goods_number : 1
             * rec_id : 2146
             * goods_id : 4
             * goods_name : Angelcolor bambi series日抛型 10枚 Cream Pink 10枚
             * goods_attr :
             * product_id : 14618
             * is_check : 0
             * market_price : 0.00
             * suppliers_id : 2
             * is_promote : 0
             * promote_start_date : 1594656000
             * promote_end_date : 1595260800
             * goods_attr_id : 87|89|8758
             * number : 11
             * product_price : 105.00
             * preferential_price : 0.00
             * search_attr : 89|8758
             * color_id : 8758
             * goods_thumb : http://img.jealook.com/backend/20200821/1597999575_4777.png
             * goods_attr_name : Cream Pink,10枚,-0.00
             */

            private String goods_number;
            private String rec_id;
            private String goods_id;
            private String goods_name;
            private String goods_attr;
            private String product_id;
            private String is_check;
            private String market_price;
            private String suppliers_id;
            private int is_promote;
            private String promote_start_date;
            private String promote_end_date;
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

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
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

    public class DiscountBean {
        /**
         * end_time : 1624982390
         * type : 2
         * reduced : 5
         * min_money : 20.00
         * residue_time : 655236
         * content : 满20.00送5元
         */

        private String end_time;
        private String type;
        private String reduced;
        private String min_money;
        private int residue_time;
        private String content;

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReduced() {
            return reduced;
        }

        public void setReduced(String reduced) {
            this.reduced = reduced;
        }

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public int getResidue_time() {
            return residue_time;
        }

        public void setResidue_time(int residue_time) {
            this.residue_time = residue_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
