package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/29$
 * @Author:pk$
 */
public class SiginOrderBean extends BaseBean {
    /**
     * list : []
     * count :
     */

    private String count;
    private List<?> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
