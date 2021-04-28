package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;

/**
 * @Description:
 * @Time:2020/5/29$
 * @Author:pk$
 */
public class CouponEvent extends BaseEvent {

    ConfirmOrderBean data;

    public CouponEvent( ConfirmOrderBean datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }
    public ConfirmOrderBean getData() {
        return data;
    }

}
