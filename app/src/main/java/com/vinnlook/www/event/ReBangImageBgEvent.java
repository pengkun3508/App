package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2021/3/12$
 * @Author:pk$
 */
public class ReBangImageBgEvent extends BaseEvent {

    String img;

    public ReBangImageBgEvent(String imgs) {
        // TODO Auto-generated constructor stub
        img = imgs;
    }

    public String getImg() {
        return img;
    }

}
