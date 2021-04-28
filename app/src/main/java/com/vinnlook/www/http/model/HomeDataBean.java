package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description: 首页数据Bean
 * @Time:2020/4/20$
 * @Author:pk$
 */
public class HomeDataBean extends BaseBean {
    /**
     * index_ad : {"value":"","type":"6","id":"21","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201022/20201022100509_67947.png","list":{"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}}
     * banner : [{"value":"166","type":"1","id":"17","photo":"http://img.jealook.com/app_img/20200909/20200909143826_71527.jpg","list":{"goods_id":"166","search_attr":"7250|7251","active_id":"","url":"","text":"","id":""}},{"value":"232","type":"1","id":"18","photo":"http://img.jealook.com/app_img/20200903/20200903153905_64455.jpg","list":{"goods_id":"232","search_attr":"9042|9043","active_id":"","url":"","text":"","id":""}},{"value":"64","type":"1","id":"19","photo":"http://img.jealook.com/app_img/20200908/20200908154703_49741.jpg","list":{"goods_id":"64","search_attr":"1824|1825","active_id":"","url":"","text":"","id":""}}]
     * artcle : [{"id":"1","title":"购物须知","content":"","add_time":"2019-10-30 22:58:58","info_url":"http://shop.jealook.com/v1/html/article-info?id=1"},{"id":"123","title":"购物须知","content":"","add_time":"2019-10-30 22:58:58","info_url":"http://shop.jealook.com/v1/html/article-info?id=123"}]
     * alert_ad : {"value":"","type":"","id":"","photo":"","list":{"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}}
     * brand : [[{"brand_id":"89","brand_name":"new mermer","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133250_50239.png"},{"brand_id":"68","brand_name":"Mermer","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094754_22551.png"},{"brand_id":"94","brand_name":"以梦seed","brand_logo":"http://img.jealook.com/app_img/20200910/20200910145233_80808.png"},{"brand_id":"92","brand_name":"Sustainable","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133408_70842.png"},{"brand_id":"91","brand_name":"sparkle","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133400_65821.png"},{"brand_id":"90","brand_name":"nscollection","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133343_16406.png"},{"brand_id":"88","brand_name":"motecon","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133222_96309.png"},{"brand_id":"87","brand_name":"elebelle","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133156_63019.png"},{"brand_id":"86","brand_name":"beeheartib","brand_logo":"http://img.jealook.com/app_img/20200909/20200909122856_34251.png"},{"brand_id":"85","brand_name":"Acorde","brand_logo":"http://img.jealook.com/app_img/20200909/20200909122740_99794.png"}],[{"brand_id":"83","brand_name":"Dopewink","brand_logo":"http://img.jealook.com/app_img/20201013/20201013172743_78190.png"},{"brand_id":"67","brand_name":"强生ACUVUE安视优","brand_logo":"http://img.jealook.com/app_img/20200602/20200602184154_16416.png"},{"brand_id":"65","brand_name":"Givre","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094723_75337.png"},{"brand_id":"64","brand_name":"Femii","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094713_55281.png"},{"brand_id":"62","brand_name":"Pienage Barbie","brand_logo":"http://img.jealook.com/app_img/20200602/20200602184355_88576.png"},{"brand_id":"61","brand_name":"Natural i","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094649_32099.png"},{"brand_id":"60","brand_name":"Angelcolor bambi seriesvintage","brand_logo":"http://img.jealook.com/app_img/20200602/20200602184441_16572.png"},{"brand_id":"54","brand_name":"晶硕香水","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094458_73407.png"},{"brand_id":"53","brand_name":"爱尔康","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094444_11875.png"},{"brand_id":"52","brand_name":"Aocmu","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094435_68988.png"}]]
     * brand_img : http://img.jealook.com/app_img/20200630/20200630173657_97842.png
     * discount : {"count_down":16702,"image":"http://shop.jealook.com/image/activity_bg.jpg","list":[{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 自然灰棕 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"7382","search_attr":"3371|3372","color_id":"3371","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20200622/1592807730_1191.png","surplus_time":16702},{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 星辰子夜灰 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"19515","search_attr":"3372|13242","color_id":"13242","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20201016/1602820565_3223.png","surplus_time":16702},{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 晨曦曙色棕 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"19541","search_attr":"3372|13243","color_id":"13243","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20201016/1602820565_5030.png","surplus_time":16702}]}
     * discount_img : http://img.jealook.com/app_img/20200630/20200630173800_36486.png
     * shop : [{"id":297,"image":{"value":"1","type":"1","id":"29","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201012/20201012092819_45921.png","list":{"goods_id":"1","search_attr":"2|3","active_id":"","url":"","text":"","id":""},"images":"http://img.jealook.com/app_img/20200630/20200630173716_95162.png"},"title":"日抛","data":[{"product_price":"108.00","search_attr":"564|588","preferential_price":"88.00","color_id":"588","goods_id":"20","goods_name":"Evercolor日抛型 Aqua Beige 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200401/1585734282_2780.png"},{"product_price":"145.00","search_attr":"529|555","preferential_price":"125.00","color_id":"555","goods_id":"19","goods_name":"Evercolor日抛型 Chiffon Brown 20枚","virtual_sales":"7","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200924/1600928126_5659.png"},{"product_price":"225.00","search_attr":"58|85","preferential_price":"0.00","color_id":"85","goods_id":"3","goods_name":"Angelcolor bambi series vintage系列日抛型 Vintage Hazel 30枚","virtual_sales":"13","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20200401/1585725812_7494.png"},{"product_price":"85.00","search_attr":"1196|1202","preferential_price":"65.00","color_id":"1202","goods_id":"42","goods_name":"Luna日抛型 Aqua 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1599550881","promote_end_date":"1600099200","brand_name":"Luna","goods_thumb":"http://img.jealook.com/backend/20200401/1585734480_5905.png"},{"product_price":"105.00","search_attr":"811|812","preferential_price":"89.00","color_id":"811","goods_id":"28","goods_name":"Givre sparkle系列日抛型 Foggy Lime 10枚","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802432","promote_end_date":"1598371200","brand_name":"sparkle","goods_thumb":"http://img.jealook.com/backend/20200401/1585732109_5405.png"},{"product_price":"228.00","search_attr":"2077|2078","preferential_price":"199.00","color_id":"2077","goods_id":"73","goods_name":"Alcon日抛型透明隐形眼镜 透明枚 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"爱尔康","goods_thumb":"http://img.jealook.com/backend/20200403/1585900813_8644.png"},{"product_price":"105.00","search_attr":"870|888","preferential_price":"104.00","color_id":"888","goods_id":"30","goods_name":"Juicy drop日抛型 Cherry 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Juicydrop","goods_thumb":"http://img.jealook.com/backend/20200401/1585732390_4036.png"},{"product_price":"85.00","search_attr":"12841|12899","preferential_price":"0.00","color_id":"12899","goods_id":"381","goods_name":"bijou 日抛型 natural系列 Airy olive 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Bijou","goods_thumb":"http://img.jealook.com/backend/20200929/1601365687_3595.png"},{"product_price":"149.00","search_attr":"3498|3524","preferential_price":"0.00","color_id":"3524","goods_id":"95","goods_name":"Givre绮芙丽 日抛型 榛果裸棕 30枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Givre","goods_thumb":"http://img.jealook.com/backend/20200403/1585896648_2987.png"}]},{"id":304,"image":{"value":"41","type":"1","id":"30","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201012/20201012092858_27152.png","list":{"goods_id":"41","search_attr":"1168|1169","active_id":"","url":"","text":"","id":""},"images":"http://img.jealook.com/app_img/20200630/20200630173729_70791.png"},"title":"双周抛","data":[{"product_price":"155.00","search_attr":"1546|1565","preferential_price":"132.00","color_id":"1565","goods_id":"54","goods_name":"Neociel双周抛型 Green 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943131","promote_end_date":"1599580800","brand_name":"Neocielsight","goods_thumb":"http://img.jealook.com/backend/20201019/1603079453_5287.png"},{"product_price":"145.00","search_attr":"493|505","preferential_price":"125.00","color_id":"505","goods_id":"18","goods_name":"eRouge双周抛型 Clarity Brown 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598422096","promote_end_date":"1599029227","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200928/1601257074_3179.png"},{"product_price":"88.00","search_attr":"3666|3692","preferential_price":"0.00","color_id":"3692","goods_id":"100","goods_name":"Erouge爱如久 双周抛型 璀璨金棕 6枚","virtual_sales":"3","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200927/1601186411_5536.png"},{"product_price":"135.00","search_attr":"9607|9630","preferential_price":"0.00","color_id":"9630","goods_id":"271","goods_name":"Pienage  Barbie 双周抛型 Barbie-Only Wish 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Pienage Barbie","goods_thumb":"http://img.jealook.com/backend/20200928/1601271383_2519.png"},{"product_price":"145.00","search_attr":"493|502","preferential_price":"125.00","color_id":"502","goods_id":"18","goods_name":"eRouge双周抛型 Chic Brown 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598422096","promote_end_date":"1599029227","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200928/1601257074_3033.png"},{"product_price":"99.00","search_attr":"10105|10106","preferential_price":"0.00","color_id":"10105","goods_id":"310","goods_name":"Rich standard 双周抛型 Innocent brown 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Rich standard","goods_thumb":"http://img.jealook.com/backend/20200911/1599808281_4229.png"},{"product_price":"145.00","search_attr":"1168|1169","preferential_price":"0.00","color_id":"1168","goods_id":"41","goods_name":"Lumia双周抛型 Chiffon Olive 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20200928/1601264568_4692.png"},{"product_price":"88.00","search_attr":"3666|13234","preferential_price":"0.00","color_id":"13234","goods_id":"100","goods_name":"Erouge爱如久 双周抛型 自然裸棕 6枚","virtual_sales":"3","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20201015/1602753725_6117.png"},{"product_price":"145.00","search_attr":"493|8755","preferential_price":"125.00","color_id":"8755","goods_id":"18","goods_name":"eRouge双周抛型 Baked Souffle 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598422096","promote_end_date":"1599029227","brand_name":"eRouge","goods_thumb":"http://img.jealook.com/backend/20200928/1601257074_7541.png"}]},{"id":315,"image":{"value":"13","type":"1","id":"31","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201012/20201012092923_56846.png","list":{"goods_id":"13","search_attr":"331|332","active_id":"","url":"","text":"","id":""},"images":"http://img.jealook.com/app_img/20200630/20200630173813_36138.png"},"title":"月抛","data":[{"product_price":"58.00","search_attr":"332|355","preferential_price":"0.00","color_id":"355","goods_id":"13","goods_name":"Chouchou月抛型 Milky Peach 1枚","virtual_sales":"10","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Chouchou","goods_thumb":"http://img.jealook.com/backend/20200924/1600915171_5145.png"},{"product_price":"66.00","search_attr":"1218|1230","preferential_price":"0.00","color_id":"1230","goods_id":"43","goods_name":"Luna月抛型 Aqua 1枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Luna","goods_thumb":"http://img.jealook.com/backend/20200401/1585734670_7985.png"},{"product_price":"95.00","search_attr":"1017|1018","preferential_price":"0.00","color_id":"1017","goods_id":"35","goods_name":"Lilmoon月抛型 Cream Beige 1枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_7458.png"},{"product_price":"108.00","search_attr":"3402|3431","preferential_price":"0.00","color_id":"3431","goods_id":"92","goods_name":"Femii妃蜜莉 月抛型 傲娇褐绿 6枚","virtual_sales":"1","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Femii","goods_thumb":"http://img.jealook.com/backend/20200622/1592807542_3936.png"},{"product_price":"119.00","search_attr":"392|417","preferential_price":"139.50","color_id":"417","goods_id":"15","goods_name":"Dorb月抛型 Choco 3枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Dorb","goods_thumb":"http://img.jealook.com/backend/20200401/1585729119_4440.png"},{"product_price":"49.00","search_attr":"7434|7470","preferential_price":"0.00","color_id":"7470","goods_id":"170","goods_name":"Seed dreaming 以梦 月抛 Wateraqua清澄绿 1枚","virtual_sales":"34","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"以梦seed","goods_thumb":"http://img.jealook.com/backend/20200923/1600845794_1265.png"},{"product_price":"98.00","search_attr":"1799|11874","preferential_price":"0.00","color_id":"11874","goods_id":"63","goods_name":"Refrear 月抛型 Refrear限量版 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200927/1601199186_8519.png"},{"product_price":"58.00","search_attr":"332|358","preferential_price":"0.00","color_id":"358","goods_id":"13","goods_name":"Chouchou月抛型 Orange Brown 1枚","virtual_sales":"10","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Chouchou","goods_thumb":"http://img.jealook.com/backend/20200924/1600915171_3048.png"},{"product_price":"68.00","search_attr":"1335|1336","preferential_price":"0.00","color_id":"1335","goods_id":"47","goods_name":"Miche Bloomin月抛型 Apricot Nude 1枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Michebloomin","goods_thumb":"http://img.jealook.com/backend/20200402/1585792592_8249.png"}]},{"id":0,"image":{"value":"73","type":"1","id":"32","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201012/20201012092946_43302.png","list":{"goods_id":"73","search_attr":"2077|2078","active_id":"","url":"","text":"","id":""},"images":"http://img.jealook.com/app_img/20200630/20200630173744_68085.png"},"title":"透明片","data":[{"product_price":"88.00","search_attr":"11122|11123","preferential_price":"0.00","color_id":"11122","goods_id":"344","goods_name":"refrear 日抛型 refrear 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200914/1600070422_1690.png"},{"product_price":"105.00","search_attr":"9887|9888","preferential_price":"0.00","color_id":"9887","goods_id":"303","goods_name":"PureNatural 日抛型 透明片38%（新款） 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Purenatural","goods_thumb":"http://img.jealook.com/backend/20200911/1599788512_5696.png"},{"product_price":"225.00","search_attr":"10542|10543","preferential_price":"0.00","color_id":"10542","goods_id":"327","goods_name":"强生ACUVUE 安视优欧舒适oasys 日抛型 透明片 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20200914/1600053235_5461.png"},{"product_price":"98.00","search_attr":"1799|11874","preferential_price":"0.00","color_id":"11874","goods_id":"63","goods_name":"Refrear 月抛型 Refrear限量版 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200927/1601199186_8519.png"},{"product_price":"88.00","search_attr":"759|760","preferential_price":"87.00","color_id":"759","goods_id":"26","goods_name":"Flanmy 日抛型 Flanmy Clear  20枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Flanmy","goods_thumb":"http://img.jealook.com/backend/20200401/1585731842_4971.png"},{"product_price":"228.00","search_attr":"2077|2078","preferential_price":"199.00","color_id":"2077","goods_id":"73","goods_name":"Alcon日抛型透明隐形眼镜 透明枚 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"爱尔康","goods_thumb":"http://img.jealook.com/backend/20200403/1585900813_8644.png"},{"product_price":"85.00","search_attr":"1771|1772","preferential_price":"0.00","color_id":"1771","goods_id":"62","goods_name":"Refrear 双周抛型 Refrear经典版 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200927/1601199355_6370.png"},{"product_price":"169.00","search_attr":"10631|10632","preferential_price":"0.00","color_id":"10631","goods_id":"329","goods_name":"强生ACUVUE 安视优舒日moist 日抛型 透明片 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20200914/1600053917_8709.png"},{"product_price":"88.00","search_attr":"11178|11179","preferential_price":"0.00","color_id":"11178","goods_id":"346","goods_name":"refrear 日抛型 refrear限量包装 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200927/1601198961_2006.png"}]}]
     */

