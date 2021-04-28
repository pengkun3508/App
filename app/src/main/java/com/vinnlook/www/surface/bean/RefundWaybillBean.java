package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/2/5$
 * @Author:pk$
 */
public class RefundWaybillBean extends BaseBean {
    /**
     * traces : [{"AcceptStation":"已签收,签收人是（放门口）先生/女士，如有疑问请联系派件员何原仔(18826725006、13016096113)，如您未收到此快递，请拨打投诉电话：19128618590，感谢使用申通快递，期待再次为您服务","AcceptTime":"2021-02-03 18:00:32"},{"AcceptStation":"广东广州金运公司-何原仔(18826725006,13016096113)-派件中","AcceptTime":"2021-02-03 16:13:01"},{"AcceptStation":"已到达-广东广州金运公司","AcceptTime":"2021-02-03 16:03:27"},{"AcceptStation":"广东广州转运中心-已发往-广东广州金运公司","AcceptTime":"2021-02-03 08:40:15"},{"AcceptStation":"已到达-广东广州转运中心","AcceptTime":"2021-02-03 08:24:15"},{"AcceptStation":"陕西西安转运中心-已发往-广东广州转运中心","AcceptTime":"2021-02-02 01:00:57"},{"AcceptStation":"已到达-陕西西安转运中心","AcceptTime":"2021-02-02 00:17:20"},{"AcceptStation":"陕西咸阳公司-已发往-陕西西安转运中心","AcceptTime":"2021-02-01 20:02:59"},{"AcceptStation":"陕西咸阳公司-西咸保税中心A301-已收件","AcceptTime":"2021-02-01 20:02:41"},{"AcceptTime":"2021-02-05 09:39:29","AcceptStation":"包裹正在等待揽收"}]
     * waybill_name : 顺丰速运
     * waybill : 112233445566778
     */

    private String waybill_name;
    private String waybill;
    private List<TracesBean> traces;

    public String getWaybill_name() {
        return waybill_name;
    }

    public void setWaybill_name(String waybill_name) {
        this.waybill_name = waybill_name;
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
         * AcceptStation : 已签收,签收人是（放门口）先生/女士，如有疑问请联系派件员何原仔(18826725006、13016096113)，如您未收到此快递，请拨打投诉电话：19128618590，感谢使用申通快递，期待再次为您服务
         * AcceptTime : 2021-02-03 18:00:32
         */

        private String status;
        private String time;

        public String getAcceptStation() {
            return status;
        }

        public void setAcceptStation(String status) {
            this.status = status;
        }

        public String getAcceptTime() {
            return time;
        }

        public void setAcceptTime(String time) {
            this.time = time;
        }
    }
}
