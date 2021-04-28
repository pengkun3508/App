package com.vinnlook.www.event;

import com.dm.lib.core.eventbas.BaseEvent;
import com.vinnlook.www.surface.bean.ProblemBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class ProblemListEvent extends BaseEvent {
    List<ProblemBean.ListBean> data;
    String count;

    public ProblemListEvent(List<ProblemBean.ListBean> datas, String counts) {
        data = datas;
        count = counts;
    }

    public List<ProblemBean.ListBean> getData() {
        return data;
    }

    public String getCount() {
        return count;
    }


}
