package com.vinnlook.www.surface.mvp.model.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019-12-24
 */
public class AddressBean extends BaseBean {
    String name;
    String address;
    String detailed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;
}
