package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/1/8$
 * @Author:pk$
 */
public class CommodityTitleBean extends BaseBean {
    /**
     * title : 日抛30片
     * flood : 297
     * pieces : 30片
     */

    private String title;
    private String flood;
    private String pieces;

    private String color_mark;//自己写的颜色标示；1--为已选中；2--为未选中

    public String getColor_mark() {
        return color_mark;
    }

    public void setColor_mark(String color_mark) {
        this.color_mark = color_mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlood() {
        return flood;
    }

    public void setFlood(String flood) {
        this.flood = flood;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
