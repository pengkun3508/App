package com.vinnlook.www.surface.mvp.model.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/4/30$
 * @Author:pk$
 */
public class AddressDetailsBean extends BaseBean {

    /**
     * address_id : 9
     * province : 2
     * city : 37
     * district : 403
     * address : a a a a a a a
     * address_refer : 北京市 北京市 东城区
     * is_default : 0
     * address_name : 李春生
     * mobile : 17623640359
     * content : null
     * province_name : 北京
     * city_name : 北京市
     * district_name : 东城区
     */

    private String address_id;
    private String province;
    private String city;
    private String district;
    private String address;
    private String address_refer;
    private String is_default;
    private String address_name;
    private String mobile;
    private Object content;
    private String province_name;
    private String city_name;
    private String district_name;

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

    public String getAddress_refer() {
        return address_refer;
    }

    public void setAddress_refer(String address_refer) {
        this.address_refer = address_refer;
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
}