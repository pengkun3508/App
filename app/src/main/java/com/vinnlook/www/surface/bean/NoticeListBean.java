package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/24$
 * @Author:pk$
 */
public class NoticeListBean extends BaseBean {

    /**
     * list : [{"id":"1","title":"购物须知","content":"","add_time":"2019/10","add_time_day":"30","info_url":"http://shop.jealook.com/v1/html/article-info?id=1"},{"id":"123","title":"购物须知","content":"","add_time":"2019/10","add_time_day":"30","info_url":"http://shop.jealook.com/v1/html/article-info?id=123"}]
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
         * title : 购物须知
         * content :
         * add_time : 2019/10
         * add_time_day : 30
         * info_url : http://shop.jealook.com/v1/html/article-info?id=1
         */

        private String id;
        private String title;
        private String content;
        private String add_time;
        private String add_time_day;
        private String info_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_time_day() {
            return add_time_day;
        }

        public void setAdd_time_day(String add_time_day) {
            this.add_time_day = add_time_day;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }
    }
}
