package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class PersonalInformationBean extends BaseBean {
    /**
     * user : {"img_url":"https://thirdwx.qlogo.cn/mmopen/vi_32/DpmD479UfVzZMzcEE9lNFlGe3OIP3UyicN2fHo2StntmEqH31pLv28MNNWFHYSgUMMj44AGOv9vyWersUmmvG8g/132","sex":"保密","user_name":"星雨","user_id":"464","mobile":"17623640359","member_end_time":"2021-11-18","points":"-1","sex_type":0,"is_member":1}
     * order_count : {"obligationcount":"1","pendingcount":"0","receivingcount":"4","commentcount":"0","replacementcount":"0"}
     * collect_count : 4
     * browse_count : 112
     * discount_count : 0
     * points : -1
     * banner : [{"value":"1","type":"1","id":"38","end_time":"1606665600","start_time":"1606101366","photo":"http://img.jealook.com/app_img/20201123/20201123112013_44804.png","list":{"goods_id":"1","search_attr":"2|3","active_id":"","url":"","text":"","id":""}},{"value":"25,26,22,23,24,25,26","type":"5","id":"39","end_time":null,"start_time":null,"photo":"http://img.jealook.com/app_img/20201123/20201123112131_56880.png","list":{"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":"39"}}]
     */

    private UserBean user;
    private OrderCountBean order_count;
    private String collect_count;
    private String browse_count;
    private String discount_count;
    private String points;
    private List<BannerBean> banner;
    private List<WaybillListBean> waybillList;

    public List<WaybillListBean> getWaybillList() {
        return waybillList;
    }

    public void setWaybillList(List<WaybillListBean> waybillList) {
        this.waybillList = waybillList;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public OrderCountBean getOrder_count() {
        return order_count;
    }

    public void setOrder_count(OrderCountBean order_count) {
        this.order_count = order_count;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getBrowse_count() {
        return browse_count;
    }

    public void setBrowse_count(String browse_count) {
        this.browse_count = browse_count;
    }

    public String getDiscount_count() {
        return discount_count;
    }

    public void setDiscount_count(String discount_count) {
        this.discount_count = discount_count;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class WaybillListBean {

        /**
         * order_id : 1407
         * image : http://img.jealook.com/backend/20200731/1596175279_5520.png
         * info : {"AcceptTime":"11/28","AcceptStation":"您提交了订单，请等待系统确认"}
         */

        private String order_id;
        private String image;
        private InfoBean info;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * AcceptTime : 11/28
             * AcceptStation : 您提交了订单，请等待系统确认
             */

            private String time;
            private String status;

            public String getAcceptTime() {
                return time;
            }

            public void setAcceptTime(String time) {
                this.time = time;
            }

            public String getAcceptStation() {
                return status;
            }

            public void setAcceptStation(String status) {
                this.status = status;
            }
        }
    }


    public static class UserBean {
        /**
         * img_url : https://thirdwx.qlogo.cn/mmopen/vi_32/DpmD479UfVzZMzcEE9lNFlGe3OIP3UyicN2fHo2StntmEqH31pLv28MNNWFHYSgUMMj44AGOv9vyWersUmmvG8g/132
         * sex : 保密
         * user_name : 星雨
         * user_id : 464
         * mobile : 17623640359
         * member_end_time : 2021-11-18
         * points : -1
         * sex_type : 0
         * is_member : 1
         */

        private String img_url;
        private String sex;
        private String user_name;
        private String user_id;
        private String mobile;
        private String member_end_time;
        private String points;
        private int sex_type;
        private int is_member;
        private String is_wechat;
        private String wechat_nickname;
        private String unread_count;

        public String getUnread_count() {
            return unread_count;
        }

        public void setUnread_count(String unread_count) {
            this.unread_count = unread_count;
        }



        public String getWechat_nickname() {
            return wechat_nickname;
        }

        public void setWechat_nickname(String wechat_nickname) {
            this.wechat_nickname = wechat_nickname;
        }

        public String getIs_wechat() {
            return is_wechat;
        }

        public void setIs_wechat(String is_wechat) {
            this.is_wechat = is_wechat;
        }


        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMember_end_time() {
            return member_end_time;
        }

        public void setMember_end_time(String member_end_time) {
            this.member_end_time = member_end_time;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public int getSex_type() {
            return sex_type;
        }

        public void setSex_type(int sex_type) {
            this.sex_type = sex_type;
        }

        public int getIs_member() {
            return is_member;
        }

        public void setIs_member(int is_member) {
            this.is_member = is_member;
        }
    }

    public static class OrderCountBean {
        /**
         * obligationcount : 1
         * pendingcount : 0
         * receivingcount : 4
         * commentcount : 0
         * replacementcount : 0
         */

        private String obligationcount;
        private String pendingcount;
        private String receivingcount;
        private String commentcount;
        private String replacementcount;

        public String getObligationcount() {
            return obligationcount;
        }

        public void setObligationcount(String obligationcount) {
            this.obligationcount = obligationcount;
        }

        public String getPendingcount() {
            return pendingcount;
        }

        public void setPendingcount(String pendingcount) {
            this.pendingcount = pendingcount;
        }

        public String getReceivingcount() {
            return receivingcount;
        }

        public void setReceivingcount(String receivingcount) {
            this.receivingcount = receivingcount;
        }

        public String getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(String commentcount) {
            this.commentcount = commentcount;
        }

        public String getReplacementcount() {
            return replacementcount;
        }

        public void setReplacementcount(String replacementcount) {
            this.replacementcount = replacementcount;
        }
    }

    public static class BannerBean {
        /**
         * value : 1
         * type : 1
         * id : 38
         * end_time : 1606665600
         * start_time : 1606101366
         * photo : http://img.jealook.com/app_img/20201123/20201123112013_44804.png
         * list : {"goods_id":"1","search_attr":"2|3","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String end_time;
        private String start_time;
        private String photo;
        private ListBean list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id : 1
             * search_attr : 2|3
             * active_id :
             * url :
             * text :
             * id :
             */

            private String goods_id;
            private String search_attr;
            private String active_id;
            private String url;
            private String text;
            private String id;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
