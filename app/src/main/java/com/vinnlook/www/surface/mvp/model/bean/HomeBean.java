package com.vinnlook.www.surface.mvp.model.bean;

import java.util.List;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/30
 */
public class HomeBean {


    /**
     * has_news : 0
     * newbanner : [{"id":"174","title":"张富清的故事\u2014\u2014五个岗位背后的为民情怀","img":"","add_time":"2019-06-01","html_url":"192.168.2.239:20002/v1/html/details?id=174","is_collect":0},{"id":"173","title":"中央文明办发布5月中国好人榜","img":"","add_time":"2019-05-30","html_url":"192.168.2.239:20002/v1/html/details?id=173","is_collect":0},{"id":"172","title":"\u201c大朋友\u201d习近平和孩子们在一起","img":"","add_time":"2019-05-30","html_url":"192.168.2.239:20002/v1/html/details?id=172","is_collect":0},{"id":"171","title":"党员干部购买\u201c新三板\u201d股票是否构成违纪","img":"","add_time":"2019-05-30","html_url":"192.168.2.239:20002/v1/html/details?id=171","is_collect":0}]
     * banner : [{"content":"测试","banner_img":"192.168.2.239:20003/image/20190530/20190530110325_77568.jpg","banner_extra":{"type":"1","data":""}},{"content":"测试","banner_img":"192.168.2.239:20003/image/20190530/20190530110402_65040.jpg","banner_extra":{"type":"1","data":""}},{"content":"阿斯达飒沓","banner_img":"192.168.2.239:20003/image/20190530/20190530111616_99397.jpg","banner_extra":{"type":"1","data":""}}]
     */

    private int has_news;
    private List<NewbannerBean> newbanner;
    private List<BannerBean> banner;

    public int getHas_news() {
        return has_news;
    }

    public void setHas_news(int has_news) {
        this.has_news = has_news;
    }

    public List<NewbannerBean> getNewbanner() {
        return newbanner;
    }

    public void setNewbanner(List<NewbannerBean> newbanner) {
        this.newbanner = newbanner;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class NewbannerBean {
        /**
         * id : 174
         * title : 张富清的故事——五个岗位背后的为民情怀
         * img :
         * add_time : 2019-06-01
         * html_url : 192.168.2.239:20002/v1/html/details?id=174
         * is_collect : 0
         */

        private String id;
        private String title;
        private String img;
        private String add_time;
        private String html_url;
        private int is_collect;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }
    }

    public static class BannerBean {
        /**
         * content : 测试
         * banner_img : 192.168.2.239:20003/image/20190530/20190530110325_77568.jpg
         * banner_extra : {"type":"1","data":""}
         */

        private String content;
        private String banner_img;
        private BannerExtraBean banner_extra;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getBanner_img() {
            return banner_img;
        }

        public void setBanner_img(String banner_img) {
            this.banner_img = banner_img;
        }

        public BannerExtraBean getBanner_extra() {
            return banner_extra;
        }

        public void setBanner_extra(BannerExtraBean banner_extra) {
            this.banner_extra = banner_extra;
        }

        public static class BannerExtraBean {
            /**
             * type : 1
             * data :
             */

            private String type;
            private String data;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }
}
