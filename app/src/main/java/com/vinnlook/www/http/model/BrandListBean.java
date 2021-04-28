package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class BrandListBean extends BaseBean {
    private List<ListBean> list;
    private List<BannerBean> banner;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class ListBean {
        /**
         * brand_id : 68
         * brand_name : MerMer
         * brand_logo : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210414/20210414095824_78895.png
         */

        private String brand_id;
        private String brand_name;
        private String brand_logo;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }
    }

    public static class BannerBean {
        /**
         * value :
         * type : 0
         * id : 56
         * end_time : null
         * start_time : null
         * parent_id : 0
         * photo : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210419/20210419162518_48604.png
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private Object end_time;
        private Object start_time;
        private String parent_id;
        private String photo;
        private ListBeanX list;

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

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
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
}
