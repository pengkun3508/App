package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/12/22$
 * @Author:pk$
 */
public class RankingTabBean extends BaseBean {

    /**
     * id : 2
     * name : 热门畅销榜
     * e_name : Hot
     */

    private int id;
    private String name;
    private String e_name;
    private String b_image;
    private String s_image;
    private String pitch_image;

    public String getB_image() {
        return b_image;
    }

    public void setB_image(String b_image) {
        this.b_image = b_image;
    }

    public String getS_image() {
        return s_image;
    }

    public void setS_image(String s_image) {
        this.s_image = s_image;
    }

    public String getPitch_image() {
        return pitch_image;
    }

    public void setPitch_image(String pitch_image) {
        this.pitch_image = pitch_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }
}
