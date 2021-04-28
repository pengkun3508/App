package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description: 问一问列表
 * @Time:2020/11/6$
 * @Author:pk$
 */
public class ProblemBean extends BaseBean {
    /**
     * list : [{"id":"6","question":"测试问题-6","answer_count":"5","content":"测试问题的回答26","user_name":"星雨"},{"id":"5","question":"测试问5","answer_count":"4","content":"测试问题的回答22","user_name":"星雨"},{"id":"4","question":"测试问题-4","answer_count":"4","content":"测试问题的回答18","user_name":"星雨"},{"id":"3","question":"测试问题-3","answer_count":"2","content":"测试问题的回答16","user_name":"星雨"},{"id":"2","question":"测试问题-2","answer_count":"10","content":"测试问题的回答6","user_name":"星雨"},{"id":"1","question":"测试问题-1","answer_count":"5","content":"测试问题的回答","user_name":"星雨"}]
     * count : 6
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
         * id : 6
         * question : 测试问题-6
         * answer_count : 5
         * content : 测试问题的回答26
         * user_name : 星雨
         */

        private String id;
        private String question;
        private String answer_count;
        private String content;
        private String user_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer_count() {
            return answer_count;
        }

        public void setAnswer_count(String answer_count) {
            this.answer_count = answer_count;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
