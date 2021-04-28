package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/6/2$
 * @Author:pk$
 */
public class OrderLogisticsBean extends BaseBean {
    /**
     * order_sn : EC2021030216364200028
     * create_time : 2021-03-02 16:36:42
     * waybill :
     * traces : [{"AcceptTime":"2021-03-02 16:36:42","AcceptStation":"您提交了订单，请等待系统确认"},{"AcceptTime":"2021-03-02 16:36:49","AcceptStation":"您的订单正在清关"}]
     */

    private String order_sn;
    private String create_time;
    private String waybill;
    private List<TracesBean> traces;

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public List<TracesBean> getTraces() {
        return traces;
    }

    public void setTraces(List<TracesBean> traces) {
        this.traces = traces;
    }

    public static class TracesBean {
        /**
         * AcceptTime : 2021-03-02 16:36:42
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
