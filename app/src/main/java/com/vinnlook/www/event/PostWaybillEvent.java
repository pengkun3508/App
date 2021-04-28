package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2021/2/5$
 * @Author:pk$
 */
public class PostWaybillEvent extends BaseEvent {
    String mark;//等于1.代表请求成功

    public PostWaybillEvent(String marks) {
        mark = marks;
    }

    public String getMark() {
        return mark;
    }
}
