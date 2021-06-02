package com.vinnlook.www.surface.bean;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/5/26$
 * @Author:pk$
 */
public class GroupDetailsListBean extends BaseBean {

    private String name;
    private String img;
    private String num;
    private String product_id;
    private String search_attr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSearch_attr() {
        return search_attr;
    }

    public void setSearch_attr(String search_attr) {
        this.search_attr = search_attr;
    }




}
