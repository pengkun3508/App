package com.dm.lib.core.eventbas;


import org.greenrobot.eventbus.EventBus;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/14
 */
public abstract class BaseEvent {

    public void post(){
        EventBus.getDefault().post(this);
    }

}
