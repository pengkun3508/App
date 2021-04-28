package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/8/12$
 * @Author:pk$
 */
public class GuangGaoEvent extends BaseEvent {

    private Boolean refresh;

    public GuangGaoEvent(Boolean refreshs) {
        refresh = refreshs;
    }

    public Boolean getRefresh() {
        return refresh;
    }
}
