package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/12/11$
 * @Author:pk$
 */
public class PreferentialBean extends BaseBean {
    /**
     * act_name : 医美面膜节
     * type : 6
     * act_range_ext : 428,429,430,431,432,433,434,436,437,438,439,440,442,443,444,447,448,449,450,451,461,462,463,465,466,474,475,476,477,479,481,482,483,484,485,486,487,426,427,412
     * start_time : 1617960695
     * end_time : 1619107200
     * gift :
     * figure : 25
     * min_amount : 200.00
     * shop_list : [{"product_id":"25334","product_sn":"6941429500037","color_id":"21744","product_price":"38.00","preferential_price":"0.00","search_attr":"18352|21744","goods_id":"447","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 斐思妮 黄膜 医美","virtual_sales":"11","attr_value":"美白抗氧化","product_number":"32","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210114/1610590473_6628.png"},{"product_id":"25329","product_sn":"6934997214116","color_id":"16639","product_price":"64.00","preferential_price":"0.00","search_attr":"16583|16639","goods_id":"427","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"绽妍 面膜 医美","virtual_sales":"23","attr_value":"补水王","product_number":"37","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608795435_1344.png"},{"product_id":"25316","product_sn":"6948060454071","color_id":"18501","product_price":"85.00","preferential_price":"0.00","search_attr":"18445|18501","goods_id":"448","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"米蓓尔 蓝色冻干面膜 医美","virtual_sales":"2","attr_value":"补水","product_number":"21","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617965659_1404.png"},{"product_id":"25306","product_sn":"6925755806391","color_id":"21967","product_price":"55.00","preferential_price":"0.00","search_attr":"21890|21967","goods_id":"482","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"斐思妮 红膜 医美","virtual_sales":"4","attr_value":"褪红提亮补水","product_number":"37","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210114/1610592783_4448.png"},{"product_id":"25300","product_sn":"6972946590652","color_id":"15364","product_price":"25.00","preferential_price":"0.00","search_attr":"15312|15364","goods_id":"412","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"斐思妮 冰膜  医美","virtual_sales":"16","attr_value":"晒后修复保湿美白","product_number":"15","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610373335_4049.png"},{"product_id":"25294","product_sn":"6972946590645","color_id":"21635","product_price":"35.00","preferential_price":"0.00","search_attr":"21561|21635","goods_id":"479","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"斐思妮 冰粉膜 医美","virtual_sales":"12","attr_value":" 补水抗氧化","product_number":"14","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617965891_2498.png"},{"product_id":"22725","product_sn":"6927999300680","color_id":"19740","product_price":"75.00","preferential_price":"0.00","search_attr":"19740|19741","goods_id":"461","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 芙清 洁面 医美","virtual_sales":"3","attr_value":"抑菌修复净肤止痒","product_number":"29","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617965453_2708.png"},{"product_id":"21439","product_sn":"6941959099926","color_id":"16550","product_price":"65.00","preferential_price":null,"search_attr":"16461|16550","goods_id":"426","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 敷尔佳 虾青素面膜 医美","virtual_sales":"27","attr_value":"美白祛红血丝","product_number":"402","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210111/1610371346_1521.png"},{"product_id":"25315","product_sn":"6948060454088","color_id":"22519","product_price":"85.00","preferential_price":"0.00","search_attr":"22441|22519","goods_id":"487","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"米蓓尔  粉色冻干面膜 医美","virtual_sales":"1","attr_value":"美白","product_number":"45","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617966148_7301.png"},{"product_id":"25312","product_sn":"6921863303784","color_id":"22407","product_price":"88.00","preferential_price":null,"search_attr":"22407|22408","goods_id":"486","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"宝龄玻尿酸面膜 医美","virtual_sales":"0","attr_value":"日常补水","product_number":"6","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210128/1611822790_2349.png"},{"product_id":"25310","product_sn":"6927999300345","color_id":"22297","product_price":"78.00","preferential_price":null,"search_attr":"22189|22297","goods_id":"485","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"芙芙 白芙芙面膜 医美","virtual_sales":"7","attr_value":"祛痘","product_number":"125","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617966135_2183.png"},{"product_id":"25308","product_sn":"6927999300758","color_id":"22187","product_price":"78.00","preferential_price":"0.00","search_attr":"22127|22187","goods_id":"484","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"芙清 黑膜 医美","virtual_sales":"2","attr_value":"抑菌祛痘","product_number":"56","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210408/1617856364_1785.png"},{"product_id":"25307","product_sn":"6927999300031","color_id":"22077","product_price":"39.00","preferential_price":"0.00","search_attr":"21997|22077","goods_id":"483","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"芙清 凝胶 医美","virtual_sales":"9","attr_value":"消炎","product_number":"88","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617965987_8924.png"},{"product_id":"25301","product_sn":"6970470450367","color_id":"21857","product_price":"35.00","preferential_price":null,"search_attr":"21781|21857","goods_id":"481","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"斐思妮 灯泡膜 医美","virtual_sales":"20","attr_value":"美白超美白","product_number":"32","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617965934_5912.png"},{"product_id":"25287","product_sn":"6974063510292","color_id":"21425","product_price":"95.00","preferential_price":null,"search_attr":"21322|21425","goods_id":"477","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"敷尔佳 黑膜 医美","virtual_sales":"19","attr_value":"美白祛痘印","product_number":"47","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210114/1610587895_5320.png"},{"product_id":"25286","product_sn":"6941959099780","color_id":"21320","product_price":"65.00","preferential_price":"0.00","search_attr":"21217|21320","goods_id":"476","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 敷尔佳 绿膜 医美","virtual_sales":"33","attr_value":"祛痘祛闭口","product_number":"470","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210114/1610587636_6476.png"},{"product_id":"25285","product_sn":"6941959099575","color_id":"21215","product_price":"68.00","preferential_price":"0.00","search_attr":"21112|21215","goods_id":"475","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 敷尔佳 积雪草面膜 医美","virtual_sales":"51","attr_value":"镇静舒敏","product_number":"376","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210113/1610544561_7496.png"},{"product_id":"25284","product_sn":"6941959099971","color_id":"21110","product_price":"65.00","preferential_price":"0.00","search_attr":"21007|21110","goods_id":"474","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"敷尔佳 白膜 医美","virtual_sales":"137","attr_value":"补水修复","product_number":"581","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210113/1610544223_4201.png"},{"product_id":"22729","product_sn":"614969000126","color_id":"20237","product_price":"409.00","preferential_price":null,"search_attr":"20139|20237","goods_id":"466","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"奥伦纳素 冰白面膜 医美","virtual_sales":"3","attr_value":"细致毛孔焕亮肤色","product_number":"36","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201230/1609299760_1533.png"},{"product_id":"22727","product_sn":"3760237080004","color_id":"20136","product_price":"33.00","preferential_price":"0.00","search_attr":"20038|20136","goods_id":"465","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"美帕 壳聚糖面膜 医美","virtual_sales":"29","attr_value":"淡化红血丝提亮肤色","product_number":"119","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201230/1609300873_7850.png"},{"product_id":"22724","product_sn":"6955449508768","color_id":"19937","product_price":"148.00","preferential_price":"0.00","search_attr":"19937|19938","goods_id":"463","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"优斐思 传明酸精华 医美","virtual_sales":"1","attr_value":"保湿提亮修护","product_number":"14","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201230/1609310835_7671.png"},{"product_id":"22722","product_sn":"6958514900023","color_id":"19839","product_price":"148.00","preferential_price":"0.00","search_attr":"19774|19839","goods_id":"462","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"艺霏 虾青素精华 医美","virtual_sales":"2","attr_value":"修护保湿淡化细纹","product_number":"9","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201230/1609316711_6121.png"},{"product_id":"21471","product_sn":"6944296203315","color_id":"18769","product_price":"159.00","preferential_price":"0.00","search_attr":"18713|18769","goods_id":"451","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 可复美 涂抹面膜 修复 亮肤 紧致 医美","virtual_sales":"13","attr_value":"青春面膜240g","product_number":"42","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210413/1618320990_7716.png"},{"product_id":"21470","product_sn":"6971136102194","color_id":"18680","product_price":"48.00","preferential_price":"48.00","search_attr":"18593|18680","goods_id":"450","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 菲诗凯尔  面膜 医美","virtual_sales":"0","attr_value":"补水清洁","product_number":"33","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609232965_6019.png"},{"product_id":"21469","product_sn":"6938001600032","color_id":"18591","product_price":"50.00","preferential_price":"0.00","search_attr":"18504|18591","goods_id":"449","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"九立德骨胶原 面膜 医美","virtual_sales":"3","attr_value":" 补水保湿","product_number":"10","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609232403_5750.png"},{"product_id":"21461","product_sn":"6972376670085","color_id":"18142","product_price":"48.00","preferential_price":"0.00","search_attr":"18086|18142","goods_id":"444","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":" 舒奈美 透明质酸面膜 医美","virtual_sales":"1","attr_value":" 祛痘修复镇定","product_number":"69","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609222996_2618.png"},{"product_id":"21460","product_sn":"6971027950033","color_id":"18053","product_price":"46.00","preferential_price":"0.00","search_attr":"17969|18053","goods_id":"443","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"菲尔思 面膜 医美","virtual_sales":"14","attr_value":"日常维稳 ","product_number":"64","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609220533_2248.png"},{"product_id":"21459","product_sn":"6953665000080","color_id":"17963","product_price":"168.00","preferential_price":"0.00","search_attr":"17963|17964","goods_id":"442","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"博乐达 水杨酸面膜 医美","virtual_sales":"9","attr_value":"疏通毛孔","product_number":"13","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609213966_6634.png"},{"product_id":"21457","product_sn":"6956152920120","color_id":"17787","product_price":"70.00","preferential_price":"0.00","search_attr":"17704|17787","goods_id":"440","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"蓝活玉 面膜 医美","virtual_sales":"7","attr_value":"修复痤疮色沉","product_number":"6","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609208945_3879.png"},{"product_id":"21456","product_sn":"6956152910060","color_id":"17699","product_price":"66.00","preferential_price":"0.00","search_attr":"17613|17699","goods_id":"439","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"白活玉 面膜 医美","virtual_sales":"4","attr_value":"牛奶皮淡化痘印","product_number":"35","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609206148_2626.png"},{"product_id":"21455","product_sn":"6948060453142","color_id":"17611","product_price":"78.00","preferential_price":null,"search_attr":"17525|17611","goods_id":"438","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"润百颜 肉肉面膜 医美","virtual_sales":"5","attr_value":" 补水","product_number":"13","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201229/1609205681_8775.png"},{"product_id":"21453","product_sn":"6921863303777","color_id":"17522","product_price":"88.00","preferential_price":null,"search_attr":"17440|17522","goods_id":"437","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"宝龄传明酸面膜 医美","virtual_sales":"4","attr_value":" 美白亮肤急救 ","product_number":"36","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201228/1609150578_3776.png"},{"product_id":"21452","product_sn":"6953309400788","color_id":"17434","product_price":"75.00","preferential_price":"0.00","search_attr":"17351|17434","goods_id":"436","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"天舒 面膜 医美","virtual_sales":"5","attr_value":"淡化痘印疏通毛孔","product_number":"28","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608803262_5853.png"},{"product_id":"21448","product_sn":"6935796613056","color_id":"17168","product_price":"78.00","preferential_price":"0.00","search_attr":"17088|17168","goods_id":"433","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"娇法尔 面膜 医美","virtual_sales":"2","attr_value":"补水保湿修复","product_number":"8","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608798107_2414.png"},{"product_id":"21447","product_sn":"6958286400042","color_id":"17080","product_price":"59.00","preferential_price":"0.00","search_attr":"16994|17080","goods_id":"432","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"美卿 面膜 医美","virtual_sales":"7","attr_value":"急救修复镇定","product_number":"8","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608797855_8337.png"},{"product_id":"21446","product_sn":"6941150600020","color_id":"16992","product_price":"75.00","preferential_price":"0.00","search_attr":"16906|16992","goods_id":"431","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"创福康 面膜 医美","virtual_sales":"9","attr_value":"补水维稳","product_number":"205","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608797544_7253.png"},{"product_id":"21444","product_sn":"6972488060064","color_id":"16903","product_price":"75.00","preferential_price":"0.00","search_attr":"16817|16903","goods_id":"430","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"芙芙 绿芙芙面膜 医美","virtual_sales":"14","attr_value":"去粉刺闭口","product_number":"2","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608796964_5383.png"},{"product_id":"21443","product_sn":"6927999300024","color_id":"16815","product_price":"68.00","preferential_price":"0.00","search_attr":"16729|16815","goods_id":"429","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"芙清 面膜 医美","virtual_sales":"20","attr_value":"祛痘祛闭口","product_number":"78","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210409/1617963112_4331.png"},{"product_id":"21442","product_sn":"6936860100885","color_id":"16727","product_price":"99.00","preferential_price":"0.00","search_attr":"16641|16727","goods_id":"428","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"可复美 面膜 医美","virtual_sales":"8","attr_value":"术后修复补水","product_number":"9","goods_thumb":"http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20201224/1608795986_6840.png"}]
     * count_down : 713569
     * url : https://shop.jealook.com/v5/html/active-info?id=130
     * flag :
     * price :
     */

