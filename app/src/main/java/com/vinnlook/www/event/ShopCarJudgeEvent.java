package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/5/14$
 * @Author:pk$
 */
public class ShopCarJudgeEvent extends BaseEvent {

    int jupdg;

    public ShopCarJudgeEvent(int jupdgs) {
        jupdg = jupdgs;
    }

    public int getJupdg() {
        return jupdg;
    }
}
