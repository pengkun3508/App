package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/26$
 * @Author:pk$
 */
public class ConfirmOrderBean extends BaseBean {
    /**
     * goods_price : 1100.00
     * price : 1085.00
     * post_fee : 0.00
     * zy_post_fee : 0.00
     * ht_post_fee : 0.00
     * tax_total : 58.72
     * discountPriceList : [{"name":"优惠券","price":15}]
     * address_list : [{"address_refer":"北京 北京市 东城区","address":"明辉丢雷","address_name":"彭堃","mobile":"151****5255","address_id":"51","is_default":"1"},{"address_refer":"北京 北京市 崇文区","address":"Mr你就听","address_name":"哦清图","mobile":"151****5251","address_id":"160","is_default":"0"},{"address_refer":"内蒙古自治区 呼和浩特市 新城区","address":"哦明明哦一下","address_name":"你一五十斤","mobile":"151****5251","address_id":"178","is_default":"0"}]
     * real_list : [{"id_card":"6****************7","name":"彭堃","is_default":"1","id":"136"}]
     * rec_id : 1613,1616,1614,1611,770
     * coupon_price : 15.00
     * bonus_info : {"start_time":"1607399041","end_time":"1608864354","id":"137","discount_id":"18","order_id":"0","coupon_name":"测试专用","use_shop_type":"1","reduced":"15","type":"2","min_money":"1000.00","use_shop":""}
     * zy_send_list : [{"product_id":"9412","goods_id":"166","is_promote":0,"promote_start_date":"1599550938","promote_end_date":"1600099200","suppliers_id":"1","goods_name":"Seed dreaming 以梦 日抛 Corn silk米绸褐 10枚","virtual_sales":"16","attr_value":"Corn silk米绸褐","goods_attr_id":"7249|7250|7251","number":1,"product_price":"99.00","preferential_price":"68.00","search_attr":"7250|7251","color_id":"7250","goods_attr":"7249|7250|7251","goods_attr_name":"Corn silk米绸褐,10枚,-0.00","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_7202.png"}]
     * ht_send_list : [{"product_id":"6086","goods_id":"64","is_promote":0,"promote_start_date":"1599550794","promote_end_date":"1600099200","suppliers_id":"2","goods_name":"Revia日抛型 Private02 10枚","virtual_sales":"1","attr_value":"Private02","goods_attr_id":"1824|1825|5508","number":1,"product_price":"105.00","preferential_price":"95.00","search_attr":"1824|1825","color_id":"1824","goods_attr":"1824|1825|5508","goods_attr_name":"Private02,10枚,-1.75","goods_thumb":"http://img.jealook.com/backend/20200927/1601191996_3936.png"}]
     * order_ad : {"value":"","type":"","id":"","photo":"","list":{"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}}
     * zy_shop_list : {"title":"自营仓（咸阳自营仓）","type":1,"shop_list":[{"product_id":"9470","goods_id":"166","product_number":"7","product_price":"99.00","preferential_price":"68.00","goods_attr":"7251|7279|7281","color_id":"7281","search_attr":"7251|7281","product_sn":"4961243174766","is_promote":"1","promote_start_date":"1599550938","promote_end_date":"1600099200","suppliers_id":"1","goods_name":"Seed dreaming 以梦 日抛 Cloudtide云汐褐 10枚","bonus_type_id":"0","goods_attr_name":"Cloudtide云汐褐,10枚,-9.50","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_8961.png","number":"4"}],"waybillList":[{"id":5,"name":"申通快递","image":"https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58","content":"自营仓满60包邮（不含偏远）","price":10},{"id":10,"name":"顺丰陆运","image":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=366146255,46260424&fm=26&gp=0.jpg","content":"自营仓满60包邮（不含顺丰）","price":18}]}
     * ht_shop_list : {"title":"海淘仓（西安保税仓）","type":2,"shop_list":[{"product_id":"845","goods_id":"12","product_number":"4","product_price":"95.00","preferential_price":"0.00","goods_attr":"299|314|323","color_id":"323","search_attr":"299|323","product_sn":"AS4589599405794","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Chouchou日抛型 Frozen HazeL 10枚","bonus_type_id":"0","goods_attr_name":"Frozen HazeL,10枚,-4.75","goods_thumb":"http://img.jealook.com/backend/20200401/1585727870_6792.png","number":"4"},{"product_id":"1473","goods_id":"16","product_number":"25","product_price":"108.00","preferential_price":"0.00","goods_attr":"421|442|443","color_id":"443","search_attr":"421|443","product_sn":"AS4589757685396","is_promote":"1","promote_start_date":"1598421716","promote_end_date":"1598976000","suppliers_id":"2","goods_name":"Envie日抛型 旧版 Olive Brown 10枚","bonus_type_id":"0","goods_attr_name":"旧版 Olive Brown,10枚,-8.00","goods_thumb":"http://img.jealook.com/backend/20200917/1600330899_9487.png","number":"1"},{"product_id":"2230","goods_id":"20","product_number":"10","product_price":"108.00","preferential_price":"88.00","goods_attr":"564|566|588","color_id":"588","search_attr":"564|588","product_sn":"AS4547683502286","is_promote":"1","promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"2","goods_name":"Evercolor日抛型 Aqua Beige 10枚","bonus_type_id":"0","goods_attr_name":"Aqua Beige,10枚,-1.25","goods_thumb":"http://img.jealook.com/backend/20200401/1585734282_2780.png","number":"1"},{"product_id":"18745","goods_id":"16","product_number":"11","product_price":"108.00","preferential_price":"0.00","goods_attr":"421|427|13211","color_id":"13211","search_attr":"421|13211","product_sn":"AS4589757684566","is_promote":"1","promote_start_date":"1598421716","promote_end_date":"1598976000","suppliers_id":"2","goods_name":"Envie日抛型 新版 Plum Black 10枚","bonus_type_id":"0","goods_attr_name":"新版 Plum Black,10枚,-2.25","goods_thumb":"http://img.jealook.com/backend/20201013/1602571106_3129.png","number":"1"}],"waybillList":[{"id":5,"name":"申通快递","image":"https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58","content":"海淘仓满200包邮（不含偏远）","price":10}]}
     */

