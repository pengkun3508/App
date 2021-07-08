package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:主题详情
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeDetailsBean extends BaseBean {

    /**
     * id : 1
     * name : test-1
     * content : test-test
     * width_image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150254_62056.png
     * height_image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210625/20210625150257_22889.png
     * color : #000000
     * list : [{"id":"3","name":"小红书热门款-1","subheading":"小红书热门款-1","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210628/20210628161939_55143.png","issues":"","height_image":"","color":""}]
     */

    private String id;
    private String name;
    private String content;
    private String width_image;
    private String height_image;
    private String color;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWidth_image() {
        return width_image;
    }

    public void setWidth_image(String width_image) {
        this.width_image = width_image;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * name : 小红书热门款-1
         * subheading : 小红书热门款-1
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210628/20210628161939_55143.png
         * issues :
         * height_image :
         * color :
         */

        private String id;
        private String name;
        private String subheading;
        private String image;
        private String issues;
        private String height_image;
        private String color;

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
