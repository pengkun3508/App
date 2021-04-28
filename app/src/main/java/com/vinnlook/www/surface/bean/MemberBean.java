package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/8/19$
 * @Author:pk$
 */
public class MemberBean extends BaseBean {
    /**
     * info : {"id":"1","name":"年卡会员","price":"88.00","original_price":"188.00"}
     * users : {"member_end_time":"1970-01-01","is_member":"0","user_name":"你来呀","img_url":"http://img.jealook.com/app_img/20200611/20200611174336_53196.jpg"}
     * discount : [{"id":"4","coupon_name":" 购买会员时赠送-1","type":"2","reduced":"1","min_money":"1000.00","use_time_type":"2","use_time":"100","use_time_start":"0","use_time_end":"0","astrict_count":"0","is_member":"3","use_shop_type":"1","content":""},{"id":"5","coupon_name":"购买会员时赠送-2","type":"2","reduced":"1","min_money":"10.00","use_time_type":"2","use_time":"10","use_time_start":"0","use_time_end":"0","astrict_count":"0","is_member":"3","use_shop_type":"1","content":""}]
     * shop : [{"product_price":"149.00","search_attr":"3498|13230","preferential_price":"0.00","color_id":"13230","product_number":"37","goods_id":"95","goods_name":"Givre绮芙丽 日抛型 琥珀魅棕 30枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Givre","goods_thumb":"http://img.jealook.com/backend/20201015/1602743137_7178.png"},{"product_price":"149.00","search_attr":"3342|13245","preferential_price":"158.00","color_id":"13245","product_number":"22","goods_id":"90","goods_name":"Femii 妃蜜莉日抛 星辰子夜灰 30枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Femii","goods_thumb":"http://img.jealook.com/backend/20201016/1602821999_4204.png"},{"product_price":"69.00","search_attr":"8956|8982","preferential_price":"59.00","color_id":"8982","product_number":"5","goods_id":"229","goods_name":"Pienage MiMi系列 日抛型 Mimi Platinum 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598942838","promote_end_date":"1599580800","brand_name":"Pienage","goods_thumb":"http://img.jealook.com/backend/20200827/1598508691_2440.png"},{"product_price":"108.00","search_attr":"421|13210","preferential_price":"0.00","color_id":"13210","product_number":"37","goods_id":"16","goods_name":"Envie日抛型 新版 Olive Brown 10枚","virtual_sales":"7","suppliers_id":"2","is_promote":0,"promote_start_date":"1598421716","promote_end_date":"1598976000","brand_name":"Envie","goods_thumb":"http://img.jealook.com/backend/20201013/1602571106_5572.png"},{"product_price":"115.00","search_attr":"7960|7995","preferential_price":"75.00","color_id":"7995","product_number":"1871","goods_id":"179","goods_name":"MerMer 日抛型 Coral Pink 10枚","virtual_sales":"37","suppliers_id":"2","is_promote":0,"promote_start_date":"1598421953","promote_end_date":"1599029258","brand_name":"new mermer","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_9918.png"},{"product_price":"78.00","search_attr":"9412|9447","preferential_price":"0.00","color_id":"9447","product_number":"10","goods_id":"266","goods_name":"Artiral 日抛型 Artiral Ochre 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Artiral","goods_thumb":"http://img.jealook.com/backend/20200923/1600849251_7734.png"},{"product_price":"115.00","search_attr":"11053|13156","preferential_price":"0.00","color_id":"13156","product_number":"16","goods_id":"342","goods_name":"Topards 日抛型 Strawberry Quartz 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Topards","goods_thumb":"http://img.jealook.com/backend/20201009/1602210082_8408.png"},{"product_price":"145.00","search_attr":"9718|9719","preferential_price":"0.00","color_id":"9718","product_number":"42","goods_id":"275","goods_name":"eRouge 双周抛型 Beige Ombre 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200928/1601258685_8558.png"},{"product_price":"99.00","search_attr":"7251|7282","preferential_price":"68.00","color_id":"7282","product_number":"3","goods_id":"166","goods_name":"Seed dreaming 以梦 日抛 Chestnut糖栗棕 10枚","virtual_sales":"9","suppliers_id":"1","is_promote":0,"promote_start_date":"1599550938","promote_end_date":"1600099200","brand_name":"以梦seed","goods_thumb":"http://img.jealook.com/backend/20200612/1591936019_9414.png"}]
     * spare_price : 0.00
     */

