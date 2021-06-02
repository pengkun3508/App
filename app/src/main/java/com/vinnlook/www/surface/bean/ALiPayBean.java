package com.vinnlook.www.surface.bean;

/**
 * @Description:
 * @Time:2020/5/15$
 * @Author:pk$
 */
public class ALiPayBean {

    /**
     * content : {"zfb_info":"alipay_sdk=alipay-sdk-php-20161101&app_id=2021001198683272&biz_content=%7B%22body%22%3A%22%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu8ba2%5Cu5355%22%2C%22out_trade_no%22%3A%22EC2021051810163516483%22%2C%22total_amount%22%3A%2219.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22ext_user_info%22%3A%7B%22name%22%3A%22%5Cu5f6d%5Cu5803%22%2C%22cert_type%22%3A%22IDENTITY_CARD%22%2C%22cert_no%22%3A%22610582199011242037%22%2C%22fix_buyer%22%3A%22T%22%2C%22need_check_info%22%3A%22T%22%7D%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fv6.vinnlook.com%2Fnotify%2Fpay-nofity%2Fali-notify&sign_type=RSA2&timestamp=2021-05-18+10%3A16%3A35&version=1.0&sign=W3Lfq2YtLc2Roli%2BTmt1PLNoT0i%2BT38cLhL%2BjHt%2BDoV9ygW3cx%2FvRwibBqiyzvpiQi1%2FpSQV5u8ns10vigSaTuNvcWmvPjeXLhIPWCL6rM9nxwdTTeoYbvTJkWQBVOe16koufXNkOHiObwyFzXyO5cbLCsRYtzvUWtHT2GOrzMHvpZAoWVx%2BMbBmnNpPFFhlF16WCf1zwN63kxijQMPQcFF4dEjwwnD5f42HaxdDo%2FxCA5Nh0MRcGRmZuN3%2FRBEHvJssf45TOubYd7cVHHU67ychfOHjhhWfgqINK4H%2FscajYtQcZVR43YqajykNdmvmc8k0eleJYxAPuULqNsyp9Q%3D%3D"}
     * order_id : 16795
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
         * zfb_info : alipay_sdk=alipay-sdk-php-20161101&app_id=2021001198683272&biz_content=%7B%22body%22%3A%22%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu8ba2%5Cu5355%22%2C%22out_trade_no%22%3A%22EC2021051810163516483%22%2C%22total_amount%22%3A%2219.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22ext_user_info%22%3A%7B%22name%22%3A%22%5Cu5f6d%5Cu5803%22%2C%22cert_type%22%3A%22IDENTITY_CARD%22%2C%22cert_no%22%3A%22610582199011242037%22%2C%22fix_buyer%22%3A%22T%22%2C%22need_check_info%22%3A%22T%22%7D%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fv6.vinnlook.com%2Fnotify%2Fpay-nofity%2Fali-notify&sign_type=RSA2&timestamp=2021-05-18+10%3A16%3A35&version=1.0&sign=W3Lfq2YtLc2Roli%2BTmt1PLNoT0i%2BT38cLhL%2BjHt%2BDoV9ygW3cx%2FvRwibBqiyzvpiQi1%2FpSQV5u8ns10vigSaTuNvcWmvPjeXLhIPWCL6rM9nxwdTTeoYbvTJkWQBVOe16koufXNkOHiObwyFzXyO5cbLCsRYtzvUWtHT2GOrzMHvpZAoWVx%2BMbBmnNpPFFhlF16WCf1zwN63kxijQMPQcFF4dEjwwnD5f42HaxdDo%2FxCA5Nh0MRcGRmZuN3%2FRBEHvJssf45TOubYd7cVHHU67ychfOHjhhWfgqINK4H%2FscajYtQcZVR43YqajykNdmvmc8k0eleJYxAPuULqNsyp9Q%3D%3D
         */

        private String zfb_info;

        public String getZfb_info() {
            return zfb_info;
        }

        public void setZfb_info(String zfb_info) {
            this.zfb_info = zfb_info;
        }
    }
}
