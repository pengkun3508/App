package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallListBean extends BaseBean {
    /**
     * list : [{"points":"68","create_time":"2020-10-17 20:11:23","order_sn":"EC202010172011175805245244"},{"points":"68","create_time":"2020-10-14 23:59:43","order_sn":"EC202010142359374312817629"}]
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
         * points : 68
         * create_time : 2020-10-17 20:11:23
         * order_sn : EC202010172011175805245244
         */

        private String points;
        private String create_time;
        private String order_sn;
        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }


        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }
    }
}
