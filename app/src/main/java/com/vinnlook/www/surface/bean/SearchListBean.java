package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

public class SearchListBean extends BaseBean {

    private List<HotListBean> hot_list;
    private List<UserListBean> user_list;

    public List<HotListBean> getHot_list() {
        return hot_list;
    }

    public void setHot_list(List<HotListBean> hot_list) {
        this.hot_list = hot_list;
    }

    public List<UserListBean> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserListBean> user_list) {
        this.user_list = user_list;
    }

    public static class HotListBean {
        /**
         * num : 4
         * keyword : 木瓜
         * id : 4
         */

        private String num;
        private String keyword;
        private String id;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class UserListBean {
        /**
         * keyword : 哈
         * id : 57
         */

        private String keyword;
        private String id;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
