package com.vinnlook.www.surface.mvp.model.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/4/2
 */
public class TrainLogBean extends BaseBean {


    /**
     * id : 12
     * title : 十九大会议纪要
     * time : 2019-03-28
     * view_name : 宝塔山
     * class_name : 十九大
     * content : 开心3
     * img : ["asd"]
     * is_submit : 1
     */

    private String id;
    private String title;
    private String time;
    private String view_name;
    private String class_name;
    private String content;
    private int is_submit;
    private List<String> img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getView_name() {
        return view_name;
    }

    public void setView_name(String view_name) {
        this.view_name = view_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_submit() {
        return is_submit;
    }

    public void setIs_submit(int is_submit) {
        this.is_submit = is_submit;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
