package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class ProblemList2Event extends BaseEvent {
    String getUser_name;
    String getContent;
    String count;
    int pos;

    public ProblemList2Event(String getUser_names, String getContents, String counts, int pos) {
        getUser_name = getUser_names;
        getContent = getContents;
        count = counts;
    }

    public String getUser_name() {
        return getUser_name;
    }

    public String getContent() {
        return getContent;
    }

    public String getCount() {
        return count;
    }

    public int getPos() {
        return pos;
    }

}