    private String goods_price;
    private String price;
    private String post_fee;
    private String zy_post_fee;
    private String ht_post_fee;
    private String tax_total;
    private String rec_id;
    private String coupon_price;
    private BonusInfoBean bonus_info;
    private OrderAdBean order_ad;
    private ZyShopListBean zy_shop_list;
    private HtShopListBean ht_shop_list;
    private List<DiscountPriceListBean> discountPriceList;
    private List<AddressListBean> address_list;
    private List<RealListBean> real_list;
    private List<ZySendListBean> zy_send_list;
    private List<HtSendListBean> ht_send_list;
    private String alert_image;
    private boolean is_member;
    private String member_price;
    private String member_order_price;
    private String is_group;

    public String getIs_group() {
        return is_group;
    }

    public void setIs_group(String is_group) {
        this.is_group = is_group;
    }

    public boolean getIs_member() {
        return is_member;
    }

    public void setIs_member(boolean is_member) {
        this.is_member = is_member;
    }

    public String getMember_price() {
        return member_price;
    }

    public void setMember_price(String member_price) {
        this.member_price = member_price;
    }

    public String getMember_order_price() {
        return member_order_price;
    }

    public void setMember_order_price(String member_order_price) {
        this.member_order_price = member_order_price;
    }



    public String getAlert_image() {
        return alert_image;
    }

