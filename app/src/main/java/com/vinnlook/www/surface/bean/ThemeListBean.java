package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeListBean extends BaseBean {
    /**
     * list : [{"id":"1","name":"test-1","height_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png","color":"#000000","width_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150254_62056.png"},{"id":"2","name":"test-2","height_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png","color":"#000000","width_image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150254_62056.png"}]
     * count : 2
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * name : test-1
         * height_image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png
         * color : #000000
         * width_image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150254_62056.png
         */

        private String id;
        private String name;
        private String height_image;
        private String color;
        private String width_image;

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

        public String getWidth_image() {
            return width_image;
        }

        public void setWidth_image(String width_image) {
            this.width_image = width_image;
        }
    }
}
