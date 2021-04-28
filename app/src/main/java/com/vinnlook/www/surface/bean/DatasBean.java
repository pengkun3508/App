package com.vinnlook.www.surface.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/11/23$
 * @Author:pk$
 */
public class DatasBean extends BaseBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public DatasBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DatasBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }


    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    public static List<DatasBean> getTestDataVideo() {
        List<DatasBean> list = new ArrayList<>();
        list.add(new DatasBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
        list.add(new DatasBean("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg", "听风.赏雨", 1));
        list.add(new DatasBean("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg",  "迪丽热巴.迪力木拉提", 1));
        list.add(new DatasBean("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg",  "爱美.人间有之", 1));
        list.add(new DatasBean("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",  "洋洋洋.气质篇", 1));
        list.add(new DatasBean("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg", "生活的态度", 1));
        return list;
    }


}