    private String act_name;
    private String type;
    private String act_range_ext;
    private String start_time;
    private String end_time;
    private String gift;
    private String figure;
    private String min_amount;
    private int count_down;
    private String url;
    private String flag;
    private String price;
    private List<ShopListBean> shop_list;
    private String active_name;

    public String getActive_name() {
        return active_name;
    }

    public void setActive_name(String active_name) {
        this.active_name = active_name;
    }
    
    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAct_range_ext() {
        return act_range_ext;
    }

    public void setAct_range_ext(String act_range_ext) {
        this.act_range_ext = act_range_ext;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public int getCount_down() {
        return count_down;
    }

    public void setCount_down(int count_down) {
        this.count_down = count_down;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ShopListBean> getShop_list() {
        return shop_list;
    }

    public void setShop_list(List<ShopListBean> shop_list) {
        this.shop_list = shop_list;
    }

    public static class ShopListBean {
        /**
         * product_id : 25334
         * product_sn : 6941429500037
         * color_id : 21744
         * product_price : 38.00
         * preferential_price : 0.00
         * search_attr : 18352|21744
         * goods_id : 447
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * suppliers_id : 1
         * goods_name :  斐思妮 黄膜 医美
         * virtual_sales : 11
         * attr_value : 美白抗氧化
         * product_number : 32
         * goods_thumb : http://vinnlook1.oss-cn-zhangjiakou.aliyuncs.com/backend/20210114/1610590473_6628.png
         */

        private String product_id;
        private String product_sn;
        private String color_id;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String goods_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String virtual_sales;
        private String attr_value;
        private String product_number;
        private String goods_thumb;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_sn() {
            return product_sn;
        }

        public void setProduct_sn(String product_sn) {
            this.product_sn = product_sn;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(String preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(String suppliers_id) {
            this.suppliers_id = suppliers_id;
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

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }

        public String getProduct_number() {
            return product_number;
        }

        public void setProduct_number(String product_number) {
            this.product_number = product_number;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }
    }
}
