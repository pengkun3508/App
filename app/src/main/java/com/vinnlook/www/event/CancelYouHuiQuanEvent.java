package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/6/29$
 * @Author:pk$
 */
public class CancelYouHuiQuanEvent extends BaseEvent {
    String mark;

    public CancelYouHuiQuanEvent(String marks) {
        // TODO Auto-generated constructor stub
        mark = marks;
    }

    public String getMark() {
        return mark;
    }


}
