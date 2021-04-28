package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

public class MainHomeActivityEvent extends BaseEvent {

    String mark;

    public MainHomeActivityEvent(String marks) {
        // TODO Auto-generated constructor stub
        mark = marks;
    }

    public String getMark() {
        return mark;
    }


}
