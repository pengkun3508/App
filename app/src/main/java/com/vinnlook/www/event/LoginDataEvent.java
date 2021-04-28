package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.utils.UserInfoBean;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class LoginDataEvent extends BaseEvent {
    UserInfoBean data;

    public LoginDataEvent(UserInfoBean datas) {
        // TODO Auto-generated constructor stub
        data = datas;
    }

    public UserInfoBean getUserInfoBean() {
        return data;
    }


}
