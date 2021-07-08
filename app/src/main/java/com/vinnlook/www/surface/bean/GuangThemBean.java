package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/6/29$
 * @Author:pk$
 */
public class GuangThemBean extends BaseBean {
    private List<BannerBean> banner;
    private List<ItemBean> item;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class BannerBean {
        /**
         * value :
         * type : 0
         * id : 86
         * end_time : null
         * start_time : null
         * parent_id : 0
         * photo : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210628/20210628150713_49362.png
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String end_time;
        private String start_time;
        private String parent_id;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
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

    public static class ItemBean {
        /**
         * name : Vinnlook主题专区
         * type : 1
         * list : [{"id":"1","name":"test-1","height_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png","color":"#000000"},{"id":"2","name":"test-2","height_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png","color":"#000000"}]
         */

        private String name;
        private int type;
        private List<ListBeanX> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * id : 1
             * name : test-1
             * height_image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png
             * color : #000000
             */

            private String id;
            private String name;
            private String height_image;
            private String color;
            private String subheading;
            private String image;
            private String issues;
            private int width;
            private int height;

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
        }
    }
}
