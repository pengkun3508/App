package com.vinnlook.www.base;

/**
 * @Description:
 * @Time:2020/6/22$
 * @Author:pk$
 */
public interface ActivityPageSetting {
    public void setupView();
    public void initView();
    public void setOnListener();
    public void setContent();
    public void setModel();
    public boolean getIntentValue();
}
