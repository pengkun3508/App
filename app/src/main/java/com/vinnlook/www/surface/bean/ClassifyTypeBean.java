package com.vinnlook.www.surface.bean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/11$
 * @Author:pk$
 */
public class ClassifyTypeBean {

    /**
     * cat_id : 34
     * parent_id : 0
     * cat_name : 抛期
     * son_list : [{"cat_id":"304","parent_id":"34","cat_name":"双周抛"},{"cat_id":"315","parent_id":"34","cat_name":"月抛"},{"cat_id":"297","parent_id":"34","cat_name":"日抛"}]
     */

    private String cat_id;
    private String parent_id;
    private String cat_name;
    private List<SonListBean> son_list;
    private String color_mark = "0";//自己写的颜色标示

    public String getColor_mark() {
        return color_mark;
    }

    public void setColor_mark(String color_mark) {
        this.color_mark = color_mark;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public List<SonListBean> getSon_list() {
        return son_list;
    }

    public void setSon_list(List<SonListBean> son_list) {
        this.son_list = son_list;
    }

    public static class SonListBean {
        /**
         * cat_id : 304
         * parent_id : 34
         * cat_name : 双周抛
         */

        private String cat_id;
        private String parent_id;
        private String cat_name;
        private String type="1";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }
    }
}