    private InfoBean info;
    private UsersBean users;
    private String spare_price;
    private List<DiscountBean> discount;
    private List<ShopBean> shop;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public String getSpare_price() {
        return spare_price;
    }

    public void setSpare_price(String spare_price) {
        this.spare_price = spare_price;
    }

    public List<DiscountBean> getDiscount() {
        return discount;
    }

    public void setDiscount(List<DiscountBean> discount) {
        this.discount = discount;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public static class InfoBean {
        /**
         * id : 1
         * name : 年卡会员
         * price : 88.00
         * original_price : 188.00
         */

        private String id;
        private String name;
        private String price;
        private String original_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }
    }

    public static class UsersBean {
        /**
         * member_end_time : 1970-01-01
         * is_member : 0
         * user_name : 你来呀
         * img_url : http://img.jealook.com/app_img/20200611/20200611174336_53196.jpg
         */

        private String member_end_time;
        private String is_member;
        private String user_name;
        private String img_url;

        public String getMember_end_time() {
            return member_end_time;
        }

        public void setMember_end_time(String member_end_time) {
            this.member_end_time = member_end_time;
        }

        public String getIs_member() {
            return is_member;
        }

        public void setIs_member(String is_member) {
            this.is_member = is_member;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }

    public static class DiscountBean {
        /**
         * id : 4
         * coupon_name :  购买会员时赠送-1
         * type : 2
         * reduced : 1
         * min_money : 1000.00
         * use_time_type : 2
         * use_time : 100
         * use_time_start : 0
         * use_time_end : 0
         * astrict_count : 0
         * is_member : 3
         * use_shop_type : 1
         * content :
         */

        private String id;
        private String coupon_name;
        private String type;
        private String reduced;
        private String min_money;
        private String use_time_type;
        private String use_time;
        private String use_time_start;
        private String use_time_end;
        private String astrict_count;
        private String is_member;
        private String use_shop_type;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
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

        public String getUse_time_type() {
            return use_time_type;
        }

        public void setUse_time_type(String use_time_type) {
            this.use_time_type = use_time_type;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getUse_time_start() {
            return use_time_start;
        }

        public void setUse_time_start(String use_time_start) {
            this.use_time_start = use_time_start;
        }

        public String getUse_time_end() {
            return use_time_end;
        }

        public void setUse_time_end(String use_time_end) {
            this.use_time_end = use_time_end;
        }

        public String getAstrict_count() {
            return astrict_count;
        }

        public void setAstrict_count(String astrict_count) {
            this.astrict_count = astrict_count;
        }

        public String getIs_member() {
            return is_member;
        }

        public void setIs_member(String is_member) {
            this.is_member = is_member;
        }

        public String getUse_shop_type() {
            return use_shop_type;
        }

        public void setUse_shop_type(String use_shop_type) {
            this.use_shop_type = use_shop_type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ShopBean {
        /**
         * product_price : 149.00
         * search_attr : 3498|13230
         * preferential_price : 0.00
         * color_id : 13230
         * product_number : 37
         * goods_id : 95
         * goods_name : Givre绮芙丽 日抛型 琥珀魅棕 30枚
         * virtual_sales : 0
         * suppliers_id : 1
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : Givre
         * goods_thumb : http://img.jealook.com/backend/20201015/1602743137_7178.png
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String product_number;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private int is_promote;
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

        public String getProduct_number() {
            return product_number;
        }

        public void setProduct_number(String product_number) {
            this.product_number = product_number;
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
    }
}