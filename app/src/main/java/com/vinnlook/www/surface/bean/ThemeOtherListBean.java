package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class ThemeOtherListBean extends BaseBean {
    /**
     * list : [{"id":"4","name":"新挖宝藏-1","subheading":"新挖宝藏-1","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210628/20210628162210_78189.png","issues":"","collect_num":"0","like_num":"0","user_name":"Vinnlook","img_url":"https://shop.jealook.com/image/default_user_image.png","is_like":0}]
     * count : 1
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
         * id : 4
         * name : 新挖宝藏-1
         * subheading : 新挖宝藏-1
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210628/20210628162210_78189.png
         * issues :
         * collect_num : 0
         * like_num : 0
         * user_name : Vinnlook
         * img_url : https://shop.jealook.com/image/default_user_image.png
         * is_like : 0
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

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }
    }
}
