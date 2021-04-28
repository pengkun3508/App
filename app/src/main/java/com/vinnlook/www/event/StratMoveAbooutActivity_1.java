package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description: 从H5页面进入程序
 * @Time:2020/8/27$
 * @Author:pk$
 */
public class StratMoveAbooutActivity_1 extends BaseEvent {

    String good_id;
    String search_attr;

    public StratMoveAbooutActivity_1(String good_ids, String search_attrs) {
        // TODO Auto-generated constructor stub
        good_id = good_ids;
        search_attr = search_attrs;
    }

    public String getGood_id() {
        return good_id;
    }

    public String getSearch_attr() {
        return search_attr;
    }

}
