package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/8/3$
 * @Author:pk$
 */
public class MainShoppingEvent extends BaseEvent {

    private String markNum;

    public MainShoppingEvent(String markNums) {
        markNum = markNums;
    }

    public String getMarkNum() {
        return markNum;
    }


}
