package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/11$
 * @Author:pk$
 */
public class ProblemDetailsBean extends BaseBean {

    /**
     * list : [{"user_name":"小姐姐","img_url":"http://img.jealook.com/app_img/20201011/20201011233418_65793.jpg","content":"搬砖呢","create_time":"2020-11-10 16:44:34","id":"46","praise_count":"1","is_praise":0}]
     * count : 1
     * is_answer : 0
     */

    private String count;
    private String is_answer;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIs_answer() {
        return is_answer;
    }

    public void setIs_answer(String is_answer) {
        this.is_answer = is_answer;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * user_name : 小姐姐
         * img_url : http://img.jealook.com/app_img/20201011/20201011233418_65793.jpg
         * content : 搬砖呢
         * create_time : 2020-11-10 16:44:34
         * id : 46
         * praise_count : 1
         * is_praise : 0
         */

        private String user_name;
        private String img_url;
        private String content;
        private String create_time;
        private String id;
        private String praise_count;
        private int is_praise;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public int getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(int is_praise) {
            this.is_praise = is_praise;
        }
    }
}
