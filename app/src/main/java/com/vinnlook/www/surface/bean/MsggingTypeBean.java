package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingTypeBean extends BaseBean {
    /**
     * name : 账户通知
     * image : http://img.jealook.com/app_img/20210302/20210302135148_74401.png
     * title : 标题8
     * type : 1
     * content : 内容8
     * unread_count : 3
     */

    private String name;
    private String image;
    private String title;
    private String type;
    private String content;
    private String unread_count;
    private String push_time;

    public String getPush_time() {
        return push_time;
    }

    public void setPush_time(String push_time) {
        this.push_time = push_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(String unread_count) {
        this.unread_count = unread_count;
    }
}
