package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.http.model.MoveDataBean;

/**
 * @Description:
 * @Time:2020/5/6$
 * @Author:pk$
 */
public class ShopTypeDataEvent extends BaseEvent {
    MoveDataBean.AttrBean.ValueBean getBanner;
    String getProduct_number;
    String getProduct_price;
    String arrt_name;
    String jupdg;
    String mark;
    int posion;

    public ShopTypeDataEvent(String jupdgs, MoveDataBean.AttrBean.ValueBean getBanners, int posions, String arrt_names) {
        // TODO Auto-generated constructor stub
        getBanner = getBanners;
        jupdg = jupdgs;
        posion = posions;
        arrt_name = arrt_names;
    }

    public MoveDataBean.AttrBean.ValueBean getBanner() {
        return getBanner;
    }

    public String getjupdg() {
        return jupdg;
    }

    public ShopTypeDataEvent(String marks, String getProduct_numbers, String getProduct_prices) {
        // TODO Auto-generated constructor stub
        getProduct_number = getProduct_numbers;
        getProduct_price = getProduct_prices;
        mark = marks;
    }

    public String getmarks() {
        return mark;
    }

    public int getPosion() {
        return posion;
    }

    public String getProduct_number() {
        return getProduct_number;
    }

    public String getProduct_price() {
        return getProduct_price;
    }

    public String getArrt_name() {
        return arrt_name;
    }

}
