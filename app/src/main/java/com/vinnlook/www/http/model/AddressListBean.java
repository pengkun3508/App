package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/4/29$
 * @Author:pk$
 */
public class AddressListBean extends BaseBean {
    /**
     * address_id : 36
     * province : 2
     * city : 37
     * district : 405
     * address : 北京 北京市 崇文区
     * is_default : 1
     * address_name : thus game
     * mobile : 567****7722
     */

    private String address_id;
    private String province;
    private String city;
    private String district;
    private String address;
    private String is_default;
    private String address_name;
    private String mobile;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}