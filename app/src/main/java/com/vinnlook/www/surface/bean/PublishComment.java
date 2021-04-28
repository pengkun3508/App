package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/21$
 * @Author:pk$
 */
public class PublishComment extends BaseBean {

    /**
     * color_id : 10
     * product_id : 27
     * product_number : 0
     * product_price : 155.00
     * preferential_price : 154.90
     * color : 棕色
     * search_attr : 3|10
     * product_sn : AS4573138926857
     * base_curve :
     * water_content :
     * diameter :
     * coloring_diameter :
     * goods_sn : JLH100001
     * goods_id : 1
     * is_promote : 0
     * promote_start_date : 0
     * promote_end_date : 0
     * suppliers_id : 2
     * shop_name : Angelcolor bambi series 系列日抛型
     * is_new : 1
     * brand_name : Angelcolor bambi seriesvintage
     * surplus_time : 0
     * shop_attr_name : Natural  Brown 20枚
     * details : ["http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868119937.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868232007.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868740208.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868839874.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868915918.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868246461.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868362219.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868283983.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868464453.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868837884.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868284023.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868486721.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868542716.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000868295912.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000869184404.jpg","http://admin.jealook.com/ueditor/php/upload/image/20200821/1598000869998375.jpg"]
     * banner : [{"type":1,"url":"http://img.jealook.com/backend/20200401/1585725300_9963.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585725300_4604.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591173352_4824.png"}]
     * origin : 中国台湾
     * toss_period : 日抛
     * textures : 水胶
     * life_span : 5年（具体见包装）
     * is_show : 0
     */

    private String color_id;
    private String product_id;
    private String product_number;
    private String product_price;
    private String preferential_price;
    private String color;
    private String search_attr;
    private String product_sn;
    private String base_curve;
    private String water_content;
    private String diameter;
    private String coloring_diameter;
    private String goods_sn;
    private String goods_id;
    private int is_promote;
    private String promote_start_date;
    private String promote_end_date;
    private String suppliers_id;
    private String shop_name;
    private String is_new;
    private String brand_name;
    private int surplus_time;
    private String shop_attr_name;
    private String origin;
    private String toss_period;
    private String textures;
    private String life_span;
    private int is_show;
    private List<String> details;
    private List<BannerBean> banner;

    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getBase_curve() {
        return base_curve;
    }

    public void setBase_curve(String base_curve) {
        this.base_curve = base_curve;
    }

    public String getWater_content() {
        return water_content;
    }

    public void setWater_content(String water_content) {
        this.water_content = water_content;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getColoring_diameter() {
        return coloring_diameter;
    }

    public void setColoring_diameter(String coloring_diameter) {
        this.coloring_diameter = coloring_diameter;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getSurplus_time() {
        return surplus_time;
    }

    public void setSurplus_time(int surplus_time) {
        this.surplus_time = surplus_time;
    }

    public String getShop_attr_name() {
        return shop_attr_name;
    }

    public void setShop_attr_name(String shop_attr_name) {
        this.shop_attr_name = shop_attr_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getToss_period() {
        return toss_period;
    }

    public void setToss_period(String toss_period) {
        this.toss_period = toss_period;
    }

    public String getTextures() {
        return textures;
    }

    public void setTextures(String textures) {
        this.textures = textures;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * type : 1
         * url : http://img.jealook.com/backend/20200401/1585725300_9963.png
         */

        private int type;
        private String url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