    private IndexAdBean index_ad;
    private AlertAdBean alert_ad;
    private String brand_img;
    private DiscountBean discount;
    private String discount_img;
    private List<BannerBean> banner;
    private List<ArtcleBean> artcle;
    private List<List<BrandBean>> brand;
    private List<ShopBean> shop;

    public IndexAdBean getIndex_ad() {
        return index_ad;
    }

    public void setIndex_ad(IndexAdBean index_ad) {
        this.index_ad = index_ad;
    }

    public AlertAdBean getAlert_ad() {
        return alert_ad;
    }

    public void setAlert_ad(AlertAdBean alert_ad) {
        this.alert_ad = alert_ad;
    }

    public String getBrand_img() {
        return brand_img;
    }

    public void setBrand_img(String brand_img) {
        this.brand_img = brand_img;
    }

    public DiscountBean getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountBean discount) {
        this.discount = discount;
    }

    public String getDiscount_img() {
        return discount_img;
    }

    public void setDiscount_img(String discount_img) {
        this.discount_img = discount_img;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ArtcleBean> getArtcle() {
        return artcle;
    }

    public void setArtcle(List<ArtcleBean> artcle) {
        this.artcle = artcle;
    }

    public List<List<BrandBean>> getBrand() {
        return brand;
    }

    public void setBrand(List<List<BrandBean>> brand) {
        this.brand = brand;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public static class IndexAdBean {
        /**
         * value :
         * type : 6
         * id : 21
         * end_time :
         * start_time :
         * photo : http://img.jealook.com/app_img/20201022/20201022100509_67947.png
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String end_time;
        private String start_time;
        private String photo;
        private ListBean list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id :
             * search_attr :
             * active_id :
             * url :
             * text :
             * id :
             */

            private String goods_id;
            private String search_attr;
            private String active_id;
            private String url;
            private String text;
            private String id;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class AlertAdBean {
        /**
         * value :
         * type :
         * id :
         * photo :
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String photo;
        private ListBeanX list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * goods_id :
             * search_attr :
             * active_id :
             * url :
             * text :
             * id :
             */

            private String goods_id;
            private String search_attr;
            private String active_id;//type==7品牌ID
            private String url;
            private String text;
            private String id;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class DiscountBean {
        /**
         * count_down : 16702
         * image : http://shop.jealook.com/image/activity_bg.jpg
         * list : [{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 自然灰棕 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"7382","search_attr":"3371|3372","color_id":"3371","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20200622/1592807730_1191.png","surplus_time":16702},{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 星辰子夜灰 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"19515","search_attr":"3372|13242","color_id":"13242","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20201016/1602820565_3223.png","surplus_time":16702},{"goods_id":"91","sort_order":"100","is_promote":1,"goods_name":"Femii 妃蜜莉日抛 晨曦曙色棕 10枚","virtual_sales":"0","suppliers_id":"1","preferential_price":"68.00","product_price":"69.00","product_id":"19541","search_attr":"3372|13243","color_id":"13243","promote_start_date":"1599550839","promote_end_date":"1603349245","goods_thumb":"http://img.jealook.com/backend/20201016/1602820565_5030.png","surplus_time":16702}]
         */

        private int count_down;
        private String image;
        private List<ListBeanXX> list;

        public int getCount_down() {
            return count_down;
        }

        public void setCount_down(int count_down) {
            this.count_down = count_down;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<ListBeanXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXX> list) {
            this.list = list;
        }

        public static class ListBeanXX {
            /**
             * goods_id : 91
             * sort_order : 100
             * is_promote : 1
             * goods_name : Femii 妃蜜莉日抛 自然灰棕 10枚
             * virtual_sales : 0
             * suppliers_id : 1
             * preferential_price : 68.00
             * product_price : 69.00
             * product_id : 7382
             * search_attr : 3371|3372
             * color_id : 3371
             * promote_start_date : 1599550839
             * promote_end_date : 1603349245
             * goods_thumb : http://img.jealook.com/backend/20200622/1592807730_1191.png
             * surplus_time : 16702
             */

            private String goods_id;
            private String sort_order;
            private int is_promote;
            private String goods_name;
            private String virtual_sales;
            private String suppliers_id;
            private String preferential_price;
            private String product_price;
            private String product_id;
            private String search_attr;
            private String color_id;
            private String promote_start_date;
            private String promote_end_date;
            private String goods_thumb;
            private int surplus_time;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getVirtual_sales() {
                return virtual_sales;
            }

            public void setVirtual_sales(String virtual_sales) {
                this.virtual_sales = virtual_sales;
            }

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public String getPreferential_price() {
                return preferential_price;
            }

            public void setPreferential_price(String preferential_price) {
                this.preferential_price = preferential_price;
            }

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
            }

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public int getSurplus_time() {
                return surplus_time;
            }

            public void setSurplus_time(int surplus_time) {
                this.surplus_time = surplus_time;
            }
        }
    }

    public static class BannerBean {
        /**
         * value : 166
         * type : 1
         * id : 17
         * photo : http://img.jealook.com/app_img/20200909/20200909143826_71527.jpg
         * list : {"goods_id":"166","search_attr":"7250|7251","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String photo;
        private ListBeanXXX list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBeanXXX getList() {
            return list;
        }

        public void setList(ListBeanXXX list) {
            this.list = list;
        }

        public static class ListBeanXXX {
            /**
             * goods_id : 166
             * search_attr : 7250|7251
             * active_id :
             * url :
             * text :
             * id :
             */

            private String goods_id;
            private String search_attr;
            private String active_id;
            private String url;
            private String text;
            private String id;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class ArtcleBean {
        /**
         * id : 1
         * title : 购物须知
         * content :
         * add_time : 2019-10-30 22:58:58
         * info_url : http://shop.jealook.com/v1/html/article-info?id=1
         */

        private String id;
        private String title;
        private String content;
        private String add_time;
        private String info_url;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }
    }

    public static class BrandBean {
        /**
         * brand_id : 89
         * brand_name : new mermer
         * brand_logo : http://img.jealook.com/app_img/20200909/20200909133250_50239.png
         */

        private String brand_id;
        private String brand_name;
        private String brand_logo;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }
    }

    public static class ShopBean {
        /**
         * id : 297
         * image : {"value":"1","type":"1","id":"29","end_time":"","start_time":"","photo":"http://img.jealook.com/app_img/20201012/20201012092819_45921.png","list":{"goods_id":"1","search_attr":"2|3","active_id":"","url":"","text":"","id":""},"images":"http://img.jealook.com/app_img/20200630/20200630173716_95162.png"}
         * title : 日抛
         * data : [{"product_price":"108.00","search_attr":"564|588","preferential_price":"88.00","color_id":"588","goods_id":"20","goods_name":"Evercolor日抛型 Aqua Beige 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200401/1585734282_2780.png"},{"product_price":"145.00","search_attr":"529|555","preferential_price":"125.00","color_id":"555","goods_id":"19","goods_name":"Evercolor日抛型 Chiffon Brown 20枚","virtual_sales":"7","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200924/1600928126_5659.png"},{"product_price":"225.00","search_attr":"58|85","preferential_price":"0.00","color_id":"85","goods_id":"3","goods_name":"Angelcolor bambi series vintage系列日抛型 Vintage Hazel 30枚","virtual_sales":"13","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20200401/1585725812_7494.png"},{"product_price":"85.00","search_attr":"1196|1202","preferential_price":"65.00","color_id":"1202","goods_id":"42","goods_name":"Luna日抛型 Aqua 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1599550881","promote_end_date":"1600099200","brand_name":"Luna","goods_thumb":"http://img.jealook.com/backend/20200401/1585734480_5905.png"},{"product_price":"105.00","search_attr":"811|812","preferential_price":"89.00","color_id":"811","goods_id":"28","goods_name":"Givre sparkle系列日抛型 Foggy Lime 10枚","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802432","promote_end_date":"1598371200","brand_name":"sparkle","goods_thumb":"http://img.jealook.com/backend/20200401/1585732109_5405.png"},{"product_price":"228.00","search_attr":"2077|2078","preferential_price":"199.00","color_id":"2077","goods_id":"73","goods_name":"Alcon日抛型透明隐形眼镜 透明枚 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"爱尔康","goods_thumb":"http://img.jealook.com/backend/20200403/1585900813_8644.png"},{"product_price":"105.00","search_attr":"870|888","preferential_price":"104.00","color_id":"888","goods_id":"30","goods_name":"Juicy drop日抛型 Cherry 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Juicydrop","goods_thumb":"http://img.jealook.com/backend/20200401/1585732390_4036.png"},{"product_price":"85.00","search_attr":"12841|12899","preferential_price":"0.00","color_id":"12899","goods_id":"381","goods_name":"bijou 日抛型 natural系列 Airy olive 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Bijou","goods_thumb":"http://img.jealook.com/backend/20200929/1601365687_3595.png"},{"product_price":"149.00","search_attr":"3498|3524","preferential_price":"0.00","color_id":"3524","goods_id":"95","goods_name":"Givre绮芙丽 日抛型 榛果裸棕 30枚","virtual_sales":"0","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Givre","goods_thumb":"http://img.jealook.com/backend/20200403/1585896648_2987.png"}]
         */

        private int id;
        private ImageBean image;
        private String title;
        private List<DataBean> data;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class ImageBean {
            /**
             * value : 1
             * type : 1
             * id : 29
             * end_time :
             * start_time :
             * photo : http://img.jealook.com/app_img/20201012/20201012092819_45921.png
             * list : {"goods_id":"1","search_attr":"2|3","active_id":"","url":"","text":"","id":""}
             * images : http://img.jealook.com/app_img/20200630/20200630173716_95162.png
             */

            private String value;
            private String type;
            private String id;
            private String end_time;
            private String start_time;
            private String photo;
            private ListBeanXXXX list;
            private String images;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public ListBeanXXXX getList() {
                return list;
            }

            public void setList(ListBeanXXXX list) {
                this.list = list;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public static class ListBeanXXXX {
                /**
                 * goods_id : 1
                 * search_attr : 2|3
                 * active_id :
                 * url :
                 * text :
                 * id :
                 */

                private String goods_id;
                private String search_attr;
                private String active_id;
                private String url;
                private String text;
                private String id;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getSearch_attr() {
                    return search_attr;
                }

                public void setSearch_attr(String search_attr) {
                    this.search_attr = search_attr;
                }

                public String getActive_id() {
                    return active_id;
                }

                public void setActive_id(String active_id) {
                    this.active_id = active_id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }

        public static class DataBean {
            /**
             * product_price : 108.00
             * search_attr : 564|588
             * preferential_price : 88.00
             * color_id : 588
             * goods_id : 20
             * goods_name : Evercolor日抛型 Aqua Beige 10枚
             * virtual_sales : 0
             * suppliers_id : 2
             * is_promote : 0
             * promote_start_date : 1594051200
             * promote_end_date : 1594656000
             * brand_name : Evercolor
             * goods_thumb : http://img.jealook.com/backend/20200401/1585734282_2780.png
             */

            private String product_price;
            private String search_attr;
            private String preferential_price;
            private String color_id;
            private String goods_id;
            private String goods_name;
            private String virtual_sales;
            private String suppliers_id;
            private int is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String brand_name;
            private String goods_thumb;

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getPreferential_price() {
                return preferential_price;
            }

            public void setPreferential_price(String preferential_price) {
                this.preferential_price = preferential_price;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getVirtual_sales() {
                return virtual_sales;
            }

            public void setVirtual_sales(String virtual_sales) {
                this.virtual_sales = virtual_sales;
            }

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }
        }
    }
}
