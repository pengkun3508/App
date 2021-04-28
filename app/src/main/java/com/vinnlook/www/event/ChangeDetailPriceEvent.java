package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;

/**
 * @Description:
 * @Time:2020/6/18$
 * @Author:pk$
 */
public class ChangeDetailPriceEvent extends BaseEvent {

    ProductBean productBean = null;
    String mark;//1===全部选择；2==只选择了颜色和规格

    public ChangeDetailPriceEvent(ProductBean productBeans, String marks) {
        // TODO Auto-generated constructor stub
        productBean = productBeans;
        mark = marks;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public String getMark() {
        return mark;
    }


}
