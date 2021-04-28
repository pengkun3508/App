package com.vinnlook.www.surface.bean;

import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/11$
 * @Author:pk$
 */
public class TypeShopCarBean extends BaseBean {

    private List<AttrBean> attr;
    private List<ProductBean> product;

    public List<AttrBean> getAttr() {
        return attr;
    }

    public void setAttr(List<AttrBean> attr) {
        this.attr = attr;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class AttrBean {
        /**
         * attr_name : 颜色
         * attr_id : 1
         * value : [{"goods_attr_id":"528","attr_value":"Antiquebeige","banner":["http://img.vinnlook.com/backend/20200401/1585733834_7964.png","http://img.vinnlook.com/backend/20200401/1585733834_3111.png"]},{"goods_attr_id":"547","attr_value":"Apricot Brown","banner":["http://img.vinnlook.com/backend/20200401/1585730512_8349.png","http://img.vinnlook.com/backend/20200401/1585730512_4684.png"]},{"goods_attr_id":"553","attr_value":"Champange Brown","banner":["http://img.vinnlook.com/backend/20200401/1585730512_6351.png","http://img.vinnlook.com/backend/20200401/1585730512_1588.png"]},{"goods_attr_id":"555","attr_value":"Chiff on Brown","banner":["http://img.vinnlook.com/backend/20200401/1585733834_1322.png","http://img.vinnlook.com/backend/20200401/1585733834_8566.png"]},{"goods_attr_id":"556","attr_value":"Classic  cheek","banner":["http://img.vinnlook.com/backend/20200401/1585730512_5683.png","http://img.vinnlook.com/backend/20200401/1585730512_3847.png"]},{"goods_attr_id":"557","attr_value":"Natural  Black","banner":["http://img.vinnlook.com/backend/20200401/1585730512_8168.png","http://img.vinnlook.com/backend/20200401/1585730512_4210.png"]},{"goods_attr_id":"558","attr_value":"Natural  Brown","banner":["http://img.vinnlook.com/backend/20200401/1585730512_7291.png","http://img.vinnlook.com/backend/20200401/1585730512_2951.png"]},{"goods_attr_id":"559","attr_value":"Natural mocha","banner":["http://img.vinnlook.com/backend/20200401/1585730513_8304.png","http://img.vinnlook.com/backend/20200401/1585730513_2370.png"]},{"goods_attr_id":"560","attr_value":"Silhouetteduo","banner":["http://img.vinnlook.com/backend/20200401/1585733834_8260.png","http://img.vinnlook.com/backend/20200401/1585733834_6140.png"]},{"goods_attr_id":"561","attr_value":"Urbannoir","banner":["http://img.vinnlook.com/backend/20200401/1585733835_1174.png","http://img.vinnlook.com/backend/20200401/1585733835_3955.png"]}]
         */

        private String attr_name;
        private String attr_id;
        private List<ValueBean> value;

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public String getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(String attr_id) {
            this.attr_id = attr_id;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            /**
             * goods_attr_id : 528
             * attr_value : Antiquebeige
             * banner : ["http://img.vinnlook.com/backend/20200401/1585733834_7964.png","http://img.vinnlook.com/backend/20200401/1585733834_3111.png"]
             */

            private String goods_attr_id;
            private String attr_value;
            private List<BannerBean> banner;
            private List<ProductBean> productBeanList;

            public List<ProductBean> getProductBeanList() {
                return productBeanList;
            }

            public void setProductBeanList(List<ProductBean> productBeanList) {
                this.productBeanList = productBeanList;
            }

            public String getGoods_attr_id() {
                return goods_attr_id;
            }

            public void setGoods_attr_id(String goods_attr_id) {
                this.goods_attr_id = goods_attr_id;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }

            public List<BannerBean> getBanner() {
                return banner;
            }

            public void setBanner(List<BannerBean> banner) {
                this.banner = banner;
            }

            public static class BannerBean {

                /**
                 * url : http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4
                 * type : 2
                 */

                private String url;
                private int type;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }

//    public static class ProductBean {
//        /**
//         * goods_attr : 527|528|529
//         * product_id : 1966
//         * product_sn : AS4547683004957
//         * product_number : 6
//         * product_price : 145.00
//         * preferential_price : 130.50
//         * color_id : 528
//         * search_attr : 528|529
//         * attr_name : 0度 Antiquebeige 20枚
//         */
//
//        private String goods_attr;
//        private String product_id;
//        private String product_sn;
//        private String product_number;
//        private String product_price;
//        private String preferential_price;
//        private String color_id;
//        private String search_attr;
//        private String attr_name;
//
//        public String getGoods_attr() {
//            return goods_attr;
//        }
//
//        public void setGoods_attr(String goods_attr) {
//            this.goods_attr = goods_attr;
//        }
//
//        public String getProduct_id() {
//            return product_id;
//        }
//
//        public void setProduct_id(String product_id) {
//            this.product_id = product_id;
//        }
//
//        public String getProduct_sn() {
//            return product_sn;
//        }
//
//        public void setProduct_sn(String product_sn) {
//            this.product_sn = product_sn;
//        }
//
//        public String getProduct_number() {
//            return product_number;
//        }
//
//        public void setProduct_number(String product_number) {
//            this.product_number = product_number;
//        }
//
//        public String getProduct_price() {
//            return product_price;
//        }
//
//        public void setProduct_price(String product_price) {
//            this.product_price = product_price;
//        }
//
//        public String getPreferential_price() {
//            return preferential_price;
//        }
//
//        public void setPreferential_price(String preferential_price) {
//            this.preferential_price = preferential_price;
//        }
//
//        public String getColor_id() {
//            return color_id;
//        }
//
//        public void setColor_id(String color_id) {
//            this.color_id = color_id;
//        }
//
//        public String getSearch_attr() {
//            return search_attr;
//        }
//
//        public void setSearch_attr(String search_attr) {
//            this.search_attr = search_attr;
//        }
//
//        public String getAttr_name() {
//            return attr_name;
//        }
//
//        public void setAttr_name(String attr_name) {
//            this.attr_name = attr_name;
//        }
//    }
}
