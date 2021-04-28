package com.vinnlook.www.eventbas;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * 描述：
 *
 * @author ANyu
 * @date 2019\4\1 0001
 */
public class ChangeUserNameEvent extends BaseEvent {

    //修改之后的昵称
    private String edtName;

    public String getEdtName() {
        return edtName;
    }

    public ChangeUserNameEvent(String edtName) {
        this.edtName = edtName;
    }

}
