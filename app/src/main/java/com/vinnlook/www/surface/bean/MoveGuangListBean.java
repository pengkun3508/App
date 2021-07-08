package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class MoveGuangListBean extends BaseBean {
    /**
     * list : [{"id":"1","name":"Juicy Drop Pure Sky&Misty Gray","subheading":"Juicy Drop Pure Sky&Misty Gray","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141604_90111.jpg","issues":"","collect_num":"4","like_num":"2","user_name":"Vinnlook","img_url":"https://shop.jealook.com/image/default_user_image.png","width":1080,"height":1440,"is_like":1},{"id":"21","name":"ceil","subheading":"ceil","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706154607_20182.png","issues":"","collect_num":"0","like_num":"0","user_name":"Vinnlook","img_url":"https://shop.jealook.com/image/default_user_image.png","width":500,"height":500,"is_like":0}]
     * count : 2
     * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609241640_5066.png
     */

    private String count;
    private String image;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
         * name : Juicy Drop Pure Sky&Misty Gray
         * subheading : Juicy Drop Pure Sky&Misty Gray
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141604_90111.jpg
         * issues :
         * collect_num : 4
         * like_num : 2
         * user_name : Vinnlook
         * img_url : https://shop.jealook.com/image/default_user_image.png
         * width : 1080
         * height : 1440
         * is_like : 1
         */

        private String id;
        private String name;
        private String subheading;
        private String image;
        private String issues;
        private String collect_num;
        private String like_num;
        private String user_name;
        private String img_url;
        private int width;
        private int height;
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

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }
    }
}
