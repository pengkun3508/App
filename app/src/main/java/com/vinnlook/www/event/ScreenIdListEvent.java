package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/5/25$
 * @Author:pk$
 */
public class ScreenIdListEvent extends BaseEvent {
    String str;
    String str1;


    public ScreenIdListEvent(String strs, String strs1) {
        str = strs;
        str1 = strs1;

    }


    public String getListId() {
        return str;
    }

    public String getListId_1() {
        return str1;
    }
}
