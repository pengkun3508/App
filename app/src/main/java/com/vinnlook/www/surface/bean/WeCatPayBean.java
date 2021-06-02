package com.vinnlook.www.surface.bean;

import com.google.gson.annotations.SerializedName;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/14$
 * @Author:pk$
 */
public class WeCatPayBean extends BaseBean {

    /**
     * content : {"appid":"wx6a9c69dcb128c19f","partnerid":"1562591411","prepayid":"wx18101045691875040fb62bd61606350000","package":"Sign=WXPay","noncestr":"PFePwpAxA9mDx3rf","timestamp":1621303845,"sign":"1F2E35AB690601E8B97B2D9FF4F07FD5"}
     * order_id : 16793
     * is_group : 1
     */

    private ContentBean content;
    private String order_id;
    private int is_group;
    private String is_group_end;

    public String getIs_group_end() {
        return is_group_end;
    }

    public void setIs_group_end(String is_group_end) {
        this.is_group_end = is_group_end;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getIs_group() {
        return is_group;
    }

    public void setIs_group(int is_group) {
        this.is_group = is_group;
    }

    public static class ContentBean {
        /**
         * appid : wx6a9c69dcb128c19f
         * partnerid : 1562591411
         * prepayid : wx18101045691875040fb62bd61606350000
         * package : Sign=WXPay
         * noncestr : PFePwpAxA9mDx3rf
         * timestamp : 1621303845
         * sign : 1F2E35AB690601E8B97B2D9FF4F07FD5
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
