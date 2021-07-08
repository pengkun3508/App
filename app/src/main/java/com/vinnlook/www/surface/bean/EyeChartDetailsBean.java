package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/7/5$
 * @Author:pk$
 */
public class EyeChartDetailsBean extends BaseBean {
    /**
     * id : 1
     * name : Juicy Drop Pure Sky&Misty Gray
     * subheading : Juicy Drop Pure Sky&Misty Gray
     * issues :
     * image : ["http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141604_90111.jpg","http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141558_18142.jpg","http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141548_86182.jpg"]
     * goods_ids : 1
     * collect_num : 1
     * like_num : 1
     * theme_id : null
     * content : 性价比超级高的日系品牌
     * 热门的两款清透色号
     * 小直径心机款
     * 不是想要单纯放大感的的女孩
     * 可以尝试下这种虹膜系列
     * create_time : 2021年07月7日
     * theme_type : 4
     * user_name : Vinnlook
     * img_url : https://shop.jealook.com/image/default_user_image.png
     * width : 1080
     * height : 1440
     * content_url : http://shop.jealook.com/v6/html/theme-article-info?id=1
     * goods_list : [{"product_price":"155.00","search_attr":"2|3","preferential_price":"129.00","color_id":"2","product_number":"2","product_id":"6","goods_id":"1","goods_name":"Angelcolor Bambi Series Natural系列 日抛 Natural  Black 20片","virtual_sales":"1574","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","active_image":"/app_img/20210607/20210607155558_83040.jpg","brand_name":"Angelcolor","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609241640_8636.png","attr_name":"Natural  Black 20片"}]
     * is_collect : 0
     * is_like : 0
     * list : [{"id":"2","name":"Juicy Drop Pure Sky","subheading":"Juicy Drop Pure Sky","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705161430_20384.jpg","issues":"","collect_num":"0","like_num":"5","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":828,"height":715,"height_image":"","color":"","is_like":0},{"id":"1","name":"Juicy Drop Pure Sky&Misty Gray","subheading":"Juicy Drop Pure Sky&Misty Gray","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141604_90111.jpg","issues":"","collect_num":"1","like_num":"1","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":1080,"height":1440,"height_image":"","color":"","is_like":0},{"id":"4","name":"Juicy Drop产品介绍","subheading":"Juicy Drop产品介绍","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705191929_62277.jpg","issues":"","collect_num":"2","like_num":"1","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":800,"height":800,"height_image":"","color":"","is_like":0},{"id":"3","name":"juicy drop清爽薄荷","subheading":"juicy drop清爽薄荷","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705191854_18841.jpg","issues":"","collect_num":"0","like_num":"0","img_url":"https://shop.jealook.com/image/default_user_image.png","user_name":"Vinnlook","width":800,"height":800,"height_image":"","color":"","is_like":0}]
     */

    private String id;
    private String name;
    private String subheading;
    private String issues;
    private String goods_ids;
    private String collect_num;
    private String like_num;
    private Object theme_id;
    private String content;
    private String create_time;
    private String theme_type;
    private String user_name;
    private String img_url;
    private int width;
    private int height;
    private String content_url;
    private int is_collect;
    private int is_like;
    private List<String> image;
    private List<GoodsListBean> goods_list;
    private List<ListBean> list;
    private int maxlines;
    private boolean check;

    public int getMaxlines() {
        return maxlines;
    }

    public void setMaxlines(int maxlines) {
        this.maxlines = maxlines;
    }


    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

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

    public Object getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Object theme_id) {
        this.theme_id = theme_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTheme_type() {
        return theme_type;
    }

    public void setTheme_type(String theme_type) {
        this.theme_type = theme_type;
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
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
         * product_price : 155.00
         * search_attr : 2|3
         * preferential_price : 129.00
         * color_id : 2
         * product_number : 2
         * product_id : 6
         * goods_id : 1
         * goods_name : Angelcolor Bambi Series Natural系列 日抛 Natural  Black 20片
         * virtual_sales : 1574
         * suppliers_id : 2
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * active_image : /app_img/20210607/20210607155558_83040.jpg
         * brand_name : Angelcolor
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609241640_8636.png
         * attr_name : Natural  Black 20片
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
         * id : 2
         * name : Juicy Drop Pure Sky
         * subheading : Juicy Drop Pure Sky
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705161430_20384.jpg
         * issues :
         * collect_num : 0
         * like_num : 5
         * img_url : https://shop.jealook.com/image/default_user_image.png
         * user_name : Vinnlook
         * width : 828
         * height : 715
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
