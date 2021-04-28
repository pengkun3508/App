package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNameDetailsBean extends BaseBean {
    /**
     * id : 7
     * name : 张浩博
     * id_card : 41010619910501011
     * is_default : 1
     */

    private String id;
    private String name;
    private String id_card;
    private String is_default;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
}
