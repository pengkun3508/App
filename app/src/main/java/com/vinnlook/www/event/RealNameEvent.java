package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.surface.bean.RealNameListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class RealNameEvent extends BaseEvent {


    List<RealNameListBean> data;

    public RealNameEvent(List<RealNameListBean> datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }

    public List<RealNameListBean> getRealNameList() {
        return data;
    }


}
