package com.vinnlook.www.event;


import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.surface.bean.RealNameListBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class RealName_2Event extends BaseEvent {


    RealNameListBean data;

    public RealName_2Event(RealNameListBean datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }

    public RealNameListBean getRealNameBean() {
        return data;
    }


}
