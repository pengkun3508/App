package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class CollectionList2Bean extends BaseBean {
    /**
     * list : [{"id":"28","article_id":"2","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705161430_20384.jpg","name":"Juicy Drop Pure Sky","theme_type":"4"},{"id":"32","article_id":"14","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706094314_72076.png","name":"小红书热门款（第二期刊）","theme_type":"1"},{"id":"33","article_id":"1","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705141604_90111.jpg","name":"Juicy Drop Pure Sky&Misty Gray","theme_type":"4"},{"id":"35","article_id":"11","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706093303_18961.png","name":"小红书热门款（第一期刊）","theme_type":"1"},{"id":"36","article_id":"12","image":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210706/20210706093422_21286.png","name":"敏感眼专区（第一刊期）","theme_type":"1"}]
     * count : 5
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
         * id : 28
         * article_id : 2
         * image : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/app_img/20210705/20210705161430_20384.jpg
         * name : Juicy Drop Pure Sky
         * theme_type : 4
         */

        private String id;
        private String article_id;
        private String image;
        private String name;
        private String theme_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTheme_type() {
            return theme_type;
        }

        public void setTheme_type(String theme_type) {
            this.theme_type = theme_type;
        }
    }
}
