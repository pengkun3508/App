package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:主题-文章详情-1
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class ArticleDetailsBean extends BaseBean {
    /**
     * id : 11
     * name : 小红书热门款（第一期刊）
     * subheading : 小红书热门款（第一期刊）
     * issues :
     * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706093303_18961.png
     * goods_ids : 392
     * collect_num : 3
     * like_num : 1
     * theme_id : 1
     * content : <p><img src="http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/ueditor/php/upload/image/20210706/1625535209432925.jpg" title="1625535209432925.jpg" alt="微信图片_20210705123758.jpg"/></p><p>（3）beeheartb106</p><p>这款是店主在上学时期就很喜欢的一个品牌，想当年日版有段时间停产了，不少妹子每隔一段时间都会来问有货了嘛，当年的火爆度亲身体会，小虹膜的花色设计，日常自然又带点琥珀色小心机～好不容易生产了，才不要错过！</p><p><br/></p><p><img src="http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/ueditor/php/upload/image/20210706/1625535223501789.jpg" title="1625535223501789.jpg" alt="微信图片_20210705123800.jpg"/></p><p>（3）beeheartb106</p><p>这款是店主在上学时期就很喜欢的一个品牌，想当年日版有段时间停产了，不少妹子每隔一段时间都会来问有货了嘛，当年的火爆度亲身体会，小虹膜的花色设计，日常自然又带点琥珀色小心机～好不容易生产了，才不要错过！</p><p><br/></p><p><img src="http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/ueditor/php/upload/image/20210706/1625535230172584.jpg" title="1625535230172584.jpg" alt="微信图片_20210705123802.jpg"/></p><p>（3）beeheartb106</p><p>这款是店主在上学时期就很喜欢的一个品牌，想当年日版有段时间停产了，不少妹子每隔一段时间都会来问有货了嘛，当年的火爆度亲身体会，小虹膜的花色设计，日常自然又带点琥珀色小心机～好不容易生产了，才不要错过！</p><p><br/></p>
     * theme_type : 1
     * content_url : http://shop.jealook.com/v6/html/theme-article-info?id=11
     * goods_list : [{"product_price":"115.00","search_attr":"13716|13788","preferential_price":null,"color_id":"13788","product_number":"124","product_id":"20357","goods_id":"392","goods_name":"Juicy Drop 日抛 Pure Sky（纯净蓝） 10片","virtual_sales":"5819","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","active_image":"/app_img/20210316/20210316202626_37431.png","brand_name":"Juicy Drop","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210316/1615873108_6352.png","attr_name":"Pure Sky（纯净蓝） 10片"}]
     * is_collect : 0
     * is_like : 0
     * list : [{"id":"11","name":"小红书热门款（第一期刊）","subheading":"小红书热门款（第一期刊）","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706093303_18961.png","issues":"","collect_num":"3","like_num":"1","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":706,"height":400,"height_image":"","color":"","is_like":0},{"id":"14","name":"小红书热门款（第二期刊）","subheading":"小红书热门款（第二期刊）","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706094314_72076.png","issues":"","collect_num":"2","like_num":"0","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":706,"height":400,"height_image":"","color":"","is_like":0},{"id":"15","name":"小红书热门款（第三期刊）","subheading":"小红书热门款（第三期刊）","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706094428_91967.png","issues":"","collect_num":"1","like_num":"0","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":706,"height":400,"height_image":"","color":"","is_like":0}]
     */

    private String id;
    private String name;
    private String subheading;
    private String issues;
    private String image;
    private String goods_ids;
    private String collect_num;
    private String like_num;
    private String theme_id;
    private String content;
    private String theme_type;
    private String content_url;
    private int is_collect;
    private int is_like;
    private List<GoodsListBean> goods_list;
    private List<ListBean> list;

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

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoods_ids() {
        return goods_ids;
    }

    public void setGoods_ids(String goods_ids) {
        this.goods_ids = goods_ids;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTheme_type() {
        return theme_type;
    }

    public void setTheme_type(String theme_type) {
        this.theme_type = theme_type;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class GoodsListBean {
        /**
         * product_price : 115.00
         * search_attr : 13716|13788
         * preferential_price : null
         * color_id : 13788
         * product_number : 124
         * product_id : 20357
         * goods_id : 392
         * goods_name : Juicy Drop 日抛 Pure Sky（纯净蓝） 10片
         * virtual_sales : 5819
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * active_image : /app_img/20210316/20210316202626_37431.png
         * brand_name : Juicy Drop
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210316/1615873108_6352.png
         * attr_name : Pure Sky（纯净蓝） 10片
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
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
        private String active_image;
        private String brand_name;
        private String goods_thumb;
        private String attr_name;

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

        public String getActive_image() {
            return active_image;
        }

        public void setActive_image(String active_image) {
            this.active_image = active_image;
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
    }

    public static class ListBean {
        /**
         * id : 11
         * name : 小红书热门款（第一期刊）
         * subheading : 小红书热门款（第一期刊）
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706093303_18961.png
         * issues :
         * collect_num : 3
         * like_num : 1
         * img_url : https://shop.jealook.com/image/default_user_image.png
         * user_name : Vinnlook
         * width : 706
         * height : 400
         * height_image :
         * color :
         * is_like : 0
         */

        private String id;
        private String name;
        private String subheading;
        private String image;
        private String issues;
        private String collect_num;
        private String like_num;
        private String img_url;
        private String user_name;
        private int width;
        private int height;
        private String height_image;
        private String color;
        private int is_like;

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

        public String getSubheading() {
            return subheading;
        }

        public void setSubheading(String subheading) {
            this.subheading = subheading;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIssues() {
            return issues;
        }

        public void setIssues(String issues) {
            this.issues = issues;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getHeight_image() {
            return height_image;
        }

        public void setHeight_image(String height_image) {
            this.height_image = height_image;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }
    }
}
