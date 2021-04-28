package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.http.model.AddressListBean;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class AddressListBean_2Event extends BaseEvent {


    AddressListBean data;

    public AddressListBean_2Event(AddressListBean datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }

    public AddressListBean getAddressListBean() {
        return data;
    }


}
