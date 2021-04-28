package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingListBean extends BaseBean {
    /**
     * list : [{"title":"标题3","content":"内容3","push_time":"2021-03-03 09:50:19","status":"0","image":"http://img.jealook.com/app_img/20210303/20210303103755_25438.jpg"},{"title":"标题6","content":"内容6","push_time":"2021-03-03 09:50:09","status":"0","image":"http://img.jealook.com/app_img/20210303/20210303103755_25438.jpg"}]
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
         * title : 标题3
         * content : 内容3
         * push_time : 2021-03-03 09:50:19
         * status : 0
         * image : http://img.jealook.com/app_img/20210303/20210303103755_25438.jpg
         */

        private String title;
        private String content;
        private String push_time;
        private String status;
        private String image;

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

        public String getPush_time() {
            return push_time;
        }

        public void setPush_time(String push_time) {
            this.push_time = push_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
