package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class BatchRefundEvent extends BaseEvent {

    String price;

    public BatchRefundEvent(String prices) {
        // TODO Auto-generated constructor stub
        price = prices;
    }

    public String getPrice() {
        return price;

    }

}