    public void setAlert_image(String alert_image) {
        this.alert_image = alert_image;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPost_fee() {
        return post_fee;
    }

    public void setPost_fee(String post_fee) {
        this.post_fee = post_fee;
    }

    public String getZy_post_fee() {
        return zy_post_fee;
    }

    public void setZy_post_fee(String zy_post_fee) {
        this.zy_post_fee = zy_post_fee;
    }

    public String getHt_post_fee() {
        return ht_post_fee;
    }

    public void setHt_post_fee(String ht_post_fee) {
        this.ht_post_fee = ht_post_fee;
    }

    public String getTax_total() {
        return tax_total;
    }

    public void setTax_total(String tax_total) {
        this.tax_total = tax_total;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public BonusInfoBean getBonus_info() {
        return bonus_info;
    }

    public void setBonus_info(BonusInfoBean bonus_info) {
        this.bonus_info = bonus_info;
    }

    public OrderAdBean getOrder_ad() {
        return order_ad;
    }

    public void setOrder_ad(OrderAdBean order_ad) {
        this.order_ad = order_ad;
    }

    public ZyShopListBean getZy_shop_list() {
        return zy_shop_list;
    }

    public void setZy_shop_list(ZyShopListBean zy_shop_list) {
        this.zy_shop_list = zy_shop_list;
    }

    public HtShopListBean getHt_shop_list() {
        return ht_shop_list;
    }

    public void setHt_shop_list(HtShopListBean ht_shop_list) {
        this.ht_shop_list = ht_shop_list;
    }

    public List<DiscountPriceListBean> getDiscountPriceList() {
        return discountPriceList;
    }

    public void setDiscountPriceList(List<DiscountPriceListBean> discountPriceList) {
        this.discountPriceList = discountPriceList;
    }

    public List<AddressListBean> getAddress_list() {
        return address_list;
    }

    public void setAddress_list(List<AddressListBean> address_list) {
        this.address_list = address_list;
    }

    public List<RealListBean> getReal_list() {
        return real_list;
    }

    public void setReal_list(List<RealListBean> real_list) {
        this.real_list = real_list;
    }

    public List<ZySendListBean> getZy_send_list() {
        return zy_send_list;
    }

    public void setZy_send_list(List<ZySendListBean> zy_send_list) {
        this.zy_send_list = zy_send_list;
    }

    public List<HtSendListBean> getHt_send_list() {
        return ht_send_list;
    }

    public void setHt_send_list(List<HtSendListBean> ht_send_list) {
        this.ht_send_list = ht_send_list;
    }

    public static class BonusInfoBean {
        /**
         * start_time : 1607399041
         * end_time : 1608864354
         * id : 137
         * discount_id : 18
         * order_id : 0
         * coupon_name : 测试专用
         * use_shop_type : 1
         * reduced : 15
         * type : 2
         * min_money : 1000.00
         * use_shop :
         */

        private String start_time;
        private String end_time;
        private String id;
        private String discount_id;
        private String order_id;
        private String coupon_name;
        private String use_shop_type;
        private String reduced;
        private String type;
        private String min_money;
        private String use_shop;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(String discount_id) {
            this.discount_id = discount_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getUse_shop_type() {
            return use_shop_type;
        }

        public void setUse_shop_type(String use_shop_type) {
            this.use_shop_type = use_shop_type;
        }

        public String getReduced() {
            return reduced;
        }

        public void setReduced(String reduced) {
            this.reduced = reduced;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public String getUse_shop() {
            return use_shop;
        }

        public void setUse_shop(String use_shop) {
            this.use_shop = use_shop;
        }
    }

    public static class OrderAdBean {
        /**
         * value :
         * type :
         * id :
         * photo :
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String photo;
        private ListBean list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id :
             * search_attr :
             * active_id :
             * url :
             * text :
             * id :
             */

            private String goods_id;
            private String search_attr;
            private String active_id;
            private String url;
            private String text;
            private String id;

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

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class ZyShopListBean {
        /**
         * title : 自营仓（咸阳自营仓）
         * type : 1
         * shop_list : [{"product_id":"9470","goods_id":"166","product_number":"7","product_price":"99.00","preferential_price":"68.00","goods_attr":"7251|7279|7281","color_id":"7281","search_attr":"7251|7281","product_sn":"4961243174766","is_promote":"1","promote_start_date":"1599550938","promote_end_date":"1600099200","suppliers_id":"1","goods_name":"Seed dreaming 以梦 日抛 Cloudtide云汐褐 10枚","bonus_type_id":"0","goods_attr_name":"Cloudtide云汐褐,10枚,-9.50","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_8961.png","number":"4"}]
         * waybillList : [{"id":5,"name":"申通快递","image":"https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58","content":"自营仓满60包邮（不含偏远）","price":10},{"id":10,"name":"顺丰陆运","image":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=366146255,46260424&fm=26&gp=0.jpg","content":"自营仓满60包邮（不含顺丰）","price":18}]
         */

        private String title;
        private int type;
        private List<ShopListBean> shop_list;
        private List<WaybillListBean> waybillList;
        private String image;
        private String logo_image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLogo_image() {
            return logo_image;
        }

        public void setLogo_image(String logo_image) {
            this.logo_image = logo_image;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ShopListBean> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBean> shop_list) {
            this.shop_list = shop_list;
        }

        public List<WaybillListBean> getWaybillList() {
            return waybillList;
        }

        public void setWaybillList(List<WaybillListBean> waybillList) {
            this.waybillList = waybillList;
        }

        public static class ShopListBean {
            /**
             * product_id : 9470
             * goods_id : 166
             * product_number : 7
             * product_price : 99.00
             * preferential_price : 68.00
             * goods_attr : 7251|7279|7281
             * color_id : 7281
             * search_attr : 7251|7281
             * product_sn : 4961243174766
             * is_promote : 1
             * promote_start_date : 1599550938
             * promote_end_date : 1600099200
             * suppliers_id : 1
             * goods_name : Seed dreaming 以梦 日抛 Cloudtide云汐褐 10枚
             * bonus_type_id : 0
             * goods_attr_name : Cloudtide云汐褐,10枚,-9.50
             * goods_thumb : http://img.jealook.com/backend/20200612/1591936019_8961.png
             * number : 4
             */

            private String product_id;
            private String goods_id;
            private String product_number;
            private String product_price;
            private String preferential_price;
            private String goods_attr;
            private String color_id;
            private String search_attr;
            private String product_sn;
            private String is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String suppliers_id;
            private String goods_name;
            private String bonus_type_id;
            private String goods_attr_name;
            private String goods_thumb;
            private String number;
            private String active_name;
            private String is_gift;
            private String payment;

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

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
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

            public String getProduct_number() {
                return product_number;
            }

            public void setProduct_number(String product_number) {
                this.product_number = product_number;
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

            public String getProduct_sn() {
                return product_sn;
            }

            public void setProduct_sn(String product_sn) {
                this.product_sn = product_sn;
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

            public String getBonus_type_id() {
                return bonus_type_id;
            }

            public void setBonus_type_id(String bonus_type_id) {
                this.bonus_type_id = bonus_type_id;
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

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }
        }

        public static class WaybillListBean {
            /**
             * id : 5
             * name : 申通快递
             * image : https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58
             * content : 自营仓满60包邮（不含偏远）
             * price : 10
             */

            private int id;
            private String name;
            private String image;
            private String content;
            private int price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }

    public static class HtShopListBean {
        /**
         * title : 海淘仓（西安保税仓）
         * type : 2
         * shop_list : [{"product_id":"845","goods_id":"12","product_number":"4","product_price":"95.00","preferential_price":"0.00","goods_attr":"299|314|323","color_id":"323","search_attr":"299|323","product_sn":"AS4589599405794","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Chouchou日抛型 Frozen HazeL 10枚","bonus_type_id":"0","goods_attr_name":"Frozen HazeL,10枚,-4.75","goods_thumb":"http://img.jealook.com/backend/20200401/1585727870_6792.png","number":"4"},{"product_id":"1473","goods_id":"16","product_number":"25","product_price":"108.00","preferential_price":"0.00","goods_attr":"421|442|443","color_id":"443","search_attr":"421|443","product_sn":"AS4589757685396","is_promote":"1","promote_start_date":"1598421716","promote_end_date":"1598976000","suppliers_id":"2","goods_name":"Envie日抛型 旧版 Olive Brown 10枚","bonus_type_id":"0","goods_attr_name":"旧版 Olive Brown,10枚,-8.00","goods_thumb":"http://img.jealook.com/backend/20200917/1600330899_9487.png","number":"1"},{"product_id":"2230","goods_id":"20","product_number":"10","product_price":"108.00","preferential_price":"88.00","goods_attr":"564|566|588","color_id":"588","search_attr":"564|588","product_sn":"AS4547683502286","is_promote":"1","promote_start_date":"1594051200","promote_end_date":"1594656000","suppliers_id":"2","goods_name":"Evercolor日抛型 Aqua Beige 10枚","bonus_type_id":"0","goods_attr_name":"Aqua Beige,10枚,-1.25","goods_thumb":"http://img.jealook.com/backend/20200401/1585734282_2780.png","number":"1"},{"product_id":"18745","goods_id":"16","product_number":"11","product_price":"108.00","preferential_price":"0.00","goods_attr":"421|427|13211","color_id":"13211","search_attr":"421|13211","product_sn":"AS4589757684566","is_promote":"1","promote_start_date":"1598421716","promote_end_date":"1598976000","suppliers_id":"2","goods_name":"Envie日抛型 新版 Plum Black 10枚","bonus_type_id":"0","goods_attr_name":"新版 Plum Black,10枚,-2.25","goods_thumb":"http://img.jealook.com/backend/20201013/1602571106_3129.png","number":"1"}]
         * waybillList : [{"id":5,"name":"申通快递","image":"https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58","content":"海淘仓满200包邮（不含偏远）","price":10}]
         */

        private String title;
        private int type;
        private List<ShopListBeanX> shop_list;
        private List<WaybillListBeanX> waybillList;
        private String image;
        private String logo_image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLogo_image() {
            return logo_image;
        }

        public void setLogo_image(String logo_image) {
            this.logo_image = logo_image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ShopListBeanX> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBeanX> shop_list) {
            this.shop_list = shop_list;
        }

        public List<WaybillListBeanX> getWaybillList() {
            return waybillList;
        }

        public void setWaybillList(List<WaybillListBeanX> waybillList) {
            this.waybillList = waybillList;
        }

        public static class ShopListBeanX {
            /**
             * product_id : 845
             * goods_id : 12
             * product_number : 4
             * product_price : 95.00
             * preferential_price : 0.00
             * goods_attr : 299|314|323
             * color_id : 323
             * search_attr : 299|323
             * product_sn : AS4589599405794
             * is_promote : 0
             * promote_start_date : 0
             * promote_end_date : 0
             * suppliers_id : 2
             * goods_name : Chouchou日抛型 Frozen HazeL 10枚
             * bonus_type_id : 0
             * goods_attr_name : Frozen HazeL,10枚,-4.75
             * goods_thumb : http://img.jealook.com/backend/20200401/1585727870_6792.png
             * number : 4
             */

            private String product_id;
            private String goods_id;
            private String product_number;
            private String product_price;
            private String preferential_price;
            private String goods_attr;
            private String color_id;
            private String search_attr;
            private String product_sn;
            private String is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String suppliers_id;
            private String goods_name;
            private String bonus_type_id;
            private String goods_attr_name;
            private String goods_thumb;
            private String number;
            private String active_name;

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
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

            public String getProduct_number() {
                return product_number;
            }

            public void setProduct_number(String product_number) {
                this.product_number = product_number;
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

            public String getProduct_sn() {
                return product_sn;
            }

            public void setProduct_sn(String product_sn) {
                this.product_sn = product_sn;
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

            public String getBonus_type_id() {
                return bonus_type_id;
            }

            public void setBonus_type_id(String bonus_type_id) {
                this.bonus_type_id = bonus_type_id;
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

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }
        }

        public static class WaybillListBeanX {
            /**
             * id : 5
             * name : 申通快递
             * image : https://dss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3293467486,1009424027&fm=58
             * content : 海淘仓满200包邮（不含偏远）
             * price : 10
             */

            private int id;
            private String name;
            private String image;
            private String content;
            private int price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }

    public static class DiscountPriceListBean {
        /**
         * name : 优惠券
         * price : 15
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

    public static class AddressListBean {
        /**
         * address_refer : 北京 北京市 东城区
         * address : 明辉丢雷
         * address_name : 彭堃
         * mobile : 151****5255
         * address_id : 51
         * is_default : 1
         */

        private String address_refer;
        private String address;
        private String address_name;
        private String mobile;
        private String address_id;
        private String is_default;

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

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }

    public static class RealListBean {
        /**
         * id_card : 6****************7
         * name : 彭堃
         * is_default : 1
         * id : 136
         */

        private String id_card;
        private String name;
        private String is_default;
        private String id;

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ZySendListBean {
        /**
         * product_id : 9412
         * goods_id : 166
         * is_promote : 0
         * promote_start_date : 1599550938
         * promote_end_date : 1600099200
         * suppliers_id : 1
         * goods_name : Seed dreaming 以梦 日抛 Corn silk米绸褐 10枚
         * virtual_sales : 16
         * attr_value : Corn silk米绸褐
         * goods_attr_id : 7249|7250|7251
         * number : 1
         * product_price : 99.00
         * preferential_price : 68.00
         * search_attr : 7250|7251
         * color_id : 7250
         * goods_attr : 7249|7250|7251
         * goods_attr_name : Corn silk米绸褐,10枚,-0.00
         * goods_thumb : http://img.jealook.com/backend/20200612/1591936019_7202.png
         */

        private String product_id;
        private String goods_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String virtual_sales;
        private String attr_value;
        private String goods_attr_id;
        private int number;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String color_id;
        private String goods_attr;
        private String goods_attr_name;
        private String goods_thumb;
        private String type;
        private String rec_id;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getVirtual_sales() {
            return virtual_sales;
        }

        public void setVirtual_sales(String virtual_sales) {
            this.virtual_sales = virtual_sales;
        }

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }

        public String getGoods_attr_id() {
            return goods_attr_id;
        }

        public void setGoods_attr_id(String goods_attr_id) {
            this.goods_attr_id = goods_attr_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
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

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
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

    public static class HtSendListBean {
        /**
         * product_id : 6086
         * goods_id : 64
         * is_promote : 0
         * promote_start_date : 1599550794
         * promote_end_date : 1600099200
         * suppliers_id : 2
         * goods_name : Revia日抛型 Private02 10枚
         * virtual_sales : 1
         * attr_value : Private02
         * goods_attr_id : 1824|1825|5508
         * number : 1
         * product_price : 105.00
         * preferential_price : 95.00
         * search_attr : 1824|1825
         * color_id : 1824
         * goods_attr : 1824|1825|5508
         * goods_attr_name : Private02,10枚,-1.75
         * goods_thumb : http://img.jealook.com/backend/20200927/1601191996_3936.png
         */

        private String product_id;
        private String goods_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String virtual_sales;
        private String attr_value;
        private String goods_attr_id;
        private int number;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String color_id;
        private String goods_attr;
        private String goods_attr_name;
        private String goods_thumb;
        private String type;
        private String rec_id;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getVirtual_sales() {
            return virtual_sales;
        }

        public void setVirtual_sales(String virtual_sales) {
            this.virtual_sales = virtual_sales;
        }

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }

        public String getGoods_attr_id() {
            return goods_attr_id;
        }

        public void setGoods_attr_id(String goods_attr_id) {
            this.goods_attr_id = goods_attr_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
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

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
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
