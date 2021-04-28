package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/24$
 * @Author:pk$
 */
public class TypeShopBean extends BaseBean {

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
         * value : [{"goods_attr_id":"563","attr_value":"Airy Brown","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734281_9456.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734281_4577.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175719_3957.png"}]},{"goods_attr_id":"588","attr_value":"Aqua Beige","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734282_2780.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734282_7800.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175719_7783.png"}]},{"goods_attr_id":"592","attr_value":"Foggy Chocolate","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734613_5613.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734613_8678.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175719_2230.png"}]},{"goods_attr_id":"593","attr_value":"Melty Olive","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_1472.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_3916.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175721_8294.png"}]},{"goods_attr_id":"594","attr_value":"Misty Ash","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_9539.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_9154.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175721_5911.png"}]},{"goods_attr_id":"597","attr_value":"Shinny Hazel","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_8655.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734283_6227.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175723_1169.png"}]},{"goods_attr_id":"599","attr_value":"Snow Veil","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734284_8986.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734284_2640.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175725_7120.png"}]},{"goods_attr_id":"600","attr_value":"Waterq Quartz","banner":[{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734284_7448.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734284_3623.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175728_2781.png"}]}]
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
             * goods_attr_id : 563
             * attr_value : Airy Brown
             * banner : [{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734281_9456.png"},{"type":1,"url":"http://img.jealook.com/backend/20200401/1585734281_4577.png"},{"type":1,"url":"http://img.jealook.com/backend/20200603/1591175719_3957.png"}]
             */

            private String goods_attr_id;
            private String attr_value;
            private List<BannerBean> banner;

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
                 * type : 1
                 * url : http://img.jealook.com/backend/20200401/1585734281_9456.png
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
    }

    public static class ProductBean {
        /**
         * goods_attr : 562|563|564
         * product_id : 2204
         * product_sn : AS4547683004308
         * product_number : 6
         * product_price : 108.00
         * preferential_price : 88.00
         * color_id : 563
         * search_attr : 563|564
         * base_curve :
         * water_content :
         * diameter :
         * coloring_diameter :
         * attr_name : -0.00 Airy Brown 10枚
         */

        private String goods_attr;
        private String product_id;
        private String product_sn;
        private String product_number;
        private String product_price;
        private String preferential_price;
        private String color_id;
        private String search_attr;
        private String base_curve;
        private String water_content;
        private String diameter;
        private String coloring_diameter;
        private String attr_name;

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

        public String getProduct_sn() {
            return product_sn;
        }

        public void setProduct_sn(String product_sn) {
            this.product_sn = product_sn;
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

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }
    }
}
