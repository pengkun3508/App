package com.vinnlook.www.eventbas;

import com.dm.lib.core.eventbas.BaseEvent;

/**
 * 描述：在线答题
 *
 * @author Yanbo
 * @date 2019/3/21
 */
public class OnlineAnswerEvent extends BaseEvent {
    private int Type=0;//回看 0 返回 1  再次答题 2
    //是否回看
    private boolean isLookBack ;
    //未做题数
    private int onJob ;
    //做错的数量
    private int error ;
    //得分
    private String fraction ;
    //总题数
    private int topicnum;

    public int getType() {
        return Type;
    }

    public int getOnJob() {
        return onJob;
    }

    public int getError() {
        return error;
    }

    public String getFraction() {
        return fraction;
    }

    public int getTopicnum() {
        return topicnum;
    }

    public boolean isLookBack() {
        return isLookBack;
    }
    public OnlineAnswerEvent(int Type){
        this.Type=Type;

    }

    public OnlineAnswerEvent(boolean isLookBack,int onJob,int error,String fraction,int topicnum){
        this.isLookBack=isLookBack;
        this.onJob=onJob;
        this.error=error;
        this.fraction=fraction;
        this.topicnum=topicnum;

    }
}
