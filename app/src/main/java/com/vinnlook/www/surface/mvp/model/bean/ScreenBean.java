package com.vinnlook.www.surface.mvp.model.bean;

import java.util.List;

public class ScreenBean {
    String title;
    List<String> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
