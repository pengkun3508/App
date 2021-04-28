package com.vinnlook.www.surface.mvp.model.bean;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/30
 */
public class VideoBean {

    /**
     * id : 8
     * name : 习近平
     * img : 192.168.2.239:20003/image/20190328/20190328160211_19071.jpg
     * video_url : 习近平
     * add_time : 2019-03-28
     * html_url : 192.168.2.239:20002/v1/html/video?id=8
     */

    private String id;
    private String name;
    private String img;
    private String video_url;
    private String add_time;
    private String html_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
