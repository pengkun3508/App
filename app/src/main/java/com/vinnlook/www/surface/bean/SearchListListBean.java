package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/25$
 * @Author:pk$
 */
public class SearchListListBean extends BaseBean {
    /**
     * list : [{"product_id":"21436","color_id":"16547","product_price":"65.00","preferential_price":"0.00","search_attr":"16547","diameter":"无","base_curve":"无","water_content":"无","goods_id":"426","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 敷尔佳系列 白膜 补水修复","virtual_sales":"0","attr_value":"白膜 补水修复","goods_thumb":"http://img.jealook.com/backend/20201224/1608794759_4429.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21437","color_id":"16548","product_price":"65.00","preferential_price":"0.00","search_attr":"16461|16548","diameter":"无","base_curve":"无","water_content":"无","goods_id":"426","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 敷尔佳系列 绿膜 祛痘祛闭口 5片","virtual_sales":"0","attr_value":"绿膜 祛痘祛闭口","goods_thumb":"http://img.jealook.com/backend/20201224/1608794759_1782.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21439","color_id":"16550","product_price":"65.00","preferential_price":"0.00","search_attr":"16461|16550","diameter":"无","base_curve":"无","water_content":"无","goods_id":"426","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 敷尔佳系列 灯泡面膜 虾青素 美白祛红血丝 5片","virtual_sales":"0","attr_value":"灯泡面膜 虾青素 美白祛红血丝","goods_thumb":"http://img.jealook.com/backend/20201224/1608794759_4285.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21440","color_id":"16551","product_price":"68.00","preferential_price":"0.00","search_attr":"16461|16551","diameter":"无","base_curve":"无","water_content":"无","goods_id":"426","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 敷尔佳系列 积雪草 镇静舒敏 5片","virtual_sales":"0","attr_value":"积雪草 镇静舒敏","goods_thumb":"http://img.jealook.com/backend/20201224/1608794759_6520.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21442","color_id":"16727","product_price":"99.00","preferential_price":"0.00","search_attr":"16641|16727","diameter":"无","base_curve":"无","water_content":"无","goods_id":"428","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 可复美 术后修复补水 5片 可复美 5片","virtual_sales":"0","attr_value":"可复美","goods_thumb":"http://img.jealook.com/backend/20201224/1608795986_6840.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21443","color_id":"16815","product_price":"68.00","preferential_price":"0.00","search_attr":"16729|16815","diameter":"无","base_curve":"无","water_content":"无","goods_id":"429","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜  芙清面膜 祛痘祛闭口 黑膜 抑菌祛痘 5片 芙清凝胶 10g 痘痘点涂 芙清 祛痘祛闭口 5片 5片","virtual_sales":"0","attr_value":"芙清 祛痘祛闭口 5片","goods_thumb":"http://img.jealook.com/backend/20201224/1608796306_7324.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21449","color_id":"17169","product_price":"39.00","preferential_price":"0.00","search_attr":"16729|17169","diameter":"无","base_curve":"无","water_content":"无","goods_id":"429","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜  芙清面膜 祛痘祛闭口 黑膜 抑菌祛痘 5片 芙清凝胶 10g 痘痘点涂 芙清 凝胶10g 5片","virtual_sales":"0","attr_value":"芙清 凝胶10g","goods_thumb":"http://img.jealook.com/backend/20201224/1608798497_7750.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21451","color_id":"17346","product_price":"78.00","preferential_price":"0.00","search_attr":"16729|17346","diameter":"无","base_curve":"无","water_content":"无","goods_id":"429","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜  芙清面膜 祛痘祛闭口 黑膜 抑菌祛痘 5片 芙清凝胶 10g 痘痘点涂 芙清 黑膜 抑菌祛痘 5片","virtual_sales":"0","attr_value":"芙清 黑膜 抑菌祛痘","goods_thumb":"http://img.jealook.com/backend/20201224/1608800394_8888.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21444","color_id":"16903","product_price":"75.00","preferential_price":"0.00","search_attr":"16817|16903","diameter":"无","base_curve":"无","water_content":"无","goods_id":"430","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 芙芙面膜  祛粉刺 祛闭口 祛痘 5片 绿芙芙 祛粉刺祛闭口 5片 5片","virtual_sales":"0","attr_value":"绿芙芙 祛粉刺祛闭口 5片","goods_thumb":"http://img.jealook.com/backend/20201224/1608796964_7569.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21445","color_id":"16904","product_price":"78.00","preferential_price":"0.00","search_attr":"16817|16904","diameter":"无","base_curve":"无","water_content":"无","goods_id":"430","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 芙芙面膜  祛粉刺 祛闭口 祛痘 5片 白芙芙 祛痘 5片 5片","virtual_sales":"0","attr_value":"白芙芙 祛痘 5片","goods_thumb":"http://img.jealook.com/backend/20201224/1608796964_2188.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21446","color_id":"16992","product_price":"75.00","preferential_price":"0.00","search_attr":"16906|16992","diameter":"无","base_curve":"无","water_content":"无","goods_id":"431","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 创福康 补水维稳 5片 创富康 补水维稳 5片 5片","virtual_sales":"0","attr_value":"创富康 补水维稳 5片","goods_thumb":"http://img.jealook.com/backend/20201224/1608797544_7253.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21447","color_id":"17080","product_price":"59.00","preferential_price":"0.00","search_attr":"16994|17080","diameter":"无","base_curve":"无","water_content":"无","goods_id":"432","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 美卿 急救修复镇定 5片 美卿 急救修复镇定 5片 5片","virtual_sales":"0","attr_value":"美卿 急救修复镇定 5片","goods_thumb":"http://img.jealook.com/backend/20201224/1608797855_8337.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21448","color_id":"17168","product_price":"78.00","preferential_price":"0.00","search_attr":"17088|17168","diameter":"无","base_curve":"无","water_content":"无","goods_id":"433","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 娇法尔  补水保湿修复  8片 娇法尔  补水保湿修复  8片 8片","virtual_sales":"0","attr_value":"娇法尔  补水保湿修复  8片","goods_thumb":"http://img.jealook.com/backend/20201224/1608798107_2414.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21450","color_id":"17258","product_price":"35.00","preferential_price":"0.00","search_attr":"17176|17258","diameter":"无","base_curve":"无","water_content":"无","goods_id":"434","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 斐思妮 绿膜 祛红 镇定 修复 10片 斐思妮 绿膜 祛红 镇定 修复 10片","virtual_sales":"0","attr_value":"斐思妮 绿膜 祛红 镇定 修复","goods_thumb":"http://img.jealook.com/backend/20201224/1608799129_4726.png","attr":"直径：无 | 基弧：无 | 含水量：无"},{"product_id":"21452","color_id":"17434","product_price":"75.00","preferential_price":"0.00","search_attr":"17351|17434","diameter":"无","base_curve":"无","water_content":"无","goods_id":"436","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜 天舒 淡化痘印疏通毛孔 7片 天舒 淡化痘印疏通毛孔 7片 7片","virtual_sales":"0","attr_value":"天舒 淡化痘印疏通毛孔 7片","goods_thumb":"http://img.jealook.com/backend/20201224/1608803262_5853.png","attr":"直径：无 | 基弧：无 | 含水量：无"}]
     * count : 43
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * product_id : 21436
         * color_id : 16547
         * product_price : 65.00
         * preferential_price : 0.00
         * search_attr : 16547
         * diameter : 无
         * base_curve : 无
         * water_content : 无
         * goods_id : 426
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * suppliers_id : 1
         * goods_name : 面膜 敷尔佳系列 白膜 补水修复
         * virtual_sales : 0
         * attr_value : 白膜 补水修复
         * goods_thumb : http://img.jealook.com/backend/20201224/1608794759_4429.png
         * attr : 直径：无 | 基弧：无 | 含水量：无
         */

        private String product_id;
        private String color_id;
        private String product_price;
        private String preferential_price;
        private String search_attr;
        private String diameter;
        private String base_curve;
        private String water_content;
        private String goods_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String goods_name;
        private String virtual_sales;
        private String attr_value;
        private String goods_thumb;
        private String attr;
        private String active_name;

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
        }



        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
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

        public String getDiameter() {
            return diameter;
        }

        public void setDiameter(String diameter) {
            this.diameter = diameter;
        }

        public String getBase_curve() {
            return base_curve;
        }

        public void setBase_curve(String base_curve) {
            this.base_curve = base_curve;
        }

        public String getWater_content() {
            return water_content;
        }

        public void setWater_content(String water_content) {
            this.water_content = water_content;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
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

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }
    }
}
