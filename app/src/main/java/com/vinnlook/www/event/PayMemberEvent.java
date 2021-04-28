package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2021/3/5$
 * @Author:pk$
 */
public class PayMemberEvent extends BaseEvent {

    boolean flag;

    public PayMemberEvent(boolean flags) {
        // TODO Auto-generated constructor stub
        flag = flags;
    }

    public boolean getFlag() {
        return flag;
    }
}
