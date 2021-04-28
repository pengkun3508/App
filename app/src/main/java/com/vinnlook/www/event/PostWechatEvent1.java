package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * @Description:
 * @Time:2020/6/2$
 * @Author:pk$
 */
public class PostWechatEvent1 extends BaseEvent {

    String headUrl;
    String openId;
    String nickName;

    public PostWechatEvent1(String headUrls, String openIds, String nickNames) {
        super();
        headUrl = headUrls;
        openId = openIds;
        nickName = nickNames;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public String getNickName() {
        return nickName;
    }


}
