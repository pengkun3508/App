package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.http.model.AddressListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2020/7/30$
 * @Author:pk$
 */
public class AddressEvent extends BaseEvent {

    List<AddressListBean> data;

    public AddressEvent(List<AddressListBean> datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }

    public List<AddressListBean> getAddressList() {
        return data;
    }


}
