package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class LogisticsEvent extends BaseEvent {


    String id;
    String value;

    public LogisticsEvent(String ids, String values) {
        // TODO Auto-generated constructor stub
        id = ids;
        value=values;
    }

    public String getId() {
        return id;
    }
    public String getValue() {
        return value;
    }
}
